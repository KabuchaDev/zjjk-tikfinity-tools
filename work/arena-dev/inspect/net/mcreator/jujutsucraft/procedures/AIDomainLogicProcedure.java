/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.mcreator.jujutsucraft.init.JujutsucraftModMobEffects
 *  net.mcreator.jujutsucraft.network.JujutsucraftModVariables$MapVariables
 *  net.mcreator.jujutsucraft.procedures.GetDistanceProcedure
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 */
package net.mcreator.jujutsucraft.procedures;

import java.util.List;
import net.mcreator.jujutsucraft.init.JujutsucraftModMobEffects;
import net.mcreator.jujutsucraft.network.JujutsucraftModVariables;
import net.mcreator.jujutsucraft.procedures.GetDistanceProcedure;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class AIDomainLogicProcedure {
    public static boolean execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        float f;
        float f2;
        LivingEntity _livEnt;
        if (entity == null) {
            return false;
        }
        boolean cancel = false;
        LivingEntity target_entity = null;
        double d = Math.random();
        if (entity instanceof LivingEntity) {
            _livEnt = (LivingEntity)entity;
            f2 = _livEnt.m_21223_();
        } else {
            f2 = -1.0f;
        }
        float f3 = Math.max(f2, 1.0f);
        if (entity instanceof LivingEntity) {
            LivingEntity _livEnt2 = (LivingEntity)entity;
            f = _livEnt2.m_21233_();
        } else {
            f = -1.0f;
        }
        if (d > (double)(1.0f - f3 / Math.max(f, 1.0f)) * 0.75) {
            LivingEntity _livEnt3;
            LivingEntity _livEnt4;
            LivingEntity _livEnt32;
            cancel = true;
            if (entity instanceof Mob) {
                Mob _mobEnt = (Mob)entity;
                v4 = _mobEnt.m_5448_();
            } else {
                v4 = target_entity = null;
            }
            if (target_entity instanceof LivingEntity && (_livEnt32 = target_entity).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()) || target_entity instanceof LivingEntity && (_livEnt4 = target_entity).m_21023_((MobEffect)JujutsucraftModMobEffects.INFINITY_EFFECT.get())) {
                cancel = false;
            }
            if (target_entity.getPersistentData().m_128459_("skill") % 100.0 == 20.0) {
                cancel = false;
            }
            if ((entity instanceof LivingEntity && (_livEnt3 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.NEUTRALIZATION.get()) ? _livEnt3.m_21124_((MobEffect)JujutsucraftModMobEffects.NEUTRALIZATION.get()).m_19564_() : 0) > 0) {
                cancel = false;
            }
        }
        if (!cancel) {
            LivingEntity _livEnt10;
            LivingEntity _livEnt9;
            if (entity.getPersistentData().m_128459_("cnt_target") < 200.0) {
                cancel = true;
            }
            if ((entity instanceof LivingEntity && (_livEnt = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.BRAIN_DAMAGE.get()) ? _livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.BRAIN_DAMAGE.get()).m_19564_() : 0) >= 2) {
                cancel = true;
            }
            if (entity instanceof LivingEntity && (_livEnt9 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()) || entity instanceof LivingEntity && (_livEnt10 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.UNSTABLE.get())) {
                cancel = true;
            }
        }
        if (!cancel && GetDistanceProcedure.execute((Entity)entity) > JujutsucraftModVariables.MapVariables.get((LevelAccessor)world).DomainExpansionRadius) {
            cancel = true;
        }
        if (!cancel && entity.getPersistentData().m_128459_("friend_num") != 0.0) {
            Vec3 _center = new Vec3(x, y, z);
            List _entfound = world.m_6443_(Entity.class, new AABB(_center, _center).m_82400_(JujutsucraftModVariables.MapVariables.get((LevelAccessor)world).DomainExpansionRadius * 2.0 / 2.0), e -> true);
            for (Entity entityiterator : _entfound) {
                LivingEntity _livEnt15;
                if (entity == entityiterator || entity.getPersistentData().m_128459_("friend_num") != entityiterator.getPersistentData().m_128459_("friend_num") || (!(entityiterator instanceof LivingEntity) || !(_livEnt15 = (LivingEntity)entityiterator).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get())) && entityiterator.getPersistentData().m_128459_("select") == 0.0 && entityiterator.getPersistentData().m_128459_("skill") % 100.0 != 20.0) continue;
                cancel = true;
                break;
            }
        }
        return cancel;
    }
}
