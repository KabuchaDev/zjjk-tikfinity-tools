import assert from 'node:assert/strict';
import fs from 'node:fs';
import {consume,emptyState} from './router.mjs';
import {audience} from './audience.mjs';
const config=JSON.parse(fs.readFileSync(new URL('./config.json',import.meta.url)));
let state=emptyState(),serial=0;
const event=(type,data)=>{const e={event:type,data},out=consume(e,config,state);return {out,notice:audience(e,out,state)};};
assert.equal(event('member',{userId:'a',nickname:'Người mới'}).notice.type,'welcome');
assert.equal(event('member',{userId:'a'}).notice,undefined);
event('streamEnd',{});assert.equal(event('member',{userId:'a'}).notice.type,'welcome');
assert.equal(event('follow',{user:{userId:'a',nickname:'Bạn A'}}).notice.type,'follow');
state=JSON.parse(JSON.stringify(state));
assert.equal(event('follow',{userId:'a'}).notice,undefined,'Follow survives restart and refollow');
assert.equal(event('follow',{userId:'b'}).notice.type,'follow');
for(const gift of config.gifts){
 const out=event('gift',{userId:'g',giftName:gift.name.toUpperCase(),repeatCount:2,msgId:'audience-'+(++serial)}).out;
 assert.equal(out.job.gift,true);assert.equal(out.job.kind,gift.kind);assert.equal(out.job.remaining,2);
}
let count=state.jobs.length;
assert.equal(event('gift',{userId:'g',giftName:'Unknown gift',repeatCount:4,giftType:1,repeatEnd:false,groupId:'unknown'}).notice,undefined);
assert.equal(event('gift',{userId:'g',giftName:'Unknown gift',repeatCount:4,giftType:1,repeatEnd:true,groupId:'unknown'}).notice.type,'thanks');
assert.equal(event('gift',{userId:'g',giftName:'Unknown gift',repeatCount:4,giftType:1,repeatEnd:true,groupId:'unknown'}).notice,undefined);
assert.equal(state.jobs.length,count,'Unknown gifts never spawn troops');
event('chat',{userId:'c',comment:'messi',msgId:'c1'});event('chat',{userId:'d',comment:'messi',msgId:'c2'});
assert.equal(event('chat',{userId:'e',comment:'messi',msgId:'c3'}).out.job.gift,undefined,'Comments do not carry a gift acknowledgement flag');
console.log(`PASS: welcome per session, persistent follow dedup, ${config.gifts.length} gift acknowledgements, unknown gift thanks-only, combo dedup, comment regression.`);
