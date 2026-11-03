/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.mcreator.jujutsucraft.init.JujutsucraftModMobEffects
 *  net.mcreator.jujutsucraft.procedures.GetDistanceProcedure
 *  net.mcreator.jujutsucraft.procedures.KeySimpleDomainOnKeyPressedProcedure
 *  net.mcreator.jujutsucraft.procedures.PlayAnimationItemProcedure
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
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.LevelAccessor
 */
package net.mcreator.jujutsucraft.procedures;

import net.mcreator.jujutsucraft.init.JujutsucraftModMobEffects;
import net.mcreator.jujutsucraft.procedures.AIActive2Procedure;
import net.mcreator.jujutsucraft.procedures.GetDistanceProcedure;
import net.mcreator.jujutsucraft.procedures.KeySimpleDomainOnKeyPressedProcedure;
import net.mcreator.jujutsucraft.procedures.PlayAnimationItemProcedure;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;

public class AIActiveProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null) {
            return;
        }
        LivingEntity target_entity = null;
        double level_jump_set = 0.0;
        double distance = 0.0;
        double level = 0.0;
        double level_speed_set = 0.0;
        double level_speed = 0.0;
        double skill = 0.0;
        double level_jump = 0.0;
        boolean hasCooldown = false;
        boolean logic_run = false;
        boolean simpleDomainning = false;
        boolean simpleDomain = false;
        boolean canFallingBlossomEmotion = false;
        boolean hasFallingBlossom = false;
        boolean canSimpleDomain = false;
        ItemStack item_mainHand = ItemStack.f_41583_;
        ItemStack item_offHand = ItemStack.f_41583_;
        if (!(entity instanceof LivingEntity)) {
            return;
        }
        LivingEntity livingEntity = (LivingEntity)entity;
        skill = entity.getPersistentData().m_128459_("skill");
        if (entity instanceof Mob) {
            Mob _mobEnt = (Mob)entity;
            v0 = _mobEnt.m_5448_();
        } else {
            v0 = target_entity = null;
        }
        if (skill == 0.0 && entity.f_19797_ % 12 == 0) {
            canSimpleDomain = entity.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("jujutsucraft:can_use_simple_domain")));
            canFallingBlossomEmotion = entity.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("jujutsucraft:can_use_falling_blossom_emotion")));
            if (canSimpleDomain || canFallingBlossomEmotion) {
                int unstableAmp;
                hasFallingBlossom = livingEntity.m_21023_((MobEffect)JujutsucraftModMobEffects.FALLING_BLOSSOM_EMOTION.get());
                int simpleDomainAmp = livingEntity.m_21023_((MobEffect)JujutsucraftModMobEffects.SIMPLE_DOMAIN.get()) ? livingEntity.m_21124_((MobEffect)JujutsucraftModMobEffects.SIMPLE_DOMAIN.get()).m_19564_() : -1;
                simpleDomainning = simpleDomainAmp > 0 || hasFallingBlossom;
                simpleDomain = false;
                hasCooldown = livingEntity.m_21023_((MobEffect)JujutsucraftModMobEffects.COOLDOWN_TIME_SIMPLE_DOMAIN.get());
                MobEffectInstance neutralEffect = livingEntity.m_21124_((MobEffect)JujutsucraftModMobEffects.NEUTRALIZATION.get());
                int neutralAmp = neutralEffect != null ? neutralEffect.m_19564_() : -1;
                int neutralDur = neutralEffect != null ? neutralEffect.m_19557_() : 0;
                int n = unstableAmp = livingEntity.m_21023_((MobEffect)JujutsucraftModMobEffects.UNSTABLE.get()) ? livingEntity.m_21124_((MobEffect)JujutsucraftModMobEffects.UNSTABLE.get()).m_19564_() : -1;
                if (!(livingEntity.m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()) || neutralAmp <= 0 || neutralAmp == 37 && unstableAmp > 0 || neutralAmp == 39 || neutralDur < 5)) {
                    if (simpleDomainning) {
                        if (hasFallingBlossom && canSimpleDomain && !hasCooldown) {
                            simpleDomain = true;
                        }
                    } else if (canFallingBlossomEmotion || !hasCooldown) {
                        entity.getPersistentData().m_128347_("cnt_simpleDomain_spread", entity.getPersistentData().m_128459_("cnt_simpleDomain_spread") + 1.0);
                        if (entity.getPersistentData().m_128459_("cnt_simpleDomain_spread") > 2.0) {
                            simpleDomain = true;
                        }
                    }
                } else if (simpleDomainning) {
                    simpleDomain = true;
                }
                if (simpleDomain) {
                    KeySimpleDomainOnKeyPressedProcedure.execute((LevelAccessor)world, (double)x, (double)y, (double)z, (Entity)entity);
                    entity.getPersistentData().m_128347_("cnt_simpleDomain_spread", 0.0);
                }
            }
        }
        if (target_entity instanceof LivingEntity && entity.getPersistentData().m_128459_("cnt_target") > 10.0) {
            MobEffectInstance speedEffect;
            int speedAmp;
            MobEffectInstance combatCool;
            LivingEntity _livEnt;
            level = Math.min(entity instanceof LivingEntity && (_livEnt = (LivingEntity)entity).m_21023_(MobEffects.f_19600_) ? _livEnt.m_21124_(MobEffects.f_19600_).m_19564_() : 0, 30);
            distance = GetDistanceProcedure.execute((Entity)entity);
            logic_run = false;
            if (distance > 32.0) {
                logic_run = true;
            }
            if (entity.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("jujutsucraft:combat_type")))) {
                if (distance > 2.0) {
                    logic_run = true;
                }
            } else {
                if (target_entity.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("jujutsucraft:combat_type")))) {
                    logic_run = false;
                }
                if (distance > 4.0) {
                    int n = entity instanceof LivingEntity && (_livEnt = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.COOLDOWN_TIME.get()) ? _livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.COOLDOWN_TIME.get()).m_19557_() : 0;
                    if ((double)n > distance * 2.0) {
                        logic_run = true;
                    }
                }
            }
            if (distance < 12.0 && (combatCool = livingEntity.m_21124_((MobEffect)JujutsucraftModMobEffects.COOLDOWN_TIME_COMBAT.get())) != null && combatCool.m_19564_() > 0 && combatCool.m_19557_() > 15 && target_entity.getPersistentData().m_128459_("skill") != 0.0 && target_entity.getPersistentData().m_128459_("Damage") > 0.0 && target_entity.getPersistentData().m_128471_("attack")) {
                logic_run = false;
            }
            int n = speedAmp = (speedEffect = livingEntity.m_21124_(MobEffects.f_19596_)) != null ? speedEffect.m_19564_() : 0;
            if (logic_run) {
                int slowAmp;
                level_speed = Math.round(Math.min(level / 4.0, 6.0));
                level_speed_set = speedAmp;
                if (speedEffect != null || entity.m_20096_()) {
                    int speedDur;
                    level_speed = Math.min(Math.round(level / 4.0), 3L);
                    level_speed_set = speedEffect != null ? (double)(speedAmp + 1) : 0.0;
                    int n2 = speedDur = speedEffect != null ? speedEffect.m_19557_() : 0;
                    if (speedDur <= 2 && !entity.m_9236_().m_5776_()) {
                        livingEntity.m_7292_(new MobEffectInstance(MobEffects.f_19596_, 10, (int)Math.min(level_speed_set, level_speed), false, false));
                    }
                }
                int n3 = slowAmp = livingEntity.m_21023_(MobEffects.f_19597_) ? livingEntity.m_21124_(MobEffects.f_19597_).m_19564_() : -1;
                if (slowAmp < 6) {
                    if (entity.m_20184_().m_7096_() * entity.m_20184_().m_7096_() + entity.m_20184_().m_7094_() * entity.m_20184_().m_7094_() > 0.04) {
                        entity.m_6858_(true);
                        if (skill == 0.0) {
                            item_mainHand = livingEntity.m_21205_();
                            item_offHand = livingEntity.m_21206_();
                            PlayAnimationItemProcedure.execute((Entity)entity, (ItemStack)item_mainHand, (String)"sprint");
                            PlayAnimationItemProcedure.execute((Entity)entity, (ItemStack)item_offHand, (String)"sprint");
                        }
                    } else {
                        entity.m_6858_(false);
                    }
                } else {
                    entity.m_6858_(false);
                }
            } else {
                entity.m_6858_(false);
            }
            if (distance > 12.0 || !entity.m_20096_()) {
                int jumpDur;
                level_jump = Math.round(Math.min(level / 4.0, 6.0));
                MobEffectInstance jumpEffect = livingEntity.m_21124_(MobEffects.f_19603_);
                level_jump_set = !entity.m_20096_() ? (jumpEffect != null ? (double)(jumpEffect.m_19564_() + 1) : 0.0) : (double)Math.round(Math.min(level_speed_set / 4.0, level / 4.0));
                int n4 = jumpDur = jumpEffect != null ? jumpEffect.m_19557_() : 0;
                if (jumpDur < 2 && !entity.m_9236_().m_5776_()) {
                    livingEntity.m_7292_(new MobEffectInstance(MobEffects.f_19603_, 8, (int)Math.min(level_jump, level_jump_set), false, false));
                }
            }
        } else {
            entity.m_6858_(false);
        }
        AIActive2Procedure.execute(world, x, y, z, entity);
    }
}
