import fs from 'node:fs';import assert from 'node:assert/strict';
import {rcon} from '../../outputs/Zeefski-JJK-3.2.1/rcon.mjs';
const cfg=JSON.parse(fs.readFileSync(new URL('../../outputs/Zeefski-JJK-3.2.1/settings.local.json',import.meta.url),'utf8').replace(/^\uFEFF/,''));
const cmd=c=>rcon(c,cfg),sleep=ms=>new Promise(r=>setTimeout(r,ms));const results=[];let pass=false;
try{
 await cmd('jjkarena start');await cmd('jjkarena spawn red miwa 1 BoundaryTest');await sleep(700);
 await cmd('execute in jjkarena:battlefield as @e[tag=jjkarena_red] run data merge entity @s {NoAI:1b,NoGravity:1b,Invulnerable:1b}');
 for(const point of ['0 81 80','0 81 -80','119 81 0','-119 81 0','0 70 0']){
  await cmd(`execute in jjkarena:battlefield run tp @e[tag=jjkarena_red] ${point}`);await sleep(250);
  const s=await cmd('execute in jjkarena:battlefield run data get entity @e[tag=jjkarena_red,limit=1] Pos');
  const p=s.match(/\[([^\]]+)\]/)?.[1].split(',').map(parseFloat);assert.ok(p,s);assert.equal(p[0],-55);assert.equal(p[1],81);assert.equal(p[2],0);results.push({point,returned:p});
 }
 // A jump inside the arena must not be flattened by the recovery guard.
 await cmd('execute in jjkarena:battlefield run tp @e[tag=jjkarena_red] 0 115 0');await sleep(250);
 assert.match(await cmd('execute in jjkarena:battlefield run data get entity @e[tag=jjkarena_red,limit=1] Pos'),/115\.0d/);
 pass=true;console.log('PASS arena boundary and void recovery; vertical flight inside arena preserved.');
}finally{await cmd('jjkarena reset');fs.writeFileSync(new URL('../../outputs/JJK-Arena/boundary-test-results.json',import.meta.url),JSON.stringify({pass,results},null,2));}
