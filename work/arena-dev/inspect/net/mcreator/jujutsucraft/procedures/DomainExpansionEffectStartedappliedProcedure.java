/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 */
package net.mcreator.jujutsucraft.procedures;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class DomainExpansionEffectStartedappliedProcedure {
    public static void execute(Entity entity) {
        double d;
        if (entity == null) {
            return;
        }
        double tick = 0.0;
        double level = 0.0;
        entity.getPersistentData().m_128347_("totalDamage", 0.0);
        CompoundTag compoundTag = entity.getPersistentData();
        if (entity instanceof LivingEntity) {
            LivingEntity _livEnt = (LivingEntity)entity;
            d = _livEnt.m_21223_();
        } else {
            d = -1.0;
        }
        compoundTag.m_128347_("oldHealth", d);
    }
}
