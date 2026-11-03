import {createHash,randomUUID} from 'node:crypto';
export const label=x=>String(x??'').normalize('NFKC').replace(/[\p{Cc}\p{Cf}§]/gu,'').trim().slice(0,60);
const hash=x=>createHash('sha256').update(x).digest('hex');
export function audience(envelope,routed,state,now=Date.now()) {
 state.audience??={followed:{},welcomed:{},seenGifts:{},jobs:[]};
 const a=state.audience,d=envelope?.data,event=envelope?.event;
 if(event==='streamEnd'){a.welcomed={};return;}
 if(!d||typeof d!=='object')return;
 const identity=String(d.userId??d.user?.userId??d.uniqueId??d.user?.uniqueId??'');if(!identity)return;
 const id=hash(identity),name=label(d.nickname??d.user?.nickname??d.uniqueId??d.user?.uniqueId??identity)||'Bạn';
 const add=(type,key,text)=>{const job={id:randomUUID(),key,type,text,createdAt:now};a.jobs.push(job);return job;};
 if(event==='member') {
  // streamEnd resets normally; expiry also recovers when TikFinity was closed before LIVE ended.
  if(a.welcomed[id]&&now-a.welcomed[id]<6*3600000)return;a.welcomed[id]=now;
  for(const [k,time] of Object.entries(a.welcomed))if(now-time>6*3600000)delete a.welcomed[k];
  return add('welcome',randomUUID(),name);
 }
 if(event==='follow') {
  if(a.followed[id])return;a.followed[id]=now;
  return add('follow',id,name);
 }
 if(event==='gift'&&routed?.job){routed.job.gift=true;return;}
 if(event!=='gift'||!routed?.unknownGift)return;
 const type=Number(d.giftType??d.extendedGiftInfo?.type??d.gift?.type??0);
 if(type===1&&![true,1,'true'].includes(d.repeatEnd))return;
 const n=Number(d.repeatCount??1);if(!Number.isSafeInteger(n)||n<1||n>100000)return;
 const gift=String(d.giftId??d.gift?.id??routed.unknownGift.name);
 const eventId=d.groupId&&String(d.groupId)!=='0'?`${identity}:${gift}:${d.groupId}`:d.msgId??d.messageId;
 const key=eventId==null?randomUUID():hash('gift:'+eventId);
 if(a.seenGifts[key])return;a.seenGifts[key]=now;
 for(const [k,time] of Object.entries(a.seenGifts))if(now-time>6*3600000)delete a.seenGifts[k];
 return add('thanks',key,`${name} • ${label(routed.unknownGift.name)} ×${n}`);
}
