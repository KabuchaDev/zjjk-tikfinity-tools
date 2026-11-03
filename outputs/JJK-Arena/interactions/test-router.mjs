import fs from 'node:fs';
import assert from 'node:assert/strict';
import {consume,emptyState} from './router.mjs';
const config=JSON.parse(fs.readFileSync(new URL('./config.json',import.meta.url),'utf8'));
let serial=0;const state=emptyState();
function chat(word,user='Viewer'){return consume({event:'chat',data:{comment:word,uniqueId:user,msgId:String(++serial)}},config,state);}
for(const [word,mapping] of Object.entries(config.comments)){
 for(let i=1;i<mapping.threshold;i++)assert.ok(chat(word,'User'+i).progress);
 const result=chat(word.toUpperCase(),'LastViewer');assert.equal(result.job.side,mapping.side);assert.equal(result.job.kind,mapping.kind);assert.equal(state.counters[word],0);
}
assert.equal(chat('messi messi messi').ignored,true);assert.equal(chat('hello').ignored,true);
for(const rule of config.gifts){
 for(const name of [rule.name,rule.name.toLowerCase(),rule.name.toUpperCase(),'  '+rule.name.replace(/ /g,'   ')+'  ',...(rule.aliases??[])]){
  const result=consume({event:'gift',data:{giftName:name,giftId:'test-'+rule.name,giftType:0,diamondCount:rule.coins,repeatCount:1,userId:'test',uniqueId:'Tester',msgId:String(++serial)}},config,state);
  assert.equal(result.job.kind,rule.kind,name);assert.equal(result.job.side,rule.side,name);
 }
}
const gift={event:'gift',data:{giftName:'Rose',giftId:1,giftType:1,repeatCount:17,userId:'test',uniqueId:'bad name;test',msgId:'streak-progress',groupId:'one',repeatEnd:false}};
assert.equal(consume(gift,config,state).ignored,true);gift.data.repeatEnd=true;gift.data.msgId='streak-end';
const end=consume(gift,config,state);assert.equal(end.job.remaining,17);assert.match(end.job.viewer,/^[-a-zA-Z0-9_.]+$/);
gift.data.msgId='duplicate-final-with-new-msg-id';assert.equal(consume(gift,config,state).duplicate,true);
gift.data.groupId='two';assert.ok(consume(gift,config,state).job);
assert.ok(consume({event:'gift',data:{giftName:'Unknown 1 coin',diamondCount:1,userId:'test'}},config,state).unknownGift);
const restored=JSON.parse(JSON.stringify(state));assert.equal(consume(gift,config,restored).duplicate,true);
for(const rule of config.gifts.filter(g=>g.id!=null)){
 const byId=consume({event:'gift',data:{giftId:rule.id,giftName:'Ten qua da duoc dich',repeatCount:1,userId:'id-test',msgId:String(++serial)}},config,state);
 assert.equal(byId.job.kind,rule.kind);assert.equal(byId.job.side,rule.side);
 const conflicting=consume({event:'gift',data:{giftId:rule.id,giftName:rule.side==='blue'?'TikTok':'Rose',repeatCount:1,userId:'id-test',msgId:String(++serial)}},config,state);
 assert.equal(conflicting.job.side,rule.side,'Verified gift ID takes priority over its displayed name');
}
assert.equal(consume({event:'gift',data:{giftName:'Rose',repeatCount:-1,userId:'test'}},config,state).error,'So luong qua khong hop le');
console.log(`PASS: 4 shared counters, ${config.gifts.length} gifts with lowercase/uppercase/whitespace/aliases, distinct equal-price gifts, combo 17, deduplication, persistence, safe viewer names.`);
