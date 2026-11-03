/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 */
package net.mcreator.jujutsucraft.procedures;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;

public class LogicAttackTargetProcedure {
    public static boolean execute(Entity entity) {
        if (entity == null) {
            return false;
        }
        boolean target_reset = false;
        LivingEntity target = null;
        double friend_num = 0.0;
        if (!entity.m_6084_()) {
            return false;
        }
        if (entity instanceof Mob) {
            Mob mob = (Mob)entity;
            target = mob.m_5448_();
            CompoundTag data = mob.getPersistentData();
            if (data.m_128459_("skill") <= -999.0) {
                return false;
            }
            if (data.m_128459_("cnt_target") <= 6.0) {
                return false;
            }
            if (target instanceof LivingEntity) {
                friend_num = data.m_128459_("friend_num");
                if (friend_num != 0.0 && friend_num == target.getPersistentData().m_128459_("friend_num")) {
                    target_reset = true;
                }
                if (mob.m_7307_((Entity)target)) {
                    target_reset = true;
                }
                if (target_reset) {
                    data.m_128359_("TARGET_UUID", "");
                    data.m_128347_("cnt_target", 0.0);
                    mob.m_6710_(null);
                    return false;
                }
            }
        }
        return true;
    }
}
