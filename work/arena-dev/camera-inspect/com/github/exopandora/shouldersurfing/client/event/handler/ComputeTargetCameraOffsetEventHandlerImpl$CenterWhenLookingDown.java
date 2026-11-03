/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.exopandora.shouldersurfing.api.client.event.ComputeTargetCameraOffsetEvent
 *  com.github.exopandora.shouldersurfing.api.client.event.handler.ComputeTargetCameraOffsetEventHandler
 *  com.github.exopandora.shouldersurfing.config.Config
 *  net.minecraft.client.Camera
 *  net.minecraft.world.phys.Vec3
 *  org.joml.Vector3f
 *  org.joml.Vector3fc
 */
package com.github.exopandora.shouldersurfing.client.event.handler;

import com.github.exopandora.shouldersurfing.api.client.event.ComputeTargetCameraOffsetEvent;
import com.github.exopandora.shouldersurfing.api.client.event.handler.ComputeTargetCameraOffsetEventHandler;
import com.github.exopandora.shouldersurfing.config.Config;
import net.minecraft.client.Camera;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;
import org.joml.Vector3fc;

public static enum ComputeTargetCameraOffsetEventHandlerImpl.CenterWhenLookingDown implements ComputeTargetCameraOffsetEventHandler
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
