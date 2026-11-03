/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.mcreator.jujutsucraft.entity.GojoSatoruEntity
 *  net.mcreator.jujutsucraft.entity.HigurumaHiromiEntity
 *  net.mcreator.jujutsucraft.entity.KenjakuEntity
 *  net.mcreator.jujutsucraft.entity.OkkotsuYutaCullingGameEntity
 *  net.mcreator.jujutsucraft.entity.OkkotsuYutaEntity
 *  net.mcreator.jujutsucraft.init.JujutsucraftModMobEffects
 *  net.mcreator.jujutsucraft.network.JujutsucraftModVariables
 *  net.mcreator.jujutsucraft.network.JujutsucraftModVariables$MapVariables
 *  net.mcreator.jujutsucraft.network.JujutsucraftModVariables$PlayerVariables
 *  net.mcreator.jujutsucraft.procedures.DomainActiveProcedure
 *  net.mcreator.jujutsucraft.procedures.DomainExpansionBattleProcedure
 *  net.mcreator.jujutsucraft.procedures.EffectCharactorProcedure
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.common.util.NonNullConsumer
 */
package net.mcreator.jujutsucraft.procedures;

import java.lang.invoke.LambdaMetafactory;
import java.util.function.Predicate;
import net.mcreator.jujutsucraft.entity.GojoSatoruEntity;
import net.mcreator.jujutsucraft.entity.HigurumaHiromiEntity;
import net.mcreator.jujutsucraft.entity.KenjakuEntity;
import net.mcreator.jujutsucraft.entity.OkkotsuYutaCullingGameEntity;
import net.mcreator.jujutsucraft.entity.OkkotsuYutaEntity;
import net.mcreator.jujutsucraft.init.JujutsucraftModMobEffects;
import net.mcreator.jujutsucraft.network.JujutsucraftModVariables;
import net.mcreator.jujutsucraft.procedures.DomainActiveProcedure;
import net.mcreator.jujutsucraft.procedures.DomainExpansionBattleProcedure;
import net.mcreator.jujutsucraft.procedures.EffectCharactorProcedure;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.NonNullConsumer;

public class DomainExpansionOnEffectActiveTickProcedure {
    /*
     * Unable to fully structure code
     */
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        block83: {
            block80: {
                block79: {
                    block81: {
                        if (entity == null) {
                            return;
                        }
                        failed = false;
                        logic_a = false;
                        logic_b = false;
                        noClosing = false;
                        update1 = false;
                        old_failed = false;
                        use_old = false;
                        range = 0.0;
                        level = 0.0;
                        tick = 0.0;
                        x_pos = 0.0;
                        y_pos = 0.0;
                        z_pos = 0.0;
                        tick_1 = 0.0;
                        tick_2 = 0.0;
                        distance = 0.0;
                        old_skill = 0.0;
                        domainPower1 = 0.0;
                        domainPower2 = 0.0;
                        str_lv = 0.0;
                        x_dis = 0.0;
                        y_dis = 0.0;
                        z_dis = 0.0;
                        if (!entity.m_6084_()) break block80;
                        tick_1 = entity instanceof LivingEntity != false && (_livEnt = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()) != false ? (double)_livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()).m_19557_() : 0.0;
                        str_lv = (entity instanceof LivingEntity != false && (_livEnt = (LivingEntity)entity).m_21023_(MobEffects.f_19600_) != false ? _livEnt.m_21124_(MobEffects.f_19600_).m_19564_() : 0) + 10;
                        if ((entity instanceof LivingEntity != false && (_livEnt = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()) != false ? _livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()).m_19564_() : 0) > 0) {
                            str_lv *= 1.15;
                        }
                        if (entity.getPersistentData().m_128459_("select") == 27.0 || entity.getPersistentData().m_128459_("skill_domain") == 27.0) {
                            str_lv *= 1.5;
                        } else if (entity.getPersistentData().m_128459_("select") == 29.0 || entity.getPersistentData().m_128459_("skill_domain") == 29.0) {
                            str_lv *= 2.0;
                        }
                        if (entity.getPersistentData().m_128459_("skill_domain") == 0.0 && entity.getPersistentData().m_128459_("skill") == 0.0) {
                            entity.getPersistentData().m_128347_("skill_domain", entity.getPersistentData().m_128459_("select"));
                            entity.getPersistentData().m_128347_("select", 0.0);
                            update1 = true;
                        }
                        if (entity.getPersistentData().m_128459_("skill_domain") > 0.0) {
                            if (!update1) {
                                DomainActiveProcedure.execute((LevelAccessor)world, (double)x, (double)y, (double)z, (Entity)entity);
                            }
                            range = JujutsucraftModVariables.MapVariables.get((LevelAccessor)world).DomainExpansionRadius * (double)((entity instanceof LivingEntity != false && (_livEnt = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()) != false ? _livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()).m_19564_() : 0) > 0 ? 18 : 2);
                        }
                        old_skill = entity.getPersistentData().m_128459_("skill");
                        entity.getPersistentData().m_128347_("skill", 0.0);
                        if (tick_1 % 10.0 == 0.0 || update1) {
                            logic_a = false;
                            _center = new Vec3(entity.getPersistentData().m_128459_("x_pos_doma"), entity.getPersistentData().m_128459_("y_pos_doma"), entity.getPersistentData().m_128459_("z_pos_doma"));
                            _entfound = world.m_6443_(Entity.class, new AABB(_center, _center).m_82400_(range / 2.0), (Predicate<Entity>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Z, lambda$execute$0(net.minecraft.world.entity.Entity ), (Lnet/minecraft/world/entity/Entity;)Z)());
                            for (Entity entityiterator : _entfound) {
                                x_dis = entity.getPersistentData().m_128459_("x_pos_doma") - entityiterator.m_20185_();
                                distance = x_dis * x_dis + (y_dis = entity.getPersistentData().m_128459_("y_pos_doma") - (entityiterator.m_20186_() + (double)entityiterator.m_20206_() * 0.5)) * y_dis + (z_dis = entity.getPersistentData().m_128459_("z_pos_doma") - entityiterator.m_20189_()) * z_dis;
                                if (!(distance < range * 0.5 * range * 0.5)) continue;
                                if (entity == entityiterator) {
                                    logic_a = true;
                                    continue;
                                }
                                if (entityiterator instanceof LivingEntity && (_livEnt28 = (LivingEntity)entityiterator).m_21023_((MobEffect)JujutsucraftModMobEffects.SIMPLE_DOMAIN.get())) {
                                    level = entityiterator instanceof LivingEntity != false && (_livEnt = (LivingEntity)entityiterator).m_21023_((MobEffect)JujutsucraftModMobEffects.SIMPLE_DOMAIN.get()) != false ? (double)_livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.SIMPLE_DOMAIN.get()).m_19564_() : 0.0;
                                    tick = entityiterator instanceof LivingEntity != false && (_livEnt = (LivingEntity)entityiterator).m_21023_((MobEffect)JujutsucraftModMobEffects.SIMPLE_DOMAIN.get()) != false ? (double)_livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.SIMPLE_DOMAIN.get()).m_19557_() : 0.0;
                                    tick -= (double)Math.round(Math.sqrt(str_lv + 1.0) * 10.0);
                                    if (entityiterator instanceof LivingEntity) {
                                        _entity = (LivingEntity)entityiterator;
                                        _entity.m_21195_((MobEffect)JujutsucraftModMobEffects.SIMPLE_DOMAIN.get());
                                    }
                                    if (level >= 0.0 && entityiterator instanceof LivingEntity && !(_entity = (LivingEntity)entityiterator).m_9236_().m_5776_()) {
                                        _entity.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.SIMPLE_DOMAIN.get(), (int)tick, (int)level, true, true));
                                    }
                                } else if (tick_1 % 20.0 == 0.0) {
                                    EffectCharactorProcedure.execute((LevelAccessor)world, (Entity)entity, (Entity)entityiterator);
                                }
                                if (entityiterator instanceof LivingEntity && (_livEnt33 = (LivingEntity)entityiterator).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()) || entityiterator.getPersistentData().m_128459_("select") != 0.0 || !(entityiterator instanceof LivingEntity) || (_entity = (LivingEntity)entityiterator).m_9236_().m_5776_()) continue;
                                _entity.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.NEUTRALIZATION.get(), 20, (int)(entity.getPersistentData().m_128459_("skill_domain") + 10.0), false, false));
                            }
                            if (!logic_a && entity.getPersistentData().m_128459_("skill_domain") > 0.0 && (entity instanceof LivingEntity != false && (_livEnt = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()) != false ? _livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()).m_19564_() : 0) == 0 && entity instanceof LivingEntity) {
                                _entity = (LivingEntity)entity;
                                _entity.m_21195_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get());
                            }
                        }
                        v0 = entity.getPersistentData().m_128459_("oldHealth");
                        if (entity instanceof LivingEntity) {
                            _livEnt = (LivingEntity)entity;
                            v1 = _livEnt.m_21223_();
                        } else {
                            v1 = -1.0f;
                        }
                        if (v0 != (double)v1) {
                            v2 = entity.getPersistentData().m_128459_("oldHealth");
                            if (entity instanceof LivingEntity) {
                                _livEnt = (LivingEntity)entity;
                                v3 = _livEnt.m_21223_();
                            } else {
                                v3 = -1.0f;
                            }
                            if (v2 > (double)v3) {
                                v4 = entity.getPersistentData();
                                v5 = entity.getPersistentData().m_128459_("totalDamage") + entity.getPersistentData().m_128459_("oldHealth");
                                if (entity instanceof LivingEntity) {
                                    _livEnt = (LivingEntity)entity;
                                    v6 = _livEnt.m_21223_();
                                } else {
                                    v6 = -1.0f;
                                }
                                v4.m_128347_("totalDamage", v5 - (double)v6);
                            } else {
                                v7 = entity.getPersistentData();
                                v8 = entity.getPersistentData().m_128459_("totalDamage");
                                v9 = entity.getPersistentData().m_128459_("oldHealth");
                                if (entity instanceof LivingEntity) {
                                    _livEnt = (LivingEntity)entity;
                                    v10 = _livEnt.m_21223_();
                                } else {
                                    v10 = -1.0f;
                                }
                                v7.m_128347_("totalDamage", v8 + (v9 - (double)v10) * 0.5);
                            }
                        }
                        v11 = entity.getPersistentData();
                        if (entity instanceof LivingEntity) {
                            _livEnt = (LivingEntity)entity;
                            v12 = _livEnt.m_21223_();
                        } else {
                            v12 = -1.0;
                        }
                        v11.m_128347_("oldHealth", v12);
                        if (tick_1 % 20.0 != 0.0 && !update1 || entity.getPersistentData().m_128459_("select") != 0.0) break block81;
                        if (entity instanceof LivingEntity) {
                            _livEnt = (LivingEntity)entity;
                            v13 = _livEnt.m_21233_();
                        } else {
                            v13 = -1.0f;
                        }
                        v14 = Math.max((double)Math.max(v13, 1.0f) - Math.max(entity.getPersistentData().m_128459_("totalDamage"), 0.0) * 2.0, 0.0);
                        if (entity instanceof LivingEntity) {
                            _livEnt = (LivingEntity)entity;
                            v15 = _livEnt.m_21233_();
                        } else {
                            v15 = -1.0f;
                        }
                        domainPower1 = str_lv * (v14 / (double)Math.max(v15, 1.0f)) * Math.min(Math.min(tick_1, 1200.0) / 2400.0 + 0.5, 1.0);
                        failed = false;
                        logic_a = false;
                        logic_b = false;
                        for (index0 = 0; index0 < 2; ++index0) {
                            _center = new Vec3(entity.getPersistentData().m_128459_("x_pos_doma"), entity.getPersistentData().m_128459_("y_pos_doma"), entity.getPersistentData().m_128459_("z_pos_doma"));
                            _entfound = world.m_6443_(Entity.class, new AABB(_center, _center).m_82400_(range / 2.0), (Predicate<Entity>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Z, lambda$execute$1(net.minecraft.world.entity.Entity ), (Lnet/minecraft/world/entity/Entity;)Z)());
                            for (Entity entityiterator : _entfound) {
                                block82: {
                                    v16 = (entity instanceof LivingEntity != false && (_livEnt = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()) != false ? _livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()).m_19564_() : 0) > 0 && (entityiterator instanceof LivingEntity != false && (_livEnt = (LivingEntity)entityiterator).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()) != false ? _livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()).m_19564_() : 0) == 0 ? true : (noClosing = false);
                                    if (entity == entityiterator || (!(entityiterator instanceof LivingEntity) || !(_livEnt65 = (LivingEntity)entityiterator).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get())) && entityiterator.getPersistentData().m_128459_("select") == 0.0 || !((distance = (x_dis = entity.getPersistentData().m_128459_("x_pos_doma") - entityiterator.m_20185_()) * x_dis + (y_dis = entity.getPersistentData().m_128459_("y_pos_doma") - (entityiterator.m_20186_() + (double)entityiterator.m_20206_() * 0.5)) * y_dis + (z_dis = entity.getPersistentData().m_128459_("z_pos_doma") - entityiterator.m_20189_()) * z_dis) < range * 0.5 * range * 0.5)) continue;
                                    domainPower2 = (entityiterator instanceof LivingEntity != false && (_livEnt = (LivingEntity)entityiterator).m_21023_(MobEffects.f_19600_) != false ? _livEnt.m_21124_(MobEffects.f_19600_).m_19564_() : 0) + 10;
                                    if ((entityiterator instanceof LivingEntity != false && (_livEnt = (LivingEntity)entityiterator).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()) != false ? _livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()).m_19564_() : 0) > 0) {
                                        domainPower2 *= 1.15;
                                    }
                                    if (entityiterator.getPersistentData().m_128459_("select") == 27.0 || entityiterator.getPersistentData().m_128459_("skill_domain") == 27.0) {
                                        domainPower2 *= 1.5;
                                    } else if (entityiterator.getPersistentData().m_128459_("select") == 29.0 || entityiterator.getPersistentData().m_128459_("skill_domain") == 29.0) {
                                        domainPower2 *= 2.0;
                                    }
                                    if (entityiterator.getPersistentData().m_128459_("select") == 0.0) {
                                        v17 = tick_2 = entityiterator instanceof LivingEntity != false && (_livEnt = (LivingEntity)entityiterator).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()) != false ? (double)_livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()).m_19557_() : 0.0;
                                        if (entityiterator instanceof LivingEntity) {
                                            _livEnt = (LivingEntity)entityiterator;
                                            v18 = _livEnt.m_21233_();
                                        } else {
                                            v18 = -1.0f;
                                        }
                                        v19 = Math.max((double)Math.max(v18, 1.0f) - Math.max(entityiterator.getPersistentData().m_128459_("totalDamage"), 0.0) * 2.0, 0.0);
                                        if (entityiterator instanceof LivingEntity) {
                                            _livEnt = (LivingEntity)entityiterator;
                                            v20 = _livEnt.m_21233_();
                                        } else {
                                            v20 = -1.0f;
                                        }
                                        domainPower2 = domainPower2 * (v19 / (double)Math.max(v20, 1.0f)) * Math.min(Math.min(tick_2, 1200.0) / 2400.0 + 0.5, 1.0);
                                    } else {
                                        tick_2 = 1200.0;
                                    }
                                    if ((domainPower1 - domainPower2 >= 10.0 || logic_a && (entityiterator instanceof LivingEntity != false && (_livEnt = (LivingEntity)entityiterator).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()) != false ? _livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()).m_19564_() : 0) <= 0) && entityiterator instanceof LivingEntity && (_livEnt86 = (LivingEntity)entityiterator).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get())) {
                                        failed = false;
                                        logic_b = true;
                                        entityiterator.getPersistentData().m_128379_("Failed", false);
                                        entityiterator.getPersistentData().m_128379_("DomainDefeated", noClosing == false);
                                        if (!(entityiterator instanceof LivingEntity && (_livEnt89 = (LivingEntity)entityiterator).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()) || !(entityiterator instanceof LivingEntity) || (_entity = (LivingEntity)entityiterator).m_9236_().m_5776_())) {
                                            _entity.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get(), 5, 0, false, false));
                                        }
                                        if (!(entityiterator instanceof LivingEntity)) continue;
                                        _entity = (LivingEntity)entityiterator;
                                        _entity.m_21195_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get());
                                        continue;
                                    }
                                    if (!noClosing || !(tick_1 <= tick_2 ? tick_2 < 1000.0 : tick_1 < 1000.0) || !(entityiterator instanceof LivingEntity) || !(_livEnt92 = (LivingEntity)entityiterator).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get())) break block82;
                                    logic_a = true;
                                    if (entityiterator instanceof Player == false ? entityiterator.getPersistentData().m_128459_("cnt_learn_domain") > 0.0 : entityiterator instanceof ServerPlayer != false && (_plr94 = (ServerPlayer)entityiterator).m_9236_() instanceof ServerLevel != false && _plr94.m_8960_().m_135996_(_plr94.f_8924_.m_129889_().m_136041_(new ResourceLocation("jujutsucraft:anti_open_barrier_type_domain"))).m_8193_() != false) ** GOTO lbl-1000
                                    if (entityiterator instanceof LivingEntity && (_livEnt96 = (LivingEntity)entityiterator).m_21023_((MobEffect)JujutsucraftModMobEffects.SUKUNA_EFFECT.get())) lbl-1000:
                                    // 2 sources

                                    {
                                        logic_a = false;
                                    } else if (entityiterator instanceof GojoSatoruEntity || entityiterator instanceof HigurumaHiromiEntity || entityiterator instanceof OkkotsuYutaEntity || entityiterator instanceof OkkotsuYutaCullingGameEntity || entityiterator instanceof KenjakuEntity) {
                                        entityiterator.getPersistentData().m_128347_("cnt_learn_domain", 1.0);
                                    } else if (entityiterator instanceof Player && entityiterator instanceof ServerPlayer) {
                                        _player = (ServerPlayer)entityiterator;
                                        _adv = _player.f_8924_.m_129889_().m_136041_(new ResourceLocation("jujutsucraft:anti_open_barrier_type_domain"));
                                        _ap = _player.m_8960_().m_135996_(_adv);
                                        if (!_ap.m_8193_()) {
                                            for (String criteria : _ap.m_8219_()) {
                                                _player.m_8960_().m_135988_(_adv, criteria);
                                            }
                                        }
                                    }
                                    if (logic_a) break;
                                }
                                if (logic_b) continue;
                                if (distance < JujutsucraftModVariables.MapVariables.get((LevelAccessor)world).DomainExpansionRadius) {
                                    entity.getPersistentData().m_128379_("Failed", true);
                                } else {
                                    use_old = true;
                                    old_failed = entity.getPersistentData().m_128471_("Failed");
                                    entity.getPersistentData().m_128379_("Failed", true);
                                }
                                failed = true;
                            }
                            if (logic_a) {
                                continue;
                            }
                            break block79;
                        }
                        break block79;
                    }
                    failed = true;
                }
                entity.getPersistentData().m_128347_("skill", old_skill);
                if (entity.getPersistentData().m_128471_("Failed") || entity.getPersistentData().m_128471_("Cover")) {
                    if (!failed && !entity.getPersistentData().m_128471_("Cover")) {
                        x_dis = entity.getPersistentData().m_128459_("x_pos_doma") - entity.m_20185_();
                        distance = x_dis * x_dis + (y_dis = entity.getPersistentData().m_128459_("y_pos_doma") - (entity.m_20186_() + (double)entity.m_20206_() * 0.5)) * y_dis + (z_dis = entity.getPersistentData().m_128459_("z_pos_doma") - entity.m_20189_()) * z_dis;
                        if (distance < JujutsucraftModVariables.MapVariables.get((LevelAccessor)world).DomainExpansionRadius * JujutsucraftModVariables.MapVariables.get((LevelAccessor)world).DomainExpansionRadius || (entity instanceof LivingEntity != false && (_livEnt = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()) != false ? _livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()).m_19564_() : 0) != 0) {
                            entity.getPersistentData().m_128347_("cnt_cover", 1.0);
                            old_failed = false;
                            entity.getPersistentData().m_128379_("Failed", false);
                            entity.getPersistentData().m_128379_("Cover", true);
                            if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).m_9236_().m_5776_()) {
                                _entity.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get(), (int)(tick_1 + 100.0), entity instanceof LivingEntity != false && (_livEnt = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()) != false ? _livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()).m_19564_() : 0, true, false));
                            }
                            entity.getPersistentData().m_128347_("x_pos_doma2", (double)Math.round(entity.m_20185_()));
                            entity.getPersistentData().m_128347_("y_pos_doma2", (double)Math.round(entity.m_20186_()));
                            entity.getPersistentData().m_128347_("z_pos_doma2", (double)Math.round(entity.m_20189_()));
                        } else if (entity instanceof LivingEntity) {
                            _entity = (LivingEntity)entity;
                            _entity.m_21195_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get());
                        }
                    }
                    if (entity.getPersistentData().m_128471_("Cover")) {
                        x_pos = entity.getPersistentData().m_128459_("x_pos_doma");
                        y_pos = entity.getPersistentData().m_128459_("y_pos_doma");
                        z_pos = entity.getPersistentData().m_128459_("z_pos_doma");
                    } else {
                        x_pos = entity.m_20185_();
                        y_pos = entity.m_20186_();
                        z_pos = entity.m_20189_();
                    }
                    old_skill = entity.getPersistentData().m_128459_("cnt1");
                    entity.getPersistentData().m_128347_("cnt1", 0.0);
                    DomainExpansionBattleProcedure.execute((LevelAccessor)world, (double)x_pos, (double)y_pos, (double)z_pos, (Entity)entity);
                    entity.getPersistentData().m_128347_("cnt1", old_skill);
                    if (use_old) {
                        entity.getPersistentData().m_128379_("Failed", old_failed);
                    }
                    if (entity.getPersistentData().m_128471_("Cover")) {
                        entity.getPersistentData().m_128347_("cnt_cover", entity.getPersistentData().m_128459_("cnt_cover") + 1.0);
                        if (entity.getPersistentData().m_128459_("cnt_cover") > JujutsucraftModVariables.MapVariables.get((LevelAccessor)world).DomainExpansionRadius * 2.0 + 1.0) {
                            entity.getPersistentData().m_128379_("Cover", false);
                        }
                    }
                } else if ((entity instanceof LivingEntity != false && (_livEnt = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.ZONE.get()) != false ? _livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.ZONE.get()).m_19557_() : 0) < 10 && entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).m_9236_().m_5776_()) {
                    _entity.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.ZONE.get(), 10, 0, false, false));
                }
                if (!entity.getPersistentData().m_128471_("Cover")) {
                    if (entity instanceof Player) {
                        if (tick_1 % 20.0 == 0.0) {
                            entity.getCapability(JujutsucraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent((NonNullConsumer)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)V, lambda$execute$2(net.minecraft.world.entity.Entity net.mcreator.jujutsucraft.network.JujutsucraftModVariables$PlayerVariables ), (Lnet/mcreator/jujutsucraft/network/JujutsucraftModVariables$PlayerVariables;)V)((Entity)entity));
                        }
                    } else if (entity.getPersistentData().m_128459_("cnt_target") > 5.0) {
                        entity.getPersistentData().m_128347_("cnt_domain_cancel", 0.0);
                    } else {
                        entity.getPersistentData().m_128347_("cnt_domain_cancel", entity.getPersistentData().m_128459_("cnt_domain_cancel") + 1.0);
                        v21 = entity.getPersistentData().m_128459_("cnt_domain_cancel");
                        v22 = entity instanceof LivingEntity != false && (_livEnt158 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.SUKUNA_EFFECT.get()) != false ? 600 : 100;
                        if (v21 > (double)v22 && entity instanceof LivingEntity) {
                            _entity = (LivingEntity)entity;
                            _entity.m_21195_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get());
                        }
                    }
                }
                break block83;
            }
            if (entity instanceof LivingEntity) {
                _entity = (LivingEntity)entity;
                _entity.m_21195_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get());
            }
        }
    }

    private static /* synthetic */ void lambda$execute$2(Entity entity, JujutsucraftModVariables.PlayerVariables capability) {
        capability.PlayerCursePowerChange -= 20.0;
        if (capability.PlayerCursePower + capability.PlayerCursePowerChange <= 0.0 && entity instanceof LivingEntity) {
            LivingEntity _entity = (LivingEntity)entity;
            _entity.m_21195_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get());
        }
    }

    private static /* synthetic */ boolean lambda$execute$1(Entity e) {
        return true;
    }

    private static /* synthetic */ boolean lambda$execute$0(Entity e) {
        return true;
    }
}
