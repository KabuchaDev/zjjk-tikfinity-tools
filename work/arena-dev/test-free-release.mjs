import fs from 'node:fs';
import os from 'node:os';
import path from 'node:path';
import net from 'node:net';
import http from 'node:http';
import assert from 'node:assert/strict';
import {createHash,randomUUID} from 'node:crypto';
import {spawn} from 'node:child_process';
import {fileURLToPath} from 'node:url';
const workspace=path.resolve(path.dirname(fileURLToPath(import.meta.url)),'../..');
const release=path.join(workspace,'outputs/KabuchaDev-JJK-Arena-Free-1.3.1');
const temp=fs.mkdtempSync(path.join(os.tmpdir(),'Kabucha release test '));
const copy=path.join(temp,'Bản thử có khoảng trắng');fs.mkdirSync(copy);
for(const name of ['setup.mjs','rcon.mjs','mods','interactions','test-panel'])fs.cpSync(path.join(release,name),path.join(copy,name),{recursive:true});
const privateSettings=JSON.parse(fs.readFileSync(path.join(workspace,'outputs/Zeefski-JJK-3.2.1/settings.local.json'),'utf8').replace(/^\uFEFF/,''));
const sleep=ms=>new Promise(r=>setTimeout(r,ms));
const run=(script,args=[])=>new Promise(resolve=>{let output='';const p=spawn(process.execPath,[script,...args],{windowsHide:true});p.stdout.on('data',b=>output+=b);p.stderr.on('data',b=>output+=b);p.on('error',e=>resolve({code:-1,output:e.message}));p.on('exit',code=>resolve({code,output}));});
const serverDir=path.join(temp,'Máy chủ mới');
fs.mkdirSync(path.join(serverDir,'libraries/net/minecraftforge/forge/1.20.1-47.4.3'),{recursive:true});
fs.mkdirSync(path.join(serverDir,'mods'));
fs.writeFileSync(path.join(serverDir,'libraries/net/minecraftforge/forge/1.20.1-47.4.3/win_args.txt'),'installer validation fixture');
fs.writeFileSync(path.join(serverDir,'mods/JujutsuCraft-ver50.1-forge-1.20.1.jar'),'installer validation fixture');
const setupArgs=['--server',serverDir,'--java',privateSettings.javaPath,'--port','26265','--rcon-port','26275'];
const install=await run(path.join(copy,'setup.mjs'),setupArgs);assert.equal(install.code,0,install.output);
const local=JSON.parse(fs.readFileSync(path.join(copy,'settings.local.json')));
assert.equal(local.serverDir,serverDir);assert.equal(local.rconPassword.length,64);
assert.notEqual(local.rconPassword,privateSettings.rconPassword);
assert.match(fs.readFileSync(path.join(serverDir,'server.properties'),'utf8'),/online-mode=true/);
assert.match(fs.readFileSync(path.join(serverDir,'eula.txt'),'utf8'),/eula=false/);
assert.notEqual((await run(path.join(copy,'setup.mjs'),setupArgs)).code,0,'second setup refuses overwrite');
const configPath=path.join(copy,'interactions/config.json');const config=JSON.parse(fs.readFileSync(configPath));
config.tikfinityUrl='ws://127.0.0.1:26213/';config.streamerbotUrl='ws://127.0.0.1:26280/';fs.writeFileSync(configPath,JSON.stringify(config));
const bridgePath=path.join(copy,'interactions/bridge.mjs');
fs.writeFileSync(bridgePath,fs.readFileSync(bridgePath,'utf8').replace("server.listen(21214,","server.listen(26214,"));
// Protocol-faithful localhost mocks; no connection to the real livestream/server.
const sockets=new Set(),commands=[],deliveries=[];
const packet=(id,type,text)=>{const b=Buffer.from(text),p=Buffer.alloc(b.length+14);p.writeInt32LE(b.length+10,0);p.writeInt32LE(id,4);p.writeInt32LE(type,8);b.copy(p,12);return p;};
const rcon=net.createServer(s=>{sockets.add(s);s.on('close',()=>sockets.delete(s));let input=Buffer.alloc(0);s.on('data',b=>{input=Buffer.concat([input,b]);while(input.length>=4){const size=input.readInt32LE(0);if(input.length<size+4)return;const p=input.subarray(0,size+4);input=input.subarray(size+4);const id=p.readInt32LE(4),type=p.readInt32LE(8),text=p.subarray(12,size+2).toString();if(type===3){s.write(packet(text===local.rconPassword?id:-1,2,''));continue;}if(!text){s.write(packet(id,0,''));continue;}commands.push(text);let response='Audience accepted';if(text==='jjkarena status')response='built=true building=no running=true paused=false red=0 blue=0 queuedRed=0 queuedBlue=0 maxHP=10000';if(text.startsWith('jjkarena spawn ')){deliveries.push(text);response='Queued '+text.split(' ')[4]+' units';}s.write(packet(id,0,response));}});});
function frame(value){const b=Buffer.from(JSON.stringify(value));if(b.length<126)return Buffer.concat([Buffer.from([0x81,b.length]),b]);const h=Buffer.alloc(4);h[0]=0x81;h[1]=126;h.writeUInt16BE(b.length,2);return Buffer.concat([h,b]);}
const tkClients=new Set();let actionCount=0;
function websocketServer(onMessage,onConnect=()=>{}){const server=http.createServer();server.on('upgrade',(req,socket)=>{
 sockets.add(socket);socket.on('close',()=>sockets.delete(socket));socket.on('error',()=>{});
 const accept=createHash('sha1').update(req.headers['sec-websocket-key']+'258EAFA5-E914-47DA-95CA-C5AB0DC85B11').digest('base64');
 socket.write('HTTP/1.1 101 Switching Protocols\r\nUpgrade: websocket\r\nConnection: Upgrade\r\nSec-WebSocket-Accept: '+accept+'\r\n\r\n');
 let incoming=Buffer.alloc(0);socket.on('data',b=>{incoming=Buffer.concat([incoming,b]);while(incoming.length>=2){const opcode=incoming[0]&15,masked=!!(incoming[1]&128);let length=incoming[1]&127,offset=2;if(length===126){if(incoming.length<4)return;length=incoming.readUInt16BE(2);offset=4;}if(length===127)throw Error('oversized test frame');const maskBytes=masked?4:0;if(incoming.length<offset+maskBytes+length)return;const mask=incoming.subarray(offset,offset+maskBytes),payload=Buffer.from(incoming.subarray(offset+maskBytes,offset+maskBytes+length));incoming=incoming.subarray(offset+maskBytes+length);if(masked)for(let i=0;i<payload.length;i++)payload[i]^=mask[i%4];if(opcode===8){socket.end();return;}if(opcode===1)onMessage(JSON.parse(payload.toString()),socket);}});onConnect(socket);
 });return server;}
const sb=websocketServer((request,socket)=>{if(request.request==='GetActions')socket.write(frame({id:request.id,status:'ok',actions:[{name:config.actionName,enabled:true}]}));else if(request.request==='DoAction'){actionCount++;assert.equal(request.action.name,config.actionName);socket.write(frame({id:request.id,status:'ok'}));run(path.join(copy,'interactions/dispatch.mjs'),[request.args.arenaJobId]).then(result=>{if(result.code)console.error(result.output);});}});
const tk=websocketServer(()=>{},s=>{tkClients.add(s);s.on('close',()=>tkClients.delete(s));});
await Promise.all([[rcon,26275],[sb,26280],[tk,26213]].map(([s,p])=>new Promise(r=>s.listen(p,'127.0.0.1',r))));
let log='';const bridge=spawn(process.execPath,[bridgePath],{windowsHide:true});bridge.stdout.on('data',b=>log+=b);bridge.stderr.on('data',b=>log+=b);
async function until(test,label,ms=10000){const start=Date.now();while(Date.now()-start<ms){if(await test())return;await sleep(100);}throw Error('Timeout: '+label+'\n'+log);}
const status=async()=>{try{return await(await fetch('http://127.0.0.1:26214/status')).json();}catch{return null;}};
let expected=[];
const event=(event,data)=>{for(const socket of tkClients)socket.write(frame({event,data}));};
try{
 await until(async()=>{const s=await status();return s?.tikfinity&&s?.streamerbot;},'sockets connected');
 for(const [word,rule] of Object.entries(config.comments)){
  for(let i=0;i<rule.threshold;i++)event('chat',{userId:'viewer'+i,uniqueId:'SyntheticViewer',msgId:randomUUID(),comment:' '+word.toUpperCase()+' '});
  expected.push(`jjkarena spawn ${rule.side} ${rule.kind} 1 SyntheticViewer`);
 }
 for(const gift of config.gifts){
  const data={userId:'gift-viewer',uniqueId:'SyntheticViewer',msgId:randomUUID(),giftName:gift.name.toLowerCase(),repeatCount:1};
  event('gift',data);event('gift',data); // duplicate should not create another troop
  expected.push(`jjkarena spawn ${gift.side} ${gift.kind} 1 SyntheticViewer gift`);
 }
 event('member',{userId:'join-viewer',nickname:'Synthetic Welcome'});
 event('follow',{userId:'follow-viewer',nickname:'Synthetic Follow'});
 event('follow',{userId:'follow-viewer',nickname:'Synthetic Follow'});
 event('gift',{userId:'unknown-viewer',giftName:'Unmapped test',msgId:randomUUID(),repeatCount:1});
 await until(async()=>{const s=await status();return s&&s.pending===0&&!s.inflight&&deliveries.length===expected.length;},'30 complete deliveries',65000);
 assert.deepEqual(deliveries,expected);assert.equal(actionCount,30);
 assert.equal(commands.filter(c=>c.startsWith('jjkarena live follow ')).length,1);
 assert.equal(commands.filter(c=>c.startsWith('jjkarena live welcome ')).length,1);
 assert.equal(commands.filter(c=>c.startsWith('jjkarena live thanks ')).length,1);
 assert.equal((await status()).held,0);
 await fetch('http://127.0.0.1:26214/stop',{method:'POST',headers:{'Content-Type':'application/json'},body:'{}'});
 await sleep(750);
 // A repeated dispatch is a no-op after its result is saved.
 const first=fs.readdirSync(path.join(copy,'interactions/spool')).find(n=>n.endsWith('.result.json')).replace('.result.json','');
 assert.equal((await run(path.join(copy,'interactions/dispatch.mjs'),[first])).code,0);assert.equal(deliveries.length,30);
 console.log('PASS: fresh setup with Unicode/spaces, unique secret, online authentication, EULA=false, repeat-setup protection.');
 console.log('PASS: synthetic TikFinity WebSocket -> release bridge -> mock Streamer.bot DoAction -> actual dispatcher -> mock RCON: 4 comment thresholds + 26 gifts, duplicate suppression, unknown thank-you, welcome, follow dedup, repeat dispatch.');
 fs.writeFileSync(path.join(workspace,'work/arena-dev/free-release-tests.json'),JSON.stringify({pass:true,at:new Date().toISOString(),setup:'fixture server directory; real Java 17',integration:'real Node release modules, synthetic WebSocket and RCON peers',deliveries:30,gifts:26,commentThresholds:4,held:0,gameplayRuntimeTest:false},null,2));
}finally{bridge.kill();for(const socket of sockets)socket.destroy();for(const server of [rcon,sb,tk])server.close();}
