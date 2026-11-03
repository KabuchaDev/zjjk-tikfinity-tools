import fs from 'node:fs';
import net from 'node:net';
import { rcon } from '../Zeefski-JJK-3.2.1/rcon.mjs';
import { EventRouter } from './event-router.mjs';

const readJson = url => JSON.parse(fs.readFileSync(url,'utf8').replace(/^\uFEFF/,''));
const config = readJson(new URL('./bridge-config.json', import.meta.url));
const serverSettings = readJson(new URL('../Zeefski-JJK-3.2.1/settings.local.json', import.meta.url));
const target = new URL(config.tikfinityUrl);
if (target.protocol !== 'ws:' || !['127.0.0.1','localhost','[::1]'].includes(target.hostname)) throw new Error('Chi cho phep TikFinity tren may nay.');
config.commentCooldownSeconds=Math.max(1,Math.min(60,Number(config.commentCooldownSeconds)||8));
config.maxPendingCommands=Math.max(1,Math.min(100,Number(config.maxPendingCommands)||60));
const router=new EventRouter(config);
const pending=[];
const logPath=new URL('./bridge.log',import.meta.url);
function log(text) {
  const line=`${new Date().toISOString()} ${text}`; console.log(line);
  if(fs.existsSync(logPath)&&fs.statSync(logPath).size>1024*1024)fs.renameSync(logPath,new URL('./bridge.previous.log',import.meta.url));
  fs.appendFileSync(logPath,line+'\n');
}
if(process.argv[2]==='--simulate') {
  const routed=router.route({event:'chat',data:{comment:process.argv[3],userId:'local-test',uniqueId:'LocalTest',msgId:String(Date.now())}});
  if(!routed){console.log('Khong phai binh luan ronaldo/messi.');process.exit(1);}
  console.log(routed.command);console.log(await rcon(routed.command,serverSettings));
  process.exit(0);
}

// A loopback listener prevents two bridge instances from duplicating live events.
const lock=net.createServer(socket=>socket.end());
lock.on('error',error=>{console.error(error.code==='EADDRINUSE'?'Bo noi TikFinity da chay. Chi mo mot ban.':error.message);process.exit(1);});
await new Promise(resolve=>lock.listen(21214,'127.0.0.1',resolve));
let socket,reconnectTimer,workerBusy=false,stopping=false,hasWarned=false;
const worker=setInterval(async()=>{
  if(workerBusy||!pending.length)return;
  const job=pending.shift();if(Date.now()-job.at>15000)return;
  workerBusy=true;
  try {const result=await rcon(job.command,serverSettings);log(`${job.side}/${job.kind}: ${result.trim()}`);}
  catch(error){log(`Khong gui duoc lenh: ${error.message}. Khong gui lai de tranh goi quan trung.`);}
  finally{workerBusy=false;}
},250);
function connect() {
  socket=new WebSocket(config.tikfinityUrl);
  socket.addEventListener('open',()=>{hasWarned=false;log('Da ket noi TikFinity. Ronaldo -> DO; Messi -> XANH.');});
  socket.addEventListener('message',event=>{
    if(typeof event.data!=='string'||event.data.length>1024*1024)return;
    try {
      const routed=router.route(JSON.parse(event.data));
      if(routed&&pending.length<config.maxPendingCommands)pending.push({...routed,at:Date.now()});
    } catch { /* Ignore unrelated or malformed event payloads. */ }
  });
  socket.addEventListener('error',()=>{if(!hasWarned){hasWarned=true;log('Dang cho TikFinity Desktop. Mo TikFinity va ket noi phien LIVE.');}});
  socket.addEventListener('close',()=>{if(!stopping){pending.length=0;reconnectTimer=setTimeout(connect,5000);}});
}
function shutdown() {stopping=true;clearInterval(worker);clearTimeout(reconnectTimer);socket?.close();lock.close();setTimeout(()=>process.exit(0),300).unref();}
process.on('SIGINT',shutdown);process.on('SIGTERM',shutdown);
log('Bo noi JJK Arena dang chay. Moi nguoi: 1 binh luan hop le / '+config.commentCooldownSeconds+' giay.');
connect();
