import fs from 'node:fs';
import assert from 'node:assert/strict';
import {rcon} from '../../outputs/Zeefski-JJK-3.2.1/rcon.mjs';
const settings=JSON.parse(fs.readFileSync(new URL('../../outputs/Zeefski-JJK-3.2.1/settings.local.json',import.meta.url),'utf8').replace(/^\uFEFF/,''));
const results=[];const sleep=ms=>new Promise(r=>setTimeout(r,ms));
async function cmd(command){const result=await rcon(command,settings);results.push({command,result});console.log(command+'\n'+result);return result;}
let pass=false;
try{
 await cmd('jjkarena start');
 assert.match(await cmd('jjkarena testprotection'),/level=true chunk=true destroy=true hooks=true,true,true/);
 await cmd('jjkarena spawn red basic 3 NativeRed');await cmd('jjkarena spawn blue basic 3 NativeBlue');
 await sleep(2000);
 let state=await cmd('jjkarena status');assert.match(state,/redGate=true blueGate=true/);
 for(let i=0;i<8;i++){await sleep(5000);state=await cmd('jjkarena status');if(/hits=[1-9]/.test(state))break;}
 assert.match(state,/hits=[1-9]/);
 await cmd('forge tps');
 await cmd('jjkarena reset');await cmd('jjkarena start');
 await cmd('jjkarena spawn red basic 1 GateAttacker');await cmd('jjkarena spawn blue basic 1 GateDefender');
 await sleep(1000);
 await cmd('execute in jjkarena:battlefield as @e[tag=jjkarena_red] run data merge entity @s {NoAI:1b}');
 await cmd('execute in jjkarena:battlefield as @e[tag=jjkarena_blue] run data merge entity @s {NoAI:1b}');
 await cmd('execute in jjkarena:battlefield as @e[tag=jjkarena_red] run tp @s 64 81 0');
 await cmd('execute in jjkarena:battlefield as @e[tag=jjkarena_blue] run tp @s -70 81 30');
 await sleep(3000);state=await cmd('jjkarena status');assert.match(state,/blueHP=1000/);assert.match(state,/blueGate=true/);
 await cmd('execute in jjkarena:battlefield run kill @e[tag=jjkarena_blue]');
 await sleep(2500);state=await cmd('jjkarena status');assert.match(state,/blueGate=false/);assert.ok(Number(state.match(/blueHP=(\d+)/)[1])<1000);
 pass=true;
}finally{await cmd('jjkarena reset');fs.writeFileSync(new URL('../../outputs/JJK-Arena/native-test-results.json',import.meta.url),JSON.stringify({pass,results},null,2));}
console.log('PASS: native combat, original stats, core shield, terrain protection.');
