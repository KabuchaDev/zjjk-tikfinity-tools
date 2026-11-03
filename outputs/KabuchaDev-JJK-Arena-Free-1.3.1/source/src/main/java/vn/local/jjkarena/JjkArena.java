package vn.local.jjkarena;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.logging.LogUtils;
import java.util.*;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.coordinates.Vec3Argument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.level.ExplosionEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;
import vn.local.jjkarena.net.CameraLink;

@Mod("jjkarena")
public final class JjkArena {
    private static final Logger LOG=LogUtils.getLogger();
    private static final ResourceKey<Level> DIM=ResourceKey.create(Registries.DIMENSION,new ResourceLocation("jjkarena","battlefield"));
    private static final String UNIT="jjkarena_unit";
    private static final int CAP=15, QUEUE_CAP=60;
    private static final Map<String,String> CHARACTERS=Map.ofEntries(
        Map.entry("miwa","miwa_kasumi"),Map.entry("nobara","nobara_kugisaki"),
        Map.entry("yuji_school","itadori_yuji"),Map.entry("inumaki","inumaki_toge"),
        Map.entry("panda","panda_normal"),Map.entry("todo","todo_aoi"),
        Map.entry("gojo_school","gojo_satoru_school_days"),Map.entry("hakari","hakari_kinji"),
        Map.entry("maki_culling","zenin_maki_culling_game"),Map.entry("yuji_shinjuku","itadori_yuji_shinjuku"),
        Map.entry("gojo","gojo_satoru"),Map.entry("jogo","jogo"),
        Map.entry("sukuna_itadori","sukuna"),Map.entry("sukuna_megumi","sukuna_fushiguro"),
        Map.entry("sukuna_heian","sukuna_perfect"));
    private MinecraftServer server;
    private ServerLevel arena;
    private ArenaState state;
    private ArenaBuilder builder;
    private ArenaTerrain terrain;
    private ArenaCelebration celebration;
    private AudienceLedger audienceLedger;
    private boolean redGate,blueGate;
    private boolean running,paused,forced;
    private int tick,intermission,redHP,blueHP,spawnSequence;
    private long hits,coreHits;
    private long cameraOrder;
    private final Set<UUID> autoViewers=new HashSet<>();
    private final Map<UUID,Boolean> deferredCamera=new HashMap<>();
    private final List<Unit> units=new ArrayList<>();
    private final ArrayDeque<Request> redQueue=new ArrayDeque<>(),blueQueue=new ArrayDeque<>();
    private final ServerBossEvent redBar=new ServerBossEvent(Component.literal("RONALDO"),BossEvent.BossBarColor.RED,BossEvent.BossBarOverlay.PROGRESS);
    private final ServerBossEvent blueBar=new ServerBossEvent(Component.literal("MESSI"),BossEvent.BossBarColor.BLUE,BossEvent.BossBarOverlay.PROGRESS);
    private record Request(boolean red,String kind,String viewer,boolean gift) {}
    private static final class Unit {
        final Mob mob; final boolean red; final String kind;
        final long order;
        int cooldown;
        Unit(Mob m,boolean r,String k,long o) {mob=m;red=r;kind=k;order=o;}
    }
    public JjkArena() { CameraLink.init(snap -> {},this::toggleAutoCamera,s -> {}); vn.local.jjkarena.net.CelebrationLink.init(s->{}); MinecraftForge.EVENT_BUS.register(this); }
    private boolean celebrating(){return celebration!=null&&celebration.active();}
    private List<Mob> cast(){return units.stream().map(u->u.mob).filter(m->m.isAlive()&&!m.isRemoved()).toList();}
    private void actionbar(String text){for(ServerPlayer p:arena.players())p.displayClientMessage(Component.literal(text),true);}
    private int live(CommandSourceStack src,String type,String key,String encoded) {
        if(!ready(src))return 0;
        if(!Set.of("welcome","follow","thanks").contains(type)||!key.matches("[a-f0-9-]{32,64}"))return error(src,"Invalid audience event");
        if(audienceLedger.contains(type,key))return reply(src,"Audience duplicate");
        String text;
        try{text=new String(Base64.getUrlDecoder().decode(encoded),java.nio.charset.StandardCharsets.UTF_8).replaceAll("[\\p{Cc}\\p{Cf}§]","");}
        catch(IllegalArgumentException ex){return error(src,"Invalid audience text");}
        if(text.isBlank()||text.length()>180)return error(src,"Invalid audience text length");
        if(type.equals("follow")&&(celebrating()||builder!=null))return reply(src,"Audience busy");
        audienceLedger.accept(type,key);
        if(type.equals("welcome"))actionbar("Chào mừng "+text+" đến với đấu trường!");
        else if(type.equals("thanks"))actionbar("Cảm ơn "+text+" đã tặng quà! ❤");
        else {actionbar("Cảm ơn "+text+" đã theo dõi! ❤");celebration.start(cast());}
        return reply(src,"Audience accepted");
    }
    private Unit bestCameraUnit() {
        Unit best=null;
        for(Unit unit:units)if(unit.mob.isAlive()&&!unit.mob.isRemoved()&&isArena(unit.mob)) {
            if(best==null||CameraPriority.outranks(CameraPriority.coins(unit.kind),unit.order,CameraPriority.coins(best.kind),best.order))best=unit;
        }
        return best;
    }
    private void toggleAutoCamera(ServerPlayer player) {setAutoCamera(player,!deferredCamera.getOrDefault(player.getUUID(),autoViewers.contains(player.getUUID())));}
    private void setAutoCamera(ServerPlayer player,boolean enabled) {
        if(celebrating()){deferredCamera.put(player.getUUID(),enabled);return;}
        if(enabled&&(!isArena(player)||!player.isSpectator())) {
            player.displayClientMessage(Component.literal("Vào /jjkarena watch trước khi bật tự bám camera."),true);return;
        }
        if(enabled)autoViewers.add(player.getUUID());else {
            autoViewers.remove(player.getUUID());
            if(!celebrating()){player.setCamera(player);CameraLink.autoState(player,false,null);}
        }
        player.displayClientMessage(Component.literal(enabled?"Tự bám camera: BẬT — ưu tiên quân giá cao nhất":"Tự bám camera: TẮT — tự do quan sát"),true);
        if(enabled&&!celebrating())updateAutoCamera(player,bestCameraUnit(),true);
    }
    private void updateAutoCamera(ServerPlayer player,Unit best,boolean refresh) {
        Entity target=best==null?player:best.mob;
        boolean changed=player.getCamera()!=target;
        if(changed)player.setCamera(target);
        // The new target may not have been sent to the client yet. The companion
        // waits for that entity instead of losing a SetCamera packet on the first tick.
        if(changed||refresh)CameraLink.autoState(player,true,best==null?null:best.mob);
    }
    private void updateAutoCameras() {
        if(celebrating())return;
        if(autoViewers.isEmpty())return;
        Unit best=bestCameraUnit();
        for(UUID id:List.copyOf(autoViewers)) {
            ServerPlayer player=server.getPlayerList().getPlayer(id);
            if(player==null){autoViewers.remove(id);continue;}
            if(!isArena(player)||!player.isSpectator()){setAutoCamera(player,false);continue;}
            updateAutoCamera(player,best,tick%40==0);
        }
    }
    @SubscribeEvent public void logout(net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent e) {autoViewers.remove(e.getEntity().getUUID());}
    private String autoCameraStatus() {
        Unit best=bestCameraUnit();
        return "autoViewers="+autoViewers.size()+" target="+(best==null?"none":best.kind+" side="+(best.red?"red":"blue")+" coins="+CameraPriority.coins(best.kind)+" order="+best.order+" uuid="+best.mob.getUUID());
    }
    private static boolean isArena(Entity e) {return e.level().dimension().equals(DIM);}
    private static boolean managed(Entity e) {return isArena(e)&&e.getTags().contains(UNIT);}
    private int reply(CommandSourceStack source,String text) {source.sendSuccess(()->Component.literal(text),false);return 1;}
    private int error(CommandSourceStack source,String text) {source.sendFailure(Component.literal(text));return 0;}
    private boolean ready(CommandSourceStack s) {if(arena==null||state==null){error(s,"Arena dimension unavailable: restart server with jjkarena installed.");return false;}return true;}
    private boolean red(String value) {return value.equalsIgnoreCase("red");}
    private boolean validTeam(String value) {return value.equalsIgnoreCase("red")||value.equalsIgnoreCase("blue");}
    private int count(boolean red) {return (int)units.stream().filter(u->u.red==red&&u.mob.isAlive()).count();}
    private int side(Entity e) {
        for(int depth=0;e!=null&&depth<6;depth++) {
            if(!isArena(e))return 0;
            if(e.getTags().contains("jjkarena_red"))return 1;
            if(e.getTags().contains("jjkarena_blue"))return 2;
            Entity owner=e instanceof Projectile p?p.getOwner():null;
            String id=e.getPersistentData().getString("OWNER_UUID");
            if(owner==null&&!id.isEmpty())try{owner=arena.getEntity(UUID.fromString(id));}catch(IllegalArgumentException ignored){}
            if(owner==null){double friend=e.getPersistentData().getDouble("friend_num");return friend==91001?1:friend==91002?2:0;}
            if(owner==e)return 0;e=owner;
        }
        return 0;
    }
    private boolean enemy(Entity a,Entity b){return b instanceof LivingEntity l&&l.isAlive()&&isArena(b)
        &&(managed(b)||!b.getType().is(net.minecraft.tags.TagKey.create(Registries.ENTITY_TYPE,new ResourceLocation("forge:not_living"))))
        &&side(a)!=0&&side(b)!=0&&side(a)!=side(b);}
    private boolean defender(boolean red) {
        if(count(red)>0)return true;
        for(Entity e:arena.getAllEntities())if(e instanceof Mob m&&m.isAlive()&&side(e)==(red?1:2)&&!e.getType().is(net.minecraft.tags.TagKey.create(Registries.ENTITY_TYPE,new ResourceLocation("forge:not_living"))))return true;
        return false;
    }
    private Vec3 spawn(boolean red) {return new Vec3(red?state.redSpawnX:state.blueSpawnX,81,red?state.redSpawnZ:state.blueSpawnZ);}
    private Vec3 core(boolean red) {return new Vec3(red?state.redCoreX:state.blueCoreX,81,red?state.redCoreZ:state.blueCoreZ);}
    private void message(String s) {for(ServerPlayer p:arena.players()) p.sendSystemMessage(Component.literal(s));LOG.info("[JJK Arena] {}",s);}
    private void force(boolean on) {
        if(arena==null||forced==on)return;
        for(int x=-7;x<=6;x++)for(int z=-5;z<=4;z++)arena.setChunkForced(x,z,on);
        forced=on;
    }
    private void clearUnits() {
        clearUnits(false);
    }
    private void clearUnits(boolean keepQueue) {
        if(celebration!=null)celebration.finish();
        for(Unit u:units)retire(u); units.clear();
        if(!keepQueue){redQueue.clear();blueQueue.clear();}
        if(arena!=null) {
            List<Entity> stale=new ArrayList<>();
            for(Entity e:arena.getAllEntities())if(!(e instanceof ServerPlayer))stale.add(e);
            stale.forEach(e->{server.getScoreboard().removePlayerFromTeam(e.getScoreboardName());e.discard();});
        }
        if(terrain!=null)terrain.clean();redGate=blueGate=false;
    }
    private void retire(Unit u) {
        server.getScoreboard().removePlayerFromTeam(u.mob.getScoreboardName());
        u.mob.discard();
    }
    private void resetRound(boolean start) {
        resetRound(start,false);
    }
    private void resetRound(boolean start,boolean keepQueue) {
        clearUnits(keepQueue); redHP=blueHP=state.maxHealth;running=start;paused=false;intermission=0;hits=coreHits=0;
        force(start||builder!=null);bars();
    }
    private void finish(boolean redWon) {
        if(!running)return;
        running=false;paused=false;intermission=300;
        if(redWon)state.redWins++;else state.blueWins++;
        state.setDirty();
        String label=redWon?"RONALDO - PHE DO THANG!":"MESSI - PHE XANH THANG!";
        for(ServerPlayer p:arena.players())p.connection.send(new ClientboundSetTitleTextPacket(Component.literal(label).withStyle(redWon?ChatFormatting.RED:ChatFormatting.AQUA)));
        message(label+" Tran moi sau 15 giay.");
        clearUnits(true);bars();
    }
    private void bars() {
        if(state==null)return;
        String phase=builder!=null?"DANG XAY "+builder.percent()+"%":intermission>0?"TRAN MOI "+(intermission+19)/20+"s":paused?"TAM DUNG":running?"DANG DAU":"CHO BAT DAU";
        redBar.setName(Component.literal("RONALDO | "+redHP+"/"+state.maxHealth+" | "+(redGate?"CONG DONG":"CONG MO")+" | Quan "+count(true)+"/15 | Cho "+redQueue.size()+" | "+phase));
        blueBar.setName(Component.literal("MESSI | "+blueHP+"/"+state.maxHealth+" | "+(blueGate?"CONG DONG":"CONG MO")+" | Quan "+count(false)+"/15 | Cho "+blueQueue.size()+" | Thang "+state.redWins+"-"+state.blueWins));
        redBar.setProgress(Mth.clamp((float)redHP/state.maxHealth,0,1));blueBar.setProgress(Mth.clamp((float)blueHP/state.maxHealth,0,1));
        for(ServerPlayer p:server.getPlayerList().getPlayers()) {
            if(p.level().dimension().equals(DIM)) {redBar.addPlayer(p);blueBar.addPlayer(p);}
            else {redBar.removePlayer(p);blueBar.removePlayer(p);}
        }
    }
    @SubscribeEvent public void started(ServerStartedEvent e) {
        server=e.getServer();arena=server.getLevel(DIM);
        if(arena==null){LOG.error("Arena dimension missing!");return;}
        state=arena.getDataStorage().computeIfAbsent(ArenaState::load,ArenaState::new,"jjkarena_state");
        audienceLedger=arena.getDataStorage().computeIfAbsent(AudienceLedger::load,AudienceLedger::new,"jjkarena_audience");
        celebration=new ArenaCelebration(arena);
        terrain=ArenaTerrain.open(arena);
        if(state.built)terrain.initialize();
        // Clear chunk tickets left by an interrupted session. Matches never resume unattended.
        forced=true;force(false);resetRound(false);
        for(boolean red:new boolean[]{true,false}) {
            String id=red?"jja_red":"jja_blue";
            PlayerTeam team=server.getScoreboard().getPlayerTeam(id);
            if(team==null)team=server.getScoreboard().addPlayerTeam(id);
            for(String oldName:new ArrayList<>(team.getPlayers()))server.getScoreboard().removePlayerFromTeam(oldName);
            team.setColor(red?ChatFormatting.RED:ChatFormatting.AQUA);team.setAllowFriendlyFire(false);
            team.setCollisionRule(net.minecraft.world.scores.Team.CollisionRule.NEVER);
        }
        LOG.info("[JJK Arena] Ready. Built={}. Ronaldo=red; Messi=blue.",state.built);
    }
    @SubscribeEvent public void stopping(ServerStoppingEvent e) {autoViewers.clear();if(arena!=null){running=false;clearUnits();force(false);redBar.removeAllPlayers();blueBar.removeAllPlayers();ArenaTerrain.close();}}
    @SubscribeEvent(priority=EventPriority.HIGHEST) public void livingTick(LivingEvent.LivingTickEvent e) {
        if(!e.getEntity().level().isClientSide&&isArena(e.getEntity())&&!(e.getEntity() instanceof ServerPlayer)&&(paused||celebrating()))e.setCanceled(true);
    }
    @SubscribeEvent(priority=EventPriority.HIGHEST) public void attack(LivingAttackEvent e) {
        if(!isArena(e.getEntity()))return;
        if(e.getEntity() instanceof ServerPlayer||paused||celebrating()||!running){e.setCanceled(true);return;}
        Entity attacker=e.getSource().getEntity();if(attacker==null)attacker=e.getSource().getDirectEntity();
        int from=side(attacker),to=side(e.getEntity());if(from!=0&&from==to)e.setCanceled(true);
    }
    @SubscribeEvent public void damage(LivingDamageEvent e){if(isArena(e.getEntity())&&e.getAmount()>0)hits++;}
    @SubscribeEvent public void drops(LivingDropsEvent e){if(isArena(e.getEntity()))e.setCanceled(true);}
    @SubscribeEvent(priority=EventPriority.LOWEST) public void target(LivingChangeTargetEvent e) {
        if(!isArena(e.getEntity())||side(e.getEntity())==0)return;
        LivingEntity next=e.getNewTarget();
        if(next!=null&&!enemy(e.getEntity(),next))e.setCanceled(true);
        else if(next==null&&e.getEntity() instanceof Mob mob&&enemy(mob,mob.getTarget())&&running&&!paused)e.setCanceled(true);
    }
    @SubscribeEvent(priority=EventPriority.HIGHEST) public void join(EntityJoinLevelEvent e) {
        if(e.getLevel().isClientSide||!e.getLevel().dimension().equals(DIM))return;
        Entity entity=e.getEntity();
        if(entity instanceof ServerPlayer)return;
        if(celebrating())celebration.freeze(entity);
        if(entity.getTags().contains(UNIT)&&!e.loadedFromDisk())return;
        // Native projectiles, domain entities and summons must be allowed to tick.
        if(e.loadedFromDisk()||!running||entity instanceof ItemEntity||entity instanceof ExperienceOrb)e.setCanceled(true);
    }
    @SubscribeEvent public void breakBlock(BlockEvent.BreakEvent e) {if(e.getLevel() instanceof ServerLevel l&&l.dimension().equals(DIM))e.setCanceled(true);}
    @SubscribeEvent public void placeBlock(BlockEvent.EntityPlaceEvent e) {if(e.getLevel() instanceof ServerLevel l&&l.dimension().equals(DIM))e.setCanceled(true);}
    @SubscribeEvent public void explosion(ExplosionEvent.Detonate e) {if(e.getLevel().dimension().equals(DIM))e.getAffectedBlocks().removeIf(p->ArenaTerrain.protectedAt(e.getLevel(),p));}
    private void gate(boolean red,boolean closed) {
        // Also catch native skill teleports into a shield that is already closed.
        // Move enemies before placing glass so it never appears through their bodies.
        if(closed&&!evacuateCore(red))return;
        if((red?redGate:blueGate)==closed)return;
        Vec3 core=core(red);int cx=Mth.floor(core.x),cz=Mth.floor(core.z);
        for(int x=cx-5;x<=cx+5;x++)for(int z=cz-5;z<=cz+5;z++)for(int y=81;y<=92;y++) {
            if(Math.abs(x-cx)!=5&&Math.abs(z-cz)!=5&&y!=92)continue;
            terrain.gateBlock(new BlockPos(x,y,z),closed,(red?Blocks.RED_STAINED_GLASS:Blocks.BLUE_STAINED_GLASS).defaultBlockState());
        }
        if(red)redGate=closed;else blueGate=closed;
    }
    private AABB gateVolume(boolean red) {
        Vec3 p=core(red);int x=Mth.floor(p.x),z=Mth.floor(p.z);
        return new AABB(x-5,81,z-5,x+6,93,z+6);
    }
    private Vec3 exitFromCore(Mob mob,boolean red,AABB volume) {
        double cx=(volume.minX+volume.maxX)/2,cz=(volume.minZ+volume.maxZ)/2;
        double clearance=7+mob.getBbWidth()/2.0,direction=red?1:-1;
        // Prefer the open lane toward midfield, with room for the entire hitbox.
        // Search around temporary domain blocks instead of destroying them.
        for(int advance=0;advance<=24;advance+=3)for(int lane=0;lane<=24;lane+=3)for(int sign:new int[]{1,-1}) {
            if(lane==0&&sign==-1)continue;
            double x=cx+direction*(clearance+advance),z=cz+lane*sign;
            for(int y=81;y<=96;y++) {
                Vec3 pos=new Vec3(x,y,z);
                AABB box=mob.getBoundingBox().move(pos.subtract(mob.position()));
                if(box.intersects(volume)||box.minX<=-92||box.maxX>=92||box.minZ<=-40||box.maxZ>=40)continue;
                BlockPos below=BlockPos.containing(x,y-1,z);
                if(arena.getBlockState(below).isFaceSturdy(arena,below,Direction.UP)&&arena.noCollision(mob,box))return pos;
            }
        }
        return null;
    }
    private boolean evacuateCore(boolean red) {
        AABB volume=gateVolume(red);boolean clear=true;
        for(Entity entity:arena.getAllEntities()) {
            if(!(entity instanceof Mob mob)||!mob.isAlive()||side(mob)!=(red?2:1)||!mob.getBoundingBox().intersects(volume))continue;
            if(!managed(mob)&&mob.getType().is(net.minecraft.tags.TagKey.create(Registries.ENTITY_TYPE,new ResourceLocation("forge:not_living"))))continue;
            Vec3 exit=exitFromCore(mob,red,volume);
            // If a domain temporarily blocks every exit, keep trying. Defender-based
            // core immunity remains active even while physical glass placement waits.
            if(exit==null){clear=false;continue;}
            ArenaSpectators.relocate(mob,exit);
            direct(mob,!red);
            arena.sendParticles(ParticleTypes.POOF,exit.x,exit.y+0.5,exit.z,8,0.3,0.3,0.3,0.01);
        }
        return clear;
    }
    private LivingEntity closest(Mob mob) {
        LivingEntity best=null;double distance=Double.MAX_VALUE;
        for(Entity candidate:arena.getAllEntities())if(enemy(mob,candidate)&&candidate instanceof Mob&&(managed(candidate)||!candidate.getType().is(net.minecraft.tags.TagKey.create(Registries.ENTITY_TYPE,new ResourceLocation("forge:not_living"))))) {
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
        if(celebrating()){if(e.phase==TickEvent.Phase.END)celebration.tick();return;}
        if(!deferredCamera.isEmpty()){
            var changes=new HashMap<>(deferredCamera);deferredCamera.clear();
            changes.forEach((id,on)->{ServerPlayer p=server.getPlayerList().getPlayer(id);if(p!=null)setAutoCamera(p,on);});
        }
        if(e.phase==TickEvent.Phase.START) {
            if(running&&!paused&&builder==null)for(Unit u:List.copyOf(units))if(u.mob.isAlive())direct(u.mob,u.red);
            return;
        }
        tick++;
        if(builder!=null) {
            final boolean[] done={false};terrain.edit(()->done[0]=builder.tick(arena));
            if(done[0]) {builder=null;state.built=true;terrain.capture();state.setDirty();force(false);message("Dau truong da xay xong. /jjkarena watch va /jjkarena start");}
            if(tick%20==0)bars();return;
        }
        if(intermission>0) {if(--intermission==0){resetRound(true,true);message("Tran moi! Comment ronaldo = DO; messi = XANH.");}updateAutoCameras();if(tick%20==0)bars();return;}
        if(running&&!paused) {
            units.removeIf(u->{if(!u.mob.isAlive()){retire(u);return true;}return false;});
            if(tick%10==0)for(boolean red:new boolean[]{true,false}) {
                ArrayDeque<Request> q=red?redQueue:blueQueue;
                if(count(red)<CAP&&!q.isEmpty())spawnUnit(q.removeFirst());
                if(celebrating())return;
            }
            gate(true,defender(true));gate(false,defender(false));
            for(Unit u:List.copyOf(units)) {
                if(!u.mob.isAlive())continue;
                if(u.cooldown>0)u.cooldown--;
                // Recover before a native teleport leaves the arena and its ticking chunks.
                // Vertical jumps and domain flight inside the arena remain unrestricted.
                if(u.mob.getY()<72||Math.abs(u.mob.getX())>92||Math.abs(u.mob.getZ())>60) {
                    ArenaSpectators.relocate(u.mob,spawn(u.red));
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
            if(redHP<=0&&blueHP<=0){message("HOA! Tran moi sau 15 giay.");running=false;intermission=300;clearUnits(true);}
            else if(redHP<=0)finish(false);else if(blueHP<=0)finish(true);
        }
        updateAutoCameras();
        if(tick%20==0)bars();
    }
    private void spawnUnit(Request req) {
        String id=CHARACTERS.getOrDefault(req.kind,req.kind.equals("boss")?(req.red?"sukuna":"gojo_satoru"):"miwa_kasumi");
        EntityType<?> type=ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation("jujutsucraft",id));
        if(type==null){message("Khong tim thay nhan vat: "+id);return;}
        Entity entity=type.create(arena);
        if(!(entity instanceof Mob mob)){if(entity!=null)entity.discard();return;}
        Unit unit=new Unit(mob,req.red,req.kind,++cameraOrder);
        mob.addTag(UNIT);mob.addTag(req.red?"jjkarena_red":"jjkarena_blue");
        mob.getPersistentData().putString("arenaViewer",req.viewer);
        mob.getPersistentData().putString("arenaKind",req.kind);
        mob.setNoAi(false);mob.setNoGravity(false);mob.setPersistenceRequired();
        String label=(req.red?"RONALDO":"MESSI")+" | "+req.viewer+(req.kind.equals("basic")?"":" | "+req.kind.toUpperCase(Locale.ROOT));
        mob.setCustomName(Component.literal(label).withStyle(req.red?ChatFormatting.RED:ChatFormatting.AQUA));mob.setCustomNameVisible(true);mob.setGlowingTag(true);
        Vec3 home=spawn(req.red);double lane=(spawnSequence++%3-1)*18;
        double z=Mth.clamp(home.z+lane,-32,32);
        mob.moveTo(home.x,home.y,z,req.red?-90:90,0);
        mob.finalizeSpawn(arena,arena.getCurrentDifficultyAt(mob.blockPosition()),MobSpawnType.COMMAND,null,null);
        mob.getPersistentData().putDouble("friend_num",req.red?91001:91002);
        if(arena.addFreshEntity(mob)) {
            server.getScoreboard().addPlayerToTeam(mob.getScoreboardName(),server.getScoreboard().getPlayerTeam(req.red?"jja_red":"jja_blue"));units.add(unit);
            arena.sendParticles(ParticleTypes.PORTAL,home.x,82,z,12,.6,.5,.6,.05);
            if(req.gift){actionbar("Cảm ơn "+req.viewer+" đã tặng quà! ❤");}
        }
    }
    private int enqueue(CommandSourceStack src,String team,String kind,int n,String viewer) {
        return enqueue(src,team,kind,n,viewer,false);
    }
    private int enqueue(CommandSourceStack src,String team,String kind,int n,String viewer,boolean gift) {
        if(!ready(src))return 0;
        if(!validTeam(team)||(!Set.of("basic","elite","boss").contains(kind)&&!CHARACTERS.containsKey(kind)))return error(src,"team: red/blue; character: "+CHARACTERS.keySet()+" or basic/elite/boss");
        if(!running||paused||builder!=null)return error(src,"Arena is not running (start/resume first). No troop queued.");
        boolean red=red(team);ArrayDeque<Request> q=red?redQueue:blueQueue;
        int accepted=Math.min(n,QUEUE_CAP-q.size());
        String name=viewer.replaceAll("[^\\p{L}\\p{N}_.-]","_");if(name.length()>24)name=name.substring(0,24);
        for(int i=0;i<accepted;i++)q.add(new Request(red,kind,name,gift&&i==0));
        bars();return reply(src,"Queued "+accepted+" "+kind+" for "+team+"; queue="+q.size()+"; active="+count(red));
    }
    private int watch(CommandSourceStack src,ServerPlayer p) {
        if(!ready(src)||!state.built)return error(src,"Build arena first.");
        if(!p.level().dimension().equals(DIM)) {
            CompoundTag back=new CompoundTag();back.putString("dim",p.level().dimension().location().toString());back.putDouble("x",p.getX());back.putDouble("y",p.getY());back.putDouble("z",p.getZ());back.putFloat("yaw",p.getYRot());back.putFloat("pitch",p.getXRot());back.putInt("mode",p.gameMode.getGameModeForPlayer().getId());p.getPersistentData().put("jjkarenaReturn",back);
        }
        p.teleportTo(arena,0,101,48,180,30);p.setGameMode(GameType.SPECTATOR);bars();return reply(src,"Welcome! Ronaldo = red; Messi = blue. /jjkarena leave to return.");
    }
    private int leave(CommandSourceStack src,ServerPlayer p) {
        CompoundTag back=p.getPersistentData().getCompound("jjkarenaReturn");
        if(back.isEmpty())return error(src,"No saved return location.");
        ServerLevel dest=server.getLevel(ResourceKey.create(Registries.DIMENSION,new ResourceLocation(back.getString("dim"))));
        if(dest==null)dest=server.overworld();
        p.teleportTo(dest,back.getDouble("x"),back.getDouble("y"),back.getDouble("z"),back.getFloat("yaw"),back.getFloat("pitch"));p.setGameMode(GameType.byId(back.getInt("mode")));p.getPersistentData().remove("jjkarenaReturn");bars();return reply(src,"Returned to your original world and game mode.");
    }
    private String status() {
        if(state==null)return "Arena unavailable";
        StringBuilder s=new StringBuilder("built="+state.built+" building="+(builder==null?"no":builder.percent()+"%")+" running="+running+" paused="+paused+" redHP="+redHP+" blueHP="+blueHP+" maxHP="+state.maxHealth+" red="+count(true)+" blue="+count(false)+" queuedRed="+redQueue.size()+" queuedBlue="+blueQueue.size()+" hits="+hits+" coreHits="+coreHits+" wins="+state.redWins+":"+state.blueWins+" intermission="+intermission+" redGate="+redGate+" blueGate="+blueGate+" protected="+terrain.size()+" blocked="+terrain.blocked()+" temporary="+terrain.temporarySize());
        for(Unit u:units.stream().limit(6).toList())s.append(String.format(Locale.ROOT,"\n%s %s hp=%.1f/%.1f damage=%.1f x=%.1f z=%.1f target=%s skill=%.0f targetTicks=%.0f effects=%s",u.red?"red":"blue",u.kind,u.mob.getHealth(),u.mob.getMaxHealth(),u.mob.getAttributeValue(Attributes.ATTACK_DAMAGE),u.mob.getX(),u.mob.getZ(),u.mob.getTarget()==null?"none":ForgeRegistries.ENTITY_TYPES.getKey(u.mob.getTarget().getType()).getPath(),u.mob.getPersistentData().getDouble("skill"),u.mob.getPersistentData().getDouble("cnt_target"),u.mob.getActiveEffects().stream().map(effect->ForgeRegistries.MOB_EFFECTS.getKey(effect.getEffect()).getPath()).filter(name->name.contains("domain")).toList()));
        return s.toString();
    }
    @SubscribeEvent public void commands(RegisterCommandsEvent e) {
        var root=Commands.literal("jjkarena").executes(c->reply(c.getSource(),"watch | leave | status; Admin: build, start, pause, resume, stop, reset, spawn <red|blue> <basic|elite|boss> [count] [viewer], setspawn, setcore, corehp"));
        root.then(Commands.literal("status").executes(c->reply(c.getSource(),status())));
        root.then(Commands.literal("celebration").requires(s->s.hasPermission(2)).executes(c->reply(c.getSource(),celebration==null?"unavailable":celebration.status())));
        root.then(Commands.literal("live").requires(s->s.hasPermission(2)).then(Commands.argument("type",StringArgumentType.word()).then(Commands.argument("key",StringArgumentType.word()).then(Commands.argument("text",StringArgumentType.word()).executes(c->live(c.getSource(),StringArgumentType.getString(c,"type"),StringArgumentType.getString(c,"key"),StringArgumentType.getString(c,"text")))))));
        root.then(Commands.literal("autocamera")
            .executes(c->{toggleAutoCamera(c.getSource().getPlayerOrException());return 1;})
            .then(Commands.literal("on").executes(c->{setAutoCamera(c.getSource().getPlayerOrException(),true);return 1;}))
            .then(Commands.literal("off").executes(c->{setAutoCamera(c.getSource().getPlayerOrException(),false);return 1;}))
            .then(Commands.literal("status").executes(c->reply(c.getSource(),autoCameraStatus()))));
        root.then(Commands.literal("camera").executes(c->{
            ServerPlayer p=c.getSource().getPlayerOrException();Entity target=p.getCamera();
            return reply(c.getSource(),String.format(Locale.ROOT,"spectating=%s companion=%s target=%s id=%d distance=%.2f auto=%s",
                p.isSpectator()&&target!=p,CameraLink.present(p),ForgeRegistries.ENTITY_TYPES.getKey(target.getType()),target.getId(),p.distanceTo(target),autoViewers.contains(p.getUUID())));
        }));
        root.then(Commands.literal("testprotection").requires(s->s.hasPermission(2)).executes(c->reply(c.getSource(),terrain.verify())));
        root.then(Commands.literal("auditterrain").requires(s->s.hasPermission(2)).executes(c->reply(c.getSource(),terrain.audit())));
        root.then(Commands.literal("watch").executes(c->watch(c.getSource(),c.getSource().getPlayerOrException())).then(Commands.argument("player",EntityArgument.player()).requires(s->s.hasPermission(2)).executes(c->watch(c.getSource(),EntityArgument.getPlayer(c,"player")))));
        root.then(Commands.literal("leave").executes(c->leave(c.getSource(),c.getSource().getPlayerOrException())).then(Commands.argument("player",EntityArgument.player()).requires(s->s.hasPermission(2)).executes(c->leave(c.getSource(),EntityArgument.getPlayer(c,"player")))));
        root.then(Commands.literal("build").requires(s->s.hasPermission(2)).executes(c->{if(!ready(c.getSource()))return 0;if(state.built)return error(c.getSource(),"Arena already built. Use rebuild only to deliberately replace arena blocks.");return build(c.getSource());}));
        root.then(Commands.literal("rebuild").requires(s->s.hasPermission(2)).executes(c->build(c.getSource())));
        root.then(Commands.literal("start").requires(s->s.hasPermission(2)).executes(c->{if(!ready(c.getSource())||!state.built||builder!=null)return error(c.getSource(),"Build arena first.");resetRound(true);return reply(c.getSource(),"Match started. Ronaldo=red, Messi=blue.");}));
        root.then(Commands.literal("pause").requires(s->s.hasPermission(2)).executes(c->{paused=true;bars();return reply(c.getSource(),"Paused; new spawns rejected.");}));
        root.then(Commands.literal("resume").requires(s->s.hasPermission(2)).executes(c->{if(!running)return error(c.getSource(),"Start a match first.");paused=false;bars();return reply(c.getSource(),"Resumed.");}));
        for(String verb:new String[]{"stop","reset"})root.then(Commands.literal(verb).requires(s->s.hasPermission(2)).executes(c->{if(!ready(c.getSource()))return 0;resetRound(false);return reply(c.getSource(),"Troops cleared, cores restored. Start when ready.");}));
        root.then(Commands.literal("spawn").requires(s->s.hasPermission(2)).then(Commands.argument("team",StringArgumentType.word()).then(Commands.argument("kind",StringArgumentType.word())
            .executes(c->enqueue(c.getSource(),StringArgumentType.getString(c,"team"),StringArgumentType.getString(c,"kind"),1,"Viewer"))
            .then(Commands.argument("count",IntegerArgumentType.integer(1,15)).executes(c->enqueue(c.getSource(),StringArgumentType.getString(c,"team"),StringArgumentType.getString(c,"kind"),IntegerArgumentType.getInteger(c,"count"),"Viewer"))
                .then(Commands.argument("viewer",StringArgumentType.word()).executes(c->enqueue(c.getSource(),StringArgumentType.getString(c,"team"),StringArgumentType.getString(c,"kind"),IntegerArgumentType.getInteger(c,"count"),StringArgumentType.getString(c,"viewer")))
                .then(Commands.literal("gift").executes(c->enqueue(c.getSource(),StringArgumentType.getString(c,"team"),StringArgumentType.getString(c,"kind"),IntegerArgumentType.getInteger(c,"count"),StringArgumentType.getString(c,"viewer"),true))))))));
        root.then(Commands.literal("corehp").requires(s->s.hasPermission(2)).then(Commands.argument("value",IntegerArgumentType.integer(10,100000)).executes(c->{if(!ready(c.getSource()))return 0;state.maxHealth=IntegerArgumentType.getInteger(c,"value");state.setDirty();resetRound(false);return reply(c.getSource(),"Core HP updated; match reset.");})));
        root.then(Commands.literal("clearscore").requires(s->s.hasPermission(2)).executes(c->{if(!ready(c.getSource()))return 0;state.redWins=state.blueWins=0;state.setDirty();bars();return reply(c.getSource(),"Win counters cleared.");}));
        for(String verb:new String[]{"setspawn","setcore"})root.then(Commands.literal(verb).requires(s->s.hasPermission(2)).then(Commands.argument("team",StringArgumentType.word()).then(Commands.argument("position",Vec3Argument.vec3()).executes(c->position(c,verb)))));
        e.getDispatcher().register(root);
    }
    private int build(CommandSourceStack s) {
        if(!ready(s))return 0;if(builder!=null)return error(s,"Build in progress.");
        resetRound(false);state.built=false;state.setDirty();builder=new ArenaBuilder();force(true);return reply(s,"Building original 193x129 arena in jjkarena:battlefield; check status.");
    }
    private int position(CommandContext<CommandSourceStack> c,String verb) {
        if(!ready(c.getSource()))return 0;
        String team=StringArgumentType.getString(c,"team");Vec3 pos=Vec3Argument.getVec3(c,"position");
        if(!validTeam(team)||Math.abs(pos.x)>74||Math.abs(pos.z)>32||Math.abs(pos.y-81)>1)return error(c.getSource(),"Use red/blue and an arena-floor point: x -74..74, y 81, z -32..32.");
        if(verb.equals("setspawn")){if(red(team)){state.redSpawnX=pos.x;state.redSpawnZ=pos.z;}else{state.blueSpawnX=pos.x;state.blueSpawnZ=pos.z;}}
        else {
            crystal(core(red(team)),red(team),false);
            if(red(team)){state.redCoreX=pos.x;state.redCoreZ=pos.z;}else{state.blueCoreX=pos.x;state.blueCoreZ=pos.z;}
            crystal(core(red(team)),red(team),true);
        }
        state.setDirty();resetRound(false);return reply(c.getSource(),verb+" "+team+" = "+pos+". Match reset; start when ready.");
    }
    private void crystal(Vec3 pos,boolean red,boolean build) {
        int cx=Mth.floor(pos.x),cz=Mth.floor(pos.z);
        for(int x=cx-2;x<=cx+2;x++)for(int y=81;y<=88;y++)for(int z=cz-2;z<=cz+2;z++) {
            var block=Blocks.AIR;
            if(build) {
                if(y<=82)block=Blocks.POLISHED_BLACKSTONE;
                else if(y==88)block=Blocks.GOLD_BLOCK;
                else if(Math.abs(x-cx)<=1&&Math.abs(z-cz)<=1)block=(x==cx&&z==cz&&y<=86)?Blocks.SEA_LANTERN:(red?Blocks.RED_STAINED_GLASS:Blocks.BLUE_STAINED_GLASS);
            }
            BlockPos blockPos=new BlockPos(x,y,z);var next=block.defaultBlockState();
            terrain.edit(()->arena.setBlock(blockPos,next,2));terrain.remember(blockPos,next);
        }
    }
}
