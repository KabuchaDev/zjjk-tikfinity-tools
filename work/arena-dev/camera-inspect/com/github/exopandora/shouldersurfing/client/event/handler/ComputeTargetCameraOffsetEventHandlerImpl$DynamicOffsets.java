/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.exopandora.shouldersurfing.api.client.event.ComputeTargetCameraOffsetEvent
 *  com.github.exopandora.shouldersurfing.api.client.event.handler.ComputeTargetCameraOffsetEventHandler
 *  com.github.exopandora.shouldersurfing.config.Config
 *  net.minecraft.client.Camera
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec3
 */
package com.github.exopandora.shouldersurfing.client.event.handler;

import com.github.exopandora.shouldersurfing.api.client.event.ComputeTargetCameraOffsetEvent;
import com.github.exopandora.shouldersurfing.api.client.event.handler.ComputeTargetCameraOffsetEventHandler;
import com.github.exopandora.shouldersurfing.config.Config;
import net.minecraft.client.Camera;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public static enum ComputeTargetCameraOffsetEventHandlerImpl.DynamicOffsets implements ComputeTargetCameraOffsetEventHandler
{
    INSTANCE;


    public void handle(ComputeTargetCameraOffsetEvent event) {
        if (!event.getCameraEntity().m_5833_() && Config.CLIENT.getCameraConfig().isOffsetDynamic()) {
            event.setResult(ComputeTargetCameraOffsetEventHandlerImpl.DynamicOffsets.calcDynamicOffsets(event.getCamera(), event.getCameraEntity(), event.getLevel(), event.getResult()));
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
