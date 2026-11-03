import fs from 'node:fs';
import assert from 'node:assert/strict';
import {rcon} from '../../outputs/Zeefski-JJK-3.2.1/rcon.mjs';
const root=new URL('../../outputs/JJK-Arena/interactions/',import.meta.url);
const cfg=JSON.parse(fs.readFileSync(new URL('config.json',root),'utf8'));
const settings=JSON.parse(fs.readFileSync(new URL('../../outputs/Zeefski-JJK-3.2.1/settings.local.json',import.meta.url),'utf8').replace(/^\uFEFF/,''));
const types={miwa:'miwa_kasumi',nobara:'nobara_kugisaki',yuji_school:'itadori_yuji',inumaki:'inumaki_toge',panda:'panda_normal',todo:'todo_aoi',gojo_school:'gojo_satoru_school_days',hakari:'hakari_kinji',maki_culling:'zenin_maki_culling_game',yuji_shinjuku:'itadori_yuji_shinjuku',gojo:'gojo_satoru',jogo:'jogo',sukuna_itadori:'sukuna',sukuna_megumi:'sukuna_fushiguro',sukuna_heian:'sukuna_perfect'};
let serial=Date.now();const results=[];let pass=false;
const sleep=ms=>new Promise(r=>setTimeout(r,ms));
async function status(){return (await fetch('http://127.0.0.1:21214/status')).json();}
async function post(path,data){return (await fetch('http://127.0.0.1:21214/'+path,{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify(data)})).json();}
async function delivered(){for(let n=0;n<60;n++){const s=await status();assert.equal(s.held,0,'Uncertain dispatch');if(s.pending===0&&!s.inflight){await sleep(650);return;}await sleep(500);}throw new Error('Action did not deliver within 30 seconds');}
async function check(rule,label){await delivered();const answer=await rcon(`execute in jjkarena:battlefield if entity @e[tag=jjkarena_${rule.side},type=jujutsucraft:${types[rule.kind]}]`,settings);assert.match(answer,/Test passed/);results.push({label,side:rule.side,kind:rule.kind,entity:types[rule.kind],pass:true});console.log('PASS '+label+' -> '+rule.side+'/'+rule.kind);}
try{
 const connection=await status();assert.equal(connection.test,true);assert.equal(connection.streamerbot,true);
 await post('reset-counters',{});
 for(const [word,rule] of Object.entries(cfg.comments)){
   await rcon('jjkarena start',settings);
   for(let i=0;i<rule.threshold;i++){
     const out=await post('event',{event:'chat',data:{comment:word,uniqueId:'SimUser'+i,msgId:String(++serial)}});
     if(i<rule.threshold-1)assert.ok(out.progress);else assert.ok(out.job);
   }
   await check(rule,word+' x'+rule.threshold);
 }
 for(const rule of cfg.gifts) for(const giftName of [rule.name,rule.name.toLowerCase(),rule.name.toUpperCase()]){
   await rcon('jjkarena start',settings);
   const out=await post('event',{event:'gift',data:{giftName,giftId:'sim-'+(++serial),giftType:0,repeatCount:1,diamondCount:rule.coins,userId:'simulation',uniqueId:'GiftTest',msgId:String(++serial)}});assert.ok(out.job);
   await check(rule,giftName);
 }
 await rcon('jjkarena start',settings);
 const gift={event:'gift',data:{giftName:'Rose',giftId:'simulation-rose',giftType:1,repeatCount:17,repeatEnd:false,userId:'simulation',uniqueId:'ComboTest',msgId:String(++serial),groupId:String(++serial)}};
 assert.ok((await post('event',gift)).ignored);gift.data.repeatEnd=true;gift.data.msgId=String(++serial);
 assert.equal((await post('event',gift)).job.remaining,17);gift.data.msgId=String(++serial);assert.ok((await post('event',gift)).duplicate);
 await delivered();const combo=await rcon('jjkarena status',settings);assert.equal(Number(combo.match(/\bblue=(\d+)/)[1])+Number(combo.match(/queuedBlue=(\d+)/)[1]),17);results.push({label:'Rose combo 17, duplicate final ignored',pass:true,result:combo});
 assert.match(await rcon('jjkarena auditterrain',settings),/changed=0\b/);pass=true;
}finally{await rcon('jjkarena reset',settings);await rcon('save-all flush',settings);fs.writeFileSync(new URL('gift-case-sukuna-test-results.json',root),JSON.stringify({pass,at:new Date().toISOString(),results},null,2));}
console.log('PASS: 4 comment thresholds and 78 gift name variants and 17-gift combo delivered through actual Streamer.bot Action to Minecraft.');
