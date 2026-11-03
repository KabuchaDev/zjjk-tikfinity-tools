/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.mcreator.jujutsucraft.entity.SukunaEntity
 *  net.mcreator.jujutsucraft.entity.SukunaFushiguroEntity
 *  net.mcreator.jujutsucraft.entity.SukunaPerfectEntity
 *  net.mcreator.jujutsucraft.init.JujutsucraftModEntities
 *  net.mcreator.jujutsucraft.init.JujutsucraftModItems
 *  net.mcreator.jujutsucraft.init.JujutsucraftModMobEffects
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.NonNullList
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.phys.Vec3
 */
package net.mcreator.jujutsucraft.procedures;

import net.mcreator.jujutsucraft.entity.SukunaEntity;
import net.mcreator.jujutsucraft.entity.SukunaFushiguroEntity;
import net.mcreator.jujutsucraft.entity.SukunaPerfectEntity;
import net.mcreator.jujutsucraft.init.JujutsucraftModEntities;
import net.mcreator.jujutsucraft.init.JujutsucraftModItems;
import net.mcreator.jujutsucraft.init.JujutsucraftModMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;

public class SukunaEntityDiesProcedure {
    public static void execute(LevelAccessor world, Entity entity, Entity sourceentity) {
        if (entity == null || sourceentity == null) {
            return;
        }
        boolean dieOnlySukuna = false;
        if (!entity.m_6084_()) {
            ItemStack itemStack;
            if (sourceentity instanceof LivingEntity) {
                LivingEntity _livEnt = (LivingEntity)sourceentity;
                itemStack = _livEnt.m_21205_();
            } else {
                itemStack = ItemStack.f_41583_;
            }
            if (itemStack.m_41720_() == JujutsucraftModItems.EXECUTIONERS_SWORD.get() && (!(sourceentity.getPersistentData().m_128459_("skill") > 0.0) || sourceentity.getPersistentData().m_128471_("attack"))) {
                dieOnlySukuna = true;
            }
            if (sourceentity.getPersistentData().m_128459_("skill") >= 2800.0 && sourceentity.getPersistentData().m_128459_("skill") < 2850.0) {
                dieOnlySukuna = true;
            }
            if (dieOnlySukuna) {
                if (entity instanceof SukunaEntity) {
                    if (world instanceof ServerLevel) {
                        _serverLevel = (ServerLevel)world;
                        Entity entityinstance = ((EntityType)JujutsucraftModEntities.ITADORI_YUJI_SHIBUYA.get()).m_262451_(_serverLevel, null, null, BlockPos.m_274561_((double)entity.m_20185_(), (double)entity.m_20186_(), (double)entity.m_20189_()), MobSpawnType.MOB_SUMMONED, false, false);
                        if (entityinstance != null) {
                            Player _player;
                            ItemStack _setstack;
                            LivingEntity _livEnt;
                            LivingEntity _living;
                            LivingEntity _entGetArmor;
                            Player _player2;
                            LivingEntity _entity;
                            entityinstance.m_146922_(world.m_213780_().m_188501_() * 360.0f);
                            if (entityinstance instanceof LivingEntity && !(_entity = (LivingEntity)entityinstance).m_9236_().m_5776_()) {
                                _entity.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.STUN.get(), 60, 1, false, false));
                            }
                            entityinstance.m_20256_(new Vec3(entity.m_20184_().m_7096_(), entity.m_20184_().m_7098_(), entity.m_20184_().m_7094_()));
                            Entity _ent = entityinstance;
                            _ent.m_146922_(entity.m_146908_());
                            _ent.m_146926_(entity.m_146909_());
                            _ent.m_5618_(_ent.m_146908_());
                            _ent.m_5616_(_ent.m_146908_());
                            _ent.f_19859_ = _ent.m_146908_();
                            _ent.f_19860_ = _ent.m_146909_();
                            if (_ent instanceof LivingEntity) {
                                LivingEntity _entity2 = (LivingEntity)_ent;
                                _entity2.f_20884_ = _entity2.m_146908_();
                                _entity2.f_20886_ = _entity2.m_146908_();
                            }
                            if ((_entity = entityinstance) instanceof Player) {
                                ItemStack itemStack2;
                                _player2 = (Player)_entity;
                                NonNullList nonNullList = _player2.m_150109_().f_35975_;
                                if (entity instanceof LivingEntity) {
                                    _entGetArmor = (LivingEntity)entity;
                                    itemStack2 = _entGetArmor.m_6844_(EquipmentSlot.HEAD);
                                } else {
                                    itemStack2 = ItemStack.f_41583_;
                                }
                                nonNullList.set(3, (Object)itemStack2);
                                _player2.m_150109_().m_6596_();
                            } else if (_entity instanceof LivingEntity) {
                                ItemStack itemStack3;
                                _living = _entity;
                                if (entity instanceof LivingEntity) {
                                    _entGetArmor = (LivingEntity)entity;
                                    itemStack3 = _entGetArmor.m_6844_(EquipmentSlot.HEAD);
                                } else {
                                    itemStack3 = ItemStack.f_41583_;
                                }
                                _living.m_8061_(EquipmentSlot.HEAD, itemStack3);
                            }
                            _entity = entityinstance;
                            if (_entity instanceof Player) {
                                ItemStack itemStack4;
                                ItemStack itemStack5;
                                _player2 = (Player)_entity;
                                NonNullList nonNullList = _player2.m_150109_().f_35975_;
                                if (entity instanceof LivingEntity) {
                                    LivingEntity _entGetArmor2 = (LivingEntity)entity;
                                    itemStack5 = _entGetArmor2.m_6844_(EquipmentSlot.CHEST);
                                } else {
                                    itemStack5 = ItemStack.f_41583_;
                                }
                                if (itemStack5.m_41720_() == JujutsucraftModItems.SUKUNA_BODY_CHESTPLATE.get()) {
                                    itemStack4 = ItemStack.f_41583_;
                                } else if (entity instanceof LivingEntity) {
                                    _entGetArmor = (LivingEntity)entity;
                                    itemStack4 = _entGetArmor.m_6844_(EquipmentSlot.CHEST);
                                } else {
                                    itemStack4 = ItemStack.f_41583_;
                                }
                                nonNullList.set(2, (Object)itemStack4);
                                _player2.m_150109_().m_6596_();
                            } else if (_entity instanceof LivingEntity) {
                                ItemStack itemStack6;
                                ItemStack itemStack7;
                                _living = _entity;
                                if (entity instanceof LivingEntity) {
                                    LivingEntity _entGetArmor3 = (LivingEntity)entity;
                                    itemStack7 = _entGetArmor3.m_6844_(EquipmentSlot.CHEST);
                                } else {
                                    itemStack7 = ItemStack.f_41583_;
                                }
                                if (itemStack7.m_41720_() == JujutsucraftModItems.SUKUNA_BODY_CHESTPLATE.get()) {
                                    itemStack6 = ItemStack.f_41583_;
                                } else if (entity instanceof LivingEntity) {
                                    _entGetArmor = (LivingEntity)entity;
                                    itemStack6 = _entGetArmor.m_6844_(EquipmentSlot.CHEST);
                                } else {
                                    itemStack6 = ItemStack.f_41583_;
                                }
                                _living.m_8061_(EquipmentSlot.CHEST, itemStack6);
                            }
                            _entity = entityinstance;
                            if (_entity instanceof Player) {
                                ItemStack itemStack8;
                                _player2 = (Player)_entity;
                                NonNullList nonNullList = _player2.m_150109_().f_35975_;
                                if (entity instanceof LivingEntity) {
                                    _entGetArmor = (LivingEntity)entity;
                                    itemStack8 = _entGetArmor.m_6844_(EquipmentSlot.LEGS);
                                } else {
                                    itemStack8 = ItemStack.f_41583_;
                                }
                                nonNullList.set(1, (Object)itemStack8);
                                _player2.m_150109_().m_6596_();
                            } else if (_entity instanceof LivingEntity) {
                                ItemStack itemStack9;
                                _living = _entity;
                                if (entity instanceof LivingEntity) {
                                    _entGetArmor = (LivingEntity)entity;
                                    itemStack9 = _entGetArmor.m_6844_(EquipmentSlot.LEGS);
                                } else {
                                    itemStack9 = ItemStack.f_41583_;
                                }
                                _living.m_8061_(EquipmentSlot.LEGS, itemStack9);
                            }
                            _entity = entityinstance;
                            if (_entity instanceof Player) {
                                ItemStack itemStack10;
                                _player2 = (Player)_entity;
                                NonNullList nonNullList = _player2.m_150109_().f_35975_;
                                if (entity instanceof LivingEntity) {
                                    _entGetArmor = (LivingEntity)entity;
                                    itemStack10 = _entGetArmor.m_6844_(EquipmentSlot.FEET);
                                } else {
                                    itemStack10 = ItemStack.f_41583_;
                                }
                                nonNullList.set(0, (Object)itemStack10);
                                _player2.m_150109_().m_6596_();
                            } else if (_entity instanceof LivingEntity) {
                                ItemStack itemStack11;
                                _living = _entity;
                                if (entity instanceof LivingEntity) {
                                    _entGetArmor = (LivingEntity)entity;
                                    itemStack11 = _entGetArmor.m_6844_(EquipmentSlot.FEET);
                                } else {
                                    itemStack11 = ItemStack.f_41583_;
                                }
                                _living.m_8061_(EquipmentSlot.FEET, itemStack11);
                            }
                            if (entityinstance instanceof LivingEntity) {
                                ItemStack itemStack12;
                                _entity = (LivingEntity)entityinstance;
                                if (entity instanceof LivingEntity) {
                                    _livEnt = (LivingEntity)entity;
                                    itemStack12 = _livEnt.m_21205_();
                                } else {
                                    itemStack12 = ItemStack.f_41583_;
                                }
                                _setstack = itemStack12.m_41777_();
                                _setstack.m_41764_(1);
                                _entity.m_21008_(InteractionHand.MAIN_HAND, _setstack);
                                if (_entity instanceof Player) {
                                    _player = (Player)_entity;
                                    _player.m_150109_().m_6596_();
                                }
                            }
                            if (entityinstance instanceof LivingEntity) {
                                ItemStack itemStack13;
                                _entity = (LivingEntity)entityinstance;
                                if (entity instanceof LivingEntity) {
                                    _livEnt = (LivingEntity)entity;
                                    itemStack13 = _livEnt.m_21206_();
                                } else {
                                    itemStack13 = ItemStack.f_41583_;
                                }
                                _setstack = itemStack13.m_41777_();
                                _setstack.m_41764_(1);
                                _entity.m_21008_(InteractionHand.OFF_HAND, _setstack);
                                if (_entity instanceof Player) {
                                    _player = (Player)_entity;
                                    _player.m_150109_().m_6596_();
                                }
                            }
                            _serverLevel.m_7967_(entityinstance);
                        }
                    }
                    if (!entity.m_9236_().m_5776_()) {
                        entity.m_146870_();
                    }
                } else if (entity instanceof SukunaFushiguroEntity || entity instanceof SukunaPerfectEntity) {
                    if (world instanceof ServerLevel) {
                        _serverLevel = (ServerLevel)world;
                        Entity entityinstance = ((EntityType)JujutsucraftModEntities.FUSHIGURO_MEGUMI_SHIBUYA.get()).m_262451_(_serverLevel, null, null, BlockPos.m_274561_((double)entity.m_20185_(), (double)entity.m_20186_(), (double)entity.m_20189_()), MobSpawnType.MOB_SUMMONED, false, false);
                        if (entityinstance != null) {
                            Player _player;
                            ItemStack _setstack;
                            LivingEntity _livEnt;
                            LivingEntity _living;
                            LivingEntity _entGetArmor;
                            Player _player3;
                            LivingEntity _entity;
                            entityinstance.m_146922_(world.m_213780_().m_188501_() * 360.0f);
                            if (entityinstance instanceof LivingEntity && !(_entity = (LivingEntity)entityinstance).m_9236_().m_5776_()) {
                                _entity.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.STUN.get(), 60, 1, false, false));
                            }
                            entityinstance.m_20256_(new Vec3(entity.m_20184_().m_7096_(), entity.m_20184_().m_7098_(), entity.m_20184_().m_7094_()));
                            Entity _ent = entityinstance;
                            _ent.m_146922_(entity.m_146908_());
                            _ent.m_146926_(entity.m_146909_());
                            _ent.m_5618_(_ent.m_146908_());
                            _ent.m_5616_(_ent.m_146908_());
                            _ent.f_19859_ = _ent.m_146908_();
                            _ent.f_19860_ = _ent.m_146909_();
                            if (_ent instanceof LivingEntity) {
                                LivingEntity _entity3 = (LivingEntity)_ent;
                                _entity3.f_20884_ = _entity3.m_146908_();
                                _entity3.f_20886_ = _entity3.m_146908_();
                            }
                            if ((_entity = entityinstance) instanceof Player) {
                                ItemStack itemStack14;
                                _player3 = (Player)_entity;
                                NonNullList nonNullList = _player3.m_150109_().f_35975_;
                                if (entity instanceof LivingEntity) {
                                    _entGetArmor = (LivingEntity)entity;
                                    itemStack14 = _entGetArmor.m_6844_(EquipmentSlot.HEAD);
                                } else {
                                    itemStack14 = ItemStack.f_41583_;
                                }
                                nonNullList.set(3, (Object)itemStack14);
                                _player3.m_150109_().m_6596_();
                            } else if (_entity instanceof LivingEntity) {
                                ItemStack itemStack15;
                                _living = _entity;
                                if (entity instanceof LivingEntity) {
                                    _entGetArmor = (LivingEntity)entity;
                                    itemStack15 = _entGetArmor.m_6844_(EquipmentSlot.HEAD);
                                } else {
                                    itemStack15 = ItemStack.f_41583_;
                                }
                                _living.m_8061_(EquipmentSlot.HEAD, itemStack15);
                            }
                            _entity = entityinstance;
                            if (_entity instanceof Player) {
                                ItemStack itemStack16;
                                ItemStack itemStack17;
                                _player3 = (Player)_entity;
                                NonNullList nonNullList = _player3.m_150109_().f_35975_;
                                if (entity instanceof LivingEntity) {
                                    LivingEntity _entGetArmor4 = (LivingEntity)entity;
                                    itemStack17 = _entGetArmor4.m_6844_(EquipmentSlot.CHEST);
                                } else {
                                    itemStack17 = ItemStack.f_41583_;
                                }
                                if (itemStack17.m_41720_() == JujutsucraftModItems.SUKUNA_BODY_CHESTPLATE.get()) {
                                    itemStack16 = ItemStack.f_41583_;
                                } else if (entity instanceof LivingEntity) {
                                    _entGetArmor = (LivingEntity)entity;
                                    itemStack16 = _entGetArmor.m_6844_(EquipmentSlot.CHEST);
                                } else {
                                    itemStack16 = ItemStack.f_41583_;
                                }
                                nonNullList.set(2, (Object)itemStack16);
                                _player3.m_150109_().m_6596_();
                            } else if (_entity instanceof LivingEntity) {
                                ItemStack itemStack18;
                                ItemStack itemStack19;
                                _living = _entity;
                                if (entity instanceof LivingEntity) {
                                    LivingEntity _entGetArmor5 = (LivingEntity)entity;
                                    itemStack19 = _entGetArmor5.m_6844_(EquipmentSlot.CHEST);
                                } else {
                                    itemStack19 = ItemStack.f_41583_;
                                }
                                if (itemStack19.m_41720_() == JujutsucraftModItems.SUKUNA_BODY_CHESTPLATE.get()) {
                                    itemStack18 = ItemStack.f_41583_;
                                } else if (entity instanceof LivingEntity) {
                                    _entGetArmor = (LivingEntity)entity;
                                    itemStack18 = _entGetArmor.m_6844_(EquipmentSlot.CHEST);
                                } else {
                                    itemStack18 = ItemStack.f_41583_;
                                }
                                _living.m_8061_(EquipmentSlot.CHEST, itemStack18);
                            }
                            _entity = entityinstance;
                            if (_entity instanceof Player) {
                                ItemStack itemStack20;
                                _player3 = (Player)_entity;
                                NonNullList nonNullList = _player3.m_150109_().f_35975_;
                                if (entity instanceof LivingEntity) {
                                    _entGetArmor = (LivingEntity)entity;
                                    itemStack20 = _entGetArmor.m_6844_(EquipmentSlot.LEGS);
                                } else {
                                    itemStack20 = ItemStack.f_41583_;
                                }
                                nonNullList.set(1, (Object)itemStack20);
                                _player3.m_150109_().m_6596_();
                            } else if (_entity instanceof LivingEntity) {
                                ItemStack itemStack21;
                                _living = _entity;
                                if (entity instanceof LivingEntity) {
                                    _entGetArmor = (LivingEntity)entity;
                                    itemStack21 = _entGetArmor.m_6844_(EquipmentSlot.LEGS);
                                } else {
                                    itemStack21 = ItemStack.f_41583_;
                                }
                                _living.m_8061_(EquipmentSlot.LEGS, itemStack21);
                            }
                            _entity = entityinstance;
                            if (_entity instanceof Player) {
                                ItemStack itemStack22;
                                _player3 = (Player)_entity;
                                NonNullList nonNullList = _player3.m_150109_().f_35975_;
                                if (entity instanceof LivingEntity) {
                                    _entGetArmor = (LivingEntity)entity;
                                    itemStack22 = _entGetArmor.m_6844_(EquipmentSlot.FEET);
                                } else {
                                    itemStack22 = ItemStack.f_41583_;
                                }
                                nonNullList.set(0, (Object)itemStack22);
                                _player3.m_150109_().m_6596_();
                            } else if (_entity instanceof LivingEntity) {
                                ItemStack itemStack23;
                                _living = _entity;
                                if (entity instanceof LivingEntity) {
                                    _entGetArmor = (LivingEntity)entity;
                                    itemStack23 = _entGetArmor.m_6844_(EquipmentSlot.FEET);
                                } else {
                                    itemStack23 = ItemStack.f_41583_;
                                }
                                _living.m_8061_(EquipmentSlot.FEET, itemStack23);
                            }
                            if (entityinstance instanceof LivingEntity) {
                                ItemStack itemStack24;
                                _entity = (LivingEntity)entityinstance;
                                if (entity instanceof LivingEntity) {
                                    _livEnt = (LivingEntity)entity;
                                    itemStack24 = _livEnt.m_21205_();
                                } else {
                                    itemStack24 = ItemStack.f_41583_;
                                }
                                _setstack = itemStack24.m_41777_();
                                _setstack.m_41764_(1);
                                _entity.m_21008_(InteractionHand.MAIN_HAND, _setstack);
                                if (_entity instanceof Player) {
                                    _player = (Player)_entity;
                                    _player.m_150109_().m_6596_();
                                }
                            }
                            if (entityinstance instanceof LivingEntity) {
                                ItemStack itemStack25;
                                _entity = (LivingEntity)entityinstance;
                                if (entity instanceof LivingEntity) {
                                    _livEnt = (LivingEntity)entity;
                                    itemStack25 = _livEnt.m_21206_();
                                } else {
                                    itemStack25 = ItemStack.f_41583_;
                                }
                                _setstack = itemStack25.m_41777_();
                                _setstack.m_41764_(1);
                                _entity.m_21008_(InteractionHand.OFF_HAND, _setstack);
                                if (_entity instanceof Player) {
                                    _player = (Player)_entity;
                                    _player.m_150109_().m_6596_();
                                }
                            }
                            _serverLevel.m_7967_(entityinstance);
                        }
                    }
                    if (!entity.m_9236_().m_5776_()) {
                        entity.m_146870_();
                    }
                }
            }
        }
    }
}
