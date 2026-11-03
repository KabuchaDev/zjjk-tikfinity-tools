import fs from 'node:fs';
import assert from 'node:assert/strict';
import {rcon} from '../../outputs/Zeefski-JJK-3.2.1/rcon.mjs';
const cfg=JSON.parse(fs.readFileSync(new URL('../../outputs/Zeefski-JJK-3.2.1/settings.local.json',import.meta.url),'utf8').replace(/^\uFEFF/,''));
const results=[];const sleep=ms=>new Promise(r=>setTimeout(r,ms));let domainObserved=false;
async function cmd(command){const result=await rcon(command,cfg);results.push({at:new Date().toISOString(),command,result});console.log(command+'\n'+result);return result;}
try{
 await cmd('jjkarena start');await cmd('jjkarena spawn red boss 1 SukunaTest');await cmd('jjkarena spawn blue boss 1 GojoTest');
 for(let i=0;i<24;i++){await sleep(5000);const s=await cmd('jjkarena status');if(/effects=\[[^\]]*\bdomain_expansion\b/.test(s)){domainObserved=true;if(i>=8)break;}}
 await cmd('jjkarena testprotection');assert.match(await cmd('jjkarena auditterrain'),/changed=0\b/);await cmd('forge tps');
 assert.ok(domainObserved,'No native expansion observed within the test window');
}finally{await cmd('jjkarena reset');await cmd('jjkarena status');await cmd('jjkarena auditterrain');await cmd('save-all flush');fs.writeFileSync(new URL('../../outputs/JJK-Arena/domain-test-results.json',import.meta.url),JSON.stringify({domainObserved,results},null,2));}
console.log('Native domain observed: '+domainObserved);
