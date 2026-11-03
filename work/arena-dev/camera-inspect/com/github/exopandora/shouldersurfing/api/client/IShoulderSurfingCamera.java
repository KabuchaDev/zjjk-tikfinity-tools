/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.exopandora.shouldersurfing.api.math.Vec2f
 *  net.minecraft.world.phys.Vec3
 */
package com.github.exopandora.shouldersurfing.api.client;

import com.github.exopandora.shouldersurfing.api.math.Vec2f;
import net.minecraft.world.phys.Vec3;

public interface IShoulderSurfingCamera {
    public double getCameraDistance();

    public Vec3 getOffset();

    public Vec3 getRenderOffset();

    public Vec3 getTargetOffset();

    public float getXRot();

    public void setXRot(float var1);

    public float getYRot();

    public void setYRot(float var1);

    public Vec2f getRenderRotation();
}
