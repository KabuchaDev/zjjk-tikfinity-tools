/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.exopandora.shouldersurfing.api.client.IShoulderSurfing
 *  com.github.exopandora.shouldersurfing.api.client.event.ComputeTargetCameraOffsetEvent
 *  com.github.exopandora.shouldersurfing.api.client.event.handler.ComputeTargetCameraOffsetEventHandler
 *  com.github.exopandora.shouldersurfing.config.CameraConfig
 *  com.github.exopandora.shouldersurfing.config.Config
 *  net.minecraft.client.Camera
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.ClientPacketListener
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec3
 *  org.joml.Vector3f
 *  org.joml.Vector3fc
 */
package com.github.exopandora.shouldersurfing.client.event.handler;

import com.github.exopandora.shouldersurfing.api.client.IShoulderSurfing;
import com.github.exopandora.shouldersurfing.api.client.event.ComputeTargetCameraOffsetEvent;
import com.github.exopandora.shouldersurfing.api.client.event.handler.ComputeTargetCameraOffsetEventHandler;
import com.github.exopandora.shouldersurfing.config.CameraConfig;
import com.github.exopandora.shouldersurfing.config.Config;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;
import org.joml.Vector3fc;

public class ComputeTargetCameraOffsetEventHandlerImpl {

    public static enum OffsetLimits implements ComputeTargetCameraOffsetEventHandler
    {
        INSTANCE;


        public void handle(ComputeTargetCameraOffsetEvent event) {
            CameraConfig cameraConfig = Config.CLIENT.getCameraConfig();
            double targetOffsetX = cameraConfig.isOffsetXUnlimited() ? event.getResult().m_7096_() : Mth.m_14008_((double)event.getResult().m_7096_(), (double)cameraConfig.getMinOffsetX(), (double)cameraConfig.getMaxOffsetX());
            double targetOffsetY = cameraConfig.isOffsetYUnlimited() ? event.getResult().m_7098_() : Mth.m_14008_((double)event.getResult().m_7098_(), (double)cameraConfig.getMinOffsetY(), (double)cameraConfig.getMaxOffsetY());
            double targetOffsetZ = cameraConfig.isOffsetZUnlimited() ? event.getResult().m_7094_() : Mth.m_14008_((double)event.getResult().m_7094_(), (double)cameraConfig.getMinOffsetZ(), (double)cameraConfig.getMaxOffsetZ());
            event.setResult(new Vec3(targetOffsetX, targetOffsetY, targetOffsetZ));
        }
    }

    public static enum DynamicOffsets implements ComputeTargetCameraOffsetEventHandler
    {
        INSTANCE;


        public void handle(ComputeTargetCameraOffsetEvent event) {
            if (!event.getCameraEntity().m_5833_() && Config.CLIENT.getCameraConfig().isOffsetDynamic()) {
                event.setResult(DynamicOffsets.calcDynamicOffsets(event.getCamera(), event.getCameraEntity(), event.getLevel(), event.getResult()));
            }
        }

        private static Vec3 calcDynamicOffsets(Camera camera, Entity cameraEntity, BlockGetter level, Vec3 targetOffset) {
            Vec3 lookVector = new Vec3(camera.m_253058_());
            Vec3 worldXYOffset = new Vec3(camera.m_253028_()).m_82490_(targetOffset.m_7098_()).m_82549_(new Vec3(camera.m_252775_()).m_82490_(targetOffset.m_7096_()));
            Vec3 worldOffset = worldXYOffset.m_82549_(lookVector.m_82490_(-targetOffset.m_7094_()));
            double offsetXAbs = Math.abs(targetOffset.m_7096_());
            double offsetYAbs = Math.abs(targetOffset.m_7098_());
            double offsetZAbs = Math.abs(targetOffset.m_7094_());
            double targetX = offsetXAbs;
            double targetY = offsetYAbs;
            double clearance = (double)cameraEntity.m_20205_() / 3.0;
            Vec3 cameraPosition = camera.m_90583_();
            for (double dz = 0.0; dz <= offsetZAbs; dz += 0.03125) {
                double newTargetY;
                Vec3 endPos;
                double scale = dz / offsetZAbs;
                Vec3 startPos = cameraPosition.m_82549_(worldOffset.m_82490_(scale));
                ClipContext context = new ClipContext(startPos, endPos = cameraPosition.m_82549_(worldXYOffset).m_82549_(lookVector.m_82490_(-dz)), ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, cameraEntity);
                BlockHitResult hitResult = level.m_45547_(context);
                if (hitResult.m_6662_() == HitResult.Type.MISS) continue;
                double distance = hitResult.m_82450_().m_82554_(startPos);
                double newTargetX = Math.max(distance + offsetXAbs * scale - clearance, 0.0);
                if (newTargetX < targetX) {
                    targetX = newTargetX;
                }
                if (!((newTargetY = Math.max(distance + offsetYAbs * scale - clearance, 0.0)) < targetY)) continue;
                targetY = newTargetY;
            }
            double targetXOffset = Math.signum(targetOffset.m_7096_()) * targetX;
            double targetYOffset = Math.signum(targetOffset.m_7098_()) * targetY;
            return new Vec3(targetXOffset, targetYOffset, targetOffset.m_7094_());
        }
    }

    public static enum CenterWhenLookingDown implements ComputeTargetCameraOffsetEventHandler
    {
        INSTANCE;

        private static final Vector3f VECTOR_NEGATIVE_Y;

        public void handle(ComputeTargetCameraOffsetEvent event) {
            if (!event.getCameraEntity().m_5833_() && this.isCameraLookingDown(event.getCamera())) {
                event.setResult(new Vec3(0.0, 0.0, event.getResult().m_7094_()));
            }
        }

        private boolean isCameraLookingDown(Camera camera) {
            return (double)camera.m_253058_().angle((Vector3fc)VECTOR_NEGATIVE_Y) < Config.CLIENT.getCameraConfig().getCenterCameraWhenLookingDownAngle() * 0.01745329238474369;
        }

        static {
            VECTOR_NEGATIVE_Y = new Vector3f(0.0f, -1.0f, 0.0f);
        }
    }

    public static enum ClimbingModifiersAndMultipliers implements AbstractModifiersAndMultipliers
    {
        INSTANCE;


        @Override
        public boolean shouldApply(ComputeTargetCameraOffsetEvent event) {
            LivingEntity living;
            Entity entity;
            ClientPacketListener connection = Minecraft.m_91087_().m_91403_();
            if (connection == null || connection.m_105147_() == null) {
                return false;
            }
            return !event.getCameraEntity().m_5833_() && (entity = event.getCameraEntity()) instanceof LivingEntity && (living = (LivingEntity)entity).m_6147_();
        }

        @Override
        public Vec3 getModifiers() {
            return Config.CLIENT.getCameraConfig().getClimbingOffsetModifiers();
        }

        @Override
        public Vec3 getMultipliers() {
            return Config.CLIENT.getCameraConfig().getClimbingMultipliers();
        }
    }

    public static enum FallFlyingModifiersAndMultipliers implements AbstractModifiersAndMultipliers
    {
        INSTANCE;


        @Override
        public boolean shouldApply(ComputeTargetCameraOffsetEvent event) {
            LivingEntity living;
            Entity entity = event.getCameraEntity();
            return entity instanceof LivingEntity && (living = (LivingEntity)entity).m_21255_();
        }

        @Override
        public Vec3 getModifiers() {
            return Config.CLIENT.getCameraConfig().getFallFlyingOffsetModifiers();
        }

        @Override
        public Vec3 getMultipliers() {
            return Config.CLIENT.getCameraConfig().getFallFlyingMultipliers();
        }
    }

    public static enum AimingModifiersAndMultipliers implements AbstractModifiersAndMultipliers
    {
        INSTANCE;


        @Override
        public boolean shouldApply(ComputeTargetCameraOffsetEvent event) {
            return IShoulderSurfing.getInstance().isAiming();
        }

        @Override
        public Vec3 getModifiers() {
            return Config.CLIENT.getCameraConfig().getAimingOffsetModifiers();
        }

        @Override
        public Vec3 getMultipliers() {
            return Config.CLIENT.getCameraConfig().getAimingOffsetMultipliers();
        }
    }

    public static enum SprintingModifiersAndMultipliers implements AbstractModifiersAndMultipliers
    {
        INSTANCE;


        @Override
        public boolean shouldApply(ComputeTargetCameraOffsetEvent event) {
            return event.getCameraEntity().m_20142_();
        }

        @Override
        public Vec3 getModifiers() {
            return Config.CLIENT.getCameraConfig().getSprintOffsetModifiers();
        }

        @Override
        public Vec3 getMultipliers() {
            return Config.CLIENT.getCameraConfig().getSprintOffsetMultipliers();
        }
    }

    public static enum PassengerModifiersAndMultipliers implements AbstractModifiersAndMultipliers
    {
        INSTANCE;


        @Override
        public boolean shouldApply(ComputeTargetCameraOffsetEvent event) {
            return event.getCameraEntity().m_20159_();
        }

        @Override
        public Vec3 getModifiers() {
            return Config.CLIENT.getCameraConfig().getPassengerOffsetModifiers();
        }

        @Override
        public Vec3 getMultipliers() {
            return Config.CLIENT.getCameraConfig().getPassengerOffsetMultipliers();
        }
    }

    private static interface AbstractModifiersAndMultipliers
    extends ComputeTargetCameraOffsetEventHandler {
        default public void handle(ComputeTargetCameraOffsetEvent event) {
            if (this.shouldApply(event)) {
                Vec3 result = event.getResult().m_82549_(event.getDefaultOffset().m_82559_(this.getMultipliers()).m_82546_(event.getDefaultOffset())).m_82549_(this.getModifiers());
                event.setResult(result);
            }
        }

        public boolean shouldApply(ComputeTargetCameraOffsetEvent var1);

        public Vec3 getModifiers();

        public Vec3 getMultipliers();
    }
}
