import fs from 'node:fs';
import path from 'node:path';
import {randomUUID} from 'node:crypto';
const root=path.resolve('outputs/JJK-Arena/interactions');
const file='C:/Users/PHAT/Downloads/Streamer.bot-x64-1.0.7/data/actions.json';
const original=fs.readFileSync(file,'utf8'),data=JSON.parse(original.replace(/^\uFEFF/,''));
const name='JJK Arena - Summon';
if(!Array.isArray(data.actions))throw new Error('Unexpected Streamer.bot actions schema');
fs.mkdirSync(path.join(root,'backups'),{recursive:true});
fs.writeFileSync(path.join(root,'backups','actions-before-'+Date.now()+'.json'),original);
const action={
 id:randomUUID(),queue:'00000000-0000-0000-0000-000000000000',enabled:true,
 excludeFromHistory:false,excludeFromPending:false,name,group:'JJK Arena',alwaysRun:false,randomAction:false,concurrent:false,triggers:[],collapsedGroups:[],
 subActions:[{command:'C:\\Users\\PHAT\\.cache\\codex-runtimes\\codex-primary-runtime\\dependencies\\node\\bin\\node.exe',
 arguments:'"'+path.join(root,'dispatch.mjs')+'" %arenaJobId%',workingDir:root,envVars:{},waitForExit:20,id:randomUUID(),weight:0.0,type:6,parentId:null,enabled:true,index:0}]
};
const old=data.actions.find(a=>a.name===name);if(old)action.id=old.id;
data.actions=data.actions.filter(a=>a.name!==name);data.actions.push(action);data.t=new Date().toISOString();
fs.writeFileSync(file,JSON.stringify(data,null,2));
fs.writeFileSync(path.join(root,'streamerbot-action.json'),JSON.stringify(action,null,2));
console.log('Installed one Streamer.bot Action: '+name);
