import fs from 'node:fs';
import path from 'node:path';
import {fileURLToPath} from 'node:url';
import {randomBytes} from 'node:crypto';
import {spawnSync} from 'node:child_process';
import {createInterface} from 'node:readline/promises';
import net from 'node:net';
const root=path.dirname(fileURLToPath(import.meta.url));
const file=path.join(root,'settings.local.json');
const args=process.argv.slice(2);
function option(name){const i=args.indexOf(name);return i<0?undefined:args[i+1];}
function clean(s){return s.trim().replace(/^"(.*)"$/,'$1');}
async function available(port){return new Promise((resolve,reject)=>{const s=net.createServer();s.once('error',()=>reject(new Error(`Port ${port} is in use. Stop the other installation before setup.`)));s.listen(port,'127.0.0.1',()=>s.close(resolve));});}
if(Number(process.versions.node.split('.')[0])<24)throw new Error('Install Node.js 24 or later.');
if(fs.existsSync(file))throw new Error('Already configured. Edit settings.local.json instead; do not share that private file.');
const rl=createInterface({input:process.stdin,output:process.stdout});
let serverDir,javaPath;
try{
 serverDir=path.resolve(clean(option('--server')??await rl.question('Full path to a NEW extracted ZJJK 3.2.1 server folder: ')));
 javaPath=clean(option('--java')??await rl.question('Full path to Java 17 java.exe: '));
}finally{rl.close();}
const serverPort=Number(option('--port')??25565),rconPort=Number(option('--rcon-port')??25575);
if(![serverPort,rconPort].every(p=>Number.isInteger(p)&&p>=1024&&p<=65535)||serverPort===rconPort)throw new Error('Invalid or conflicting ports.');
if(!path.isAbsolute(javaPath)||!fs.existsSync(javaPath))throw new Error('Provide the full path to Java 17 java.exe.');
// java -version normally uses stderr.
const probe=spawnSync(javaPath,['-version'],{encoding:'utf8'});
if(probe.status!==0||!/(?:java|openjdk) version "17\./.test(probe.stdout+probe.stderr))throw new Error('Java 17 is required.');
const forgeArgs='libraries/net/minecraftforge/forge/1.20.1-47.4.3/win_args.txt';
if(!fs.existsSync(path.join(serverDir,forgeArgs)))throw new Error('Forge 47.4.3 is not installed. Follow docs/INSTALL-EN.md first.');
if(!fs.existsSync(path.join(serverDir,'mods/JujutsuCraft-ver50.1-forge-1.20.1.jar')))throw new Error('This release requires the matching ZJJK 3.2.1 server pack with Jujutsu Craft 50.1.');
if(fs.existsSync(path.join(serverDir,'world')))throw new Error('Use a fresh server folder without a world. This setup deliberately refuses to change an existing world.');
if(fs.readdirSync(path.join(serverDir,'mods')).some(n=>/^jjkarena.*\.jar$/i.test(n)))throw new Error('An arena mod is already installed. Use a fresh server directory.');
await available(serverPort);await available(rconPort);
const settings={serverDir,javaPath,nodePath:process.execPath,serverPort,rconPort,rconPassword:randomBytes(32).toString('hex'),maxMemoryGB:6};
const propsPath=path.join(serverDir,'server.properties');
let props=fs.existsSync(propsPath)?fs.readFileSync(propsPath,'utf8'):'';
if(fs.existsSync(propsPath))fs.copyFileSync(propsPath,propsPath+'.before-kabuchadev-'+Date.now());
const values={'server-ip':'127.0.0.1','server-port':serverPort,'online-mode':'true','enable-rcon':'true','rcon.port':rconPort,'rcon.password':settings.rconPassword,'broadcast-rcon-to-ops':'false','level-name':'world','gamemode':'creative','difficulty':'normal','view-distance':8,'simulation-distance':6,'spawn-protection':0,'motd':'KabuchaDev JJK Arena - Free Edition'};
for(const [k,v] of Object.entries(values)){
 const regex=new RegExp('^'+k.replace(/[.*+?^${}()|[\]\\]/g,'\\$&')+'=.*$','gm');
 props=regex.test(props)?props.replace(regex,k+'='+v):props+'\n'+k+'='+v;
}
fs.writeFileSync(propsPath,props.trim()+'\n');
fs.writeFileSync(path.join(serverDir,'eula.txt'),'# Read https://www.minecraft.net/en-us/eula and set true only if you agree.\neula=false\n');
fs.copyFileSync(path.join(root,'mods/server/jjkarena-1.3.1.jar'),path.join(serverDir,'mods/jjkarena-1.3.1.jar'));
fs.writeFileSync(file,JSON.stringify(settings,null,2)+'\n',{flag:'wx'});
const subdir=path.join(root,'interactions');
fs.writeFileSync(path.join(root,'STREAMERBOT-LOCAL.txt'),[
 'Private local setup values: do not include this file when sharing.',
 'Action name: JJK Arena - Summon',
 'Sub-action: Core > System > Run a Program',
 'Target / Command: '+settings.nodePath,
 'Arguments: "'+path.join(subdir,'dispatch.mjs')+'" %arenaJobId%',
 'Working Directory: '+subdir,
 'Wait for Exit: 20 seconds',
 'See docs/INSTALL-EN.md or docs/CAI-DAT-VI.md for WebSocket settings.'
].join('\r\n'));
console.log('Setup complete. Read and accept the Minecraft EULA yourself before starting.');
console.log('Minecraft address: 127.0.0.1:'+serverPort);
console.log('Next: install the client camera jar, configure Streamer.bot using STREAMERBOT-LOCAL.txt, then START-SERVER.cmd.');
