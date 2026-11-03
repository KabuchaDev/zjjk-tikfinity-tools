import fs from 'node:fs';
import http from 'node:http';
import {randomUUID} from 'node:crypto';
import {consume,emptyState} from './router.mjs';
import {describeArena} from './arena-status.mjs';
import {audience} from './audience.mjs';
import {rcon} from '../../Zeefski-JJK-3.2.1/rcon.mjs';
const read=url=>JSON.parse(fs.readFileSync(url,'utf8').replace(/^\uFEFF/,''));
const config=read(new URL('./config.json',import.meta.url));
const settings=read(new URL('../../Zeefski-JJK-3.2.1/settings.local.json',import.meta.url));
const test=process.argv.includes('--test');
for(const addr of [config.tikfinityUrl,config.streamerbotUrl]){const u=new URL(addr);if(u.protocol!=='ws:'||!['127.0.0.1','localhost','[::1]'].includes(u.hostname))throw new Error('Chi cho phep ket noi tren may nay');}
const stateFile=new URL(test?'./state-test.json':'./state.json',import.meta.url);
const state=fs.existsSync(stateFile)?read(stateFile):emptyState();
const spool=new URL('./spool/',import.meta.url);fs.mkdirSync(spool,{recursive:true});
const save=()=>{const tmp=new URL(test?'./state-test.tmp':'./state.tmp',import.meta.url);fs.writeFileSync(tmp,JSON.stringify(state,null,2));fs.renameSync(tmp,stateFile);};
const logFile=new URL(test?'./test.log':'./bridge.log',import.meta.url);
function log(msg){const line=`${new Date().toISOString()} ${msg}`;console.log(line);if(fs.existsSync(logFile)&&fs.statSync(logFile).size>2e6)fs.renameSync(logFile,new URL(test?'./test.previous.log':'./bridge.previous.log',import.meta.url));fs.appendFileSync(logFile,line+'\n');}
let sb,tk,sbReady=false,tkReady=false,stopping=false,busy=false,lastNotice='',lastPrune=0,lastAudience=0;
let arena={connected:false,state:'checking',message:'DANG KIEM TRA SERVER'},lastArenaCheck=0,lastArenaMessage='';
async function checkArena(){
 let raw=null;
 try{raw=await rcon('jjkarena status',settings);arena=describeArena(raw);}
 catch(error){arena={connected:false,state:'offline',message:'CHUA KET NOI SERVER: kiem tra BAT-SERVER.cmd. '+error.message,checkedAt:Date.now()};raw=null;}
 lastArenaCheck=Date.now();
 if(arena.message!==lastArenaMessage){lastArenaMessage=arena.message;log(arena.message);}
 return raw;
}
const requests=new Map(),timers=new Set();
function later(fn){const t=setTimeout(()=>{timers.delete(t);if(!stopping)fn();},5000);timers.add(t);}
function request(body){return new Promise((resolve,reject)=>{if(sb?.readyState!==WebSocket.OPEN)return reject(new Error('Streamer.bot chua ket noi'));const id=randomUUID();const timer=setTimeout(()=>{requests.delete(id);reject(new Error('Streamer.bot khong phan hoi'));},5000);requests.set(id,{resolve,reject,timer});sb.send(JSON.stringify({...body,id}));});}
async function checkAction(){const answer=await request({request:'GetActions'});sbReady=answer.actions?.some(a=>a.name===config.actionName&&a.enabled)===true;if(!sbReady)throw new Error('Chua co Action '+config.actionName);log('Da ket noi Streamer.bot; Action trieu hoi san sang.');}
function connectSb(){
 sb=new WebSocket(config.streamerbotUrl);
 sb.addEventListener('open',()=>{checkAction().catch(e=>{log(e.message);sb.close();});});
 sb.addEventListener('message',e=>{try{const value=JSON.parse(e.data),pending=requests.get(value.id);if(pending){clearTimeout(pending.timer);requests.delete(value.id);value.status==='error'?pending.reject(new Error(value.error??'Streamer.bot request rejected')):pending.resolve(value);}}catch{}});
 sb.addEventListener('error',()=>{});sb.addEventListener('close',()=>{sbReady=false;for(const p of requests.values()){clearTimeout(p.timer);p.reject(new Error('Streamer.bot mat ket noi'));}requests.clear();later(connectSb);});
}
function input(envelope){const out=consume(envelope,config,state);const social=audience(envelope,out,state);if(out.duplicate)return out;save();if(out.job)log(`Nhan ${out.job.source}: ${out.job.remaining} ${out.job.kind}, phe ${out.job.side}.`);else if(social)log(`Loi chao/cam on: ${social.type}`);else if(out.progress)log(`Binh luan ${out.progress.word}: ${out.progress.count}/${out.progress.threshold}`);else if(out.unknownGift)log('QUA CHUA KHOP: '+JSON.stringify(out.unknownGift));else if(out.error)log(out.error);return {...out,...(social?{audience:social}:{})};}
function connectTk(){
 tk=new WebSocket(config.tikfinityUrl);tk.addEventListener('open',()=>{tkReady=true;log('Da ket noi TikFinity. Dem chung: messi/ronaldo 3, m10/cr7 5.');});
 tk.addEventListener('message',e=>{if(typeof e.data!=='string'||e.data.length>1048576)return;try{input(JSON.parse(e.data));}catch(err){log('Bo qua su kien loi: '+err.message);}});
 tk.addEventListener('error',()=>{});tk.addEventListener('close',()=>{tkReady=false;later(connectTk);});
}
function hold(reason){const batch=state.inflight;const job=state.jobs.find(j=>j.id===batch.jobId);if(job)job.remaining-=batch.count;state.held.push({...batch,reason});state.jobs=state.jobs.filter(j=>j.remaining>0);state.inflight=null;save();log('CAN KIEM TRA: '+reason+'. Luot nay duoc giu trong held, khong tu gui lai de tranh trung.');}
async function tick(){
 if(busy||stopping)return;busy=true;
 try{
   if(state.audience?.jobs.length&&Date.now()-lastAudience>1200){
     lastAudience=Date.now();const notice=state.audience.jobs[0];
     const result=await rcon(`jjkarena live ${notice.type} ${notice.key} ${Buffer.from(notice.text).toString('base64url')}`,settings);
     if(/Audience accepted|Audience duplicate/.test(result)){state.audience.jobs.shift();save();}
   }
   if(state.inflight){
     const batch=state.inflight,resultFile=new URL(`./spool/${batch.id}.result.json`,import.meta.url);
     if(fs.existsSync(resultFile)){
       const result=read(resultFile);if(result.uncertain){hold(result.error);return;}
       if(!Number.isInteger(result.accepted)||result.accepted<0||result.accepted>batch.count){hold('Phan hoi so luong khong hop le');return;}
       const job=state.jobs.find(j=>j.id===batch.jobId);if(job){job.remaining-=result.accepted;if(result.accepted>0&&batch.gift)job.celebrated=true;}
       state.jobs=state.jobs.filter(j=>j.remaining>0);state.inflight=null;save();log(`Server da nhan ${result.accepted}/${batch.count} ${batch.kind}, phe ${batch.side}.`);
     }else if(Date.now()-batch.at>45000)hold('Qua 45 giay chua xac dinh duoc ket qua Action');
     return;
   }
   if(!sbReady||!state.jobs.length){if(Date.now()-lastArenaCheck>3000)await checkArena();return;}
   const status=await checkArena();if(!status)return;
   if(!/running=true paused=false/.test(status))return;
   const redFree=60-Number(status.match(/queuedRed=(\d+)/)?.[1]??60),blueFree=60-Number(status.match(/queuedBlue=(\d+)/)?.[1]??60);
   const job=state.jobs.find(j=>(j.side==='red'?redFree:blueFree)>0);if(!job)return;
   const batch={id:randomUUID(),jobId:job.id,side:job.side,kind:job.kind,viewer:job.viewer,gift:!!job.gift&&!job.celebrated,count:Math.min(15,job.remaining,job.side==='red'?redFree:blueFree),at:Date.now()};
   fs.writeFileSync(new URL(`./spool/${batch.id}.json`,import.meta.url),JSON.stringify(batch));state.inflight=batch;save();
   try{await request({request:'DoAction',action:{name:config.actionName},args:{arenaJobId:batch.id}});lastNotice='';}
   catch(error){log('Action chua xac nhan: '+error.message+'. Dang doi ket qua de tranh trieu hoi trung.');}
 }catch(error){if(lastNotice!==error.message){lastNotice=error.message;log('Dang cho server: '+error.message);}}
 finally{busy=false;}
}
const server=http.createServer(async(req,res)=>{
 res.setHeader('Content-Type','application/json; charset=utf-8');
 if(req.headers.origin){res.writeHead(403);return res.end('{}');}
 if(req.method==='GET'&&req.url==='/status')return res.end(JSON.stringify({test,tikfinity:tkReady,streamerbot:sbReady,arena,counters:state.counters,pending:state.jobs.reduce((n,j)=>n+j.remaining,0),held:state.held.length,inflight:!!state.inflight}));
 if(req.method==='POST'&&req.headers['content-type']?.startsWith('application/json')){
   let raw='';try{for await(const chunk of req){raw+=chunk;if(raw.length>1048576)throw new Error('Too large');}
     if(test&&req.url==='/event')return res.end(JSON.stringify(input(JSON.parse(raw))));
     if(req.url==='/reset-counters'){state.counters=emptyState().counters;save();log('Da dat lai bon bo dem binh luan.');return res.end('{"ok":true}');}
     if(req.url==='/stop'){res.end('{"ok":true}');shutdown();return;}
   }catch(error){res.writeHead(400);return res.end(JSON.stringify({error:error.message}));}
 }
 res.writeHead(404);res.end('{}');
});
server.on('error',e=>{console.error(e.code==='EADDRINUSE'?'Bo ket noi khac dang chay. Dong bo cu truoc khi mo ban nay.':e.message);process.exit(1);});
await new Promise(resolve=>server.listen(21214,'127.0.0.1',resolve));
log('JJK Arena - TikFinity -> Streamer.bot -> Minecraft'+(test?' [THU NGHIEM]':''));
connectSb();if(!test)connectTk();
const worker=setInterval(tick,500);save();
function shutdown(){if(stopping)return;stopping=true;clearInterval(worker);for(const t of timers)clearTimeout(t);save();sb?.close();tk?.close();server.close();setTimeout(()=>process.exit(0),500).unref();}
process.on('SIGINT',shutdown);process.on('SIGTERM',shutdown);
