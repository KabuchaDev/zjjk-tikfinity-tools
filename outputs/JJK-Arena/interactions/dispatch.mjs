import fs from 'node:fs';
import {rcon} from '../../Zeefski-JJK-3.2.1/rcon.mjs';
import {kinds} from './router.mjs';
const id=process.argv[2];
if(process.argv.length!==3||!/^[0-9a-f-]{36}$/.test(id??''))throw new Error('Invalid job ID');
const file=new URL(`./spool/${id}.json`,import.meta.url),resultFile=new URL(`./spool/${id}.result.json`,import.meta.url);
if(fs.existsSync(resultFile))process.exit(0);
let lock;
try{lock=fs.openSync(new URL(`./spool/${id}.lock`,import.meta.url),'wx');}catch{process.exit(0);}
const write=result=>{fs.writeFileSync(new URL(`./spool/${id}.result.tmp`,import.meta.url),JSON.stringify(result));fs.renameSync(new URL(`./spool/${id}.result.tmp`,import.meta.url),resultFile);};
try{
 const job=JSON.parse(fs.readFileSync(file,'utf8'));
 if(job.gift!==undefined&&typeof job.gift!=='boolean')throw new Error('Invalid gift flag');
 if(!['red','blue'].includes(job.side)||!kinds.has(job.kind)||!Number.isInteger(job.count)||job.count<1||job.count>15||!/^[-a-zA-Z0-9_.]{1,24}$/.test(job.viewer))throw new Error('Invalid summon payload');
 const cfg=JSON.parse(fs.readFileSync(new URL('../../Zeefski-JJK-3.2.1/settings.local.json',import.meta.url),'utf8').replace(/^\uFEFF/,''));
 const response=await rcon(`jjkarena spawn ${job.side} ${job.kind} ${job.count} ${job.viewer}${job.gift?' gift':''}`,cfg);
 const accepted=response.match(/Queued (\d+) /);
 if(accepted)write({accepted:Number(accepted[1]),response});
 else if(response.includes('not running'))write({accepted:0,response});
 else write({uncertain:true,error:response});
}catch(error){write({uncertain:true,error:error.message});process.exitCode=1;}
finally{if(lock!==undefined)fs.closeSync(lock);}
