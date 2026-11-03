import fs from 'node:fs';
import assert from 'node:assert/strict';
import {rcon} from '../../outputs/Zeefski-JJK-3.2.1/rcon.mjs';
const cfg=JSON.parse(fs.readFileSync(new URL('../../outputs/Zeefski-JJK-3.2.1/settings.local.json',import.meta.url),'utf8').replace(/^\uFEFF/,''));
const results=[];
const sleep=ms=>new Promise(r=>setTimeout(r,ms));
async function cmd(c){const text=await rcon(c,cfg);results.push({command:c,result:text});console.log(c+'\n'+text.trim());return text;}
async function waitStatus(predicate,limit=60){for(let elapsed=0;elapsed<limit;elapsed+=5){await sleep(5000);const s=await cmd('jjkarena status');if(predicate(s))return s;}throw new Error('Timed out waiting for arena condition');}
try {
  await cmd('jjkarena start');
  await cmd('jjkarena spawn red basic 1 TestRed');
  await cmd('jjkarena spawn blue basic 1 TestBlue');
  await sleep(1500);
  const before=await cmd('jjkarena status');assert.match(before,/red=1 blue=1/);
  await cmd('jjkarena pause');
  const p1=await cmd('jjkarena status');await sleep(3000);const p2=await cmd('jjkarena status');assert.equal(p1,p2);
  assert.match(await cmd('jjkarena spawn red basic'),/not running/);
  await cmd('jjkarena resume');
  await waitStatus(s=>/hits=[1-9]/.test(s),45);
  await waitStatus(s=>/red=0|blue=0/.test(s),30);
  await cmd('jjkarena corehp 10');await cmd('jjkarena start');await cmd('jjkarena spawn red basic 1 CoreTest');
  await sleep(1000);
  await cmd('execute in jjkarena:battlefield as @e[tag=jjkarena_red] run tp @s 64 81 0');
  await waitStatus(s=>/intermission=[1-9]/.test(s),20);
  await waitStatus(s=>/running=true/.test(s)&&/intermission=0/.test(s),25);
  await cmd('jjkarena corehp 1000');await cmd('jjkarena start');
  for(let i=0;i<6;i++)await cmd('jjkarena spawn blue basic 15 CapTest');
  await sleep(2000);const capped=await cmd('jjkarena status');
  const active=Number(capped.match(/\bblue=(\d+)/)[1]);const queued=Number(capped.match(/queuedBlue=(\d+)/)[1]);
  assert.ok(active<=15&&queued<=60);
  await cmd('jjkarena reset');assert.match(await cmd('jjkarena status'),/red=0 blue=0 queuedRed=0 queuedBlue=0/);
  await cmd('forge tps');
  fs.writeFileSync(new URL('../../outputs/JJK-Arena/test-results.json',import.meta.url),JSON.stringify({pass:true,at:new Date().toISOString(),results},null,2));
  console.log('PASS: movement/combat, pause/resume, core destruction, round restart, caps, cleanup.');
}catch(error){console.error(error);fs.writeFileSync(new URL('../../outputs/JJK-Arena/test-results.json',import.meta.url),JSON.stringify({pass:false,error:error.message,results},null,2));process.exitCode=1;}
