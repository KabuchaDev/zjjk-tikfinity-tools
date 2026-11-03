/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.mcreator.jujutsucraft.procedures.GetEntityFromUUIDProcedure
 *  net.mcreator.jujutsucraft.procedures.LogicAttackProcedure
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 */
package net.mcreator.jujutsucraft.procedures;

import java.lang.invoke.LambdaMetafactory;
import java.util.function.Predicate;
import net.mcreator.jujutsucraft.procedures.GetEntityFromUUIDProcedure;
import net.mcreator.jujutsucraft.procedures.LogicAttackProcedure;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class SetTargetProcedure {
    /*
     * Unable to fully structure code
     */
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        block40: {
            block41: {
                if (entity == null) {
                    return;
                }
                entity_a = null;
                entity_target = null;
                logic_a = false;
                if (!(entity instanceof LivingEntity) || !entity.getPersistentData().m_128461_("TARGET_UUID").isEmpty() || !(entity.getPersistentData().m_128459_("cnt_target") <= 6.0)) break block40;
                logic_a = false;
                if (!logic_a && (entity_a = GetEntityFromUUIDProcedure.execute((LevelAccessor)world, (String)entity.getPersistentData().m_128461_("OWNER_UUID"))) instanceof LivingEntity) {
                    if (entity_a instanceof Mob) {
                        _mobEnt = (Mob)entity_a;
                        v0 = _mobEnt.m_5448_();
                    } else {
                        v0 = null;
                    }
                    if (v0 instanceof LivingEntity && entity_a.getPersistentData().m_128459_("cnt_target") > 6.0) {
                        logic_a = true;
                        if (entity_a instanceof Mob) {
                            _mobEnt = (Mob)entity_a;
                            v1 = _mobEnt.m_5448_();
                        } else {
                            v1 = entity_target = null;
                        }
                    }
                }
                if (entity.getPersistentData().m_128459_("friend_num") == 0.0) {
                    entity.getPersistentData().m_128347_("friend_num", Math.random());
                }
                if (!(entity instanceof LivingEntity) || !(_livingEntity12 = (LivingEntity)entity).m_21204_().m_22171_(Attributes.f_22277_)) break block41;
                v2 = entity instanceof LivingEntity != false && (_livingEntity13 = (LivingEntity)entity).m_21204_().m_22171_(Attributes.f_22277_) != false ? _livingEntity13.m_21051_(Attributes.f_22277_).m_22135_() : 0.0;
                if (!(v2 > 0.0)) break block41;
                if (!(entity_target instanceof LivingEntity)) {
                    _center = new Vec3(x, y, z);
                    _entfound = world.m_6443_(Entity.class, new AABB(_center, _center).m_82400_(Math.min(16.0, entity instanceof LivingEntity != false && (_livingEntity15 = (LivingEntity)entity).m_21204_().m_22171_(Attributes.f_22277_) != false ? _livingEntity15.m_21051_(Attributes.f_22277_).m_22135_() : 0.0) / 2.0), (Predicate<Entity>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Z, lambda$execute$0(net.minecraft.world.entity.Entity ), (Lnet/minecraft/world/entity/Entity;)Z)());
                    for (Entity entityiterator : _entfound) {
                        if (entity == entityiterator || !(entityiterator instanceof LivingEntity) || !entityiterator.m_6084_() || !LogicAttackProcedure.execute((LevelAccessor)world, (Entity)entity, (Entity)entityiterator)) continue;
                        entity_target = entityiterator;
                        break;
                    }
                }
                if (entity_target instanceof LivingEntity) break block41;
                _center = new Vec3(x, y, z);
                _entfound = world.m_6443_(Entity.class, new AABB(_center, _center).m_82400_(Math.min(80.0, entity instanceof LivingEntity != false && (_livingEntity21 = (LivingEntity)entity).m_21204_().m_22171_(Attributes.f_22277_) != false ? _livingEntity21.m_21051_(Attributes.f_22277_).m_22135_() : 0.0) / 2.0), (Predicate<Entity>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Z, lambda$execute$1(net.minecraft.world.entity.Entity ), (Lnet/minecraft/world/entity/Entity;)Z)());
                for (Entity entityiterator : _entfound) {
                    block42: {
                        if (entity == entityiterator || !(entityiterator instanceof LivingEntity) || !entityiterator.m_6084_() || !LogicAttackProcedure.execute((LevelAccessor)world, (Entity)entity, (Entity)entityiterator)) continue;
                        if (entityiterator instanceof Mob) {
                            _mobEnt = (Mob)entityiterator;
                            v3 = _mobEnt.m_5448_();
                        } else {
                            v3 = null;
                        }
                        if (!(v3 instanceof LivingEntity) || !(entityiterator.getPersistentData().m_128459_("cnt_target") > 6.0)) break block42;
                        if (entity.getPersistentData().m_128459_("friend_num") != 0.0) {
                            if (entityiterator instanceof Mob) {
                                _mobEnt = (Mob)entityiterator;
                                v4 = _mobEnt.m_5448_();
                            } else {
                                v4 = null;
                            }
                            if (v4.getPersistentData().m_128459_("friend_num") == entity.getPersistentData().m_128459_("friend_num")) {
                                entity_target = entityiterator;
                                break;
                            }
                        }
                        if (!(entity instanceof LivingEntity)) ** GOTO lbl-1000
                        _teamEnt = (LivingEntity)entity;
                        v5 = _teamEnt.m_9236_().m_6188_();
                        if (_teamEnt instanceof Player) {
                            _pl = (Player)_teamEnt;
                            v6 = _pl.m_36316_().getName();
                        } else {
                            v6 = _teamEnt.m_20149_();
                        }
                        if (v5.m_83500_(v6) != null) {
                            v7 = _teamEnt.m_9236_().m_6188_();
                            if (_teamEnt instanceof Player) {
                                _pl = (Player)_teamEnt;
                                v8 = _pl.m_36316_().getName();
                            } else {
                                v8 = _teamEnt.m_20149_();
                            }
                            v9 = v7.m_83500_(v8).m_5758_();
                        } else lbl-1000:
                        // 2 sources

                        {
                            v9 = "";
                        }
                        if (v9.isEmpty()) break block42;
                        if (entityiterator instanceof Mob) {
                            _mobEnt = (Mob)entityiterator;
                            v10 = _mobEnt.m_5448_();
                        } else {
                            v10 = var28_25 = null;
                        }
                        if (!(var28_25 instanceof LivingEntity)) ** GOTO lbl-1000
                        _teamEnt = var28_25;
                        v11 = _teamEnt.m_9236_().m_6188_();
                        if (_teamEnt instanceof Player) {
                            _pl = (Player)_teamEnt;
                            v12 = _pl.m_36316_().getName();
                        } else {
                            v12 = _teamEnt.m_20149_();
                        }
                        if (v11.m_83500_(v12) != null) {
                            v13 = _teamEnt.m_9236_().m_6188_();
                            if (_teamEnt instanceof Player) {
                                _pl = (Player)_teamEnt;
                                v14 = _pl.m_36316_().getName();
                            } else {
                                v14 = _teamEnt.m_20149_();
                            }
                            v15 = v13.m_83500_(v14).m_5758_();
                        } else lbl-1000:
                        // 2 sources

                        {
                            v15 = "";
                        }
                        if (!(entity instanceof LivingEntity)) ** GOTO lbl-1000
                        _teamEnt = (LivingEntity)entity;
                        v16 = _teamEnt.m_9236_().m_6188_();
                        if (_teamEnt instanceof Player) {
                            _pl = (Player)_teamEnt;
                            v17 = _pl.m_36316_().getName();
                        } else {
                            v17 = _teamEnt.m_20149_();
                        }
                        if (v16.m_83500_(v17) != null) {
                            v18 = _teamEnt.m_9236_().m_6188_();
                            if (_teamEnt instanceof Player) {
                                _pl = (Player)_teamEnt;
                                v19 = _pl.m_36316_().getName();
                            } else {
                                v19 = _teamEnt.m_20149_();
                            }
                            v20 = v18.m_83500_(v19).m_5758_();
                        } else lbl-1000:
                        // 2 sources

                        {
                            v20 = "";
                        }
                        if (v15.equals(v20)) {
                            entity_target = entityiterator;
                            break;
                        }
                    }
                    if (!(entityiterator instanceof Player) || entity_a instanceof Player && !logic_a) continue;
                    entity_target = entityiterator;
                    break;
                }
            }
            if (entity_target instanceof LivingEntity) {
                entity.getPersistentData().m_128359_("TARGET_UUID", entity_target.m_20149_());
            } else {
                entity.getPersistentData().m_128359_("TARGET_UUID", "");
            }
        }
    }

    private static /* synthetic */ boolean lambda$execute$1(Entity e) {
        return true;
    }

    private static /* synthetic */ boolean lambda$execute$0(Entity e) {
        return true;
    }
}
