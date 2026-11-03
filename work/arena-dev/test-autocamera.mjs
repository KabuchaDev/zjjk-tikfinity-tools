import fs from 'node:fs';
import assert from 'node:assert/strict';
import {rcon} from '../../outputs/Zeefski-JJK-3.2.1/rcon.mjs';
const cfg=JSON.parse(fs.readFileSync(new URL('../../outputs/Zeefski-JJK-3.2.1/settings.local.json',import.meta.url),'utf8').replace(/^\uFEFF/,''));
const interactions=JSON.parse(fs.readFileSync(new URL('../../outputs/JJK-Arena/interactions/config.json',import.meta.url),'utf8'));
const source=fs.readFileSync(new URL('../../outputs/JJK-Arena/source/src/main/java/vn/local/jjkarena/CameraPriority.java',import.meta.url),'utf8');
const values=Object.fromEntries([...source.matchAll(/Map.entry\("([a-z_]+)",(\d+)\)/g)].map(m=>[m[1],Number(m[2])]));
for(const gift of interactions.gifts)if(gift.coins!==undefined)assert.equal(values[gift.kind],gift.coins,gift.name);
for(const comment of Object.values(interactions.comments))assert.equal(values[comment.kind],0);
const cmd=c=>rcon(c,cfg),sleep=ms=>new Promise(r=>setTimeout(r,ms));
const arena=c=>cmd('execute in jjkarena:battlefield run '+c);
const results=[];let pass=false;
async function best(label,kind,side){
 const status=await cmd('jjkarena autocamera status');
 assert.match(status,new RegExp(`target=${kind}\\b`),label+': '+status);
 if(side)assert.match(status,new RegExp(`side=${side}\\b`),label+': '+status);
 results.push({label,status});return status.match(/uuid=([\da-f-]+)/)?.[1];
}
async function spawn(side,kind){
 assert.match(await cmd(`jjkarena spawn ${side} ${kind} 1 AutoCameraTest`),/Queued 1/);
 await sleep(650);
 await arena('execute as @e[tag=jjkarena_unit] run data merge entity @s {NoAI:1b,NoGravity:1b,Invulnerable:1b}');
}
try{
 await cmd('jjkarena start');await best('Empty arena','none');
 await spawn('red','miwa');await best('First free troop','miwa','red');
 await spawn('blue','nobara');await best('Same price prefers newest','nobara','blue');
 await spawn('red','yuji_school');await best('One coin outranks comments','yuji_school','red');
 await spawn('blue','miwa');await best('New free troop does not interrupt paid troop','yuji_school','red');
 await spawn('blue','gojo');await best('249 coin troop','gojo','blue');
 await spawn('red','todo');await best('Cheaper gift does not interrupt','gojo','blue');
 await spawn('red','sukuna_megumi');await best('299 coin upgrade','sukuna_megumi','red');
 await spawn('blue','sukuna_heian');const first=await best('349 coin upgrade','sukuna_heian','blue');
 await spawn('red','sukuna_heian');const second=await best('Newest equal-price troop across teams','sukuna_heian','red');
 assert.notEqual(first,second);
 await arena(`kill ${second}`);await sleep(150);assert.equal(await best('Death falls back to surviving highest price','sukuna_heian','blue'),first);
 await arena(`kill ${first}`);await sleep(150);const next=await best('Falls back to 299','sukuna_megumi','red');
 await arena(`kill ${next}`);await sleep(150);await best('Falls back to 249','gojo','blue');
 await cmd('jjkarena reset');await best('Reset clears selection','none');
 await cmd('jjkarena start');await spawn('red','maki_culling');await spawn('blue','jogo');
 await best('Different characters at same 149 price prefer newest','jogo','blue');
 pass=true;console.log(`PASS ${results.length} live selection cases; ${interactions.gifts.filter(g=>g.coins!==undefined).length} configured gift prices match.`);
}finally{
 await cmd('jjkarena reset');await cmd('save-all flush');
 fs.writeFileSync(new URL('../../outputs/JJK-Arena/autocamera-test-results.json',import.meta.url),JSON.stringify({pass,at:new Date().toISOString(),results,configuredGiftPrices:values,clientKeyTest:'Requires Minecraft restart and physical key test'},null,2));
}
