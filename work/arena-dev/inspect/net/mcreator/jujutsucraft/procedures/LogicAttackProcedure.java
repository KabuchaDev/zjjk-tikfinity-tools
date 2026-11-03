/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.mcreator.jujutsucraft.entity.CrowEntity
 *  net.mcreator.jujutsucraft.entity.UraumeEntity
 *  net.mcreator.jujutsucraft.init.JujutsucraftModGameRules
 *  net.mcreator.jujutsucraft.init.JujutsucraftModItems
 *  net.mcreator.jujutsucraft.init.JujutsucraftModMobEffects
 *  net.mcreator.jujutsucraft.procedures.GetEntityFromUUIDProcedure
 *  net.mcreator.jujutsucraft.procedures.ReturnInsideItemProcedure
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.tags.TagKey
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.LevelAccessor
 */
package net.mcreator.jujutsucraft.procedures;

import net.mcreator.jujutsucraft.entity.CrowEntity;
import net.mcreator.jujutsucraft.entity.UraumeEntity;
import net.mcreator.jujutsucraft.init.JujutsucraftModGameRules;
import net.mcreator.jujutsucraft.init.JujutsucraftModItems;
import net.mcreator.jujutsucraft.init.JujutsucraftModMobEffects;
import net.mcreator.jujutsucraft.procedures.GetEntityFromUUIDProcedure;
import net.mcreator.jujutsucraft.procedures.LogicAttackDomainProcedure;
import net.mcreator.jujutsucraft.procedures.ReturnInsideItemProcedure;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;

public class LogicAttackProcedure {
    public static boolean execute(LevelAccessor world, Entity entity, Entity entityiterator) {
        double s;
        LivingEntity le;
        Mob m;
        Mob m2;
        Mob m3;
        Mob m4;
        Mob m5;
        Mob m6;
        LivingEntity le4;
        LivingEntity le3;
        LivingEntity le2;
        LivingEntity le5;
        Entity found;
        Entity found2;
        int safeCount;
        if (entity == null || entityiterator == null || entity == entityiterator) {
            return false;
        }
        if (!(entityiterator instanceof LivingEntity)) {
            return false;
        }
        Entity attacker_owner = entity;
        Entity iterator_owner = entityiterator;
        String aOwnerUUID = attacker_owner.getPersistentData().m_128461_("OWNER_UUID");
        for (safeCount = 0; !aOwnerUUID.isEmpty() && safeCount < 5 && (found2 = GetEntityFromUUIDProcedure.execute((LevelAccessor)world, (String)aOwnerUUID)) instanceof LivingEntity; ++safeCount) {
            attacker_owner = found2;
            aOwnerUUID = attacker_owner.getPersistentData().m_128461_("OWNER_UUID");
        }
        String iOwnerUUID = iterator_owner.getPersistentData().m_128461_("OWNER_UUID");
        for (safeCount = 0; !iOwnerUUID.isEmpty() && safeCount < 5 && (found = GetEntityFromUUIDProcedure.execute((LevelAccessor)world, (String)iOwnerUUID)) instanceof LivingEntity; ++safeCount) {
            iterator_owner = found;
            iOwnerUUID = iterator_owner.getPersistentData().m_128461_("OWNER_UUID");
        }
        if (entity.getPersistentData().m_128471_("onlyLiving") && entityiterator.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("forge:not_living")))) {
            return false;
        }
        if (entity.getPersistentData().m_128471_("onlyRanged") && !entityiterator.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("forge:ranged_ammo")))) {
            return false;
        }
        if (entityiterator.m_20147_()) {
            return false;
        }
        if (entityiterator instanceof Player) {
            Player p = (Player)entityiterator;
            if (p.m_150110_().f_35937_ || p.m_5833_()) {
                return false;
            }
        }
        if (entity.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("forge:not_living"))) && entity.m_146895_() == entityiterator || entityiterator.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("forge:not_living"))) && entityiterator.m_146895_() == entity) {
            return false;
        }
        boolean logic_attack = true;
        boolean pTarget = attacker_owner instanceof Player;
        boolean pIterator = iterator_owner instanceof Player;
        if (pTarget && pIterator) {
            if (!world.m_6106_().m_5470_().m_46207_(JujutsucraftModGameRules.JUJUTSUPVP)) {
                logic_attack = false;
            }
        } else if ((attacker_owner.getPersistentData().m_128471_("JujutsuSorcerer") || entity.getPersistentData().m_128471_("JujutsuSorcerer")) && (iterator_owner.getPersistentData().m_128471_("JujutsuSorcerer") || entityiterator.getPersistentData().m_128471_("JujutsuSorcerer"))) {
            logic_attack = false;
        } else if ((attacker_owner.getPersistentData().m_128471_("CursedSpirit") || entity.getPersistentData().m_128471_("CursedSpirit")) && (iterator_owner.getPersistentData().m_128471_("CursedSpirit") || entityiterator.getPersistentData().m_128471_("CursedSpirit"))) {
            logic_attack = false;
        } else if ((attacker_owner.getPersistentData().m_128471_("CurseUser") || entity.getPersistentData().m_128471_("CurseUser")) && (iterator_owner.getPersistentData().m_128471_("CurseUser") || entityiterator.getPersistentData().m_128471_("CurseUser"))) {
            logic_attack = false;
        }
        if (attacker_owner instanceof LivingEntity && (le5 = (LivingEntity)attacker_owner).m_21023_((MobEffect)JujutsucraftModMobEffects.SUKUNA_EFFECT.get()) || entity instanceof LivingEntity && (le2 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.SUKUNA_EFFECT.get()) || iterator_owner instanceof LivingEntity && (le3 = (LivingEntity)iterator_owner).m_21023_((MobEffect)JujutsucraftModMobEffects.SUKUNA_EFFECT.get()) || entityiterator instanceof LivingEntity && (le4 = (LivingEntity)entityiterator).m_21023_((MobEffect)JujutsucraftModMobEffects.SUKUNA_EFFECT.get())) {
            logic_attack = true;
        }
        if (logic_attack) {
            if (attacker_owner instanceof UraumeEntity && (ReturnInsideItemProcedure.execute((Entity)iterator_owner).m_41720_() == JujutsucraftModItems.SUKUNA_FINGER.get() || iterator_owner instanceof LivingEntity && (le5 = (LivingEntity)iterator_owner).m_21023_((MobEffect)JujutsucraftModMobEffects.SUKUNA_EFFECT.get()))) {
                logic_attack = false;
            }
            if (iterator_owner instanceof UraumeEntity && (ReturnInsideItemProcedure.execute((Entity)attacker_owner).m_41720_() == JujutsucraftModItems.SUKUNA_FINGER.get() || attacker_owner instanceof LivingEntity && (le5 = (LivingEntity)attacker_owner).m_21023_((MobEffect)JujutsucraftModMobEffects.SUKUNA_EFFECT.get()))) {
                logic_attack = false;
            }
            if ((attacker_owner.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("forge:group_1"))) || attacker_owner instanceof Player && attacker_owner.getPersistentData().m_128471_("CurseUser")) && (iterator_owner.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("forge:group_1"))) || iterator_owner instanceof Player && iterator_owner.getPersistentData().m_128471_("CurseUser")) || attacker_owner.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("forge:group_2"))) && iterator_owner.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("forge:group_2"))) || attacker_owner.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("forge:group_3"))) && iterator_owner.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("forge:group_3"))) || attacker_owner.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("forge:group_4"))) && iterator_owner.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("forge:group_4"))) || attacker_owner.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("forge:group_5"))) && iterator_owner.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("forge:group_5")))) {
                logic_attack = false;
            }
        }
        if (!logic_attack) {
            Mob m7;
            if (pTarget && entityiterator instanceof Mob && (m7 = (Mob)entityiterator).m_5448_() instanceof LivingEntity && entityiterator.getPersistentData().m_128459_("cnt_target") > 6.0 && (m7.m_5448_() instanceof Player || m7.m_5448_().getPersistentData().m_128471_("Player"))) {
                logic_attack = true;
            }
            if (pIterator && entity instanceof Mob && (m7 = (Mob)entity).m_5448_() instanceof LivingEntity && entity.getPersistentData().m_128459_("cnt_target") > 6.0 && (m7.m_5448_() instanceof Player || m7.m_5448_().getPersistentData().m_128471_("Player"))) {
                logic_attack = true;
            }
        }
        if (entity instanceof CrowEntity && entityiterator instanceof CrowEntity) {
            logic_attack = false;
        }
        boolean targeting = false;
        if (entity instanceof Mob && (m6 = (Mob)entity).m_5448_() == entityiterator && entity.getPersistentData().m_128459_("cnt_target") > 6.0) {
            targeting = true;
        } else if (entityiterator instanceof Mob && (m5 = (Mob)entityiterator).m_5448_() == entity && entityiterator.getPersistentData().m_128459_("cnt_target") > 6.0) {
            targeting = true;
        } else if (attacker_owner instanceof Mob && (m4 = (Mob)attacker_owner).m_5448_() == entityiterator && attacker_owner.getPersistentData().m_128459_("cnt_target") > 6.0) {
            targeting = true;
        } else if (iterator_owner instanceof Mob && (m3 = (Mob)iterator_owner).m_5448_() == entity && iterator_owner.getPersistentData().m_128459_("cnt_target") > 6.0) {
            targeting = true;
        } else if (attacker_owner instanceof Mob && (m2 = (Mob)attacker_owner).m_5448_() == iterator_owner && attacker_owner.getPersistentData().m_128459_("cnt_target") > 6.0) {
            targeting = true;
        } else if (iterator_owner instanceof Mob && (m = (Mob)iterator_owner).m_5448_() == attacker_owner && iterator_owner.getPersistentData().m_128459_("cnt_target") > 6.0) {
            targeting = true;
        }
        if (targeting) {
            logic_attack = true;
        }
        if (attacker_owner == iterator_owner) {
            logic_attack = false;
        }
        if (entity.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("jujutsucraft:ten_shadows_technique"))) && !entity.getPersistentData().m_128471_("Ambush") || entityiterator.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("jujutsucraft:ten_shadows_technique"))) && !entityiterator.getPersistentData().m_128471_("Ambush")) {
            logic_attack = true;
        }
        if (entity.getPersistentData().m_128459_("friend_num") != 0.0 && entity.getPersistentData().m_128459_("friend_num") == entityiterator.getPersistentData().m_128459_("friend_num")) {
            logic_attack = false;
        }
        double myRanged = entity.getPersistentData().m_128459_("NameRanged_ranged");
        double myName = entity.getPersistentData().m_128459_("NameRanged");
        double targetName = entityiterator.getPersistentData().m_128459_("NameRanged");
        double targetRanged = entityiterator.getPersistentData().m_128459_("NameRanged_ranged");
        if (myRanged != 0.0) {
            if (myRanged == targetName || myRanged == targetRanged) {
                logic_attack = false;
            }
            if (entity.getPersistentData().m_128471_("betrayal")) {
                if (iterator_owner.m_20149_().equals(entity.m_20149_()) || entity.m_20149_().equals(iterator_owner.m_20149_())) {
                    logic_attack = false;
                } else if (myRanged != targetRanged) {
                    return true;
                }
            }
        }
        if (myName != 0.0 && (myName == targetName || myName == targetRanged)) {
            logic_attack = false;
        }
        if (entityiterator instanceof LivingEntity && (le = (LivingEntity)entityiterator).m_21023_((MobEffect)JujutsucraftModMobEffects.PRAYER_SONG.get()) && le.m_21023_((MobEffect)JujutsucraftModMobEffects.GUARD.get()) && le.m_21124_((MobEffect)JujutsucraftModMobEffects.GUARD.get()).m_19564_() > 0 && (!((s = entity.getPersistentData().m_128459_("skill")) >= 305.0) || !(s <= 310.0)) && s != 205.0 && s != 705.0) {
            logic_attack = false;
        }
        if (!logic_attack) {
            return false;
        }
        if (entity.getPersistentData().m_128471_("DomainAttack") && !LogicAttackDomainProcedure.execute(world, entity, entityiterator)) {
            return false;
        }
        double targetType = entity.getPersistentData().m_128459_("target_type");
        if (targetType != 0.0) {
            double dx = entityiterator.m_20184_().m_7096_();
            double dy = entityiterator.m_20184_().m_7098_();
            double dz = entityiterator.m_20184_().m_7094_();
            double vSq = dx * dx + dy * dy + dz * dz;
            if (targetType == 1.0 ? vSq <= 1.0 : targetType == 2.0 && vSq < 0.01 && !entityiterator.m_20142_()) {
                return false;
            }
        }
        return true;
    }
}
