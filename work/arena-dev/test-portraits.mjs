import fs from 'node:fs';
import assert from 'node:assert/strict';
import {rcon} from '../../outputs/Zeefski-JJK-3.2.1/rcon.mjs';
const settings=JSON.parse(fs.readFileSync(new URL('../../outputs/Zeefski-JJK-3.2.1/settings.local.json',import.meta.url),'utf8').replace(/^\uFEFF/,''));
const results=[];
async function cmd(command){const result=await rcon(command,settings);results.push({command,result});return result;}
let pass=false;
try {
 assert.match(await cmd('jjkarena portraits'),/changed=0 unprotected=0 red=ronaldo blue=messi/);
 for(const [name,sign] of [['ronaldo',-1],['messi',1]]) {
  const photo=JSON.parse(fs.readFileSync(new URL(`../../outputs/JJK-Arena/source/src/main/resources/data/jjkarena/portraits/${name}.json`,import.meta.url),'utf8'));
  for(const [col,row] of [[0,0],[photo.width-1,0],[0,photo.height-1],[photo.width-1,photo.height-1],[Math.floor(photo.width/2),Math.floor(photo.height/2)]]) {
   const xyz=`${sign*94} ${166-Math.floor((64-photo.height)/2)-row} ${sign*(Math.floor((83-photo.width)/2)+col-41)}`;
   const block=photo.palette[photo.rows[row][col]];
   const check=`execute in jjkarena:battlefield if block ${xyz} ${block} run time query gametime`;
   assert.match(await cmd(check),/The time is/);
   await cmd(`execute in jjkarena:battlefield run setblock ${xyz} air`);
   assert.match(await cmd(check),/The time is/,'Portrait pixel was destroyed');
  }
 }
 assert.match(await cmd('jjkarena testprotection'),/level=true chunk=true destroy=true/);
 await cmd('jjkarena reset');
 assert.match(await cmd('jjkarena portraits'),/changed=0 unprotected=0/);
 assert.match(await cmd('jjkarena auditterrain'),/changed=0\b/);
 assert.match(await cmd('jjkarena status'),/maxHP=10000/);
 await cmd('save-all flush');
 pass=true;console.log('PASS: both portraits match their block plans, sampled pixels cannot be removed, all portrait blocks protected, reset preserves artwork.');
}finally {
 fs.writeFileSync(new URL('../../outputs/JJK-Arena/portraits-test-results.json',import.meta.url),JSON.stringify({pass,at:new Date().toISOString(),results},null,2));
}
