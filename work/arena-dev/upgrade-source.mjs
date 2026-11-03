import fs from 'node:fs';
const path='outputs/JJK-Arena/source/src/main/java/vn/local/jjkarena/JjkArena.java';
let s=fs.readFileSync(path,'utf8');
const begin=s.indexOf('    @SubscribeEvent public void tick(TickEvent.ServerTickEvent e)');
const end=s.indexOf('    private void spawnUnit(Request req)',begin);
if(begin<0||end<0)throw new Error('Expected tick section missing');
s=s.slice(0,begin)+`    private void gate(boolean red,boolean closed) {
        if((red?redGate:blueGate)==closed)return;
        Vec3 core=core(red);int cx=Mth.floor(core.x),cz=Mth.floor(core.z);
        for(int x=cx-5;x<=cx+5;x++)for(int z=cz-5;z<=cz+5;z++)for(int y=81;y<=92;y++) {
            if(Math.abs(x-cx)!=5&&Math.abs(z-cz)!=5&&y!=92)continue;
            terrain.gateBlock(new BlockPos(x,y,z),closed,(red?Blocks.RED_STAINED_GLASS:Blocks.BLUE_STAINED_GLASS).defaultBlockState());
        }
        if(red)redGate=closed;else blueGate=closed;
    }
    private LivingEntity closest(Mob mob) {
        LivingEntity best=null;double distance=Double.MAX_VALUE;
        for(Entity candidate:arena.getAllEntities())if(enemy(mob,candidate)&&candidate instanceof Mob&&!candidate.getType().is(net.minecraft.tags.TagKey.create(Registries.ENTITY_TYPE,new ResourceLocation("forge:not_living")))) {
            double d=mob.distanceToSqr(candidate);if(d<distance){distance=d;best=(LivingEntity)candidate;}
        }
        return best;
    }
    private void direct(Mob mob,boolean red) {
        mob.getPersistentData().putDouble("friend_num",red?91001:91002);
        LivingEntity target=mob.getTarget();
        if(!enemy(mob,target))target=closest(mob);
        if(target!=null) {
            mob.getPersistentData().putString("TARGET_UUID",target.getStringUUID());
            mob.getPersistentData().putDouble("cnt_target",Math.max(12,mob.getPersistentData().getDouble("cnt_target")));
            if(mob.getTarget()!=target)mob.setTarget(target);
            // Only assist travel from the spawn. Native AI owns melee, cooldowns,
            // movement during skills, damage, healing, domains and animations.
            if(tick%20==0&&mob.distanceToSqr(target)>144&&mob.getPersistentData().getDouble("skill")==0)
                mob.getNavigation().moveTo(target,1.0);
        } else {
            mob.setTarget(null);mob.getPersistentData().putString("TARGET_UUID","");
            if(tick%20==0) {Vec3 goal=core(!red);mob.getNavigation().moveTo(goal.x,81,goal.z,1.0);}
        }
    }
    @SubscribeEvent public void tick(TickEvent.ServerTickEvent e) {
        if(arena==null||state==null)return;
        if(e.phase==TickEvent.Phase.START) {
            if(running&&!paused&&builder==null)for(Unit u:List.copyOf(units))if(u.mob.isAlive())direct(u.mob,u.red);
            return;
        }
        tick++;
        if(builder!=null) {
            final boolean[] done={false};terrain.edit(()->done[0]=builder.tick(arena));
            if(done[0]) {builder=null;state.built=true;state.setDirty();terrain.capture();force(false);message("Dau truong da xay xong. /jjkarena watch va /jjkarena start");}
            if(tick%20==0)bars();return;
        }
        if(intermission>0) {if(--intermission==0){resetRound(true);message("Tran moi! Comment ronaldo = DO; messi = XANH.");}if(tick%20==0)bars();return;}
        if(running&&!paused) {
            units.removeIf(u->{if(!u.mob.isAlive()){retire(u);return true;}return false;});
            if(tick%10==0)for(boolean red:new boolean[]{true,false}) {
                ArrayDeque<Request> q=red?redQueue:blueQueue;
                if(count(red)<CAP&&!q.isEmpty())spawnUnit(q.removeFirst());
            }
            gate(true,defender(true));gate(false,defender(false));
            for(Unit u:List.copyOf(units)) {
                if(!u.mob.isAlive())continue;
                if(u.cooldown>0)u.cooldown--;
                // Catch falls into the void only; native jumps and domain flight are preserved.
                if(u.mob.getY()<72||Math.abs(u.mob.getX())>120||Math.abs(u.mob.getZ())>95) {
                    Vec3 home=spawn(u.red);u.mob.teleportTo(home.x,81,home.z);
                }
                if(enemy(u.mob,u.mob.getTarget())||defender(!u.red))continue;
                Vec3 goal=core(!u.red);
                if(u.mob.position().distanceToSqr(goal)<=49&&u.cooldown==0) {
                    u.cooldown=20;
                    int damage=Math.max(1,(int)Math.ceil(u.mob.getAttributeValue(Attributes.ATTACK_DAMAGE)));
                    if(u.red)blueHP=Math.max(0,blueHP-damage);else redHP=Math.max(0,redHP-damage);
                    coreHits++;u.mob.swing(InteractionHand.MAIN_HAND);
                    arena.sendParticles(ParticleTypes.SWEEP_ATTACK,goal.x,84,goal.z,1,0,0,0,0);
                }
            }
            if(redHP<=0&&blueHP<=0){message("HOA! Tran moi sau 15 giay.");running=false;intermission=300;clearUnits();}
            else if(redHP<=0)finish(false);else if(blueHP<=0)finish(true);
        }
        if(tick%20==0)bars();
    }
`+s.slice(end);
s=s.replace('mob.setNoAi(true);mob.setNoGravity(true);mob.setPersistenceRequired();mob.setSilent(true);\n        if(mob.getAttribute(Attributes.MAX_HEALTH)!=null)mob.getAttribute(Attributes.MAX_HEALTH).setBaseValue(unit.maxHP);\n        mob.setHealth(unit.maxHP);','mob.setNoAi(false);mob.setNoGravity(false);mob.setPersistenceRequired();');
// The file can have Windows line endings from the original project.
s=s.replace(/mob\.setNoAi\(true\);mob\.setNoGravity\(true\);mob\.setPersistenceRequired\(\);mob\.setSilent\(true\);\r?\n\s*if\(mob.getAttribute\(Attributes.MAX_HEALTH\)!=null\)mob.getAttribute\(Attributes.MAX_HEALTH\).setBaseValue\(unit.maxHP\);\r?\n\s*mob.setHealth\(unit.maxHP\);/,'mob.setNoAi(false);mob.setNoGravity(false);mob.setPersistenceRequired();');
s=s.replace('mob.moveTo(home.x,home.y,z,req.red?-90:90,0);','mob.moveTo(home.x,home.y,z,req.red?-90:90,0);\n        mob.finalizeSpawn(arena,arena.getCurrentDifficultyAt(mob.blockPosition()),MobSpawnType.COMMAND,null,null);\n        mob.getPersistentData().putDouble("friend_num",req.red?91001:91002);');
s=s.replace('u.kind,u.hp,u.mob.getX(),u.mob.getZ()','u.kind,u.mob.getHealth(),u.mob.getX(),u.mob.getZ()');
s=s.replace('" intermission="+intermission);','" intermission="+intermission+" redGate="+redGate+" blueGate="+blueGate+" protected="+terrain.size()+" blocked="+terrain.blocked()+" temporary="+terrain.temporarySize());');
s=s.replace('" | Quan "+count(true)+"/15','" | "+(redGate?"CONG DONG":"CONG MO")+" | Quan "+count(true)+"/15');
s=s.replace('" | Quan "+count(false)+"/15','" | "+(blueGate?"CONG DONG":"CONG MO")+" | Quan "+count(false)+"/15');
s=s.replace('arena.setBlock(new BlockPos(x,y,z),block.defaultBlockState(),2);','BlockPos pos=new BlockPos(x,y,z);var next=block.defaultBlockState();\n            terrain.edit(()->arena.setBlock(pos,next,2));terrain.remember(pos,next);');
fs.writeFileSync(path,s);
for(const file of ['outputs/JJK-Arena/source/build.gradle','outputs/JJK-Arena/source/src/main/resources/META-INF/mods.toml']){
 let content=fs.readFileSync(file,'utf8').replaceAll('1.0.0','1.1.0').replace('deterministic team combat','native JJK combat, protected terrain');fs.writeFileSync(file,content);
}
