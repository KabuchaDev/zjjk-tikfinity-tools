/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.mcreator.jujutsucraft.entity.EightHandledSwordDivergentSilaDivineGeneralMahoragaEntity
 *  net.mcreator.jujutsucraft.entity.GojoSatoruEntity
 *  net.mcreator.jujutsucraft.entity.JogoEntity
 *  net.mcreator.jujutsucraft.entity.PurpleEntity
 *  net.mcreator.jujutsucraft.entity.SukunaEntity
 *  net.mcreator.jujutsucraft.entity.SukunaFushiguroEntity
 *  net.mcreator.jujutsucraft.entity.SukunaPerfectEntity
 *  net.mcreator.jujutsucraft.init.JujutsucraftModItems
 *  net.mcreator.jujutsucraft.init.JujutsucraftModMobEffects
 *  net.mcreator.jujutsucraft.procedures.AIFushiguroMegumiProcedure
 *  net.mcreator.jujutsucraft.procedures.CalculateAttackProcedure
 *  net.mcreator.jujutsucraft.procedures.GetDistanceProcedure
 *  net.mcreator.jujutsucraft.procedures.LogicConfilmDomainProcedure
 *  net.mcreator.jujutsucraft.procedures.LogicCooldownCombatProcedure
 *  net.mcreator.jujutsucraft.procedures.LogicStartPassiveProcedure
 *  net.mcreator.jujutsucraft.procedures.LogicStartProcedure
 *  net.mcreator.jujutsucraft.procedures.ResetCounterProcedure
 *  net.mcreator.jujutsucraft.procedures.ReturnShadowProcedure
 *  net.mcreator.jujutsucraft.procedures.WhenBackStepProcedure
 *  net.minecraft.commands.CommandSource
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.LevelAccessor
 */
package net.mcreator.jujutsucraft.procedures;

import net.mcreator.jujutsucraft.entity.EightHandledSwordDivergentSilaDivineGeneralMahoragaEntity;
import net.mcreator.jujutsucraft.entity.GojoSatoruEntity;
import net.mcreator.jujutsucraft.entity.JogoEntity;
import net.mcreator.jujutsucraft.entity.PurpleEntity;
import net.mcreator.jujutsucraft.entity.SukunaEntity;
import net.mcreator.jujutsucraft.entity.SukunaFushiguroEntity;
import net.mcreator.jujutsucraft.entity.SukunaPerfectEntity;
import net.mcreator.jujutsucraft.init.JujutsucraftModItems;
import net.mcreator.jujutsucraft.init.JujutsucraftModMobEffects;
import net.mcreator.jujutsucraft.procedures.AIActiveProcedure;
import net.mcreator.jujutsucraft.procedures.AIDomainLogicProcedure;
import net.mcreator.jujutsucraft.procedures.AIFushiguroMegumiProcedure;
import net.mcreator.jujutsucraft.procedures.CalculateAttackProcedure;
import net.mcreator.jujutsucraft.procedures.GetDistanceProcedure;
import net.mcreator.jujutsucraft.procedures.LogicConfilmDomainProcedure;
import net.mcreator.jujutsucraft.procedures.LogicCooldownCombatProcedure;
import net.mcreator.jujutsucraft.procedures.LogicStartPassiveProcedure;
import net.mcreator.jujutsucraft.procedures.LogicStartProcedure;
import net.mcreator.jujutsucraft.procedures.ResetCounterProcedure;
import net.mcreator.jujutsucraft.procedures.ReturnShadowProcedure;
import net.mcreator.jujutsucraft.procedures.WhenBackStepProcedure;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelAccessor;

public class AISukunaProcedure {
    /*
     * Unable to fully structure code
     */
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        block94: {
            block95: {
                block96: {
                    block99: {
                        block102: {
                            block103: {
                                block101: {
                                    block100: {
                                        block98: {
                                            block97: {
                                                if (entity == null) {
                                                    return;
                                                }
                                                item_head = ItemStack.f_41583_;
                                                rnd = 0.0;
                                                tick = 0.0;
                                                NUM_SUM = 0.0;
                                                distance = 0.0;
                                                domain = false;
                                                infinity = false;
                                                fushiguro_body = false;
                                                mahoraga_exist = false;
                                                vsMahoraga = false;
                                                flag_megumiTechnique = false;
                                                target_entity = null;
                                                if (entity instanceof Mob) {
                                                    _mobEnt = (Mob)entity;
                                                    v0 = _mobEnt.m_5448_();
                                                } else {
                                                    v0 = target_entity = null;
                                                }
                                                if (!entity.m_6084_()) break block94;
                                                AIActiveProcedure.execute(world, x, y, z, entity);
                                                fushiguro_body = entity instanceof SukunaFushiguroEntity;
                                                if (entity instanceof LivingEntity) {
                                                    _entGetArmor = (LivingEntity)entity;
                                                    v1 = _entGetArmor.m_6844_(EquipmentSlot.HEAD);
                                                } else {
                                                    v1 = ItemStack.f_41583_;
                                                }
                                                item_head = v1.m_41777_();
                                                if (fushiguro_body && !entity.getPersistentData().m_128471_("flag_start")) {
                                                    entity.getPersistentData().m_128379_("flag_start", true);
                                                    rnd = 4.0;
                                                    for (index0 = 0; index0 < 7; ++index0) {
                                                        entity.getPersistentData().m_128347_("TenShadowsTechnique" + Math.round(rnd), 1.0);
                                                        rnd += 1.0;
                                                    }
                                                    entity.getPersistentData().m_128347_("TenShadowsTechnique1", -2.0);
                                                    entity.getPersistentData().m_128347_("TenShadowsTechnique5", -2.0);
                                                    entity.getPersistentData().m_128347_("TenShadowsTechnique14", 1.0);
                                                }
                                                if (!(entity instanceof LivingEntity) || !(_livEnt10 = (LivingEntity)entity).m_21023_(MobEffects.f_19600_)) {
                                                    if (entity instanceof SukunaPerfectEntity || entity instanceof SukunaFushiguroEntity && ((Boolean)(_datEntL12 = (SukunaFushiguroEntity)entity).m_20088_().m_135370_(SukunaFushiguroEntity.DATA_perfect_mode)).booleanValue()) {
                                                        rnd = 35.0;
                                                        entity.getPersistentData().m_128347_("KnockbackFix", 1.0);
                                                    } else {
                                                        rnd = fushiguro_body != false ? 30.0 : 25.0;
                                                    }
                                                    if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).m_9236_().m_5776_()) {
                                                        _entity.m_7292_(new MobEffectInstance(MobEffects.f_19600_, 0x7FFFFFFF, (int)Math.round(rnd), false, false));
                                                    }
                                                    if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).m_9236_().m_5776_()) {
                                                        _entity.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.SUKUNA_EFFECT.get(), 0x7FFFFFFF, (int)Math.round(Math.min(Math.max(rnd - 11.0, 0.0), 19.0)), false, false));
                                                    }
                                                }
                                                if (!(entity instanceof LivingEntity && (_livEnt16 = (LivingEntity)entity).m_21023_(MobEffects.f_19606_) || !(entity instanceof LivingEntity) || (_entity = (LivingEntity)entity).m_9236_().m_5776_())) {
                                                    _entity.m_7292_(new MobEffectInstance(MobEffects.f_19606_, 0x7FFFFFFF, 3, false, false));
                                                }
                                                if (entity instanceof SukunaEntity && target_entity instanceof JogoEntity && entity.getPersistentData().m_128459_("cnt_target") > 6.0) {
                                                    if (target_entity.getPersistentData().m_128459_("skill") == 415.0) {
                                                        if (!entity.getPersistentData().m_128471_("flag2")) {
                                                            entity.getPersistentData().m_128379_("flag2", true);
                                                        }
                                                        if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).m_9236_().m_5776_()) {
                                                            _entity.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.COOLDOWN_TIME.get(), 10, 0, false, false));
                                                        }
                                                        if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).m_9236_().m_5776_()) {
                                                            _entity.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.COOLDOWN_TIME_COMBAT.get(), 10, 0, false, false));
                                                        }
                                                    } else if (entity.getPersistentData().m_128471_("flag2") && (distance = GetDistanceProcedure.execute((Entity)entity)) < 32.0) {
                                                        if (distance < 6.0 && entity.m_20096_()) {
                                                            if (entity instanceof LivingEntity) {
                                                                _entity = (LivingEntity)entity;
                                                                _entity.m_21195_(MobEffects.f_19597_);
                                                            }
                                                            entity.getPersistentData().m_128379_("PRESS_S", true);
                                                            WhenBackStepProcedure.execute((LevelAccessor)world, (Entity)entity);
                                                            entity.getPersistentData().m_128379_("PRESS_S", false);
                                                        }
                                                        if (entity instanceof LivingEntity) {
                                                            _entity = (LivingEntity)entity;
                                                            _entity.m_21195_(MobEffects.f_19596_);
                                                        }
                                                        if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).m_9236_().m_5776_()) {
                                                            _entity.m_7292_(new MobEffectInstance(MobEffects.f_19597_, 10, 9, false, false));
                                                        }
                                                        if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).m_9236_().m_5776_()) {
                                                            _entity.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.COOLDOWN_TIME_COMBAT.get(), 10, 0, false, false));
                                                        }
                                                        if (target_entity instanceof LivingEntity) {
                                                            _entity = target_entity;
                                                            _entity.m_21195_(MobEffects.f_19596_);
                                                        }
                                                        if (target_entity instanceof LivingEntity && !(_entity = target_entity).m_9236_().m_5776_()) {
                                                            _entity.m_7292_(new MobEffectInstance(MobEffects.f_19597_, 10, 9, false, false));
                                                        }
                                                        if (target_entity instanceof LivingEntity && !(_entity = target_entity).m_9236_().m_5776_()) {
                                                            _entity.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.COOLDOWN_TIME_COMBAT.get(), 10, 0, false, false));
                                                        }
                                                        if (target_entity.m_20096_() && (target_entity instanceof LivingEntity != false && (_livEnt = target_entity).m_21023_((MobEffect)JujutsucraftModMobEffects.COOLDOWN_TIME.get()) != false ? _livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.COOLDOWN_TIME.get()).m_19557_() : 0) < 200 && target_entity instanceof LivingEntity) {
                                                            _entity = target_entity;
                                                            _entity.m_21195_((MobEffect)JujutsucraftModMobEffects.COOLDOWN_TIME.get());
                                                        }
                                                    }
                                                }
                                                if (!(target_entity instanceof LivingEntity) || !(entity.getPersistentData().m_128459_("cnt_target") > 6.0)) break block95;
                                                if (!(!fushiguro_body || entity instanceof SukunaFushiguroEntity && ((Boolean)(_datEntL42 = (SukunaFushiguroEntity)entity).m_20088_().m_135370_(SukunaFushiguroEntity.DATA_perfect_mode)).booleanValue() || target_entity instanceof GojoSatoruEntity || target_entity instanceof PurpleEntity)) {
                                                    if (entity instanceof LivingEntity) {
                                                        _livEnt = (LivingEntity)entity;
                                                        v2 = _livEnt.m_21223_();
                                                    } else {
                                                        v2 = -1.0f;
                                                    }
                                                    v3 = v2;
                                                    if (entity instanceof LivingEntity) {
                                                        _livEnt = (LivingEntity)entity;
                                                        v4 = _livEnt.m_21233_();
                                                    } else {
                                                        v4 = -1.0f;
                                                    }
                                                    if (v3 <= (double)v4 * 0.3) {
                                                        if (entity instanceof SukunaFushiguroEntity) {
                                                            _datEntSetL = (SukunaFushiguroEntity)entity;
                                                            _datEntSetL.m_20088_().m_135381_(SukunaFushiguroEntity.DATA_perfect_mode, (Object)true);
                                                        }
                                                        if (entity instanceof SukunaFushiguroEntity) {
                                                            animatable = (SukunaFushiguroEntity)entity;
                                                            animatable.setTexture("sukuna_perfect");
                                                        }
                                                        if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).m_9236_().m_5776_()) {
                                                            _entity.m_7292_(new MobEffectInstance(MobEffects.f_19601_, 10, 10, false, false));
                                                        }
                                                        if (!(_ent = entity).m_9236_().m_5776_() && _ent.m_20194_() != null) {
                                                            _ent.m_20194_().m_129892_().m_230957_(new CommandSourceStack(CommandSource.f_80164_, _ent.m_20182_(), _ent.m_20155_(), _ent.m_9236_() instanceof ServerLevel != false ? (ServerLevel)_ent.m_9236_() : null, 4, _ent.m_7755_().getString(), _ent.m_5446_(), _ent.m_9236_().m_7654_(), _ent), "item replace entity @s weapon.offhand with jujutsucraft:supreme_martial_solution");
                                                        }
                                                        if (!(_ent = entity).m_9236_().m_5776_() && _ent.m_20194_() != null) {
                                                            _ent.m_20194_().m_129892_().m_230957_(new CommandSourceStack(CommandSource.f_80164_, _ent.m_20182_(), _ent.m_20155_(), _ent.m_9236_() instanceof ServerLevel != false ? (ServerLevel)_ent.m_9236_() : null, 4, _ent.m_7755_().getString(), _ent.m_5446_(), _ent.m_9236_().m_7654_(), _ent), "item replace entity @s armor.chest with jujutsucraft:sukuna_body_chestplate");
                                                        }
                                                        if (entity instanceof LivingEntity) {
                                                            _entity = (LivingEntity)entity;
                                                            _entity.m_21195_(MobEffects.f_19600_);
                                                        }
                                                        entity.getPersistentData().m_128347_("cnt_reverse_lim", 0.0);
                                                        entity.getPersistentData().m_128347_("skill", 1.0);
                                                        ReturnShadowProcedure.execute((LevelAccessor)world, (double)x, (double)y, (double)z, (Entity)entity);
                                                        entity.getPersistentData().m_128347_("skill", 0.0);
                                                        if (item_head.m_41720_() == JujutsucraftModItems.MAHORAGA_WHEEL_HELMET.get()) {
                                                            _entity = entity;
                                                            if (_entity instanceof Player) {
                                                                _player = (Player)_entity;
                                                                _player.m_150109_().f_35975_.set(3, (Object)ItemStack.f_41583_);
                                                                _player.m_150109_().m_6596_();
                                                            } else if (_entity instanceof LivingEntity) {
                                                                _living = _entity;
                                                                _living.m_8061_(EquipmentSlot.HEAD, ItemStack.f_41583_);
                                                            }
                                                        }
                                                    }
                                                }
                                                infinity = target_entity instanceof LivingEntity != false && (_livEnt59 = target_entity).m_21023_((MobEffect)JujutsucraftModMobEffects.INFINITY_EFFECT.get()) != false;
                                                entity.getPersistentData().m_128347_("cnt_x", entity.getPersistentData().m_128459_("cnt_x") + 1.0);
                                                entity.getPersistentData().m_128347_("cnt_rest", 0.0);
                                                if (!(entity.getPersistentData().m_128459_("cnt_x") > 10.0) || entity.getPersistentData().m_128459_("skill") != 0.0) break block96;
                                                mahoraga_exist = entity.getPersistentData().m_128459_("TenShadowsTechnique14") == -1.0;
                                                vsMahoraga = target_entity instanceof EightHandledSwordDivergentSilaDivineGeneralMahoragaEntity;
                                                distance = GetDistanceProcedure.execute((Entity)entity);
                                                if (vsMahoraga && entity.getPersistentData().m_128459_("cnt_target") >= 2400.0 && entity.getPersistentData().m_128459_("cnt_target") <= 3600.0) {
                                                    entity.getPersistentData().m_128379_("flag_domain", true);
                                                }
                                                domain = LogicConfilmDomainProcedure.execute((LevelAccessor)world, (double)x, (double)y, (double)z, (Entity)entity) != false && mahoraga_exist == false;
                                                ResetCounterProcedure.execute((Entity)entity);
                                                if (fushiguro_body && (!(entity instanceof SukunaFushiguroEntity) || !((Boolean)(_datEntL70 = (SukunaFushiguroEntity)entity).m_20088_().m_135370_(SukunaFushiguroEntity.DATA_perfect_mode)).booleanValue()) && entity.getPersistentData().m_128459_("cnt_target") > 200.0 && entity.getPersistentData().m_128459_("TenShadowsTechnique14") >= 1.0 && item_head.m_41720_() != JujutsucraftModItems.MAHORAGA_WHEEL_HELMET.get()) {
                                                    _entity = entity;
                                                    if (_entity instanceof Player) {
                                                        _player = (Player)_entity;
                                                        _player.m_150109_().f_35975_.set(3, (Object)new ItemStack((ItemLike)JujutsucraftModItems.MAHORAGA_WHEEL_HELMET.get()));
                                                        _player.m_150109_().m_6596_();
                                                    } else if (_entity instanceof LivingEntity) {
                                                        _living = _entity;
                                                        _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)JujutsucraftModItems.MAHORAGA_WHEEL_HELMET.get()));
                                                    }
                                                }
                                                if ((!entity.getPersistentData().m_128471_("flag1") && entity instanceof LivingEntity && (_livEnt76 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()) || entity.getPersistentData().m_128471_("flag2")) && distance < 48.0 && entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).m_9236_().m_5776_()) {
                                                    _entity.m_7292_(new MobEffectInstance(MobEffects.f_19612_, 20, 0, false, false));
                                                }
                                                if (!fushiguro_body || entity instanceof SukunaFushiguroEntity && ((Boolean)(_datEntL79 = (SukunaFushiguroEntity)entity).m_20088_().m_135370_(SukunaFushiguroEntity.DATA_perfect_mode)).booleanValue()) break block97;
                                                entity.getPersistentData().m_128379_("flag_mahoraga", entity.getPersistentData().m_128459_("TenShadowsTechnique14") > 0.0);
                                                v5 = entity.getPersistentData();
                                                if (!entity.getPersistentData().m_128471_("flag_mahoraga")) ** GOTO lbl-1000
                                                if (entity instanceof LivingEntity) {
                                                    _livEnt = (LivingEntity)entity;
                                                    v6 = _livEnt.m_21223_();
                                                } else {
                                                    v6 = -1.0f;
                                                }
                                                v7 = v6;
                                                if (entity instanceof LivingEntity) {
                                                    _livEnt = (LivingEntity)entity;
                                                    v8 = _livEnt.m_21233_();
                                                } else {
                                                    v8 = -1.0f;
                                                }
                                                if (v7 < (double)v8 * 0.6 || item_head.m_41784_().m_128459_("skill205") >= 100.0 && infinity) {
                                                    v9 = true;
                                                } else lbl-1000:
                                                // 2 sources

                                                {
                                                    v9 = false;
                                                }
                                                v5.m_128379_("flag_mahoraga", v9);
                                                if (entity instanceof LivingEntity) {
                                                    _livEnt = (LivingEntity)entity;
                                                    v10 = _livEnt.m_21223_();
                                                } else {
                                                    v10 = -1.0f;
                                                }
                                                v11 = v10;
                                                if (entity instanceof LivingEntity) {
                                                    _livEnt = (LivingEntity)entity;
                                                    v12 = _livEnt.m_21233_();
                                                } else {
                                                    v12 = -1.0f;
                                                }
                                                if (v11 > (double)v12 * 0.6) {
                                                    if (entity instanceof Mob) {
                                                        _mobEnt = (Mob)entity;
                                                        v13 = _mobEnt.m_5448_();
                                                    } else {
                                                        v13 = null;
                                                    }
                                                    if (v13 instanceof GojoSatoruEntity && (entity instanceof LivingEntity != false && (_livEnt = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.BRAIN_DAMAGE.get()) != false ? _livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.BRAIN_DAMAGE.get()).m_19564_() : 0) < 1) {
                                                        entity.getPersistentData().m_128379_("flag_mahoraga", false);
                                                    }
                                                }
                                                break block98;
                                            }
                                            entity.getPersistentData().m_128379_("flag_mahoraga", false);
                                        }
                                        v14 = !(!fushiguro_body || entity instanceof SukunaFushiguroEntity && ((Boolean)(_datEntL94 = (SukunaFushiguroEntity)entity).m_20088_().m_135370_(SukunaFushiguroEntity.DATA_perfect_mode)).booleanValue()) ? (entity.getPersistentData().m_128471_("flag_mahoraga") || Math.random() < 0.2 && !infinity) && (!(entity instanceof LivingEntity) || !(_livEnt96 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get())) && !domain && entity.getPersistentData().m_128459_("TenShadowsTechnique14") >= 1.0 || entity.getPersistentData().m_128471_("flag_agito") && entity.getPersistentData().m_128459_("TenShadowsTechnique13") >= 0.0 : (flag_megumiTechnique = false);
                                        if (!(LogicStartProcedure.execute((Entity)entity) != false && (Math.random() > (infinity != false ? 0.75 : 0.0) && distance < 48.0 || entity instanceof LivingEntity != false && (_livEnt100 = (LivingEntity)entity).m_21023_(MobEffects.f_19612_) != false || flag_megumiTechnique != false)) && !domain) break block99;
                                        if (!flag_megumiTechnique) break block100;
                                        AIFushiguroMegumiProcedure.execute((LevelAccessor)world, (double)x, (double)y, (double)z, (Entity)entity);
                                        break block96;
                                    }
                                    entity.getPersistentData().m_128347_("cnt_x", 0.0);
                                    if (!domain) break block101;
                                    rnd = 20.0;
                                    tick = 20.0;
                                    break block102;
                                }
                                if (!entity.getPersistentData().m_128471_("flag2")) break block103;
                                if ((target_entity instanceof LivingEntity != false && (_livEnt = target_entity).m_21023_((MobEffect)JujutsucraftModMobEffects.COOLDOWN_TIME.get()) != false ? _livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.COOLDOWN_TIME.get()).m_19557_() : 0) > 20 || (target_entity instanceof LivingEntity != false && (_livEnt = target_entity).m_21023_((MobEffect)JujutsucraftModMobEffects.UNSTABLE.get()) != false ? _livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.UNSTABLE.get()).m_19557_() : 0) > 20) break block102;
                                entity.getPersistentData().m_128379_("flag2", false);
                                rnd = 7.0;
                                tick = 250.0;
                                if (entity instanceof LivingEntity) {
                                    _entity = (LivingEntity)entity;
                                    _entity.m_21195_((MobEffect)JujutsucraftModMobEffects.COOLDOWN_TIME.get());
                                }
                                if (entity instanceof LivingEntity) {
                                    _entity = (LivingEntity)entity;
                                    _entity.m_21195_((MobEffect)JujutsucraftModMobEffects.UNSTABLE.get());
                                }
                                break block102;
                            }
                            if (!(entity.getPersistentData().m_128471_("flag1") || target_entity instanceof GojoSatoruEntity && entity instanceof SukunaFushiguroEntity || !(entity instanceof LivingEntity) || !(_livEnt111 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()) || entity.getPersistentData().m_128471_("Failed") || !(entity.getPersistentData().m_128459_("dust_amount") > 100.0) || (entity instanceof LivingEntity != false && (_livEnt = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()) != false ? _livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()).m_19557_() : 0) >= 1200 && !vsMahoraga)) {
                                entity.getPersistentData().m_128379_("flag1", true);
                                rnd = 7.0;
                                tick = 250.0;
                            } else if (!(!infinity || entity instanceof SukunaFushiguroEntity && ((Boolean)(_datEntL116 = (SukunaFushiguroEntity)entity).m_20088_().m_135370_(SukunaFushiguroEntity.DATA_world_cut)).booleanValue() || entity instanceof SukunaPerfectEntity)) {
                                rnd = 0.0;
                            } else {
                                for (index1 = 0; index1 < 256; ++index1) {
                                    rnd = Mth.m_216271_((RandomSource)RandomSource.m_216327_(), (int)5, (int)8);
                                    if (rnd == 5.0) {
                                        tick = 50.0;
                                        break;
                                    }
                                    if (rnd == 6.0) {
                                        rnd = 0.0;
                                        tick = 100.0;
                                        if (infinity || vsMahoraga || target_entity instanceof GojoSatoruEntity || distance > 5.0) continue;
                                        rnd = 6.0;
                                        break;
                                    }
                                    if (rnd == 7.0) {
                                        rnd = 0.0;
                                        tick = 250.0;
                                        if (infinity || entity instanceof SukunaFushiguroEntity && target_entity instanceof GojoSatoruEntity || target_entity instanceof JogoEntity || entity.getPersistentData().m_128459_("cnt_target") <= 1200.0 || vsMahoraga && !entity.getPersistentData().m_128471_("flag1") || Math.random() < 0.95 || distance < 8.0) continue;
                                        rnd = 7.0;
                                        break;
                                    }
                                    if (rnd != 20.0) continue;
                                    rnd = 0.0;
                                    tick = 20.0;
                                    if (mahoraga_exist || AIDomainLogicProcedure.execute(world, x, y, z, entity)) continue;
                                    rnd = 20.0;
                                    break;
                                }
                            }
                        }
                        if (rnd > 0.0) {
                            if (!(rnd != 20.0 || entity instanceof LivingEntity && (_livEnt125 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.BRAIN_DAMAGE.get()))) {
                                entity.getPersistentData().m_128347_("skill", 1.0);
                                ReturnShadowProcedure.execute((LevelAccessor)world, (double)x, (double)y, (double)z, (Entity)entity);
                            }
                            if (mahoraga_exist && rnd == 7.0) {
                                tick = 100.0;
                                entity.getPersistentData().m_128347_("skill", 1007.0);
                            } else {
                                entity.getPersistentData().m_128347_("skill", 100.0 + rnd);
                            }
                            if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).m_9236_().m_5776_()) {
                                _entity.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.COOLDOWN_TIME.get(), (int)tick, 0, false, false));
                            }
                            if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).m_9236_().m_5776_()) {
                                _entity.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.CURSED_TECHNIQUE.get(), 0x7FFFFFFF, 0, false, false));
                            }
                        } else {
                            CalculateAttackProcedure.execute((LevelAccessor)world, (Entity)entity);
                        }
                        break block96;
                    }
                    entity.getPersistentData().m_128347_("cnt_x", 0.0);
                    rnd = 0.0;
                    tick = 0.0;
                    if (!LogicCooldownCombatProcedure.execute((Entity)entity) && !infinity) {
                        if (entity instanceof LivingEntity) {
                            _entity = (LivingEntity)entity;
                            _entity.m_21195_((MobEffect)JujutsucraftModMobEffects.DOMAIN_AMPLIFICATION.get());
                        }
                        if (LogicStartPassiveProcedure.execute((Entity)entity)) {
                            if (distance < 16.0 && Math.random() < 0.5) {
                                rnd = 111.0;
                                tick = 50.0;
                            }
                            if (distance < 10.0 && Math.random() < 0.75) {
                                rnd = 112.0;
                                tick = 50.0;
                            }
                            if (distance < 48.0 && Math.random() < 0.25) {
                                rnd = 113.0;
                                tick = 50.0;
                            }
                        }
                    }
                    if (rnd != 0.0) {
                        entity.getPersistentData().m_128347_("skill", rnd);
                        if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).m_9236_().m_5776_()) {
                            _entity.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.COOLDOWN_TIME_COMBAT.get(), (int)tick, 0, false, false));
                        }
                        if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).m_9236_().m_5776_()) {
                            _entity.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.CURSED_TECHNIQUE.get(), 0x7FFFFFFF, 0, false, false));
                        }
                    } else {
                        CalculateAttackProcedure.execute((LevelAccessor)world, (Entity)entity);
                    }
                }
                if (entity instanceof LivingEntity && (_livEnt136 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.UNSTABLE.get()) && (!(entity instanceof LivingEntity) || !(_livEnt137 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()))) {
                    entity.getPersistentData().m_128379_("flag1", false);
                }
                break block94;
            }
            entity.getPersistentData().m_128379_("flag2", false);
            entity.getPersistentData().m_128347_("cnt_x", 0.0);
            entity.getPersistentData().m_128347_("cnt_rest", entity.getPersistentData().m_128459_("cnt_rest") + 1.0);
            if (entity.getPersistentData().m_128459_("cnt_rest") > 120.0) {
                entity.getPersistentData().m_128347_("cnt_rest", 0.0);
                entity.getPersistentData().m_128347_("skill", 1.0);
                ReturnShadowProcedure.execute((LevelAccessor)world, (double)x, (double)y, (double)z, (Entity)entity);
                entity.getPersistentData().m_128347_("skill", 0.0);
            }
        }
    }
}
