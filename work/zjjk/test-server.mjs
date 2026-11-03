import fs from 'node:fs';
import {rcon} from '../../outputs/Zeefski-JJK-3.2.1/rcon.mjs';
const root = new URL('../../outputs/Zeefski-JJK-3.2.1/', import.meta.url);
const cfg = JSON.parse(fs.readFileSync(new URL('settings.local.json', root), 'utf8').replace(/^\uFEFF/, ''));
const report = [];
const run = async command => {
  const result = await rcon(command, cfg);
  report.push({command, result});
  console.log(command + '\n' + result);
  return result;
};
await run('list');
try { await rcon('list', {...cfg,rconPassword:'incorrect-test-password'}); throw new Error('Invalid password was accepted'); }
catch(e) { if (!e.message.includes('Sai mat khau')) throw e; report.push({test:'incorrect password rejected',pass:true}); }
await run('forceload add 0 0');
try {
  const spawn = await run('summon jujutsucraft:miwa_kasumi 8 220 8 {NoAI:1b,NoGravity:1b,Silent:1b,PersistenceRequired:1b,Tags:["zjjk_setup_test"]}');
  if (!spawn.includes('Summoned')) throw new Error('Mod entity summon failed');
  await run('execute if entity @e[tag=zjjk_setup_test]');
} finally {
  await run('kill @e[tag=zjjk_setup_test]');
  await run('forceload remove 0 0');
}
await run('save-all flush');
await run('forge tps');
fs.writeFileSync(new URL('test-results.json', root), JSON.stringify({testedAt:new Date().toISOString(),results:report},null,2));
