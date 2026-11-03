/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.mcreator.jujutsucraft.entity.GojoSatoruSchoolDaysEntity
 *  net.mcreator.jujutsucraft.entity.UroTakakoEntity
 *  net.mcreator.jujutsucraft.init.JujutsucraftModItems
 *  net.mcreator.jujutsucraft.init.JujutsucraftModMobEffects
 *  net.mcreator.jujutsucraft.procedures.AntiInfinityProcedure
 *  net.mcreator.jujutsucraft.procedures.DaitenguFanRightclickedProcedure
 *  net.mcreator.jujutsucraft.procedures.DetectEnemyProjectileProcedure
 *  net.mcreator.jujutsucraft.procedures.DragonBoneRightclickedProcedure
 *  net.mcreator.jujutsucraft.procedures.GetDistanceProcedure
 *  net.mcreator.jujutsucraft.procedures.InsideSolidCalculateProcedure
 *  net.mcreator.jujutsucraft.procedures.KeyReverseCursedTechniqueOnKeyPressedProcedure
 *  net.mcreator.jujutsucraft.procedures.KeyReverseCursedTechniqueOnKeyReleasedProcedure
 *  net.mcreator.jujutsucraft.procedures.KeySpaceOnKeyPressedProcedure
 *  net.mcreator.jujutsucraft.procedures.RotateEntityProcedure
 *  net.mcreator.jujutsucraft.procedures.StartGuardProcedure
 *  net.mcreator.jujutsucraft.procedures.SupremeMartialSolutionRightClickedInAirProcedure
 *  net.mcreator.jujutsucraft.procedures.WhenBackStepProcedure
 *  net.minecraft.commands.CommandSource
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.tags.TagKey
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.LiquidBlock
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.common.ForgeMod
 */
package net.mcreator.jujutsucraft.procedures;

import java.util.List;
import net.mcreator.jujutsucraft.entity.GojoSatoruSchoolDaysEntity;
import net.mcreator.jujutsucraft.entity.UroTakakoEntity;
import net.mcreator.jujutsucraft.init.JujutsucraftModItems;
import net.mcreator.jujutsucraft.init.JujutsucraftModMobEffects;
import net.mcreator.jujutsucraft.procedures.AntiInfinityProcedure;
import net.mcreator.jujutsucraft.procedures.DaitenguFanRightclickedProcedure;
import net.mcreator.jujutsucraft.procedures.DetectEnemyProjectileProcedure;
import net.mcreator.jujutsucraft.procedures.DragonBoneRightclickedProcedure;
import net.mcreator.jujutsucraft.procedures.GetDistanceProcedure;
import net.mcreator.jujutsucraft.procedures.InsideSolidCalculateProcedure;
import net.mcreator.jujutsucraft.procedures.KeyReverseCursedTechniqueOnKeyPressedProcedure;
import net.mcreator.jujutsucraft.procedures.KeyReverseCursedTechniqueOnKeyReleasedProcedure;
import net.mcreator.jujutsucraft.procedures.KeySpaceOnKeyPressedProcedure;
import net.mcreator.jujutsucraft.procedures.LogicAttackProcedure;
import net.mcreator.jujutsucraft.procedures.RotateEntityProcedure;
import net.mcreator.jujutsucraft.procedures.StartGuardProcedure;
import net.mcreator.jujutsucraft.procedures.SupremeMartialSolutionRightClickedInAirProcedure;
import net.mcreator.jujutsucraft.procedures.WhenBackStepProcedure;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;

public class AIActive2Procedure {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        LivingEntity _livingEntity150;
        LivingEntity _entity;
        Entity _ent;
        LivingEntity _livEnt;
        LivingEntity _livEnt2;
        double speed;
        double x_power;
        double fix;
        double z_power;
        double y_power;
        double distance;
        boolean using2;
        boolean using;
        boolean target;
        boolean test;
        ItemStack offItem;
        ItemStack mainItem;
        LivingEntity target_entity;
        block111: {
            block112: {
                float f;
                float f2;
                LivingEntity _livEnt108;
                GojoSatoruSchoolDaysEntity _datEntL67;
                float f3;
                LivingEntity _livEnt62;
                LivingEntity _livEnt3;
                LivingEntity _livEnt6;
                LivingEntity livingEntity;
                if (entity == null) {
                    return;
                }
                target_entity = null;
                mainItem = ItemStack.f_41583_;
                offItem = ItemStack.f_41583_;
                boolean logic_guard = false;
                boolean logic_heal = false;
                boolean logic_heal_cancel = false;
                test = false;
                target = false;
                boolean logic_avoid = false;
                using = false;
                boolean output = false;
                boolean infinity = false;
                using2 = false;
                double x_knockback = 0.0;
                double y_knockback = 0.0;
                double z_knockback = 0.0;
                double dis = 0.0;
                double old_skill = 0.0;
                double limit = 0.0;
                distance = 0.0;
                y_power = 0.0;
                z_power = 0.0;
                fix = 0.0;
                x_power = 0.0;
                speed = 0.0;
                double level_neutralization = 0.0;
                logic_heal_cancel = true;
                if (entity instanceof Mob) {
                    Mob _mobEnt = (Mob)entity;
                    livingEntity = _mobEnt.m_5448_();
                } else {
                    livingEntity = null;
                }
                target_entity = livingEntity;
                entity.getPersistentData().m_128347_("cnt_guard", Math.max(entity.getPersistentData().m_128459_("cnt_guard") - 1.0, 0.0));
                entity.getPersistentData().m_128347_("cnt_backstep", Math.max(entity.getPersistentData().m_128459_("cnt_backstep") - 1.0, 0.0));
                double d = level_neutralization = entity instanceof LivingEntity && (_livEnt2 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.NEUTRALIZATION.get()) ? (double)_livEnt2.m_21124_((MobEffect)JujutsucraftModMobEffects.NEUTRALIZATION.get()).m_19564_() : 0.0;
                if (entity instanceof LivingEntity && (_livEnt6 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.NEUTRALIZATION.get()) && level_neutralization == 37.0) {
                    return;
                }
                if (entity.getPersistentData().m_128459_("cnt_target") > 6.0) {
                    test = entity.getPersistentData().m_128459_("cnt_target") % 5.0 == 4.0;
                    target = target_entity instanceof LivingEntity;
                }
                distance = 99.0;
                if (entity.getPersistentData().m_128459_("skill") == 0.0) {
                    if (target) {
                        LivingEntity _livEnt13;
                        LivingEntity _livEnt12;
                        LivingEntity _livEnt11;
                        distance = GetDistanceProcedure.execute((Entity)entity);
                        boolean bl = infinity = entity instanceof LivingEntity && (_livEnt11 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.INFINITY_EFFECT.get()) && (!(entity instanceof LivingEntity) || !(_livEnt12 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.NEUTRALIZATION.get()));
                        if (test) {
                            logic_heal_cancel = false;
                        }
                        if ((entity instanceof LivingEntity && (_livEnt13 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.DAMAGE_EFFECT.get()) || target_entity.getPersistentData().m_128459_("skill") != 0.0 && target_entity.getPersistentData().m_128459_("Damage") > 0.0) && (!infinity || AntiInfinityProcedure.execute((Entity)target_entity))) {
                            logic_heal_cancel = true;
                            if (test) {
                                logic_guard = true;
                            }
                        }
                        if (test) {
                            LivingEntity _livEnt35;
                            List _entfound;
                            Vec3 _center;
                            LivingEntity _livEnt16;
                            if (!(logic_guard || entity instanceof LivingEntity && (_livEnt16 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.SIMPLE_DOMAIN.get()) || !(level_neutralization > 0.0) || level_neutralization == 37.0)) {
                                logic_guard = true;
                            }
                            if (!logic_guard) {
                                LivingEntity _livEnt18;
                                dis = 24.0;
                                if (entity instanceof UroTakakoEntity || entity instanceof LivingEntity && (_livEnt18 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.SUKUNA_EFFECT.get())) {
                                    dis = 36.0;
                                }
                                _center = new Vec3(x, y, z);
                                _entfound = world.m_6443_(Entity.class, new AABB(_center, _center).m_82400_(dis / 2.0), e -> true);
                                for (Object entityiterator : _entfound) {
                                    if (entity == entityiterator || infinity && !AntiInfinityProcedure.execute((Entity)entityiterator)) continue;
                                    if (entityiterator instanceof Projectile) {
                                        if (!DetectEnemyProjectileProcedure.execute((Entity)entity, (Entity)entityiterator)) continue;
                                        logic_avoid = true;
                                        logic_guard = true;
                                        break;
                                    }
                                    if (entityiterator.getPersistentData().m_128459_("skill") == 0.0 && !entityiterator.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("forge:ranged_ammo"))) || !(entityiterator.getPersistentData().m_128459_("Damage") > 0.0) || !LogicAttackProcedure.execute(world, entity, (Entity)entityiterator)) continue;
                                    logic_avoid = true;
                                    logic_guard = true;
                                    break;
                                }
                            }
                            if (!logic_guard) {
                                dis = 16.0;
                                if (entity instanceof UroTakakoEntity) {
                                    dis = 32.0;
                                }
                                _center = new Vec3(x, y, z);
                                _entfound = world.m_6443_(Entity.class, new AABB(_center, _center).m_82400_(dis / 2.0), e -> true);
                                for (Object entityiterator : _entfound) {
                                    if (entity == entityiterator || infinity && !AntiInfinityProcedure.execute((Entity)entityiterator) || entityiterator.getPersistentData().m_128459_("skill") == 0.0 || !(entityiterator.getPersistentData().m_128459_("Damage") > 0.0) && !entityiterator.getPersistentData().m_128471_("PRESS_Z") || !LogicAttackProcedure.execute(world, entity, (Entity)entityiterator)) continue;
                                    logic_avoid = true;
                                    logic_guard = true;
                                    break;
                                }
                            }
                            if (logic_avoid) {
                                LivingEntity _livEnt32;
                                if (entity.getPersistentData().m_128459_("skill") != 0.0) {
                                    logic_avoid = false;
                                }
                                if (entity instanceof LivingEntity && (_livEnt32 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.COOLDOWN_TIME_COMBAT.get())) {
                                    if ((entity instanceof LivingEntity && (_livEnt3 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.COOLDOWN_TIME_COMBAT.get()) ? _livEnt3.m_21124_((MobEffect)JujutsucraftModMobEffects.COOLDOWN_TIME_COMBAT.get()).m_19557_() : 0) < 6) {
                                        logic_avoid = false;
                                    }
                                } else {
                                    logic_avoid = false;
                                }
                            }
                            if (entity.getPersistentData().m_128471_("CursedSpirit") && entity instanceof LivingEntity && (_livEnt35 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.REVERSE_CURSED_TECHNIQUE.get())) {
                                logic_avoid = true;
                            }
                        }
                        boolean bl2 = logic_heal_cancel = logic_heal_cancel || logic_guard;
                        if (!(logic_avoid || infinity && !AntiInfinityProcedure.execute((Entity)target_entity) || (entity instanceof LivingEntity && (_livEnt2 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.COOLDOWN_TIME.get()) ? _livEnt2.m_21124_((MobEffect)JujutsucraftModMobEffects.COOLDOWN_TIME.get()).m_19557_() : 0) <= 10 && !(entity.getPersistentData().m_128459_("cnt_x") < 0.0))) {
                            LivingEntity _livEnt40;
                            if ((entity instanceof LivingEntity && (_livEnt3 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.COOLDOWN_TIME_COMBAT.get()) ? _livEnt3.m_21124_((MobEffect)JujutsucraftModMobEffects.COOLDOWN_TIME_COMBAT.get()).m_19564_() : 0) > 0 && (entity instanceof LivingEntity && (_livEnt = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.COOLDOWN_TIME_COMBAT.get()) ? _livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.COOLDOWN_TIME_COMBAT.get()).m_19557_() : 0) > 10 && distance < 6.0) {
                                logic_avoid = true;
                            }
                            if (entity instanceof LivingEntity && (_livEnt40 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.COOLDOWN_TIME_COMBAT.get()) && distance < 24.0 && target_entity.getPersistentData().m_128459_("skill") != 0.0 && target_entity.getPersistentData().m_128459_("skill") > -900.0) {
                                logic_avoid = true;
                            }
                        }
                        if (entity.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("jujutsucraft:no_guard")))) {
                            logic_guard = false;
                        }
                        logic_heal_cancel = logic_heal_cancel || logic_avoid;
                    } else {
                        logic_heal_cancel = false;
                    }
                }
                if (logic_guard) {
                    LivingEntity _livEnt50;
                    StartGuardProcedure.execute((LevelAccessor)world, (Entity)entity);
                    entity.m_6858_(false);
                    entity.getPersistentData().m_128347_("cnt_guard", 20.0);
                    if (entity.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("jujutsucraft:can_use_domain_amplification"))) && (!(target_entity instanceof LivingEntity) || target_entity.getPersistentData().m_128459_("skill") % 100.0 > 2.0 && !target_entity.getPersistentData().m_128471_("attack") && target_entity instanceof LivingEntity && (_livEnt50 = target_entity).m_21023_((MobEffect)JujutsucraftModMobEffects.ATTACKING.get())) && !(_ent = entity).m_9236_().m_5776_() && _ent.m_20194_() != null) {
                        _ent.m_20194_().m_129892_().m_230957_(new CommandSourceStack(CommandSource.f_80164_, _ent.m_20182_(), _ent.m_20155_(), _ent.m_9236_() instanceof ServerLevel ? (ServerLevel)_ent.m_9236_() : null, 4, _ent.m_7755_().getString(), _ent.m_5446_(), _ent.m_9236_().m_7654_(), _ent), "effect give @s jujutsucraft:domain_amplification 1 " + Math.round(entity instanceof LivingEntity && (_livEnt = (LivingEntity)entity).m_21023_(MobEffects.f_19600_) ? (float)_livEnt.m_21124_(MobEffects.f_19600_).m_19564_() : 0.0f) + " false");
                    }
                }
                if (logic_avoid && !entity.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("jujutsucraft:no_guard")))) {
                    LivingEntity _livEnt60;
                    if (entity.m_20096_() && entity.getPersistentData().m_128459_("cnt_target") > 6.0) {
                        entity.getPersistentData().m_128379_("PRESS_S", true);
                        WhenBackStepProcedure.execute((LevelAccessor)world, (Entity)entity);
                        entity.getPersistentData().m_128379_("PRESS_S", false);
                        entity.getPersistentData().m_128347_("cnt_backstep", 20.0);
                        if (entity instanceof Mob) {
                            Mob _entity2 = (Mob)entity;
                            _entity2.m_21573_().m_26573_();
                        }
                    }
                    if (entity instanceof LivingEntity && (_livEnt60 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.FLY_EFFECT.get())) {
                        entity.getPersistentData().m_128347_("mode_fly", (double)(Math.random() < 0.5 ? -2 : -3));
                    }
                }
                if (test && (logic_avoid || Math.random() < 0.2) && GetDistanceProcedure.execute((Entity)entity) > 8.0 && entity instanceof LivingEntity && (_livEnt62 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.DOUBLE_JUMP_EFFECT.get())) {
                    KeySpaceOnKeyPressedProcedure.execute((LevelAccessor)world, (double)x, (double)y, (double)z, (Entity)entity);
                }
                if (entity instanceof LivingEntity) {
                    _livEnt2 = (LivingEntity)entity;
                    f3 = _livEnt2.m_21233_();
                } else {
                    f3 = -1.0f;
                }
                limit = f3 >= 800.0f ? 150 : 50;
                if (!entity.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("jujutsucraft:can_use_reverse_cursed_technique"))) && (!entity.getPersistentData().m_128441_("entity_can_use_rct") || !entity.getPersistentData().m_128471_("entity_can_use_rct")) && (!(entity instanceof GojoSatoruSchoolDaysEntity) || !((Boolean)(_datEntL67 = (GojoSatoruSchoolDaysEntity)entity).m_20088_().m_135370_(GojoSatoruSchoolDaysEntity.DATA_awaking)).booleanValue())) break block111;
                output = entity.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("jujutsucraft:can_use_reverse_cursed_technique_output")));
                if (!logic_heal_cancel) {
                    float f4;
                    float f5;
                    if (distance < 8.0) {
                        LivingEntity _livEnt71;
                        float f6;
                        float f7;
                        logic_heal_cancel = true;
                        if (entity instanceof LivingEntity) {
                            _livEnt3 = (LivingEntity)entity;
                            f7 = _livEnt3.m_21223_();
                        } else {
                            f7 = -1.0f;
                        }
                        double d2 = f7;
                        if (entity instanceof LivingEntity) {
                            _livEnt = (LivingEntity)entity;
                            f6 = _livEnt.m_21233_();
                        } else {
                            f6 = -1.0f;
                        }
                        if (d2 <= (double)f6 * 0.5) {
                            logic_heal_cancel = false;
                        }
                        if (!(entity instanceof LivingEntity && (_livEnt71 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()) || (entity instanceof LivingEntity && (_livEnt = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.NEUTRALIZATION.get()) ? _livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.NEUTRALIZATION.get()).m_19564_() : 0) < 1)) {
                            logic_heal_cancel = false;
                        }
                    }
                    if (entity instanceof LivingEntity) {
                        _livEnt3 = (LivingEntity)entity;
                        f5 = _livEnt3.m_21223_();
                    } else {
                        f5 = -1.0f;
                    }
                    if (entity instanceof LivingEntity) {
                        _livEnt = (LivingEntity)entity;
                        f4 = _livEnt.m_21233_();
                    } else {
                        f4 = -1.0f;
                    }
                    if (f5 >= f4) {
                        logic_heal_cancel = true;
                    }
                }
                if (entity.getPersistentData().m_128459_("cnt_reverse_lim") + 1.0 >= limit) {
                    logic_heal_cancel = true;
                    output = false;
                }
                if (output) {
                    output = false;
                    if (entity.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("forge:cant_combat"))) || entity.getPersistentData().m_128459_("cnt_target") <= 6.0) {
                        dis = entity.m_20205_() * 1.0f;
                        using = false;
                        Vec3 _center = new Vec3(entity.m_20185_() + entity.m_20154_().f_82479_ * dis, entity.m_20186_() + (double)entity.m_20206_() * 0.9 + entity.m_20154_().f_82480_ * dis, entity.m_20189_() + entity.m_20154_().f_82481_ * dis);
                        List _entfound = world.m_6443_(Entity.class, new AABB(_center, _center).m_82400_(dis * 3.0 / 2.0), e -> true);
                        for (Entity entityiterator : _entfound) {
                            if (!(entityiterator instanceof LivingEntity) || entityiterator.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("forge:not_living"))) || !entityiterator.m_6084_() || entity == entityiterator) continue;
                            using = false;
                            if (LogicAttackProcedure.execute(world, entity, entityiterator)) {
                                if (entityiterator.getPersistentData().m_128471_("CursedSpirit")) {
                                    using = true;
                                }
                            } else if (!entityiterator.getPersistentData().m_128471_("CursedSpirit")) {
                                float f8;
                                float f9;
                                if (entityiterator instanceof LivingEntity) {
                                    LivingEntity _livEnt4 = (LivingEntity)entityiterator;
                                    f9 = _livEnt4.m_21223_();
                                } else {
                                    f9 = -1.0f;
                                }
                                if (entityiterator instanceof LivingEntity) {
                                    LivingEntity _livEnt5 = (LivingEntity)entityiterator;
                                    f8 = _livEnt5.m_21233_();
                                } else {
                                    f8 = -1.0f;
                                }
                                if (f9 < f8) {
                                    using = true;
                                }
                            }
                            if (!using) continue;
                            output = true;
                            logic_heal_cancel = false;
                            break;
                        }
                    }
                }
                if (logic_heal_cancel) {
                    logic_heal = false;
                } else {
                    logic_heal = true;
                    if (output) {
                        entity.getPersistentData().m_128347_("cnt_reverse_test", Math.max(entity.getPersistentData().m_128459_("cnt_reverse_test"), 100.0));
                    }
                }
                entity.getPersistentData().m_128347_("cnt_reverse_test", entity.getPersistentData().m_128459_("cnt_reverse_test") + 1.0);
                if (logic_heal) {
                    if (entity.getPersistentData().m_128459_("cnt_reverse_test") > 100.0) {
                        LivingEntity _livEnt101;
                        entity.getPersistentData().m_128347_("cnt_reverse_test", 0.0);
                        if (!(entity instanceof LivingEntity && (_livEnt101 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.REVERSE_CURSED_TECHNIQUE.get()) || entity.getPersistentData().m_128471_("PRESS_M"))) {
                            if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).m_9236_().m_5776_()) {
                                _entity.m_7292_(new MobEffectInstance(MobEffects.f_19597_, 18, 9, false, false));
                            }
                            entity.getPersistentData().m_128347_("cnt_reverse", 15.0);
                            KeyReverseCursedTechniqueOnKeyPressedProcedure.execute((Entity)entity);
                        }
                    }
                } else if (!target) {
                    entity.getPersistentData().m_128347_("cnt_reverse_lim", Math.max(entity.getPersistentData().m_128459_("cnt_reverse_lim") - 0.1, 0.0));
                }
                if (!entity.getPersistentData().m_128471_("PRESS_M")) break block111;
                if (entity instanceof LivingEntity && (_livEnt108 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.REVERSE_CURSED_TECHNIQUE.get())) {
                    entity.getPersistentData().m_128347_("cnt_reverse_lim", entity.getPersistentData().m_128459_("cnt_reverse_lim") + 1.0);
                }
                entity.getPersistentData().m_128347_("cnt_reverse", Math.max(entity.getPersistentData().m_128459_("cnt_reverse") - 1.0, 0.0));
                if (logic_heal) break block111;
                if (entity.getPersistentData().m_128459_("cnt_reverse") <= 0.0 || entity.getPersistentData().m_128459_("cnt_reverse_lim") >= limit) break block112;
                if (entity instanceof LivingEntity) {
                    _livEnt3 = (LivingEntity)entity;
                    f2 = _livEnt3.m_21223_();
                } else {
                    f2 = -1.0f;
                }
                if (entity instanceof LivingEntity) {
                    _livEnt = (LivingEntity)entity;
                    f = _livEnt.m_21233_();
                } else {
                    f = -1.0f;
                }
                if (!(f2 >= f) || output) break block111;
            }
            entity.getPersistentData().m_128347_("cnt_reverse", 0.0);
            KeyReverseCursedTechniqueOnKeyReleasedProcedure.execute((Entity)entity);
        }
        if (target && entity.getPersistentData().m_128459_("skill") == 0.0) {
            entity.getPersistentData().m_128347_("cnt_weapon_main", entity.getPersistentData().m_128459_("cnt_weapon_main") + 1.0);
            entity.getPersistentData().m_128347_("cnt_weapon_off", entity.getPersistentData().m_128459_("cnt_weapon_off") + 1.0);
            using = false;
            using2 = false;
            mainItem = ItemStack.f_41583_.m_41777_();
            offItem = ItemStack.f_41583_.m_41777_();
            if (entity.getPersistentData().m_128459_("cnt_weapon_main") > 20.0) {
                entity.getPersistentData().m_128347_("cnt_weapon_main", 0.0);
                entity.getPersistentData().m_128347_("cnt_weapon_off", Math.min(entity.getPersistentData().m_128459_("cnt_weapon_off"), 100.0));
                if (Math.abs(entity.getPersistentData().m_128459_("cnt_weapon_main") - entity.getPersistentData().m_128459_("cnt_weapon_off")) < 20.0) {
                    entity.getPersistentData().m_128347_("cnt_weapon_main", entity.getPersistentData().m_128459_("cnt_weapon_off") - 20.0);
                }
                using = true;
                if (entity instanceof LivingEntity) {
                    _livEnt2 = (LivingEntity)entity;
                    v14 = _livEnt2.m_21205_();
                } else {
                    v14 = mainItem = ItemStack.f_41583_;
                }
            }
            if (entity.getPersistentData().m_128459_("cnt_weapon_off") > 60.0) {
                entity.getPersistentData().m_128347_("cnt_weapon_off", 40.0);
                if (Math.abs(entity.getPersistentData().m_128459_("cnt_weapon_main") - entity.getPersistentData().m_128459_("cnt_weapon_off")) < 20.0) {
                    entity.getPersistentData().m_128347_("cnt_weapon_off", entity.getPersistentData().m_128459_("cnt_weapon_main") - 20.0);
                }
                using2 = true;
                if (entity instanceof LivingEntity) {
                    _livEnt2 = (LivingEntity)entity;
                    v15 = _livEnt2.m_21206_();
                } else {
                    v15 = offItem = ItemStack.f_41583_;
                }
            }
            if (using ^ using2) {
                fix = 0.0;
                if ((using && mainItem.m_41720_() == JujutsucraftModItems.SUPREME_MARTIAL_SOLUTION.get() || using2 && offItem.m_41720_() == JujutsucraftModItems.SUPREME_MARTIAL_SOLUTION.get()) && distance < 40.0) {
                    SupremeMartialSolutionRightClickedInAirProcedure.execute((LevelAccessor)world, (Entity)entity);
                    fix = 200.0;
                }
                if ((using && mainItem.m_41720_() == JujutsucraftModItems.DAITENGU_FAN.get() || using2 && offItem.m_41720_() == JujutsucraftModItems.DAITENGU_FAN.get()) && distance < 24.0) {
                    DaitenguFanRightclickedProcedure.execute((LevelAccessor)world, (double)x, (double)y, (double)z, (Entity)entity);
                    fix = 200.0;
                }
                if ((using && mainItem.m_41720_() == JujutsucraftModItems.DRAGON_BONE.get() || using2 && offItem.m_41720_() == JujutsucraftModItems.DRAGON_BONE.get()) && distance < 32.0) {
                    DragonBoneRightclickedProcedure.execute((LevelAccessor)world, (Entity)entity);
                    fix = 100.0;
                }
                if (fix > 0.0) {
                    if (using) {
                        entity.getPersistentData().m_128347_("cnt_weapon_main", 20.0 - fix);
                    } else if (using2) {
                        entity.getPersistentData().m_128347_("cnt_weapon_off", 60.0 - fix);
                    }
                }
            }
        }
        if (test && entity.getPersistentData().m_128459_("skill") == 0.0 && entity.m_20072_() && entity.m_20142_() && entity instanceof LivingEntity && (_livingEntity150 = (LivingEntity)entity).m_21204_().m_22171_((Attribute)ForgeMod.SWIM_SPEED.get())) {
            if (target_entity instanceof LivingEntity) {
                RotateEntityProcedure.execute((double)target_entity.m_20185_(), (double)(target_entity.m_20186_() + (double)target_entity.m_20206_() * 0.5), (double)target_entity.m_20189_(), (Entity)entity);
                if (entity.m_20146_() < 4) {
                    _ent = entity;
                    _ent.m_146922_(entity.m_146908_());
                    _ent.m_146926_(-90.0f);
                    _ent.m_5618_(_ent.m_146908_());
                    _ent.m_5616_(_ent.m_146908_());
                    _ent.f_19859_ = _ent.m_146908_();
                    _ent.f_19860_ = _ent.m_146909_();
                    if (_ent instanceof LivingEntity) {
                        _entity = (LivingEntity)_ent;
                        _entity.f_20884_ = _entity.m_146908_();
                        _entity.f_20886_ = _entity.m_146908_();
                    }
                } else {
                    fix = entity.m_20186_();
                    for (int index0 = 0; index0 < (int)Math.round(Math.ceil(entity.m_20206_())); ++index0) {
                        if (!(world.m_8055_(BlockPos.m_274561_((double)entity.m_20185_(), (double)fix, (double)entity.m_20189_())).m_60734_() instanceof LiquidBlock)) {
                            Entity _ent2 = entity;
                            _ent2.m_146922_(entity.m_146908_());
                            _ent2.m_146926_((float)Math.max((double)entity.m_146909_(), 22.5));
                            _ent2.m_5618_(_ent2.m_146908_());
                            _ent2.m_5616_(_ent2.m_146908_());
                            _ent2.f_19859_ = _ent2.m_146908_();
                            _ent2.f_19860_ = _ent2.m_146909_();
                            if (!(_ent2 instanceof LivingEntity)) break;
                            LivingEntity _entity3 = (LivingEntity)_ent2;
                            _entity3.f_20884_ = _entity3.m_146908_();
                            _entity3.f_20886_ = _entity3.m_146908_();
                            break;
                        }
                        fix += 1.0;
                    }
                }
            }
            if (!InsideSolidCalculateProcedure.execute((LevelAccessor)world, (double)entity.m_20185_(), (double)entity.m_20186_(), (double)entity.m_20189_(), (double)1.0, (double)(entity.m_20205_() + 1.0f))) {
                LivingEntity _livingEntity174;
                LivingEntity _livEnt172;
                fix = 1.0;
                if (entity instanceof LivingEntity && (_livEnt172 = (LivingEntity)entity).m_21023_(MobEffects.f_19597_)) {
                    fix = Math.max(fix - 0.15 * (double)((entity instanceof LivingEntity && (_livEnt = (LivingEntity)entity).m_21023_(MobEffects.f_19597_) ? _livEnt.m_21124_(MobEffects.f_19597_).m_19564_() : 0) + 1), 0.0);
                }
                speed = 0.75 * (fix *= Math.min(Math.max(entity instanceof LivingEntity && (_livingEntity174 = (LivingEntity)entity).m_21204_().m_22171_((Attribute)ForgeMod.SWIM_SPEED.get()) ? _livingEntity174.m_21051_((Attribute)ForgeMod.SWIM_SPEED.get()).m_22135_() : 0.0, 0.0), 4.0));
                x_power = entity.m_20184_().m_7096_() + entity.m_20154_().f_82479_ * speed;
                y_power = entity.m_20184_().m_7098_() + entity.m_20154_().f_82480_ * speed;
                z_power = entity.m_20184_().m_7094_() + entity.m_20154_().f_82481_ * speed;
                x_power = x_power < 0.0 ? Math.min(entity.m_20184_().m_7096_(), Math.max(x_power, speed * -1.0)) : Math.max(entity.m_20184_().m_7096_(), Math.min(x_power, speed * 1.0));
                y_power = y_power < 0.0 ? Math.min(entity.m_20184_().m_7098_(), Math.max(y_power, speed * -1.0)) : Math.max(entity.m_20184_().m_7098_(), Math.min(y_power, speed * 1.0));
                z_power = z_power < 0.0 ? Math.min(entity.m_20184_().m_7094_(), Math.max(z_power, speed * -1.0)) : Math.max(entity.m_20184_().m_7094_(), Math.min(z_power, speed * 1.0));
                entity.m_20256_(new Vec3(x_power, y_power, z_power));
                if (entity instanceof Mob) {
                    Mob _entity4 = (Mob)entity;
                    _entity4.m_21573_().m_26573_();
                }
            }
        }
    }
}
