export function describeArena(raw) {
 const flags=raw.match(/built=(true|false) building=(\S+) running=(true|false) paused=(true|false)/);
 if(!flags)throw new Error('Khong doc duoc trang thai dau truong');
 let state,message;
 if(flags[1]!=='true'||flags[2]!=='no'){state='building';message='SAN CHUA SAN SANG: dang xay hoac chua co san.';}
 else if(flags[4]==='true'){state='paused';message='TRAN TAM DUNG: bam Tiep tuc trong bang dieu khien de nhan quan.';}
 else if(flags[3]!=='true'){
  if(Number(raw.match(/intermission=(\d+)/)?.[1]??0)>0){state='intermission';message='NGHI GIUA HAI TRAN: luot trieu hoi duoc giu cho tran tiep theo.';}
  else{state='stopped';message='CHUA BAT DAU TRAN: bam Bat dau trong bang dieu khien. Binh luan/qua van duoc giu trong hang cho.';}
 }else{state='ready';message='TRAN DANG CHAY: san sang nhan quan.';}
 return {connected:true,state,message,maxHP:Number(raw.match(/maxHP=(\d+)/)?.[1]??0),checkedAt:Date.now()};
}
