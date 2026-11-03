package vn.local.jjkarena.camera;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.lwjgl.glfw.GLFW;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import vn.local.jjkarena.net.CameraLink;

@Mod("jjkarenacamera")
public final class ArenaCamera {
    public ArenaCamera() {
        vn.local.jjkarena.net.CelebrationLink.init(scene -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT,()->()->CinemaClient.scene(scene)));
        CameraLink.init(snap -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT,()->()->Client.snap(snap)),p -> {},
            state -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT,()->()->Client.state(state)));
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT,()->Client::register);
    }

    private static final class Client {
        private static final KeyMapping TOGGLE=new KeyMapping("key.jjkarenacamera.auto_follow",KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,GLFW.GLFW_KEY_LEFT_CONTROL,"key.categories.jjkarenacamera");
        private static boolean held;
        private static ClientLevel world;
        private static CameraLink.AutoState pending;
        private static int pendingTicks;
        static void register() {
            CinemaClient.register();
            FMLJavaModLoadingContext.get().getModEventBus().addListener(Client::keys);
            MinecraftForge.EVENT_BUS.addListener(Client::tick);
        }
        static void keys(RegisterKeyMappingsEvent event) {event.register(TOGGLE);}
        static boolean inArena(Minecraft mc) {
            return mc.player!=null&&mc.level!=null&&mc.player.isSpectator()
                &&mc.level.dimension().location().toString().equals("jjkarena:battlefield");
        }
        static void state(CameraLink.AutoState state) {
            Minecraft mc=Minecraft.getInstance();
            world=mc.level;pending=null;
            if(!inArena(mc))return;
            if(CinemaClient.active()){pending=state;pendingTicks=80;return;}
            if(!state.enabled()||state.targetId()<0) {
                mc.setCameraEntity(mc.player);return;
            }
            pending=state;pendingTicks=40;
            attach(mc);
        }
        private static void attach(Minecraft mc) {
            if(pending==null||mc.level==null||CinemaClient.active())return;
            if(!pending.enabled()||pending.targetId()<0){mc.setCameraEntity(mc.player);pending=null;return;}
            Entity target=mc.level.getEntity(pending.targetId());
            if(target!=null&&target.isAlive()&&target.getUUID().equals(pending.targetUuid())) {
                if(mc.getCameraEntity()!=target)mc.setCameraEntity(target);
                pending=null;
            }
        }
        static void tick(TickEvent.ClientTickEvent event) {
            if(event.phase!=TickEvent.Phase.END)return;
            Minecraft mc=Minecraft.getInstance();
            if(world!=mc.level){world=mc.level;pending=null;}
            boolean clicked=false;
            while(TOGGLE.consumeClick())clicked=true;
            if(clicked&&!held&&mc.screen==null&&inArena(mc)&&mc.getConnection()!=null) {
                if(!CameraLink.toggle(mc.getConnection().getConnection()))
                    mc.player.displayClientMessage(Component.literal("Server chưa có chế độ tự bám camera."),true);
            }
            held=TOGGLE.isDown();
            if(!inArena(mc)){pending=null;return;}
            if(CinemaClient.active())return;
            if(pending!=null) {
                attach(mc);
                if(--pendingTicks<=0)pending=null;
            }
        }
        static void snap(CameraLink.Snap snap) {
            Minecraft mc=Minecraft.getInstance();
            if(mc.player==null||mc.level==null||!mc.player.isSpectator()
                ||!mc.level.dimension().location().toString().equals("jjkarena:battlefield"))return;
            Entity target=mc.getCameraEntity();
            // Never reattach after the player exits spectating or changes target.
            if(target==null||target==mc.player||target.getId()!=snap.entityId()||!target.getUUID().equals(snap.uuid()))return;
            if(!Double.isFinite(snap.x())||!Double.isFinite(snap.y())||!Double.isFinite(snap.z()))return;
            // Some native skills produce invalid head/body angles. Do not let those
            // contaminate the render interpolation when applying a recovery snapshot.
            float yaw=Float.isFinite(snap.yaw())?snap.yaw():0;
            float pitch=Float.isFinite(snap.pitch())?snap.pitch():0;
            float head=Float.isFinite(snap.head())?snap.head():yaw;
            float body=Float.isFinite(snap.body())?snap.body():yaw;
            target.lerpTo(snap.x(),snap.y(),snap.z(),yaw,pitch,0,true);
            target.moveTo(snap.x(),snap.y(),snap.z(),yaw,pitch);
            target.xo=target.xOld=snap.x();target.yo=target.yOld=snap.y();target.zo=target.zOld=snap.z();
            target.yRotO=yaw;target.xRotO=pitch;
            target.setDeltaMovement(Vec3.ZERO);
            if(target instanceof LivingEntity living) {
                living.lerpHeadTo(head,0);
                living.yHeadRot=living.yHeadRotO=head;
                living.yBodyRot=living.yBodyRotO=body;
            }
            com.mojang.logging.LogUtils.getLogger().info("[JJK Camera] Spectator snapped to entity {} at {}, {}, {}",snap.entityId(),snap.x(),snap.y(),snap.z());
            // Do not modify the packet-position codec: the vanilla tracker still owns
            // its delta baseline. Normal movement and camera response stay unchanged.
        }
    }
}
