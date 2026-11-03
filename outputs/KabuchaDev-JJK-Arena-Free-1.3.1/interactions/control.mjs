const action=process.argv[2]??'status';
if(!['status','reset-counters','stop'].includes(action))throw new Error('Invalid action');
try {
 const result=await fetch('http://127.0.0.1:21214/'+action,action==='status'?{}:{method:'POST',headers:{'Content-Type':'application/json'},body:'{}'});
 const s=await result.json();
 if(action==='status')console.log('TikFinity: '+(s.tikfinity?'DA KET NOI':'CHUA KET NOI')+'\nStreamer.bot: '+(s.streamerbot?'DA KET NOI':'CHUA KET NOI')+'\nBo dem: '+JSON.stringify(s.counters)+'\nQuan dang cho: '+s.pending+'\nLuot can kiem tra: '+s.held);
 else console.log(s.ok?'Da thuc hien.':'Khong thanh cong.');
 if(action==='status')console.log('Dau truong: '+(s.arena?.message??'Chua co thong tin; mo lai bo ket noi de cap nhat.'));
}catch{console.log('Bo ket noi chua chay. Mo START-BRIDGE.cmd truoc.');process.exitCode=1;}
