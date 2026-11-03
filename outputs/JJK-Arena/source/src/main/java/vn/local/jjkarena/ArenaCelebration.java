package vn.local.jjkarena;

import java.util.*;
import net.minecraft.server.level.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.phys.Vec3;
import vn.local.jjkarena.net.CelebrationLink;
import vn.local.jjkarena.net.CameraLink;
import net.minecraft.world.level.levelgen.Heightmap;

/** A short reversible scene: no attributes, skill data, health or AI goals are changed. */
final class ArenaCelebration {
    private final ServerLevel level;
    private final Map<Entity,Boolean> frozen=new IdentityHashMap<>();
    private final List<Pose> poses=new ArrayList<>();
    private final List<Viewer> viewers=new ArrayList<>();
    private long deadline;
    private int completed;
    private boolean gift;
    private record Pose(Mob mob,Vec3 pos,Vec3 motion,float yaw,float pitch,float head,float body) {
        Pose(Mob m){this(m,m.position(),m.getDeltaMovement(),m.getYRot(),m.getXRot(),m.yHeadRot,m.yBodyRot);}
        void restore(){if(!mob.isRemoved()){
            mob.moveTo(pos.x,pos.y,pos.z,yaw,pitch);mob.setDeltaMovement(motion);
            mob.yHeadRot=head;mob.yBodyRot=body;
        }}
    }
    private record Viewer(ServerPlayer player,Entity camera,Vec3 pos,float yaw,float pitch) {}
    ArenaCelebration(ServerLevel level){this.level=level;}
    boolean active(){return deadline!=0;}
    String status(){return "celebrating="+active()+" scene="+(active()?(gift?"gift":"follow"):"none")+" frozen="+frozen.size()+" cast="+poses.size()+" completed="+completed;}
    void freeze(Entity entity){if(active()&&!(entity instanceof ServerPlayer)){frozen.putIfAbsent(entity,entity.canUpdate());entity.canUpdate(false);}}
    void start(List<Mob> cast,Mob star) {
        if(active()||cast.isEmpty())return;
        gift=star!=null;deadline=System.nanoTime()+3_000_000_000L;
        for(Entity entity:level.getAllEntities())freeze(entity);
        List<CelebrationLink.Actor> actors=new ArrayList<>();
        // A native domain can occupy midfield. Place the temporary cast above its
        // roof so it cannot obscure the scene; never erase or alter domain blocks.
        double stageY=81;
        for(int x=-25;x<=25;x++)for(int z=-20;z<=36;z++)
            stageY=Math.max(stageY,level.getHeight(Heightmap.Types.MOTION_BLOCKING,x,z));
        int cols=Math.min(8,Math.max(1,cast.size()-(gift?1:0))),i=0;
        for(Mob mob:cast) {
            poses.add(new Pose(mob));
            if(mob==star)mob.moveTo(0,stageY,8,0,0);
            else {int col=i%cols,row=i/cols;i++;mob.moveTo((col-(cols-1)/2.0)*5.5,stageY,-row*5.5,0,0);}
            float yaw=gift&&mob!=star?(float)Math.toDegrees(Math.atan2(-(0-mob.getX()),8-mob.getZ())):0;
            mob.setYRot(yaw);mob.yHeadRot=mob.yBodyRot=yaw;mob.setDeltaMovement(Vec3.ZERO);
            String kind=mob.getPersistentData().getString("arenaKind");
            int style=List.of("miwa","nobara","yuji_school","inumaki","panda","todo","gojo_school","hakari","maki_culling","yuji_shinjuku","gojo","jogo","sukuna_itadori","sukuna_megumi","sukuna_heian").indexOf(kind);
            actors.add(new CelebrationLink.Actor(mob.getUUID(),Math.max(0,style)));
        }
        double y=stageY+(gift?5:13),z=gift?22:Math.max(26,cols*4.2);
        float pitch=gift?17:22;
        CelebrationLink.Scene scene=new CelebrationLink.Scene(true,gift,star==null?new UUID(0,0):star.getUUID(),actors,0,y,z,180,pitch);
        for(ServerPlayer p:List.copyOf(level.players()))if(p.isSpectator()) {
            viewers.add(new Viewer(p,p.getCamera(),p.position(),p.getYRot(),p.getXRot()));
            p.setCamera(p);p.connection.teleport(0,y,z,180,pitch);level.getChunkSource().move(p);
            CelebrationLink.send(p,scene);
        }
    }
    void tick(){if(active()&&System.nanoTime()>=deadline)finish();}
    void finish() {
        if(!active())return;deadline=0;
        for(Pose pose:poses)pose.restore();
        frozen.forEach((entity,previous)->{if(!entity.isRemoved())entity.canUpdate(previous);});
        CelebrationLink.Scene end=new CelebrationLink.Scene(false,false,new UUID(0,0),List.of(),0,0,0,0,0);
        for(Viewer v:viewers) {
            ServerPlayer p=v.player;
            if(p.isRemoved()||p.serverLevel()!=level)continue;
            CelebrationLink.send(p,end);
            if(!p.isSpectator())continue;
            p.connection.teleport(v.pos.x,v.pos.y,v.pos.z,v.yaw,v.pitch);
            if(v.camera.isAlive()&&!v.camera.isRemoved()&&v.camera.level()==level){
                p.setCamera(v.camera);
                if(v.camera instanceof Mob mob)CameraLink.snap(p,mob);
            }
            level.getChunkSource().move(p);
        }
        poses.clear();frozen.clear();viewers.clear();completed++;
    }
}
