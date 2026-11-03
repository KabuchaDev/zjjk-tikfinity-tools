import fs from 'node:fs';
import {rcon} from '../Zeefski-JJK-3.2.1/rcon.mjs';
const settings=JSON.parse(fs.readFileSync(new URL('../Zeefski-JJK-3.2.1/settings.local.json',import.meta.url),'utf8').replace(/^\uFEFF/,''));
try{
 const status=await rcon('jjkarena status',settings);
 if(!status.includes('built=true building=no'))throw new Error('San dau chua san sang.');
 if(process.argv[2]==='ensure'){
  if(status.includes('running=false')&&/intermission=0\b/.test(status)){
   const result=await rcon('jjkarena start',settings);
   if(!result.includes('Match started'))throw new Error(result);
   console.log('Da bat server va mo tran.');
  }else console.log(status.includes('paused=true')?'Server da chay. Tran dang tam dung; bam TIEP-TUC-TRAN khi muon choi.':'Server va san dau da chay; khong khoi dong trung.');
  console.log('Vao Minecraft: 127.0.0.1:25566');
  console.log('Trong game, go /jjkarena watch de vao khu quan sat.');
 }else console.log('READY');
}catch(error){if(process.argv[2]==='ensure')console.error(error.message);process.exitCode=1;}
