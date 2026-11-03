import fs from 'node:fs';
import assert from 'node:assert/strict';
import {rcon} from '../../outputs/Zeefski-JJK-3.2.1/rcon.mjs';
const cfg=JSON.parse(fs.readFileSync(new URL('../../outputs/Zeefski-JJK-3.2.1/settings.local.json',import.meta.url),'utf8').replace(/^\uFEFF/,''));
const results=[];
async function cmd(command){const result=await rcon(command,cfg);results.push({command,result});console.log(command+'\n'+result);return result;}
const sleep=ms=>new Promise(r=>setTimeout(r,ms));
let pass=false;
try {
  await cmd('jjkarena start');
  await cmd('jjkarena spawn red basic 15 LoadRed');
  await cmd('jjkarena spawn blue basic 15 LoadBlue');
  await sleep(8500);
  assert.match(await cmd('jjkarena status'),/red=15 blue=15 queuedRed=0 queuedBlue=0/);
  await cmd('forge tps');
  await cmd('jjkarena reset');
  assert.match(await cmd('team list jja_red'),/no members/i);
  assert.match(await cmd('team list jja_blue'),/no members/i);
  await cmd('jjkarena setcore red -60 81 0');
  await cmd('jjkarena start');
  await sleep(1000);
  assert.match(await cmd('execute in jjkarena:battlefield if block -60 84 0 minecraft:sea_lantern'),/Test passed/);
  assert.match(await cmd('execute in jjkarena:battlefield if block -69 84 0 minecraft:air'),/Test passed/);
  await cmd('jjkarena setcore red -69 81 0');
  await cmd('jjkarena start');
  await cmd('jjkarena spawn red boss 1 BossTest');
  await cmd('jjkarena spawn blue boss 1 BossTest');
  await sleep(1500);
  assert.match(await cmd('jjkarena status'),/red=1 blue=1/);
  assert.match(await cmd('execute in jjkarena:battlefield if entity @e[type=jujutsucraft:sukuna,tag=jjkarena_red]'),/Test passed/);
  assert.match(await cmd('execute in jjkarena:battlefield if entity @e[type=jujutsucraft:gojo_satoru,tag=jjkarena_blue]'),/Test passed/);
  pass=true;
}finally {
  await cmd('jjkarena setcore red -69 81 0');
  await cmd('jjkarena reset');
  await cmd('jjkarena clearscore');
  await cmd('save-all flush');
  fs.writeFileSync(new URL('../../outputs/JJK-Arena/final-test-results.json',import.meta.url),JSON.stringify({pass,at:new Date().toISOString(),results},null,2));
}
console.log('PASS: 30 active troops, team cleanup, movable core, boss entity spawning.');
