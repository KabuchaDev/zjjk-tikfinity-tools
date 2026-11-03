/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraftforge.event.entity.living.LivingDamageEvent
 *  net.minecraftforge.eventbus.api.Event
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 */
package net.mcreator.jujutsucraft.procedures;

import javax.annotation.Nullable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ChangeTargetProcedure {
    private static LivingDamageEvent _event;

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        _event = event;
        ChangeTargetProcedure.execute((Event)event, (LevelAccessor)event.getEntity().m_9236_(), (Entity)event.getEntity(), event.getSource().m_7639_());
    }

    public static void execute(LevelAccessor world, Entity entity, Entity sourceentity) {
        ChangeTargetProcedure.execute(null, world, entity, sourceentity);
    }

    private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, Entity sourceentity) {
        LivingEntity _ent;
        Entity entity_a = null;
        if (event == null || entity == null || sourceentity == null) {
            return;
        }
        if (entity == sourceentity) {
            return;
        }
        if (!sourceentity.getPersistentData().m_128461_("OWNER_UUID").isEmpty()) {
            entity_a = new /* Unavailable Anonymous Inner Class!! */.apply(world, sourceentity.getPersistentData().m_128461_("OWNER_UUID"));
        }
        if (entity_a instanceof LivingEntity) {
            sourceentity = entity_a;
        }
        if (!entity.m_6084_() || !sourceentity.m_6084_()) {
            return;
        }
        if (entity instanceof Mob) {
            Mob _entity = (Mob)entity;
            if (sourceentity instanceof LivingEntity) {
                _ent = (LivingEntity)sourceentity;
                _entity.m_6710_(_ent);
                _entity.getPersistentData().m_128359_("TARGET_UUID", "");
            }
        }
        if (entity instanceof PathfinderMob) {
            PathfinderMob _pathfinderMob = (PathfinderMob)entity;
            if (sourceentity instanceof LivingEntity) {
                _ent = (LivingEntity)sourceentity;
                _pathfinderMob.f_21346_.m_25352_(1, (Goal)new HurtByTargetGoal(_pathfinderMob, new Class[0]));
            }
        }
    }
}
