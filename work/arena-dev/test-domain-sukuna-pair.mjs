import fs from 'node:fs';import assert from 'node:assert/strict';
import {rcon} from '../../outputs/Zeefski-JJK-3.2.1/rcon.mjs';
const cfg=JSON.parse(fs.readFileSync(new URL('../../outputs/Zeefski-JJK-3.2.1/settings.local.json',import.meta.url),'utf8').replace(/^\uFEFF/,''));
const results=[],sleep=ms=>new Promise(r=>setTimeout(r,ms));let domainObserved=false;
async function cmd(command){const result=await rcon(command,cfg);results.push({command,result});return result;}
try{
 await cmd('jjkarena start');await cmd('jjkarena spawn red sukuna_heian 1 DomainAudit');await cmd('jjkarena spawn blue sukuna_heian 1 DomainAudit');await sleep(700);
 // Temporary properties on disposable test mobs only. AI stays enabled;
 // native domain selection still decides whether and when to cast.
 for(let i=0;i<16;i++){
  await cmd('execute in jjkarena:battlefield as @e[tag=jjkarena_unit] run data merge entity @s {Invulnerable:1b,Health:220.0f}');
  await sleep(5000);const s=await cmd('jjkarena status');console.log(s);
  assert.doesNotMatch(s,/target=(projectile_slash|thunder_impact|domain_expansion_entity)\b/,'A fighter targeted a spell effect instead of an opponent');
  if(/effects=\[[^\]]*\bdomain_expansion\b/.test(s)){domainObserved=true;await sleep(3000);break;}
 }
 assert.match(await cmd('jjkarena testprotection'),/level=true chunk=true destroy=true hooks=true,true,true/);
 assert.match(await cmd('jjkarena auditterrain'),/changed=0\b/);await cmd('forge tps');
 assert.ok(domainObserved,'Native domain not observed under controlled low-health test');
}finally{
 await cmd('jjkarena reset');assert.match(await cmd('jjkarena auditterrain'),/changed=0 temporary=0/);await cmd('save-all flush');
 fs.writeFileSync(new URL('../../outputs/JJK-Arena/controlled-domain-test-results.json',import.meta.url),JSON.stringify({domainObserved,conditions:'Disposable Sukuna Heian test mobs with low health and Invulnerable; native AI enabled; no production changes.',results},null,2));
}
console.log('PASS controlled native domain and terrain protection.');
