/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.exopandora.shouldersurfing.api.client.event.ComputeTargetCameraOffsetEvent
 *  com.github.exopandora.shouldersurfing.config.Config
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.phys.Vec3
 */
package com.github.exopandora.shouldersurfing.client.event.handler;

import com.github.exopandora.shouldersurfing.api.client.event.ComputeTargetCameraOffsetEvent;
import com.github.exopandora.shouldersurfing.client.event.handler.ComputeTargetCameraOffsetEventHandlerImpl;
import com.github.exopandora.shouldersurfing.config.Config;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public static enum ComputeTargetCameraOffsetEventHandlerImpl.FallFlyingModifiersAndMultipliers implements ComputeTargetCameraOffsetEventHandlerImpl.AbstractModifiersAndMultipliers
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
