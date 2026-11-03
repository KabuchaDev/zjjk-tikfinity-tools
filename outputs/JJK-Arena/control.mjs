import fs from 'node:fs';
import {rcon} from '../Zeefski-JJK-3.2.1/rcon.mjs';
const settings=JSON.parse(fs.readFileSync(new URL('../Zeefski-JJK-3.2.1/settings.local.json',import.meta.url),'utf8').replace(/^\uFEFF/,''));
const commands={
  start:'jjkarena start',pause:'jjkarena pause',resume:'jjkarena resume',reset:'jjkarena reset',
  status:'jjkarena status',watch:'jjkarena watch @a[limit=1]',leave:'jjkarena leave @a[limit=1]',
  red:'jjkarena spawn red basic 1 LocalTest',blue:'jjkarena spawn blue basic 1 LocalTest',
  redboss:'jjkarena spawn red boss 1 LocalTest',blueboss:'jjkarena spawn blue boss 1 LocalTest'
};
const cmd=commands[process.argv[2]];
if(!cmd){console.error('Lenh hop le: '+Object.keys(commands).join(', '));process.exit(1);}
try{console.log(await rcon(cmd,settings));}catch(error){console.error(error.message);process.exitCode=1;}
