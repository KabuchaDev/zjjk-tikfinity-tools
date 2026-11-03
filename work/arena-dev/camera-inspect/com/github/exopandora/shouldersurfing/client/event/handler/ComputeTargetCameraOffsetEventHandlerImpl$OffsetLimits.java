/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.exopandora.shouldersurfing.api.client.event.ComputeTargetCameraOffsetEvent
 *  com.github.exopandora.shouldersurfing.api.client.event.handler.ComputeTargetCameraOffsetEventHandler
 *  com.github.exopandora.shouldersurfing.config.CameraConfig
 *  com.github.exopandora.shouldersurfing.config.Config
 *  net.minecraft.util.Mth
 *  net.minecraft.world.phys.Vec3
 */
package com.github.exopandora.shouldersurfing.client.event.handler;

import com.github.exopandora.shouldersurfing.api.client.event.ComputeTargetCameraOffsetEvent;
import com.github.exopandora.shouldersurfing.api.client.event.handler.ComputeTargetCameraOffsetEventHandler;
import com.github.exopandora.shouldersurfing.config.CameraConfig;
import com.github.exopandora.shouldersurfing.config.Config;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public static enum ComputeTargetCameraOffsetEventHandlerImpl.OffsetLimits implements ComputeTargetCameraOffsetEventHandler
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
