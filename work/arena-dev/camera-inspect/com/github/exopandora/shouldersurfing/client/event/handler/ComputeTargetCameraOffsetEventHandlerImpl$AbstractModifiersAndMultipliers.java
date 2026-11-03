/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.exopandora.shouldersurfing.api.client.event.ComputeTargetCameraOffsetEvent
 *  com.github.exopandora.shouldersurfing.api.client.event.handler.ComputeTargetCameraOffsetEventHandler
 *  net.minecraft.world.phys.Vec3
 */
package com.github.exopandora.shouldersurfing.client.event.handler;

import com.github.exopandora.shouldersurfing.api.client.event.ComputeTargetCameraOffsetEvent;
import com.github.exopandora.shouldersurfing.api.client.event.handler.ComputeTargetCameraOffsetEventHandler;
import net.minecraft.world.phys.Vec3;

private static interface ComputeTargetCameraOffsetEventHandlerImpl.AbstractModifiersAndMultipliers
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
