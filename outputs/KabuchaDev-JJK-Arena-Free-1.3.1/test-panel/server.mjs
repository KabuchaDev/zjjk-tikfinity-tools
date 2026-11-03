import http from 'node:http';
import fs from 'node:fs';
import {randomBytes} from 'node:crypto';
import {rcon} from '../rcon.mjs';
import {kinds} from '../interactions/router.mjs';
const settings=JSON.parse(fs.readFileSync(new URL('../settings.local.json',import.meta.url),'utf8').replace(/^\uFEFF/,''));
const origin='http://127.0.0.1:21215',token=randomBytes(32).toString('hex');
const html=fs.readFileSync(new URL('./index.html',import.meta.url),'utf8').replace('__TOKEN__',token);
let busy=false;
const controls={start:'jjkarena start',pause:'jjkarena pause',resume:'jjkarena resume',reset:'jjkarena reset'};
function parseStatus(raw){
 const data={};for(const [,key,value] of raw.matchAll(/(\w+)=(true|false|-?\d+)(?=\s|$)/g))data[key]=value==='true'?true:value==='false'?false:Number(value);
 if(typeof data.running!=='boolean')throw new Error('Không đọc được trạng thái sân.');
 return data;
}
const server=http.createServer(async(req,res)=>{
 res.setHeader('Cache-Control','no-store');res.setHeader('X-Content-Type-Options','nosniff');
 res.setHeader('Content-Security-Policy',"default-src 'self'; script-src 'self' 'unsafe-inline'; style-src 'self' 'unsafe-inline'; connect-src 'self'; frame-ancestors 'none'");
 const send=(code,body)=>{res.writeHead(code,{'Content-Type':'application/json; charset=utf-8'});res.end(JSON.stringify(body));};
 if(req.headers.host!=='127.0.0.1:21215'||(req.headers.origin&&req.headers.origin!==origin))return send(403,{error:'Yêu cầu không hợp lệ.'});
 if(req.method==='GET'&&req.url==='/health')return send(200,{app:'jjk-test-panel'});
 if(req.method==='GET'&&req.url==='/'){res.writeHead(200,{'Content-Type':'text/html; charset=utf-8'});return res.end(html);}
 if(req.method==='GET'&&req.url==='/api/status'){
  try{return send(200,{status:parseStatus(await rcon('jjkarena status',settings))});}
  catch{return send(503,{error:'Chưa kết nối server. Hãy mở START-SERVER.cmd và đợi server khởi động xong.'});}
 }
 if(req.method!=='POST'||req.url!=='/api/action')return send(404,{error:'Không tìm thấy.'});
 if(req.headers['x-panel-token']!==token||!req.headers['content-type']?.startsWith('application/json'))return send(403,{error:'Hãy tải lại bảng điều khiển.'});
 if(busy)return send(409,{error:'Đang thực hiện lệnh trước, bạn đợi một chút nhé.'});
 busy=true;
 try{
  let raw='';for await(const chunk of req){raw+=chunk;if(raw.length>4096)return send(400,{error:'Yêu cầu quá dài.'});}
  let body;try{body=JSON.parse(raw);}catch{return send(400,{error:'Dữ liệu không hợp lệ.'});}
  if(!body||typeof body!=='object')return send(400,{error:'Dữ liệu không hợp lệ.'});
  let command;
  if(['welcome','follow','thanks'].includes(body.action)){
   const key=randomBytes(24).toString('hex');
   const text=Buffer.from('Người xem thử nghiệm').toString('base64url');
   const answer=await rcon(`jjkarena live ${body.action} ${key} ${text}`,settings);
   if(answer.includes('Audience busy'))return send(409,{error:'Đang có cảnh cảm ơn khác. Đợi 3 giây rồi thử lại.'});
   if(!answer.includes('Audience accepted'))return send(409,{error:'Chưa phát được cảnh thử.'});
   return send(200,{message:body.action==='follow'?'Đã thử follow. Cần có quân trên sân để xem cảnh cúi chào.':'Đã gửi dòng chữ phía trên thanh vật phẩm.'});
  }
  if(body.action==='spawn'){
   if(!kinds.has(body.kind)||!['red','blue'].includes(body.side)||!Number.isInteger(body.count)||body.count<1||body.count>15)return send(400,{error:'Chọn nhân vật, phe và số lượng từ 1 đến 15.'});
   const s=parseStatus(await rcon('jjkarena status',settings));
   if(!s.running)return send(409,{error:'Sân chưa bắt đầu. Bấm “Bắt đầu trận mới” trước.'});
   if(s.paused)return send(409,{error:'Trận đang tạm dừng. Bấm “Tiếp tục” rồi triệu hồi.'});
   command=`jjkarena spawn ${body.side} ${body.kind} ${body.count} TestPanel${body.gift===true?' gift':''}`;
  }else command=Object.hasOwn(controls,body.action)?controls[body.action]:null;
  if(!command)return send(400,{error:'Thao tác không hợp lệ.'});
  const answer=await rcon(command,settings);
  if(body.action==='spawn'){
   const match=answer.match(/Queued (\d+)/);if(!match)return send(409,{error:'Server chưa nhận quân. Kiểm tra trạng thái trận rồi thử lại.'});
   const accepted=Number(match[1]);
   return send(200,{accepted,message:accepted?`Đã nhận ${accepted}/${body.count} quân cho phe ${body.side==='red'?'đỏ':'xanh'}. Quân sẽ xuất hiện lần lượt.`:'Hàng chờ phe này đã đầy. Đợi bớt quân hoặc dọn trận.'});
  }
  const expected={start:'Match started',pause:'Paused',resume:'Resumed',reset:'Troops cleared'};
  if(!answer.includes(expected[body.action]))return send(409,{error:'Chưa thực hiện được. Nếu sân đang chờ, hãy bấm “Bắt đầu trận mới”.'});
  return send(200,{message:{start:'Đã mở trận mới, dọn quân cũ và hồi đầy hai lõi.',pause:'Đã tạm dừng trận.',resume:'Trận đã tiếp tục.',reset:'Đã dọn quân và hồi đầy lõi. Bấm “Bắt đầu trận mới” khi muốn thử tiếp.'}[body.action]});
 }catch{return send(503,{error:'Chưa xác nhận được kết quả từ server. Kiểm tra trong game trước khi bấm lại để tránh gọi trùng.'});}
 finally{busy=false;}
});
server.on('error',e=>{console.error(e.code==='EADDRINUSE'?'Bảng điều khiển đã mở tại '+origin:e.message);process.exit(1);});
server.listen(21215,'127.0.0.1',()=>console.log('JJK Test Panel: '+origin));
