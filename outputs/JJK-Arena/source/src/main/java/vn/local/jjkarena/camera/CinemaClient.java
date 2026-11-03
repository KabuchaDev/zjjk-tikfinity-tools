package vn.local.jjkarena.camera;

import java.util.*;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.CameraType;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import vn.local.jjkarena.net.CelebrationLink;

/** Rendering-only poses keep Jujutsu Craft's skill state and combat animations intact. */
final class CinemaClient {
    private static CelebrationLink.Scene scene;
    private static long start;
    private static ClientLevel level;
    private static ArmorStand camera;
    private static CameraType previousType;
    private static Entity previousCamera;
    private static SimpleSoundInstance music;
    private static final Set<UUID> pushed=new HashSet<>();
    static boolean active(){return scene!=null;}
    static void register() {
        MinecraftForge.EVENT_BUS.addListener(CinemaClient::tick);
        MinecraftForge.EVENT_BUS.addListener(CinemaClient::angles);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST,CinemaClient::pre);
        MinecraftForge.EVENT_BUS.addListener(CinemaClient::post);
    }
    static void scene(CelebrationLink.Scene next) {
        Minecraft mc=Minecraft.getInstance();
        if(!next.active()){finish(false);return;}
        if(mc.level==null||mc.player==null||!mc.player.isSpectator()||!mc.level.dimension().location().toString().equals("jjkarena:battlefield"))return;
        finish(false);
        scene=next;start=System.nanoTime();level=mc.level;
        previousType=mc.options.getCameraType();previousCamera=mc.getCameraEntity();
        camera=new ArmorStand(EntityType.ARMOR_STAND,level);
        camera.moveTo(next.x(),next.y()-camera.getEyeHeight(),next.z(),next.yaw(),next.pitch());
        camera.xo=camera.xOld=camera.getX();camera.yo=camera.yOld=camera.getY();camera.zo=camera.zOld=camera.getZ();
        camera.yRotO=next.yaw();camera.xRotO=next.pitch();
        mc.options.setCameraType(CameraType.FIRST_PERSON);mc.setCameraEntity(camera);
        if(next.gift()) {
            if(music!=null)mc.getSoundManager().stop(music);
            music=SimpleSoundInstance.forUI(SoundEvent.createVariableRangeEvent(new ResourceLocation("jjkarenacamera","tet_gift")),1.0f);
            mc.getSoundManager().play(music);
        }
        com.mojang.logging.LogUtils.getLogger().info("[JJK Cinema] Started {} scene; cast={}",next.gift()?"gift":"follow",next.actors().size());
    }
    static void finish(boolean disconnect) {
        Minecraft mc=Minecraft.getInstance();
        if(scene!=null) {
            if(previousType!=null)mc.options.setCameraType(previousType);
            if(mc.getCameraEntity()==camera)mc.setCameraEntity(previousCamera!=null&&previousCamera.level()==mc.level&&previousCamera.isAlive()?previousCamera:mc.player);
            scene=null;camera=null;pushed.clear();
            com.mojang.logging.LogUtils.getLogger().info("[JJK Cinema] Camera restored");
        }
        if(disconnect&&music!=null){mc.getSoundManager().stop(music);music=null;}
    }
    private static void tick(TickEvent.ClientTickEvent event) {
        if(event.phase!=TickEvent.Phase.END||scene==null)return;
        Minecraft mc=Minecraft.getInstance();
        if(mc.level!=level||mc.player==null||!mc.player.isSpectator()){finish(true);return;}
        // Local fail-safe restores the camera even if the end packet is lost.
        if(System.nanoTime()-start>3_300_000_000L){finish(false);return;}
        mc.options.setCameraType(CameraType.FIRST_PERSON);mc.setCameraEntity(camera);
    }
    private static void angles(ViewportEvent.ComputeCameraAngles event) {
        if(scene!=null){event.setYaw(scene.yaw());event.setPitch(scene.pitch());event.setRoll(0);}
    }
    private static void pre(RenderLivingEvent.Pre<?,?> event) {
        if(scene==null)return;
        UUID id=event.getEntity().getUUID();
        CelebrationLink.Actor actor=scene.actors().stream().filter(a->a.uuid().equals(id)).findFirst().orElse(null);
        if(actor==null)return;
        var pose=event.getPoseStack();pose.pushPose();pushed.add(id);
        double t=Math.min(3,(System.nanoTime()-start)/1e9);
        if(!scene.gift()) {
            double bow=Math.sin(Math.PI*Math.min(1,Math.max(0,(t-.25)/2.5)));
            pose.translate(0,.85,0);pose.mulPose(Axis.XP.rotationDegrees((float)(32*bow)));pose.translate(0,-.85,0);
        } else if(scene.star().equals(id)) {
            int k=actor.style();double beat=t*(5.5+k*.23),ease=Math.min(1,t*5)*Math.min(1,(3-t)*5);
            pose.translate(Math.sin(beat)*(0.08+(k%4)*.065)*ease,Math.abs(Math.sin(beat))*(.15+(k%5)*.08)*ease,Math.cos(beat*.5)*.07*ease);
            pose.mulPose(Axis.YP.rotationDegrees((float)(Math.sin(beat*.65)*(12+k*4)*ease)));
            pose.mulPose(Axis.ZP.rotationDegrees((float)(Math.sin(beat)* (5+k%6*2)*ease)));
            pose.mulPose(Axis.XP.rotationDegrees((float)(Math.cos(beat*.8)*(k%3*5)*ease)));
        }
    }
    private static void post(RenderLivingEvent.Post<?,?> event) {
        if(pushed.remove(event.getEntity().getUUID()))event.getPoseStack().popPose();
    }
}
