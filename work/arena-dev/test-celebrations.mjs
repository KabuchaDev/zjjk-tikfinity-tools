import fs from 'node:fs';
import assert from 'node:assert/strict';
import {randomBytes} from 'node:crypto';
import {rcon} from '../../outputs/Zeefski-JJK-3.2.1/rcon.mjs';
const settings=JSON.parse(fs.readFileSync(new URL('../../outputs/Zeefski-JJK-3.2.1/settings.local.json',import.meta.url),'utf8').replace(/^\uFEFF/,''));
const results=[];
const cmd=async command=>{const result=await rcon(command,settings);results.push({command,result});return result;};
const sleep=ms=>new Promise(r=>setTimeout(r,ms));
const live=(type,key=randomBytes(24).toString('hex'))=>cmd(`jjkarena live ${type} ${key} ${Buffer.from('Kiểm tra cảnh').toString('base64url')}`);
const data=path=>cmd(`execute in jjkarena:battlefield run data get entity @e[tag=jjkarena_red,limit=1] ${path}`);
const waitScene=async active=>{for(let i=0;i<30;i++){const s=await cmd('jjkarena celebration');if(s.includes('celebrating='+active))return s;await sleep(150);}throw Error('Scene timeout '+active);};
let pass=false;
try {
 const online=await cmd('list');assert.match(online,/There are 0 /,'Controlled test requires empty server');
 const previousReport=new URL('../../outputs/JJK-Arena/celebrations-test-results.json',import.meta.url);
 if(fs.existsSync(previousReport)){
  const prior=JSON.parse(fs.readFileSync(previousReport));
  const accepted=prior.results?.find(r=>r.command.startsWith('jjkarena live follow ')&&r.result.includes('Audience accepted'));
  if(accepted)assert.match(await cmd(accepted.command),/Audience duplicate/,'Follow ledger survives server restart');
 }
 const old=await cmd('jjkarena status');
 await cmd('jjkarena start');await cmd('jjkarena spawn red miwa 1 CinemaTest');await cmd('jjkarena spawn blue nobara 1 CinemaTest');await sleep(650);await cmd('jjkarena pause');
 const before=await data('Pos'),health=await data('Health'),skill=await data('ForgeData.skill');
 await cmd('execute in jjkarena:battlefield run setblock 0 95 0 minecraft:stone');
 const followKey=randomBytes(24).toString('hex');assert.match(await live('follow',followKey),/Audience accepted/);
 assert.match(await waitScene(true),/scene=follow.*cast=2/);
 assert.notEqual(await data('Pos'),before);
 assert.match(await data('Pos'),/96\.0d/,'Stage is above temporary domain roof');
 await cmd('execute in jjkarena:battlefield run damage @e[tag=jjkarena_red,limit=1] 10 minecraft:generic');
 assert.equal(await data('Health'),health,'Scene prevents damage');
 assert.match(await live('follow'),/Audience busy/);
 await waitScene(false);
 assert.equal(await data('Pos'),before,'Original position restored');assert.equal(await data('Health'),health);
 assert.equal(await data('ForgeData.skill'),skill,'Native skill unchanged');
 assert.match(await cmd('execute in jjkarena:battlefield if block 0 95 0 minecraft:stone'),/Test passed/,'Temporary domain block preserved');
 assert.match(await live('follow',followKey),/Audience duplicate/);
 assert.match(await live('welcome'),/Audience accepted/);assert.match(await live('thanks'),/Audience accepted/);
 assert.match(await cmd('jjkarena status'),/red=1 blue=1/);
 console.log('PASS follow: 3s, damage blocked, skill/position restored, dedup, busy retry, text-only events.');
 await cmd('jjkarena start');
 const previous=Number((await cmd('jjkarena celebration')).match(/completed=(\d+)/)[1]);
 await cmd('jjkarena spawn blue yuji_school 3 ComboTest gift');await waitScene(true);await waitScene(false);await sleep(1200);
 assert.match(await cmd('jjkarena status'),/red=0 blue=3 queuedRed=0 queuedBlue=0/);
 assert.equal(Number((await cmd('jjkarena celebration')).match(/completed=(\d+)/)[1]),previous+1,'Combo has one scene');
 console.log('PASS gift combo: 3 troops, only 1 celebration.');
 await cmd('jjkarena start');await cmd('jjkarena spawn blue hakari 1 ResetTest gift');await waitScene(true);
 await cmd('jjkarena reset');assert.match(await cmd('jjkarena celebration'),/celebrating=false.*frozen=0 cast=0/);
 await cmd('jjkarena start');
 assert.match(await cmd('jjkarena auditterrain'),/changed=0/);
 const final=await cmd('jjkarena status');assert.equal(final.match(/wins=\S+/)[0],old.match(/wins=\S+/)[0]);
 await cmd('save-all flush');pass=true;
} finally {
 if((await cmd('list')).includes('There are 0 '))await cmd('jjkarena start');
 fs.writeFileSync(new URL('../../outputs/JJK-Arena/celebrations-test-results.json',import.meta.url),JSON.stringify({pass,at:new Date().toISOString(),results},null,2));
}
console.log('PASS cleanup, map protection, scores preserved.');
