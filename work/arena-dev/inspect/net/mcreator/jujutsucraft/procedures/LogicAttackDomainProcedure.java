/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.mcreator.jujutsucraft.entity.DomainExpansionEntityEntity
 *  net.mcreator.jujutsucraft.entity.EightHandledSwordDivergentSilaDivineGeneralMahoragaEntity
 *  net.mcreator.jujutsucraft.entity.OkkotsuYutaCullingGameEntity
 *  net.mcreator.jujutsucraft.init.JujutsucraftModItems
 *  net.mcreator.jujutsucraft.init.JujutsucraftModMobEffects
 *  net.mcreator.jujutsucraft.network.JujutsucraftModVariables
 *  net.mcreator.jujutsucraft.network.JujutsucraftModVariables$PlayerVariables
 *  net.mcreator.jujutsucraft.procedures.LogicAttackProcedure
 *  net.mcreator.jujutsucraft.procedures.LogicSimpleDomainProcedure
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.tags.TagKey
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraftforge.common.capabilities.Capability
 */
package net.mcreator.jujutsucraft.procedures;

import net.mcreator.jujutsucraft.entity.DomainExpansionEntityEntity;
import net.mcreator.jujutsucraft.entity.EightHandledSwordDivergentSilaDivineGeneralMahoragaEntity;
import net.mcreator.jujutsucraft.entity.OkkotsuYutaCullingGameEntity;
import net.mcreator.jujutsucraft.init.JujutsucraftModItems;
import net.mcreator.jujutsucraft.init.JujutsucraftModMobEffects;
import net.mcreator.jujutsucraft.network.JujutsucraftModVariables;
import net.mcreator.jujutsucraft.procedures.LogicAttackProcedure;
import net.mcreator.jujutsucraft.procedures.LogicSimpleDomainProcedure;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.common.capabilities.Capability;

public class LogicAttackDomainProcedure {
    public static boolean execute(LevelAccessor world, Entity entity, Entity entityiterator) {
        MobEffectInstance attackerDomain;
        LivingEntity le;
        if (world.m_5776_()) {
            return false;
        }
        if (entity == null || entityiterator == null) {
            return false;
        }
        if (!(entityiterator instanceof LivingEntity)) {
            return false;
        }
        LivingEntity target = (LivingEntity)entityiterator;
        if (target instanceof Player) {
            Player p = (Player)target;
            if (p.m_150110_().f_35937_ || p.m_5833_()) {
                return false;
            }
        }
        if (target instanceof DomainExpansionEntityEntity) {
            return false;
        }
        boolean isZeroCursePower = false;
        if (target instanceof Player) {
            Player pTarget = (Player)target;
            isZeroCursePower = ((JujutsucraftModVariables.PlayerVariables)pTarget.getCapability((Capability)JujutsucraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse((Object)new JujutsucraftModVariables.PlayerVariables())).PlayerCursePowerFormer == 0.0;
        } else {
            isZeroCursePower = target.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("forge:no_curse_power")));
        }
        if (isZeroCursePower) {
            MobEffectInstance domainEffect;
            if (entity instanceof LivingEntity) {
                le = (LivingEntity)entity;
                v0 = le.m_21124_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get());
            } else {
                v0 = domainEffect = null;
            }
            if (domainEffect == null || domainEffect.m_19564_() == 0) {
                return false;
            }
        }
        if (target.m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()) || target.getPersistentData().m_128459_("select") != 0.0) {
            return false;
        }
        if (entity instanceof LivingEntity) {
            le = (LivingEntity)entity;
            v1 = le.m_21124_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get());
        } else {
            v1 = attackerDomain = null;
        }
        if (target.m_21023_((MobEffect)JujutsucraftModMobEffects.SIMPLE_DOMAIN.get()) && LogicSimpleDomainProcedure.execute() && attackerDomain != null && attackerDomain.m_19564_() >= 0) {
            return false;
        }
        if (!target.m_21023_((MobEffect)JujutsucraftModMobEffects.NEUTRALIZATION.get())) {
            return false;
        }
        if (entity.getPersistentData().m_128471_("Failed") || entity.getPersistentData().m_128471_("Cover")) {
            return false;
        }
        if (entity.getPersistentData().m_128471_("onlyLiving") && target.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("forge:not_living")))) {
            return false;
        }
        double friendNum = entity.getPersistentData().m_128459_("friend_num");
        if (friendNum != 0.0 && friendNum == target.getPersistentData().m_128459_("friend_num")) {
            return false;
        }
        double myRanged = entity.getPersistentData().m_128459_("NameRanged_ranged");
        double myName = entity.getPersistentData().m_128459_("NameRanged");
        double targetName = target.getPersistentData().m_128459_("NameRanged");
        double targetRanged = target.getPersistentData().m_128459_("NameRanged_ranged");
        if (myRanged != 0.0 && (myRanged == targetName || myRanged == targetRanged)) {
            return false;
        }
        if (myName != 0.0 && (myName == targetName || myName == targetRanged)) {
            return false;
        }
        ItemStack helmet = target.m_6844_(EquipmentSlot.HEAD);
        if (helmet.m_41720_() == JujutsucraftModItems.MAHORAGA_WHEEL_HELMET.get() || helmet.m_41720_() == JujutsucraftModItems.MAHORAGA_BODY_HELMET.get()) {
            boolean isMahoraga = false;
            if (target instanceof Player) {
                Player pTarget = (Player)target;
                JujutsucraftModVariables.PlayerVariables pVars = (JujutsucraftModVariables.PlayerVariables)pTarget.getCapability(JujutsucraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse((Object)new JujutsucraftModVariables.PlayerVariables());
                isMahoraga = pVars.PlayerCurseTechnique == 16.0 || pVars.PlayerCurseTechnique2 == 16.0;
            } else {
                isMahoraga = target instanceof EightHandledSwordDivergentSilaDivineGeneralMahoragaEntity;
            }
            if (isMahoraga && helmet.m_41784_().m_128459_("domain" + Math.round(entity.getPersistentData().m_128459_("skill_domain"))) >= 100.0) {
                return false;
            }
        }
        if (entity instanceof OkkotsuYutaCullingGameEntity) {
            boolean old_domainAttack = entity.getPersistentData().m_128471_("DomainAttack");
            entity.getPersistentData().m_128379_("DomainAttack", false);
            boolean canAttack = LogicAttackProcedure.execute((LevelAccessor)world, (Entity)entity, (Entity)entityiterator);
            entity.getPersistentData().m_128379_("DomainAttack", old_domainAttack);
            if (!canAttack) {
                return false;
            }
        }
        return true;
    }
}
