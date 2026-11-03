import assert from 'node:assert/strict';
import fs from 'node:fs';
import {randomUUID} from 'node:crypto';
import {rcon} from '../../outputs/Zeefski-JJK-3.2.1/rcon.mjs';
const settings=JSON.parse(fs.readFileSync(new URL('../../outputs/Zeefski-JJK-3.2.1/settings.local.json',import.meta.url),'utf8').replace(/^\uFEFF/,''));
const cmd=s=>rcon(s,settings),sleep=ms=>new Promise(r=>setTimeout(r,ms));
const status=async()=>await(await fetch('http://127.0.0.1:21214/status')).json();
const post=async(event,data)=>await(await fetch('http://127.0.0.1:21214/event',{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify({event,data})})).json();
const wait=async test=>{for(let i=0;i<45;i++){if(await test())return;await sleep(200);}throw Error('Pipeline timeout');};
let pass=false;const results=[];
try {
 assert.match(await cmd('list'),/There are 0 /);
 assert.equal((await status()).test,true);assert.equal((await status()).streamerbot,true);
 await cmd('jjkarena start');
 const userId='cinema-'+randomUUID();
 const welcome=await post('member',{userId,nickname:'Bạn thử nghiệm'});assert.equal(welcome.audience.type,'welcome');
 const gift={userId,uniqueId:'CinemaGift',giftName:'rose',giftId:5655,giftType:1,repeatEnd:true,repeatCount:1,groupId:randomUUID(),msgId:randomUUID()};
 assert.equal((await post('gift',gift)).job.gift,true);
 await wait(async()=>(await cmd('jjkarena celebration')).includes('scene=gift'));
 assert.match(await cmd('execute in jjkarena:battlefield if entity @e[tag=jjkarena_blue,type=jujutsucraft:itadori_yuji]'),/Test passed/);
 await wait(async()=>(await cmd('jjkarena celebration')).includes('celebrating=false'));
 const count=Number((await cmd('jjkarena celebration')).match(/completed=(\d+)/)[1]);
 const unknown=await post('gift',{...gift,giftId:'unknown-cinema',giftName:'Unmapped test gift',groupId:randomUUID(),msgId:randomUUID()});
 assert.equal(unknown.audience.type,'thanks');assert.equal(unknown.job,undefined);
 const follow=await post('follow',{userId,nickname:'Bạn thử nghiệm'});assert.equal(follow.audience.type,'follow');
 await wait(async()=>(await cmd('jjkarena celebration')).includes('scene=follow'));
 assert.equal((await post('follow',{userId})).audience,undefined);
 await wait(async()=>(await cmd('jjkarena celebration')).includes('celebrating=false'));
 assert.equal(Number((await cmd('jjkarena celebration')).match(/completed=(\d+)/)[1]),count+1);
 await wait(async()=>{let s=await status();return s.pending===0&&!s.inflight;});
 assert.equal((await status()).held,0);
 const arena=await cmd('jjkarena status');assert.match(arena,/red=0 blue=1/);
 results.push('Member -> welcome; Rose -> real Streamer.bot Action -> blue school Yuji + gift scene; unknown -> text only; follow -> bow once; no duplicate troops.');
 pass=true;
}finally{
 if((await cmd('list')).includes('There are 0 '))await cmd('jjkarena start');
 fs.writeFileSync(new URL('../../outputs/JJK-Arena/celebration-pipeline-results.json',import.meta.url),JSON.stringify({pass,at:new Date().toISOString(),results},null,2));
}
console.log('PASS: '+results.join(' '));
