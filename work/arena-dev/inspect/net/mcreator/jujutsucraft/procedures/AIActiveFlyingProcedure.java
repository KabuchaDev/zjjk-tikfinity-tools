/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.mcreator.jujutsucraft.entity.EntityBroomEntity
 *  net.mcreator.jujutsucraft.init.JujutsucraftModMobEffects
 *  net.mcreator.jujutsucraft.procedures.GetDistanceProcedure
 *  net.mcreator.jujutsucraft.procedures.GetHeightFromGroundProcedure
 *  net.mcreator.jujutsucraft.procedures.LogicStartProcedure
 *  net.mcreator.jujutsucraft.procedures.RotateEntityProcedure
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.tags.TagKey
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.phys.Vec3
 */
package net.mcreator.jujutsucraft.procedures;

import net.mcreator.jujutsucraft.entity.EntityBroomEntity;
import net.mcreator.jujutsucraft.init.JujutsucraftModMobEffects;
import net.mcreator.jujutsucraft.procedures.GetDistanceProcedure;
import net.mcreator.jujutsucraft.procedures.GetHeightFromGroundProcedure;
import net.mcreator.jujutsucraft.procedures.LogicStartProcedure;
import net.mcreator.jujutsucraft.procedures.RotateEntityProcedure;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;

public class AIActiveFlyingProcedure {
    /*
     * Unable to fully structure code
     */
    public static void execute(LevelAccessor world, Entity entity) {
        block57: {
            block51: {
                block52: {
                    block59: {
                        block60: {
                            block58: {
                                block53: {
                                    block55: {
                                        block56: {
                                            block54: {
                                                if (entity == null) {
                                                    return;
                                                }
                                                distance = 0.0;
                                                height = 0.0;
                                                x_pos = 0.0;
                                                y_pos = 0.0;
                                                z_pos = 0.0;
                                                x_power = 0.0;
                                                y_power = 0.0;
                                                z_power = 0.0;
                                                yaw = 0.0;
                                                pitch = 0.0;
                                                old_mode_fly = 0.0;
                                                speed = 0.0;
                                                target = false;
                                                logic_avoid = false;
                                                strong_enemy = false;
                                                entity_b = null;
                                                if (entity instanceof Mob) {
                                                    _mobEnt = (Mob)entity;
                                                    v0 = _mobEnt.m_5448_();
                                                } else {
                                                    v0 = null;
                                                }
                                                target = v0 instanceof LivingEntity != false && entity.getPersistentData().m_128459_("cnt_target") > 6.0;
                                                entity.f_19789_ = 0.0f;
                                                entity_b = entity.m_20202_();
                                                if (!(entity_b instanceof LivingEntity)) {
                                                    entity_b = entity;
                                                }
                                                if (!(entity instanceof LivingEntity) || !(_livEnt6 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.COOLDOWN_TIME_BACK_STEP.get())) {
                                                    if (entity.m_20096_() || entity.m_20069_()) {
                                                        if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).m_9236_().m_5776_()) {
                                                            _entity.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.FLY_EFFECT.get(), 60, 0, false, false));
                                                        }
                                                    } else if (entity.m_20159_() && (entity_b.m_20096_() || entity_b.m_20069_()) && entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).m_9236_().m_5776_()) {
                                                        _entity.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.FLY_EFFECT.get(), 200, 0, false, false));
                                                    }
                                                }
                                                if (!target || entity.getPersistentData().m_128459_("skill") != 0.0) break block51;
                                                if (!(entity instanceof LivingEntity) || !(_livEnt15 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.FLY_EFFECT.get())) break block52;
                                                if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).m_9236_().m_5776_()) {
                                                    _entity.m_7292_(new MobEffectInstance(MobEffects.f_19591_, 20, 9, false, false));
                                                }
                                                if (entity_b instanceof LivingEntity && !(_entity = (LivingEntity)entity_b).m_9236_().m_5776_()) {
                                                    _entity.m_7292_(new MobEffectInstance(MobEffects.f_19591_, 20, 9, false, false));
                                                }
                                                distance = GetDistanceProcedure.execute((Entity)entity);
                                                height = GetHeightFromGroundProcedure.execute((LevelAccessor)world, (Entity)entity);
                                                if (!(Math.random() < 0.05)) break block53;
                                                old_mode_fly = entity.getPersistentData().m_128459_("mode_fly");
                                                strong_enemy = false;
                                                if (!(distance > 24.0) && (!(distance > 6.0 && LogicStartProcedure.execute((Entity)entity) == false && (entity instanceof LivingEntity != false && (_livEnt = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.COOLDOWN_TIME.get()) != false ? _livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.COOLDOWN_TIME.get()).m_19557_() : 0) > 20 && strong_enemy == false) && !entity.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("jujutsucraft:combat_type"))) || (entity instanceof LivingEntity != false && (_livEnt = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.COOLDOWN_TIME_COMBAT.get()) != false ? _livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.COOLDOWN_TIME_COMBAT.get()).m_19557_() : 0) >= 10)) break block54;
                                                entity.getPersistentData().m_128347_("mode_fly", 1.0);
                                                break block55;
                                            }
                                            if (!(LogicStartProcedure.execute((Entity)entity) == false && entity.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("jujutsucraft:combat_type"))) == false || entity instanceof LivingEntity != false && (_livEnt24 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.COOLDOWN_TIME_COMBAT.get()) != false || strong_enemy != false) && !(Math.random() < 0.25)) break block56;
                                            if (!(distance > 4.0)) ** GOTO lbl-1000
                                            if (distance > 16.0) ** GOTO lbl-1000
                                            if (entity instanceof Mob) {
                                                _mobEnt = (Mob)entity;
                                                v1 = _mobEnt.m_5448_();
                                            } else {
                                                v1 = null;
                                            }
                                            if (v1.getPersistentData().m_128459_("skill") != 0.0 || Math.random() < 0.5) lbl-1000:
                                            // 2 sources

                                            {
                                                if (old_mode_fly == -2.0 || old_mode_fly == -3.0) {
                                                    entity.getPersistentData().m_128347_("mode_fly", old_mode_fly);
                                                } else {
                                                    entity.getPersistentData().m_128347_("mode_fly", (double)(Math.random() < 0.5 ? -2 : -3));
                                                }
                                            } else lbl-1000:
                                            // 2 sources

                                            {
                                                entity.getPersistentData().m_128347_("mode_fly", -1.0);
                                            }
                                            break block55;
                                        }
                                        entity.getPersistentData().m_128347_("mode_fly", old_mode_fly);
                                    }
                                    if ((entity instanceof LivingEntity && (_livEnt31 = (LivingEntity)entity).m_21023_(MobEffects.f_19597_) || entity.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("jujutsucraft:combat_type")))) && entity.getPersistentData().m_128459_("mode_fly") < 0.0) {
                                        entity.getPersistentData().m_128347_("mode_fly", old_mode_fly < 0.0 ? 0.0 : old_mode_fly);
                                    }
                                }
                                if (entity.getPersistentData().m_128459_("mode_fly") == 0.0) break block57;
                                speed = entity.m_20202_() instanceof LivingEntity != false ? 4.0 : 1.0;
                                if (entity instanceof Mob) {
                                    _mobEnt = (Mob)entity;
                                    v2 = _mobEnt.m_5448_();
                                } else {
                                    v2 = null;
                                }
                                x_pos = v2.m_20185_();
                                if (entity instanceof Mob) {
                                    _mobEnt = (Mob)entity;
                                    v3 = _mobEnt.m_5448_();
                                } else {
                                    v3 = null;
                                }
                                v4 = v3.m_20186_();
                                if (entity instanceof Mob) {
                                    _mobEnt = (Mob)entity;
                                    v5 = _mobEnt.m_5448_();
                                } else {
                                    v5 = null;
                                }
                                y_pos = v4 + (double)v5.m_20206_() * 0.5;
                                if (entity instanceof Mob) {
                                    _mobEnt = (Mob)entity;
                                    v6 = _mobEnt.m_5448_();
                                } else {
                                    v6 = null;
                                }
                                z_pos = v6.m_20189_();
                                RotateEntityProcedure.execute((double)x_pos, (double)y_pos, (double)z_pos, (Entity)entity);
                                yaw = entity.m_146908_();
                                pitch = entity.m_146909_();
                                if (!(entity.getPersistentData().m_128459_("mode_fly") > 0.0)) break block58;
                                v7 = entity.m_20186_();
                                if (entity instanceof Mob) {
                                    _mobEnt = (Mob)entity;
                                    v8 = _mobEnt.m_5448_();
                                } else {
                                    v8 = null;
                                }
                                v9 = (v7 + v8.m_20186_()) * 0.5;
                                v10 = entity.m_20185_();
                                if (entity instanceof Mob) {
                                    _mobEnt = (Mob)entity;
                                    v11 = _mobEnt.m_5448_();
                                } else {
                                    v11 = null;
                                }
                                v12 = Math.pow(v10 - v11.m_20185_(), 2.0);
                                v13 = entity.m_20189_();
                                if (entity instanceof Mob) {
                                    _mobEnt = (Mob)entity;
                                    v14 = _mobEnt.m_5448_();
                                } else {
                                    v14 = null;
                                }
                                y_pos = v9 + Math.sqrt(Math.sqrt(v12 + Math.pow(v13 - v14.m_20189_(), 2.0)));
                                RotateEntityProcedure.execute((double)x_pos, (double)y_pos, (double)z_pos, (Entity)entity);
                                x_power = entity.m_20154_().f_82479_ * Math.max(Math.min(1.25, distance * 0.25), 0.25);
                                y_power = entity.m_20154_().f_82480_ * Math.max(Math.min(1.25, distance * 0.25), 0.25);
                                z_power = entity.m_20154_().f_82481_ * Math.max(Math.min(1.25, distance * 0.25), 0.25);
                                if (distance < 4.0) {
                                    v15 = entity.m_20186_();
                                    if (entity instanceof Mob) {
                                        _mobEnt = (Mob)entity;
                                        v16 = _mobEnt.m_5448_();
                                    } else {
                                        v16 = null;
                                    }
                                    if (v15 > v16.m_20186_()) {
                                        entity.getPersistentData().m_128347_("mode_fly", 0.0);
                                    }
                                }
                                break block59;
                            }
                            if (!(entity.getPersistentData().m_128459_("mode_fly") < 0.0)) break block59;
                            if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).m_9236_().m_5776_()) {
                                _entity.m_7292_(new MobEffectInstance(MobEffects.f_19597_, 40, 0, false, false));
                            }
                            if (entity.getPersistentData().m_128459_("mode_fly") != -1.0) break block60;
                            x_power = entity.m_20154_().f_82479_ * -1.0;
                            if (height <= Math.max(Math.min(distance * 0.75, 15.0), 4.0)) ** GOTO lbl-1000
                            v17 = entity.m_20186_();
                            if (entity instanceof Mob) {
                                _mobEnt = (Mob)entity;
                                v18 = _mobEnt.m_5448_();
                            } else {
                                v18 = null;
                            }
                            if (v17 <= v18.m_20186_()) lbl-1000:
                            // 2 sources

                            {
                                v19 = 1;
                            } else {
                                v19 = -1;
                            }
                            y_power = v19;
                            z_power = entity.m_20154_().f_82481_ * -1.0;
                            if (distance > 16.0) {
                                entity.getPersistentData().m_128347_("mode_fly", (double)(Math.random() < 0.5 ? -2 : -3));
                            }
                            break block59;
                        }
                        if (entity.getPersistentData().m_128459_("mode_fly") != -2.0 && entity.getPersistentData().m_128459_("mode_fly") != -3.0) break block59;
                        _ent = entity;
                        _ent.m_146922_((float)((double)entity.m_146908_() + (90.0 + Math.random() * (double)(distance > 16.0 ? -45 : 45)) * (double)(entity.getPersistentData().m_128459_("mode_fly") == -2.0 ? 1 : -1)));
                        _ent.m_146926_(entity.m_146909_());
                        _ent.m_5618_(_ent.m_146908_());
                        _ent.m_5616_(_ent.m_146908_());
                        _ent.f_19859_ = _ent.m_146908_();
                        _ent.f_19860_ = _ent.m_146909_();
                        if (_ent instanceof LivingEntity) {
                            _entity = (LivingEntity)_ent;
                            _entity.f_20884_ = _entity.m_146908_();
                            _entity.f_20886_ = _entity.m_146908_();
                        }
                        x_power = entity.m_20154_().f_82479_ * 1.0;
                        if (height <= Math.max(Math.min(distance * 0.75, 15.0), 4.0)) ** GOTO lbl-1000
                        v20 = entity.m_20186_();
                        if (entity instanceof Mob) {
                            _mobEnt = (Mob)entity;
                            v21 = _mobEnt.m_5448_();
                        } else {
                            v21 = null;
                        }
                        if (v20 <= v21.m_20186_()) lbl-1000:
                        // 2 sources

                        {
                            v22 = 1;
                        } else {
                            v22 = -1;
                        }
                        y_power = v22;
                        z_power = entity.m_20154_().f_82481_ * 1.0;
                        if (distance < 4.0) {
                            entity.getPersistentData().m_128347_("mode_fly", -1.0);
                        }
                    }
                    x_power = entity.m_20184_().m_7096_() + 0.1 * (double)(entity.m_20184_().m_7096_() > x_power * speed ? -1 : 1) * speed;
                    y_power = entity.m_20184_().m_7098_() + 0.1 * (double)(entity.m_20184_().m_7098_() > y_power * speed ? -1 : 1) * speed;
                    z_power = entity.m_20184_().m_7094_() + 0.1 * (double)(entity.m_20184_().m_7094_() > z_power * speed ? -1 : 1) * speed;
                    entity.m_20256_(new Vec3(x_power, y_power, z_power));
                    _ent = entity;
                    _ent.m_146922_((float)yaw);
                    _ent.m_146926_((float)pitch);
                    _ent.m_5618_(_ent.m_146908_());
                    _ent.m_5616_(_ent.m_146908_());
                    _ent.f_19859_ = _ent.m_146908_();
                    _ent.f_19860_ = _ent.m_146909_();
                    if (_ent instanceof LivingEntity) {
                        _entity = (LivingEntity)_ent;
                        _entity.f_20884_ = _entity.m_146908_();
                        _entity.f_20886_ = _entity.m_146908_();
                    }
                    if (entity.m_20202_() instanceof EntityBroomEntity) {
                        entity.m_20202_().m_20256_(new Vec3(x_power, y_power, z_power));
                        _ent = entity.m_20202_();
                        _ent.m_146922_((float)yaw);
                        _ent.m_146926_((float)pitch);
                        _ent.m_5618_(_ent.m_146908_());
                        _ent.m_5616_(_ent.m_146908_());
                        _ent.f_19859_ = _ent.m_146908_();
                        _ent.f_19860_ = _ent.m_146909_();
                        if (_ent instanceof LivingEntity) {
                            _entity = (LivingEntity)_ent;
                            _entity.f_20884_ = _entity.m_146908_();
                            _entity.f_20886_ = _entity.m_146908_();
                        }
                    }
                    break block57;
                }
                entity.getPersistentData().m_128347_("mode_fly", 0.0);
                break block57;
            }
            entity.getPersistentData().m_128347_("mode_fly", 0.0);
        }
    }
}
