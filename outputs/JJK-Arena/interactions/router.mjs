import {randomUUID} from 'node:crypto';
export const kinds=new Set(['miwa','nobara','yuji_school','inumaki','panda','todo','gojo_school','hakari','maki_culling','yuji_shinjuku','gojo','jogo','sukuna_itadori','sukuna_megumi','sukuna_heian']);
const normalize=x=>String(x??'').normalize('NFKC').trim().toLowerCase().replace(/\s+/g,' ');
export const emptyState=()=>({counters:{messi:0,ronaldo:0,m10:0,cr7:0},seen:{},jobs:[],inflight:null,held:[],giftCatalog:{}});
export function consume(envelope,config,state,now=Date.now()) {
 const event=envelope?.event,data=envelope?.data;
 if(!data||typeof data!=='object'||!['chat','gift'].includes(event))return {ignored:true};
 const identity=String(data.userId??data.uniqueId??data.user?.userId??data.user?.uniqueId??'');
 if(!identity)return {ignored:true};
 const viewer=String(data.uniqueId??data.user?.uniqueId??identity).replace(/[^a-zA-Z0-9_.-]/g,'_').slice(0,24)||'Viewer';
 let rule,amount=1,word='',dedupId=data.msgId??data.messageId;
 if(event==='chat') {
   word=normalize(data.comment);rule=config.comments[word];if(!rule)return {ignored:true};
 }else{
   const giftName=String(data.giftName??data.extendedGiftInfo?.name??data.gift?.name??'');
   const giftId=String(data.giftId??data.gift?.id??'');
   const coins=Number(data.diamondCount??data.extendedGiftInfo?.diamond_count??data.gift?.diamond_count??0);
   if(giftId)state.giftCatalog[giftId]={name:giftName,coins,seenAt:now};
   rule=config.gifts.find(g=>g.id!=null&&String(g.id)===giftId)
     ??config.gifts.find(g=>[g.name,...(g.aliases??[])].some(n=>normalize(n)===normalize(giftName)));
   if(!rule)return {unknownGift:{id:giftId,name:giftName,coins}};
   const type=Number(data.giftType??data.extendedGiftInfo?.type??data.gift?.type??0);
   if(type===1&&data.repeatEnd!==true&&data.repeatEnd!==1&&data.repeatEnd!=='true')return {ignored:true};
   amount=Number(data.repeatCount??1);
   if(!Number.isSafeInteger(amount)||amount<1||amount>100000)return {error:'So luong qua khong hop le'};
   if(data.groupId!=null&&String(data.groupId)!=='0')dedupId=`${identity}:${giftId}:${data.groupId}`;
 }
 if(!['red','blue'].includes(rule.side)||!kinds.has(rule.kind))return {error:'Cau hinh nhan vat/phe khong hop le'};
 for(const [id,at] of Object.entries(state.seen))if(now-at>6*3600000)delete state.seen[id];
 const key=dedupId==null?null:`${event}:${dedupId}`;
 if(key&&state.seen[key])return {duplicate:true};
 if(key)state.seen[key]=now;
 const ids=Object.keys(state.seen);if(ids.length>30000)for(const old of ids.slice(0,ids.length-30000))delete state.seen[old];
 if(event==='chat') {
   const count=(state.counters[word]??0)+1;
   state.counters[word]=count%rule.threshold;
   if(count<rule.threshold)return {progress:{word,count,threshold:rule.threshold}};
 }
 const job={id:randomUUID(),side:rule.side,kind:rule.kind,remaining:amount,viewer,source:event==='chat'?word:rule.name,createdAt:now};
 state.jobs.push(job);return {job};
}
