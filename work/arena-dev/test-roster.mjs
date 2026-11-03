import fs from 'node:fs';
import assert from 'node:assert/strict';
import {rcon} from '../../outputs/Zeefski-JJK-3.2.1/rcon.mjs';
const cfg=JSON.parse(fs.readFileSync(new URL('../../outputs/Zeefski-JJK-3.2.1/settings.local.json',import.meta.url),'utf8').replace(/^\uFEFF/,''));
const roster={miwa:'miwa_kasumi',nobara:'nobara_kugisaki',yuji_school:'itadori_yuji',inumaki:'inumaki_toge',panda:'panda_normal',todo:'todo_aoi',gojo_school:'gojo_satoru_school_days',hakari:'hakari_kinji',maki_culling:'zenin_maki_culling_game',yuji_shinjuku:'itadori_yuji_shinjuku',gojo:'gojo_satoru',jogo:'jogo'};
const results=[];async function cmd(c){const r=await rcon(c,cfg);results.push({command:c,result:r});return r;}
try{
 for(const [kind,entity] of Object.entries(roster)){
  await cmd('jjkarena start');assert.match(await cmd('jjkarena spawn red '+kind+' 1 RosterTest'),/Queued 1/);
  await new Promise(r=>setTimeout(r,850));
  assert.match(await cmd('execute in jjkarena:battlefield if entity @e[tag=jjkarena_red,type=jujutsucraft:'+entity+']'),/Test passed/);
  console.log('PASS '+kind+' -> '+entity);
 }
 await cmd('jjkarena start');await cmd('jjkarena spawn red miwa 1 RedTest');await cmd('jjkarena spawn blue miwa 1 BlueTest');
 await new Promise(r=>setTimeout(r,18000));const combat=await cmd('jjkarena status');console.log(combat);assert.match(combat,/hits=[1-9]/);
}finally{await cmd('jjkarena reset');await cmd('jjkarena clearscore');await cmd('save-all flush');fs.writeFileSync(new URL('../../outputs/JJK-Arena/interactions/roster-test-results.json',import.meta.url),JSON.stringify(results,null,2));}
