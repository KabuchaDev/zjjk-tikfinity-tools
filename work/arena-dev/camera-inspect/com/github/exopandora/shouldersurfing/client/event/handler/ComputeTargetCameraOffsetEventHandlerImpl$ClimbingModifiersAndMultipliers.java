/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.exopandora.shouldersurfing.api.client.event.ComputeTargetCameraOffsetEvent
 *  com.github.exopandora.shouldersurfing.config.Config
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.ClientPacketListener
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.phys.Vec3
 */
package com.github.exopandora.shouldersurfing.client.event.handler;

import com.github.exopandora.shouldersurfing.api.client.event.ComputeTargetCameraOffsetEvent;
import com.github.exopandora.shouldersurfing.client.event.handler.ComputeTargetCameraOffsetEventHandlerImpl;
import com.github.exopandora.shouldersurfing.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public static enum ComputeTargetCameraOffsetEventHandlerImpl.ClimbingModifiersAndMultipliers implements ComputeTargetCameraOffsetEventHandlerImpl.AbstractModifiersAndMultipliers
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
