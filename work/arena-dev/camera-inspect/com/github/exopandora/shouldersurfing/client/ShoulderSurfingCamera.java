/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.exopandora.shouldersurfing.api.client.CrosshairType
 *  com.github.exopandora.shouldersurfing.api.client.IShoulderSurfingCamera
 *  com.github.exopandora.shouldersurfing.api.client.world.phys.PickVector
 *  com.github.exopandora.shouldersurfing.api.math.Vec2f
 *  com.github.exopandora.shouldersurfing.api.util.EntityHelper
 *  com.github.exopandora.shouldersurfing.client.EventHooks
 *  com.github.exopandora.shouldersurfing.client.ShoulderSurfing
 *  com.github.exopandora.shouldersurfing.config.CameraConfig
 *  com.github.exopandora.shouldersurfing.config.Config
 *  com.github.exopandora.shouldersurfing.config.PlayerConfig
 *  net.minecraft.client.Camera
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.player.LocalPlayer
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
 *  org.jetbrains.annotations.Nullable
 */
package com.github.exopandora.shouldersurfing.client;

import com.github.exopandora.shouldersurfing.api.client.CrosshairType;
import com.github.exopandora.shouldersurfing.api.client.IShoulderSurfingCamera;
import com.github.exopandora.shouldersurfing.api.client.world.phys.PickVector;
import com.github.exopandora.shouldersurfing.api.math.Vec2f;
import com.github.exopandora.shouldersurfing.api.util.EntityHelper;
import com.github.exopandora.shouldersurfing.client.EventHooks;
import com.github.exopandora.shouldersurfing.client.ShoulderSurfing;
import com.github.exopandora.shouldersurfing.config.CameraConfig;
import com.github.exopandora.shouldersurfing.config.Config;
import com.github.exopandora.shouldersurfing.config.PlayerConfig;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class ShoulderSurfingCamera
implements IShoulderSurfingCamera {
    private final ShoulderSurfing instance;
    private Vec3 offset;
    private Vec3 offsetO;
    private Vec3 renderOffset;
    private Vec3 targetOffset;
    private Vec3 deltaMovementO;
    private double cameraDistance;
    private double maxCameraDistance;
    private double maxCameraDistanceO;
    private Vec2f rotation;
    private Vec2f rotationO;
    private Vec2f rotationOffset;
    private Vec2f rotationOffsetO;
    private Vec2f renderRotation;
    private float lastMovedYRot;
    private boolean initialized;
    private int turnCameraWithPlayerDelay;
    private float turnCameraWithPlayerEaseIn;
    private float turnCameraWithPlayerEaseInO;

    public ShoulderSurfingCamera(ShoulderSurfing instance) {
        this.instance = instance;
        this.init();
    }

    private void init() {
        this.offset = Config.CLIENT.getCameraConfig().getOffset();
        Entity cameraEntity = Minecraft.m_91087_().m_91288_();
        if (cameraEntity != null) {
            this.rotation = new Vec2f(cameraEntity.m_146909_(), cameraEntity.m_146908_());
            this.deltaMovementO = EntityHelper.getDeltaMovementWithoutGravity((Entity)cameraEntity);
        } else {
            this.rotation = new Vec2f(0.0f, -180.0f);
            this.deltaMovementO = Vec3.f_82478_;
        }
        this.rotationO = this.rotation;
        this.offsetO = this.offset;
        this.renderOffset = this.offset;
        this.targetOffset = this.offset;
        this.maxCameraDistanceO = this.maxCameraDistance = this.offset.m_82553_();
        this.renderRotation = this.rotation;
        this.rotationOffset = Vec2f.ZERO;
        this.rotationOffsetO = Vec2f.ZERO;
        this.lastMovedYRot = this.rotation.y();
        this.turnCameraWithPlayerDelay = 0;
        this.turnCameraWithPlayerEaseIn = 1.0f;
        this.turnCameraWithPlayerEaseInO = 1.0f;
        this.initialized = true;
    }

    public void tick() {
        if (!this.initialized) {
            this.init();
        }
        CameraConfig cameraConfig = Config.CLIENT.getCameraConfig();
        double cameraTransitionSpeedMultiplier = cameraConfig.getCameraTransitionSpeedMultiplier();
        this.rotationO = this.rotation;
        this.rotationOffsetO = this.rotationOffset;
        this.offsetO = this.offset;
        this.offset = this.offsetO.m_165921_(this.targetOffset, cameraTransitionSpeedMultiplier);
        this.maxCameraDistanceO = this.maxCameraDistance;
        this.maxCameraDistance += (this.offset.m_82553_() - this.maxCameraDistance) * cameraTransitionSpeedMultiplier;
        Entity cameraEntity = Minecraft.m_91087_().m_91288_();
        if (this.shouldResetCameraTurningWithPlayerDelay()) {
            this.turnCameraWithPlayerDelay = cameraConfig.getCameraTurningWithPlayerDelay();
            this.turnCameraWithPlayerEaseIn = 1.0f;
            this.turnCameraWithPlayerEaseInO = 1.0f;
        } else if (this.turnCameraWithPlayerDelay == 0) {
            this.turnCameraWithPlayerEaseInO = this.turnCameraWithPlayerEaseIn;
            this.turnCameraWithPlayerEaseIn *= 1.0f - (float)cameraConfig.getCameraTransitionSpeedMultiplier();
        } else if (this.turnCameraWithPlayerDelay > 0) {
            --this.turnCameraWithPlayerDelay;
        }
        this.rotation = this.applyPassengerRotations(this.rotation, cameraEntity, 1.0f);
        if (cameraEntity != null) {
            this.deltaMovementO = EntityHelper.getDeltaMovementWithoutGravity((Entity)cameraEntity);
        }
        if (!this.instance.isFreeLooking()) {
            this.rotationOffset = this.rotationOffset.scale(0.5f);
        }
    }

    public void renderTick(Entity cameraEntity, float partialTick) {
        if (!this.instance.isShoulderSurfing()) {
            return;
        }
        if (this.isCameraTurningWithPlayer()) {
            float easeIn = 1.0f - Mth.m_14179_((float)partialTick, (float)this.turnCameraWithPlayerEaseInO, (float)this.turnCameraWithPlayerEaseIn);
            float f = partialTick * (float)Config.CLIENT.getCameraConfig().getCameraTransitionSpeedMultiplier() * easeIn;
            float dy = Mth.m_14118_((float)this.rotation.y(), (float)EntityHelper.getLerpedXRot((Entity)cameraEntity, (float)partialTick));
            this.rotation = this.rotationO.add(new Vec2f(0.0f, dy).scale(f));
        }
        if (!this.instance.isCameraDecoupled() && EntityHelper.isPlayerSpectatingEntity() && cameraEntity instanceof LivingEntity) {
            LivingEntity living = (LivingEntity)cameraEntity;
            this.renderRotation = new Vec2f(living.m_5686_(partialTick), living.m_5675_(partialTick));
        } else {
            Vec2f rotationOffset = this.rotationOffsetO.rotLerp(this.rotationOffset, partialTick);
            Vec2f rotation = this.rotation.add(rotationOffset).clampX(-90.0f, 90.0f);
            this.renderRotation = this.applyPassengerRotations(rotation, cameraEntity, partialTick);
        }
    }

    public void setup(Camera camera, BlockGetter level, float partialTick, Entity cameraEntity) {
        Vec3 defaultOffset = Config.CLIENT.getCameraConfig().getOffset();
        this.targetOffset = EventHooks.getTargetOffset((Vec3)defaultOffset, (Camera)camera, (Entity)cameraEntity, (BlockGetter)level);
        Vec3 drag = this.calcCameraDrag(camera, cameraEntity, partialTick);
        Vec3 lerpedOffset = this.offsetO.m_165921_(this.offset, (double)partialTick).m_82549_(drag);
        if (cameraEntity.m_5833_()) {
            this.cameraDistance = lerpedOffset.m_82553_();
            this.renderOffset = lerpedOffset;
        } else {
            double targetCameraDistance = ShoulderSurfingCamera.maxZoom(camera, cameraEntity, level, lerpedOffset, partialTick);
            if (targetCameraDistance < this.maxCameraDistance) {
                this.maxCameraDistance = targetCameraDistance;
            }
            double lerpedMaxDistance = Mth.m_14139_((double)partialTick, (double)this.maxCameraDistanceO, (double)this.maxCameraDistance);
            this.cameraDistance = Math.min(targetCameraDistance, lerpedMaxDistance);
            this.renderOffset = lerpedOffset.m_82541_().m_82490_(this.cameraDistance);
        }
    }

    private Vec2f applyPassengerRotations(Vec2f rotation, @Nullable Entity cameraEntity, float partialTick) {
        Entity vehicle;
        if (this.instance.isCameraDecoupled()) {
            if (EntityHelper.isPlayerSpectatingEntity() && cameraEntity instanceof LivingEntity) {
                LivingEntity living = (LivingEntity)cameraEntity;
                float dx = living.m_146909_() - living.f_19860_;
                float dy = living.m_6080_() - living.f_20886_;
                return rotation.add(new Vec2f(dx, dy).scale(partialTick));
            }
        } else if (ShoulderSurfingCamera.isCameraTurningWithVehicle(cameraEntity) && (vehicle = cameraEntity.m_20202_()) != null) {
            return rotation.add(0.0f, (vehicle.m_146908_() - vehicle.f_19859_) * partialTick);
        }
        return rotation;
    }

    private static double maxZoom(Camera camera, Entity cameraEntity, BlockGetter level, Vec3 cameraOffset, float partialTick) {
        double distance = cameraOffset.m_82553_();
        Vec3 worldOffset = new Vec3(camera.m_253028_()).m_82490_(cameraOffset.m_7098_()).m_82549_(new Vec3(camera.m_252775_()).m_82490_(cameraOffset.m_7096_())).m_82549_(new Vec3(camera.m_253058_()).m_82490_(-cameraOffset.m_7094_()));
        Vec3 eyePosition = cameraEntity.m_20299_(partialTick);
        for (int i = 0; i < 8; ++i) {
            double newDistance;
            Vec3 toOffset;
            Vec3 to;
            Vec3 offset = new Vec3((double)(i & 1), (double)(i >> 1 & 1), (double)(i >> 2 & 1)).m_82490_(2.0).m_82492_(1.0, 1.0, 1.0);
            Vec3 fromOffset = offset.m_82490_((double)Mth.m_14036_((float)(cameraEntity.m_20205_() / 2.0f / Mth.m_14116_((float)2.0f)), (float)0.0f, (float)0.15f)).m_82496_(-camera.m_90589_() * ((float)Math.PI / 180)).m_82524_(-camera.m_90590_() * ((float)Math.PI / 180));
            Vec3 from = eyePosition.m_82549_(fromOffset);
            ClipContext context = new ClipContext(from, to = eyePosition.m_82549_(toOffset = offset.m_82490_(0.15).m_82496_(-camera.m_90589_() * ((float)Math.PI / 180)).m_82524_(-camera.m_90590_() * ((float)Math.PI / 180))).m_82549_(worldOffset), ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, cameraEntity);
            BlockHitResult hitResult = level.m_45547_(context);
            if (hitResult.m_6662_() == HitResult.Type.MISS || !((newDistance = hitResult.m_82450_().m_82554_(eyePosition)) < distance)) continue;
            distance = newDistance;
        }
        return distance;
    }

    private Vec3 calcCameraDrag(Camera cameraIn, Entity cameraEntity, float partialTick) {
        Vec3 deltaMovement = EntityHelper.getDeltaMovementWithoutGravity((Entity)cameraEntity);
        Vec3 deltaMovementLerped = this.deltaMovementO.m_165921_(deltaMovement, (double)partialTick).m_82559_(Config.CLIENT.getCameraConfig().getCameraDragMultipliers()).m_82524_(cameraIn.m_90590_() * ((float)Math.PI / 180)).m_82496_(cameraIn.m_90589_() * ((float)Math.PI / 180));
        return new Vec3(-deltaMovementLerped.f_82479_, -deltaMovementLerped.f_82480_, deltaMovementLerped.f_82481_);
    }

    public Vec2f calcSway(Entity cameraEntity, float partialTick) {
        Vec3 deltaMovement = EntityHelper.getDeltaMovementWithoutGravity((Entity)cameraEntity);
        Vec3 deltaMovementLerped = this.deltaMovementO.m_165921_(deltaMovement, (double)partialTick).m_82524_(this.getYRot() * ((float)Math.PI / 180)).m_82496_(this.getXRot() * ((float)Math.PI / 180));
        CameraConfig cameraConfig = Config.CLIENT.getCameraConfig();
        double maxVelocityX = cameraConfig.getCameraSwayXMaxVelocity() / 20.0;
        double maxVelocityZ = cameraConfig.getCameraSwayZMaxVelocity() / 20.0;
        double maxAngleX = cameraConfig.getCameraSwayXMaxAngle();
        double maxAngleZ = cameraConfig.getCameraSwayZMaxAngle();
        double swayX = Math.min(Math.abs(deltaMovementLerped.f_82480_), maxVelocityX) / maxVelocityX * maxAngleX * Math.signum(deltaMovementLerped.f_82480_);
        double swayZ = Math.min(Math.abs(deltaMovementLerped.f_82479_), maxVelocityZ) / maxVelocityZ * maxAngleZ * Math.signum(deltaMovementLerped.f_82479_);
        return new Vec2f((float)swayX, (float)swayZ);
    }

    public boolean turn(LocalPlayer player, double yRot, double xRot) {
        boolean isMoving;
        if (!this.instance.isShoulderSurfing()) {
            return false;
        }
        if (yRot != 0.0 || xRot != 0.0 || EntityHelper.isPlayerSpectatingEntity()) {
            this.turnCameraWithPlayerDelay = Config.CLIENT.getCameraConfig().getCameraTurningWithPlayerDelay();
            this.turnCameraWithPlayerEaseIn = 1.0f;
            this.turnCameraWithPlayerEaseInO = 1.0f;
        }
        Vec2f dRot = new Vec2f((float)xRot, (float)yRot);
        Vec2f dRotScaled = dRot.scale(0.15f);
        if (this.instance.isFreeLooking()) {
            this.rotationOffsetO = this.rotationOffset = this.rotationOffset.add(dRotScaled).clampX(-90.0f, 90.0f);
            return true;
        }
        Vec2f cameraRot = this.rotation.add(dRotScaled).clampX(-90.0f, 90.0f);
        this.rotation = EventHooks.setupCameraRotation((LocalPlayer)player, (Vec2f)cameraRot, (Vec2f)this.rotation, (Vec2f)dRot, (Vec2f)dRotScaled);
        boolean bl = isMoving = player.f_108618_.f_108566_ != 0.0f || player.f_108618_.f_108567_ != 0.0f || player.m_21255_();
        if (this.instance.isCameraDecoupled()) {
            if (!this.instance.isLookFollowingCrosshairTarget()) {
                this.turnPlayerWithCamera(player, dRotScaled, isMoving);
            }
            if (isMoving) {
                this.lastMovedYRot = player.m_146908_();
            }
        }
        return this.instance.isCameraDecoupled();
    }

    private void turnPlayerWithCamera(LocalPlayer player, Vec2f scaledRot, boolean isMoving) {
        boolean isPickingFromPlayerWithDynamicCrosshair;
        PlayerConfig playerConfig = Config.CLIENT.getPlayerConfig();
        boolean bl = isPickingFromPlayerWithDynamicCrosshair = Config.CLIENT.getObjectPickerConfig().getPickVector() == PickVector.PLAYER && Config.CLIENT.getCrosshairConfig().getCrosshairType() == CrosshairType.DYNAMIC;
        if (playerConfig.isPlayerXRotTurningWithCamera() || isPickingFromPlayerWithDynamicCrosshair) {
            player.m_146926_(this.rotation.x());
            player.f_19860_ += Mth.m_14118_((float)this.rotation.x(), (float)this.rotation.x());
        }
        if ((playerConfig.isPlayerYRotTurningWithCamera() || isPickingFromPlayerWithDynamicCrosshair) && !isMoving) {
            float maxFollowAngle = (float)playerConfig.getPlayerYRotTurnAngleLimit();
            float playerYRot = Mth.m_14148_((float)this.lastMovedYRot, (float)(player.m_146908_() + scaledRot.y()), (float)maxFollowAngle);
            player.f_19859_ = player.m_146908_();
            player.m_146922_(playerYRot);
        }
    }

    private boolean shouldResetCameraTurningWithPlayerDelay() {
        if (this.instance.isFreeLooking()) {
            return true;
        }
        Minecraft minecraft = Minecraft.m_91087_();
        if (minecraft.f_91074_ != null && minecraft.f_91074_.m_150108_()) {
            return true;
        }
        if (minecraft.f_91080_ != null) {
            return true;
        }
        if (EntityHelper.isPlayerSpectatingEntity()) {
            return true;
        }
        return this.instance.isLookFollowingCrosshairTarget();
    }

    private boolean isCameraTurningWithPlayer() {
        return this.instance.isCameraDecoupled() && Config.CLIENT.getCameraConfig().isCameraTurningWithPlayer() && this.turnCameraWithPlayerDelay == 0;
    }

    private static boolean isCameraTurningWithVehicle(@Nullable Entity entity) {
        if (!(entity instanceof LivingEntity)) {
            return false;
        }
        Entity vehicle = entity.m_20202_();
        if (vehicle == null) {
            return false;
        }
        return EventHooks.isRidingBoat((LivingEntity)((LivingEntity)entity), (Entity)vehicle);
    }

    public void resetState() {
        this.initialized = false;
    }

    public double getCameraDistance() {
        return this.cameraDistance;
    }

    public Vec3 getOffset() {
        return this.offset;
    }

    public Vec3 getRenderOffset() {
        return this.renderOffset;
    }

    public Vec3 getTargetOffset() {
        return this.targetOffset;
    }

    public float getXRot() {
        return this.rotation.x() + this.rotationOffset.x();
    }

    public void setXRot(float xRot) {
        this.rotation = new Vec2f(xRot, this.rotation.y());
        this.rotationOffset = new Vec2f(0.0f, this.rotationOffset.y());
        this.rotationOffsetO = new Vec2f(0.0f, this.rotationOffsetO.y());
    }

    public float getYRot() {
        return this.rotation.y() + this.rotationOffset.y();
    }

    public void setYRot(float yRot) {
        this.rotation = new Vec2f(this.rotation.x(), yRot);
        this.rotationOffset = new Vec2f(this.rotationOffset.x(), 0.0f);
        this.rotationOffsetO = new Vec2f(this.rotationOffsetO.x(), 0.0f);
    }

    public Vec2f getRenderRotation() {
        return this.renderRotation;
    }

    public void setLastMovedYRot(float lastMovedYRot) {
        this.lastMovedYRot = lastMovedYRot;
    }

    public boolean isLookingUp() {
        return (double)this.getXRot() < Config.CLIENT.getPlayerConfig().getHidePlayerWhenLookingUpAngle() - 90.0;
    }

    public boolean isInsideEntity(Entity cameraEntity) {
        return this.getCameraDistance() < (double)cameraEntity.m_20205_() * Config.CLIENT.getCameraConfig().keepCameraOutOfHeadMultiplier();
    }
}
