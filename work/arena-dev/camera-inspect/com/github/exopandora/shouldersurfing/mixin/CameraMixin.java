/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.exopandora.shouldersurfing.api.client.Perspective
 *  com.github.exopandora.shouldersurfing.api.math.Vec2f
 *  com.github.exopandora.shouldersurfing.client.ShoulderSurfing
 *  com.github.exopandora.shouldersurfing.client.ShoulderSurfingCamera
 *  com.github.exopandora.shouldersurfing.mixinduck.CameraDuck
 *  net.minecraft.client.Camera
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.phys.Vec3
 *  org.joml.Quaternionf
 *  org.joml.Quaternionfc
 *  org.joml.Vector3f
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.github.exopandora.shouldersurfing.mixin;

import com.github.exopandora.shouldersurfing.api.client.Perspective;
import com.github.exopandora.shouldersurfing.api.math.Vec2f;
import com.github.exopandora.shouldersurfing.client.ShoulderSurfing;
import com.github.exopandora.shouldersurfing.client.ShoulderSurfingCamera;
import com.github.exopandora.shouldersurfing.mixinduck.CameraDuck;
import net.minecraft.client.Camera;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Camera.class})
abstract class CameraMixin
implements CameraDuck {
    @Shadow
    @Final
    private Vector3f f_90554_;
    @Shadow
    @Final
    private Vector3f f_90555_;
    @Shadow
    @Final
    private Vector3f f_90556_;
    @Shadow
    private float f_90557_;
    @Shadow
    private float f_90558_;
    @Unique
    private float zRot;
    @Shadow
    @Final
    private Quaternionf f_90559_;

    CameraMixin() {
    }

    @Shadow
    abstract void m_90568_(double var1, double var3, double var5);

    @Shadow
    abstract void m_90572_(float var1, float var2);

    @Inject(method={"setup"}, at={@At(value="HEAD")})
    private void setupRotations(CallbackInfo ci) {
        this.shouldersurfing$setZRot(0.0f);
    }

    @Inject(method={"setup"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/Camera;setPosition(DDD)V", shift=At.Shift.AFTER, ordinal=0)})
    private void setupRotations(BlockGetter level, Entity cameraEntity, boolean detached, boolean isMirrored, float partialTick, CallbackInfo ci) {
        LivingEntity livingEntity;
        if (!(Perspective.SHOULDER_SURFING != Perspective.current() || cameraEntity instanceof LivingEntity && (livingEntity = (LivingEntity)cameraEntity).m_5803_())) {
            Vec2f rotation = ShoulderSurfing.getInstance().getCamera().getRenderRotation();
            this.m_90572_(rotation.y(), rotation.x());
        }
    }

    @Redirect(method={"setup"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/Camera;move(DDD)V", ordinal=0))
    private void setupPosition(Camera cameraIn, double x, double y, double z, BlockGetter level, Entity cameraEntity, boolean detached, boolean isMirrored, float partialTick) {
        LivingEntity livingEntity;
        if (!(Perspective.SHOULDER_SURFING != Perspective.current() || cameraEntity instanceof LivingEntity && (livingEntity = (LivingEntity)cameraEntity).m_5803_())) {
            ShoulderSurfingCamera camera = ShoulderSurfing.getInstance().getCamera();
            camera.setup(cameraIn, level, partialTick, cameraEntity);
            Vec3 cameraOffset = camera.getRenderOffset();
            this.m_90568_(-cameraOffset.m_7094_(), cameraOffset.m_7098_(), cameraOffset.m_7096_());
            Vec2f sway = camera.calcSway(cameraEntity, partialTick);
            this.shouldersurfing$rotate(sway.x(), 0.0f, sway.y());
        } else {
            this.m_90568_(x, y, z);
        }
    }

    @Unique
    private void shouldersurfing$rotate(float xRot, float yRot, float zRot) {
        this.f_90557_ += xRot;
        this.f_90558_ += yRot;
        this.zRot += zRot;
        this.f_90559_.rotationYXZ(-this.f_90558_ * ((float)Math.PI / 180), this.f_90557_ * ((float)Math.PI / 180), this.zRot * ((float)Math.PI / 180));
        this.f_90554_.set(0.0f, 0.0f, 1.0f).rotate((Quaternionfc)this.f_90559_);
        this.f_90555_.set(0.0f, 1.0f, 0.0f).rotate((Quaternionfc)this.f_90559_);
        this.f_90556_.set(1.0f, 0.0f, 0.0f).rotate((Quaternionfc)this.f_90559_);
    }

    public float shouldersurfing$getZRot() {
        return this.zRot;
    }

    public void shouldersurfing$setZRot(float zRot) {
        this.zRot = zRot;
    }
}
