/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Camera$NearPlane
 *  net.minecraft.client.Minecraft
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.BlockPos$MutableBlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Position
 *  net.minecraft.tags.FluidTags
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.material.FluidState
 *  net.minecraft.world.level.material.FogType
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  org.joml.Quaternionf
 *  org.joml.Quaternionfc
 *  org.joml.Vector3f
 */
package net.minecraft.client;

import java.util.Arrays;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import org.joml.Vector3f;

@OnlyIn(value=Dist.CLIENT)
public class Camera {
    private boolean initialized;
    private BlockGetter level;
    private Entity entity;
    private Vec3 position = Vec3.ZERO;
    private final BlockPos.MutableBlockPos blockPosition = new BlockPos.MutableBlockPos();
    private final Vector3f forwards = new Vector3f(0.0f, 0.0f, 1.0f);
    private final Vector3f up = new Vector3f(0.0f, 1.0f, 0.0f);
    private final Vector3f left = new Vector3f(1.0f, 0.0f, 0.0f);
    private float xRot;
    private float yRot;
    private final Quaternionf rotation = new Quaternionf(0.0f, 0.0f, 0.0f, 1.0f);
    private boolean detached;
    private float eyeHeight;
    private float eyeHeightOld;
    public static final float FOG_DISTANCE_SCALE = 0.083333336f;

    public void setup(BlockGetter p_90576_, Entity p_90577_, boolean p_90578_, boolean p_90579_, float p_90580_) {
        this.initialized = true;
        this.level = p_90576_;
        this.entity = p_90577_;
        this.detached = p_90578_;
        this.setRotation(p_90577_.getViewYRot(p_90580_), p_90577_.getViewXRot(p_90580_));
        this.setPosition(Mth.lerp((double)p_90580_, (double)p_90577_.xo, (double)p_90577_.getX()), Mth.lerp((double)p_90580_, (double)p_90577_.yo, (double)p_90577_.getY()) + (double)Mth.lerp((float)p_90580_, (float)this.eyeHeightOld, (float)this.eyeHeight), Mth.lerp((double)p_90580_, (double)p_90577_.zo, (double)p_90577_.getZ()));
        if (p_90578_) {
            if (p_90579_) {
                this.setRotation(this.yRot + 180.0f, -this.xRot);
            }
            this.move(-this.getMaxZoom(4.0), 0.0, 0.0);
        } else if (p_90577_ instanceof LivingEntity && ((LivingEntity)p_90577_).isSleeping()) {
            Direction direction = ((LivingEntity)p_90577_).getBedOrientation();
            this.setRotation(direction != null ? direction.toYRot() - 180.0f : 0.0f, 0.0f);
            this.move(0.0, 0.3, 0.0);
        }
    }

    public void tick() {
        if (this.entity != null) {
            this.eyeHeightOld = this.eyeHeight;
            this.eyeHeight += (this.entity.getEyeHeight() - this.eyeHeight) * 0.5f;
        }
    }

    private double getMaxZoom(double p_90567_) {
        for (int i = 0; i < 8; ++i) {
            double d0;
            Vec3 vec31;
            BlockHitResult hitresult;
            float f = (i & 1) * 2 - 1;
            float f1 = (i >> 1 & 1) * 2 - 1;
            float f2 = (i >> 2 & 1) * 2 - 1;
            Vec3 vec3 = this.position.add((double)(f *= 0.1f), (double)(f1 *= 0.1f), (double)(f2 *= 0.1f));
            if ((hitresult = this.level.clip(new ClipContext(vec3, vec31 = new Vec3(this.position.x - (double)this.forwards.x() * p_90567_ + (double)f, this.position.y - (double)this.forwards.y() * p_90567_ + (double)f1, this.position.z - (double)this.forwards.z() * p_90567_ + (double)f2), ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, this.entity))).getType() == HitResult.Type.MISS || !((d0 = hitresult.getLocation().distanceTo(this.position)) < p_90567_)) continue;
            p_90567_ = d0;
        }
        return p_90567_;
    }

    protected void move(double p_90569_, double p_90570_, double p_90571_) {
        double d0 = (double)this.forwards.x() * p_90569_ + (double)this.up.x() * p_90570_ + (double)this.left.x() * p_90571_;
        double d1 = (double)this.forwards.y() * p_90569_ + (double)this.up.y() * p_90570_ + (double)this.left.y() * p_90571_;
        double d2 = (double)this.forwards.z() * p_90569_ + (double)this.up.z() * p_90570_ + (double)this.left.z() * p_90571_;
        this.setPosition(new Vec3(this.position.x + d0, this.position.y + d1, this.position.z + d2));
    }

    protected void setRotation(float p_90573_, float p_90574_) {
        this.xRot = p_90574_;
        this.yRot = p_90573_;
        this.rotation.rotationYXZ(-p_90573_ * ((float)Math.PI / 180), p_90574_ * ((float)Math.PI / 180), 0.0f);
        this.forwards.set(0.0f, 0.0f, 1.0f).rotate((Quaternionfc)this.rotation);
        this.up.set(0.0f, 1.0f, 0.0f).rotate((Quaternionfc)this.rotation);
        this.left.set(1.0f, 0.0f, 0.0f).rotate((Quaternionfc)this.rotation);
    }

    protected void setPosition(double p_90585_, double p_90586_, double p_90587_) {
        this.setPosition(new Vec3(p_90585_, p_90586_, p_90587_));
    }

    protected void setPosition(Vec3 p_90582_) {
        this.position = p_90582_;
        this.blockPosition.set(p_90582_.x, p_90582_.y, p_90582_.z);
    }

    public Vec3 getPosition() {
        return this.position;
    }

    public BlockPos getBlockPosition() {
        return this.blockPosition;
    }

    public float getXRot() {
        return this.xRot;
    }

    public float getYRot() {
        return this.yRot;
    }

    public Quaternionf rotation() {
        return this.rotation;
    }

    public Entity getEntity() {
        return this.entity;
    }

    public boolean isInitialized() {
        return this.initialized;
    }

    public boolean isDetached() {
        return this.detached;
    }

    public NearPlane getNearPlane() {
        Minecraft minecraft = Minecraft.getInstance();
        double d0 = (double)minecraft.getWindow().getWidth() / (double)minecraft.getWindow().getHeight();
        double d1 = Math.tan((double)((float)((Integer)minecraft.options.fov().get()).intValue() * ((float)Math.PI / 180)) / 2.0) * (double)0.05f;
        double d2 = d1 * d0;
        Vec3 vec3 = new Vec3(this.forwards).scale((double)0.05f);
        Vec3 vec31 = new Vec3(this.left).scale(d2);
        Vec3 vec32 = new Vec3(this.up).scale(d1);
        return new NearPlane(vec3, vec31, vec32);
    }

    public FogType getFluidInCamera() {
        if (!this.initialized) {
            return FogType.NONE;
        }
        FluidState fluidstate = this.level.getFluidState((BlockPos)this.blockPosition);
        if (fluidstate.is(FluidTags.WATER) && this.position.y < (double)((float)this.blockPosition.getY() + fluidstate.getHeight(this.level, (BlockPos)this.blockPosition))) {
            return FogType.WATER;
        }
        NearPlane camera$nearplane = this.getNearPlane();
        for (Vec3 vec3 : Arrays.asList(camera$nearplane.forward, camera$nearplane.getTopLeft(), camera$nearplane.getTopRight(), camera$nearplane.getBottomLeft(), camera$nearplane.getBottomRight())) {
            Vec3 vec31 = this.position.add(vec3);
            BlockPos blockpos = BlockPos.containing((Position)vec31);
            FluidState fluidstate1 = this.level.getFluidState(blockpos);
            if (fluidstate1.is(FluidTags.LAVA)) {
                if (!(vec31.y <= (double)(fluidstate1.getHeight(this.level, blockpos) + (float)blockpos.getY()))) continue;
                return FogType.LAVA;
            }
            BlockState blockstate = this.level.getBlockState(blockpos);
            if (!blockstate.is(Blocks.POWDER_SNOW)) continue;
            return FogType.POWDER_SNOW;
        }
        return FogType.NONE;
    }

    public final Vector3f getLookVector() {
        return this.forwards;
    }

    public final Vector3f getUpVector() {
        return this.up;
    }

    public final Vector3f getLeftVector() {
        return this.left;
    }

    public void reset() {
        this.level = null;
        this.entity = null;
        this.initialized = false;
    }

    public void setAnglesInternal(float yaw, float pitch) {
        this.yRot = yaw;
        this.xRot = pitch;
    }

    public BlockState getBlockAtCamera() {
        if (!this.initialized) {
            return Blocks.AIR.defaultBlockState();
        }
        return this.level.getBlockState((BlockPos)this.blockPosition).getStateAtViewpoint(this.level, (BlockPos)this.blockPosition, this.position);
    }
}
