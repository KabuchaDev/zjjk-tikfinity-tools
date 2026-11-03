import fs from 'node:fs';
import assert from 'node:assert/strict';
import {rcon} from '../../outputs/Zeefski-JJK-3.2.1/rcon.mjs';
const settings=JSON.parse(fs.readFileSync(new URL('../../outputs/Zeefski-JJK-3.2.1/settings.local.json',import.meta.url),'utf8').replace(/^\uFEFF/,''));
const transcript=[];
const cmd=async c=>{const out=await rcon(c,settings);transcript.push({command:c,result:out});return out;},sleep=ms=>new Promise(r=>setTimeout(r,ms));
const arena=c=>cmd('execute in jjkarena:battlefield run '+c);
const selector=tag=>`@e[tag=${tag},limit=1]`;
async function pos(tag){const s=await arena(`data get entity ${selector(tag)} Pos`);const a=s.match(/\[([^\]]+)\]/);assert.ok(a,tag+': '+s);return a[1].split(',').map(parseFloat);}
async function spawn(side,tag){
 assert.match(await cmd(`jjkarena spawn ${side} miwa 1 GateTest`),/Queued 1/);await sleep(700);
 await arena(`tag @e[tag=jjkarena_${side},tag=!gate_test] add ${tag}`);
 await arena(`tag ${selector(tag)} add gate_test`);
 await arena(`data merge entity ${selector(tag)} {NoAI:1b,Invulnerable:1b,NoGravity:1b}`);
}
function outside(p,cx){return p[0]<cx-5.4||p[0]>cx+6.4||p[2]<-5.4||p[2]>6.4||p[1]>93;}
const results=[];let pass=false;
try{
 for(const defending of ['blue','red']){
  const attacking=defending==='blue'?'red':'blue',cx=defending==='blue'?69:-69;
  await cmd('jjkarena start');
  for(let i=0;i<3;i++)await spawn(attacking,'gate_attacker_'+i);
  await arena(`tp ${selector('gate_attacker_0')} ${cx+3.5} 81 3.5`);
  await arena(`tp ${selector('gate_attacker_1')} ${cx-5} 81 3.5`);
  await arena(`tp ${selector('gate_attacker_2')} ${cx+3.5} 92 3.5`);
  // An allied summon outside the managed roster must be evacuated as well.
  await arena(`summon jujutsucraft:miwa_kasumi ${cx+3.5} 81 -3.5 {Tags:["gate_summon","jjkarena_${attacking}"],NoAI:1b,Invulnerable:1b,NoGravity:1b}`);
  let status=await cmd('jjkarena status');assert.match(status,new RegExp(`${defending}Gate=false`));
  await spawn(defending,'gate_defender');await sleep(250);
  status=await cmd('jjkarena status');assert.match(status,new RegExp(`${defending}Gate=true`));
  const hp=Number(status.match(new RegExp(`${defending}HP=(\\d+)`))[1]);
  const positions=[];
  for(const tag of ['gate_attacker_0','gate_attacker_1','gate_attacker_2','gate_summon']){
   const p=await pos(tag);assert.ok(outside(p,cx),tag+' still trapped: '+p);assert.equal(p[1],81,'Must stand on the arena floor');positions.push({tag,p});
   assert.match(await arena(`data get entity ${selector(tag)} DeathTime`),/0s/);
  }
  // Simulate a later native teleport back inside an already closed shield.
  await arena(`tp ${selector('gate_attacker_0')} ${cx+3.5} 81 3.5`);await sleep(200);
  assert.ok(outside(await pos('gate_attacker_0'),cx),'Closed gate must reject re-entry');
  // Friendly defenders are allowed inside and must not be displaced.
  await arena(`tp ${selector('gate_defender')} ${cx+3.5} 81 -3.5`);await sleep(250);
  const friend=await pos('gate_defender');assert.equal(friend[0],cx+3.5);assert.equal(friend[2],-3.5);
  await sleep(1200);status=await cmd('jjkarena status');
  assert.equal(Number(status.match(new RegExp(`${defending}HP=(\\d+)`))[1]),hp,'Core damaged despite defenders');
  await arena(`kill ${selector('gate_defender')}`);await sleep(300);
  assert.match(await cmd('jjkarena status'),new RegExp(`${defending}Gate=false`));
  results.push({defending,positions,shieldHP:hp,closedGateReentry:true,friendlyNotMoved:true,reopened:true});
  console.log('PASS '+defending+': interior, wall, roof, summon evacuated; re-entry blocked; friendly stays; core protected; gate reopens.');
 }
 assert.match(await cmd('jjkarena auditterrain'),/changed=0\b/);pass=true;
}finally{
 await cmd('jjkarena reset');await cmd('save-all flush');
 fs.writeFileSync(new URL('../../outputs/JJK-Arena/gate-exit-test-results.json',import.meta.url),JSON.stringify({pass,at:new Date().toISOString(),results,transcript},null,2));
}
