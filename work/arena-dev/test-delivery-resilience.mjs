import fs from 'node:fs';
import {spawn} from 'node:child_process';
import {fileURLToPath} from 'node:url';
import assert from 'node:assert/strict';
import {rcon} from '../../outputs/Zeefski-JJK-3.2.1/rcon.mjs';
import {emptyState} from '../../outputs/JJK-Arena/interactions/router.mjs';
const root=new URL('../../outputs/JJK-Arena/interactions/',import.meta.url);
const settings=JSON.parse(fs.readFileSync(new URL('../../outputs/Zeefski-JJK-3.2.1/settings.local.json',import.meta.url),'utf8').replace(/^\uFEFF/,''));
const cmd=c=>rcon(c,settings),sleep=ms=>new Promise(r=>setTimeout(r,ms));
const status=async()=>await(await fetch('http://127.0.0.1:21214/status')).json();
const post=async(path,data={})=>await(await fetch('http://127.0.0.1:21214/'+path,{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify(data)})).json();
const game=async()=>{const raw=await cmd('jjkarena status');const s={raw};for(const [,k,v] of raw.matchAll(/(\w+)=(true|false|\d+)(?=\s|$)/g))s[k]=v==='true'?true:v==='false'?false:Number(v);return s;};
let child,serial=Date.now();const results=[];
const event=(name,count=1)=>({event:'gift',data:{userId:'audit',uniqueId:'Audit',msgId:String(++serial),giftId:name==='Rose'?'5655':'5269',giftName:name,repeatCount:count,giftType:0}});
async function boot(){
 child=spawn(settings.nodePath,[fileURLToPath(new URL('bridge.mjs',root)),'--test'],{windowsHide:true,stdio:'ignore'});
 for(let i=0;i<60;i++){try{const s=await status();assert.equal(s.test,true);if(s.streamerbot)return;}catch{}await sleep(250);}throw new Error('Test bridge/Streamer.bot not ready');
}
async function stop(){await post('stop');for(let i=0;i<30;i++){try{await status();}catch{return;}await sleep(100);}throw new Error('Test bridge did not stop');}
async function drain(){for(let i=0;i<60;i++){const s=await status();assert.equal(s.held,0);if(!s.pending&&!s.inflight)return;await sleep(250);}throw new Error('Queue did not drain');}
function freshTestState(){fs.writeFileSync(new URL('state-test.json',root),JSON.stringify(emptyState()));}
let pass=false;
try{
 await cmd('jjkarena reset');freshTestState();await boot();
 for(let i=0;i<2;i++)await post('event',{event:'chat',data:{uniqueId:'Audit'+i,msgId:String(++serial),comment:'messi'}});
 await stop();await boot();assert.equal((await status()).counters.messi,2);
 await post('event',{event:'chat',data:{uniqueId:'Audit3',msgId:String(++serial),comment:'messi'}});
 const realIdGift=event('Rose',2);realIdGift.data.giftName='Tên hoa đã dịch';assert.equal((await post('event',realIdGift)).job.remaining,2);
 await sleep(1000);assert.equal((await status()).pending,3);assert.equal((await game()).blue,0);
 await stop();await boot();assert.equal((await status()).pending,3);assert.ok((await post('event',realIdGift)).duplicate);
 await cmd('jjkarena start');await drain();
 const accepted=await game();assert.equal(accepted.blue+accepted.queuedBlue,3);
 results.push('Stopped arena: retain 3 summons; bridge restart retains counters, jobs and deduplication; all 3 delivered after start.');console.log('PASS restart, stopped arena, verified gift ID, deduplication');
 await cmd('jjkarena pause');await post('event',event('TikTok'));await sleep(1000);assert.equal((await status()).pending,1);
 await cmd('jjkarena resume');await drain();const resumed=await game();assert.equal(resumed.red+resumed.queuedRed,1);
 results.push('Paused arena retains a gift and delivers after resume.');console.log('PASS pause/resume');
 await cmd('jjkarena reset');await stop();freshTestState();await boot();await cmd('jjkarena start');
 await post('event',event('Rose',200));
 let saturated;
 for(let i=0;i<90;i++){
  await cmd('execute in jjkarena:battlefield as @e[tag=jjkarena_unit] run data merge entity @s {NoAI:1b,Invulnerable:1b}');
  const b=await status(),g=await game();
  if(g.blue===15&&g.queuedBlue===60&&!b.inflight){saturated={b,g};break;}await sleep(250);
 }
 assert.ok(saturated,'Capacity test did not saturate');assert.equal(saturated.b.pending+75,200);
 await post('event',event('TikTok'));
 for(let i=0;i<40;i++){const g=await game();if(g.red+g.queuedRed===1)break;await sleep(250);}
 const otherTeam=await game();assert.equal(otherTeam.red+otherTeam.queuedRed,1,'Full blue queue blocked red');
 for(let i=0;i<20&&(await status()).inflight;i++)await sleep(100);
 assert.equal((await status()).pending,125);
 results.push('200 gifts: 15 active + 60 queued in game + 125 pending externally; full blue queue does not block red.');console.log('PASS 200-gift capacity and independent teams');
 await stop();freshTestState();await cmd('jjkarena corehp 10');await cmd('jjkarena start');
 await cmd('jjkarena spawn red miwa 15 RoundAudit');await sleep(600);
 await cmd('execute in jjkarena:battlefield as @e[tag=jjkarena_red] run data merge entity @s {NoAI:1b,Invulnerable:1b}');
 await cmd('execute in jjkarena:battlefield run tp @e[tag=jjkarena_red,limit=1] 72.5 81 3.5');
 let ended;
 for(let i=0;i<40;i++){const g=await game();if(g.intermission>0){ended=g;break;}await sleep(100);}
 assert.ok(ended,'Round did not finish');assert.ok(ended.queuedRed>0,'Pending summons erased at round end');const carried=ended.queuedRed;
 let next;
 for(let i=0;i<100;i++){const g=await game();if(g.running){await cmd('jjkarena pause');next=await game();break;}await sleep(200);}
 assert.ok(next,'Next round did not start');assert.equal(next.red+next.queuedRed,carried,'Queue erased on new round');
 results.push(`Automatic next round preserved all ${carried} not-yet-spawned units.`);console.log('PASS automatic round rollover, '+carried+' summons preserved');
 pass=true;
}finally{
 try{if((await status()).test)await stop();}catch{}
 await cmd('jjkarena corehp 10000');await cmd('save-all flush');
 fs.writeFileSync(new URL('../../outputs/JJK-Arena/delivery-resilience-test-results.json',import.meta.url),JSON.stringify({pass,at:new Date().toISOString(),results},null,2));
}
