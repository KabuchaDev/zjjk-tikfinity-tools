/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.mcreator.jujutsucraft.entity.RabbitEscapeEntity
 *  net.mcreator.jujutsucraft.entity.UraumeEntity
 *  net.mcreator.jujutsucraft.init.JujutsucraftModGameRules
 *  net.mcreator.jujutsucraft.init.JujutsucraftModItems
 *  net.mcreator.jujutsucraft.init.JujutsucraftModMobEffects
 *  net.mcreator.jujutsucraft.network.JujutsucraftModVariables
 *  net.mcreator.jujutsucraft.network.JujutsucraftModVariables$PlayerVariables
 *  net.mcreator.jujutsucraft.procedures.GetEntityFromUUIDProcedure
 *  net.mcreator.jujutsucraft.procedures.ReturnInsideItemProcedure
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.nbt.CompoundTag
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

import net.mcreator.jujutsucraft.entity.RabbitEscapeEntity;
import net.mcreator.jujutsucraft.entity.UraumeEntity;
import net.mcreator.jujutsucraft.init.JujutsucraftModGameRules;
import net.mcreator.jujutsucraft.init.JujutsucraftModItems;
import net.mcreator.jujutsucraft.init.JujutsucraftModMobEffects;
import net.mcreator.jujutsucraft.network.JujutsucraftModVariables;
import net.mcreator.jujutsucraft.procedures.GetEntityFromUUIDProcedure;
import net.mcreator.jujutsucraft.procedures.ReturnInsideItemProcedure;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;

public class LogicAttackTargetStartProcedure {
    public static boolean execute(LevelAccessor world, Entity entity) {
        LivingEntity targetsTarget;
        LivingEntity le;
        boolean tSukuna;
        LivingEntity le2;
        LivingEntity le3;
        LivingEntity le22;
        LivingEntity le4;
        boolean tOwnerIsPlayer;
        Entity found;
        Entity found2;
        int safeCounter;
        if (world.m_5776_()) {
            return false;
        }
        if (!(entity instanceof Mob)) {
            return false;
        }
        Mob mob = (Mob)entity;
        LivingEntity target = mob.m_5448_();
        if (!(target instanceof LivingEntity)) {
            return true;
        }
        LivingEntity livingTarget = target;
        CompoundTag entityData = entity.getPersistentData();
        CompoundTag targetData = target.getPersistentData();
        double friendNum = entityData.m_128459_("friend_num");
        if (friendNum != 0.0 && friendNum == targetData.m_128459_("friend_num")) {
            return false;
        }
        if (entityData.m_128461_("TARGET_UUID").equals(target.m_20149_())) {
            return true;
        }
        Entity event_owner = entity;
        LivingEntity target_owner = target;
        String ownerUUID = event_owner.getPersistentData().m_128461_("OWNER_UUID");
        for (safeCounter = 0; !ownerUUID.isEmpty() && safeCounter < 5 && (found2 = GetEntityFromUUIDProcedure.execute((LevelAccessor)world, (String)ownerUUID)) instanceof LivingEntity; ++safeCounter) {
            event_owner = found2;
            ownerUUID = event_owner.getPersistentData().m_128461_("OWNER_UUID");
        }
        String t_ownerUUID = target_owner.getPersistentData().m_128461_("OWNER_UUID");
        for (safeCounter = 0; !t_ownerUUID.isEmpty() && safeCounter < 5 && (found = GetEntityFromUUIDProcedure.execute((LevelAccessor)world, (String)t_ownerUUID)) instanceof LivingEntity; ++safeCounter) {
            target_owner = found;
            t_ownerUUID = target_owner.getPersistentData().m_128461_("OWNER_UUID");
        }
        boolean logic_a = true;
        boolean eOwnerIsPlayer = event_owner instanceof Player || event_owner.getPersistentData().m_128471_("Player");
        boolean bl = tOwnerIsPlayer = target_owner instanceof Player || target_owner.getPersistentData().m_128471_("Player");
        if (eOwnerIsPlayer && tOwnerIsPlayer) {
            if (!world.m_6106_().m_5470_().m_46207_(JujutsucraftModGameRules.JUJUTSUPVP)) {
                logic_a = false;
            }
        } else if ((event_owner.getPersistentData().m_128471_("JujutsuSorcerer") || entityData.m_128471_("JujutsuSorcerer")) && (target_owner.getPersistentData().m_128471_("JujutsuSorcerer") || targetData.m_128471_("JujutsuSorcerer"))) {
            logic_a = false;
        } else if ((event_owner.getPersistentData().m_128471_("CursedSpirit") || event_owner.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("jujutsucraft:transfigured_human")))) && (target_owner.getPersistentData().m_128471_("CursedSpirit") || target_owner.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("jujutsucraft:transfigured_human"))))) {
            logic_a = false;
        } else if (!(!event_owner.getPersistentData().m_128471_("CurseUser") && !entityData.m_128471_("CurseUser") || target_owner.getPersistentData().m_128471_("JujutsuSorcerer") || target_owner.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("curseuser"))) || target_owner.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("cursedspirit"))))) {
            logic_a = false;
        }
        if (event_owner instanceof LivingEntity && (le4 = (LivingEntity)event_owner).m_21023_((MobEffect)JujutsucraftModMobEffects.SUKUNA_EFFECT.get()) || entity instanceof LivingEntity && (le22 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.SUKUNA_EFFECT.get()) || target_owner instanceof LivingEntity && (le3 = target_owner).m_21023_((MobEffect)JujutsucraftModMobEffects.SUKUNA_EFFECT.get()) || livingTarget.m_21023_((MobEffect)JujutsucraftModMobEffects.SUKUNA_EFFECT.get())) {
            logic_a = true;
        }
        if (event_owner instanceof UraumeEntity && (ReturnInsideItemProcedure.execute((Entity)target_owner).m_41720_() == JujutsucraftModItems.SUKUNA_FINGER.get() || target_owner instanceof LivingEntity && (le4 = target_owner).m_21023_((MobEffect)JujutsucraftModMobEffects.SUKUNA_EFFECT.get()))) {
            logic_a = false;
        }
        if (event_owner.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("jujutsucraft:death_painting")))) {
            boolean targetIsItadori = false;
            if (target_owner instanceof Player) {
                Player pTarget = (Player)target_owner;
                JujutsucraftModVariables.PlayerVariables pVars = (JujutsucraftModVariables.PlayerVariables)pTarget.getCapability(JujutsucraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse((Object)new JujutsucraftModVariables.PlayerVariables());
                targetIsItadori = pVars.PlayerCurseTechnique == 21.0 || pVars.PlayerCurseTechnique2 == 21.0;
            } else {
                targetIsItadori = target_owner.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("jujutsucraft:death_painting")));
            }
            if (targetIsItadori) {
                logic_a = false;
            }
        }
        boolean eSukuna = event_owner instanceof LivingEntity && (le2 = (LivingEntity)event_owner).m_21023_((MobEffect)JujutsucraftModMobEffects.SUKUNA_EFFECT.get());
        boolean bl2 = tSukuna = target_owner instanceof LivingEntity && (le = target_owner).m_21023_((MobEffect)JujutsucraftModMobEffects.SUKUNA_EFFECT.get());
        if ((event_owner.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("forge:group_1"))) || event_owner instanceof Player && event_owner.getPersistentData().m_128471_("CurseUser") && !eSukuna) && (target_owner.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("forge:group_1"))) || target_owner instanceof Player && target_owner.getPersistentData().m_128471_("CurseUser") && !tSukuna) || event_owner.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("forge:group_2"))) && target_owner.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("forge:group_2"))) || event_owner.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("forge:group_3"))) && target_owner.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("forge:group_3"))) || event_owner.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("forge:group_4"))) && target_owner.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("forge:group_4"))) || event_owner.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("forge:group_5"))) && target_owner.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("forge:group_5"))) || event_owner.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("jujutsucraft:zenin"))) && target_owner.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("jujutsucraft:zenin")))) {
            logic_a = false;
        }
        if (target instanceof Mob) {
            Mob m = (Mob)target;
            v2 = m.m_5448_();
        } else {
            v2 = targetsTarget = null;
        }
        if (targetsTarget instanceof LivingEntity && targetData.m_128459_("cnt_target") > 6.0) {
            if (friendNum != 0.0 && friendNum == targetsTarget.getPersistentData().m_128459_("friend_num")) {
                logic_a = true;
            }
            if (event_owner.m_7307_((Entity)targetsTarget)) {
                logic_a = true;
            }
            if (targetsTarget == entity) {
                logic_a = true;
            }
        }
        if (event_owner == target_owner) {
            logic_a = false;
        }
        if (entity.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("jujutsucraft:ten_shadows_technique"))) && !entityData.m_128471_("Ambush")) {
            if (!target.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("jujutsucraft:ten_shadows_technique")))) {
                logic_a = true;
            }
            if (entity instanceof RabbitEscapeEntity && target instanceof RabbitEscapeEntity) {
                logic_a = false;
            }
        }
        return logic_a;
    }
}
