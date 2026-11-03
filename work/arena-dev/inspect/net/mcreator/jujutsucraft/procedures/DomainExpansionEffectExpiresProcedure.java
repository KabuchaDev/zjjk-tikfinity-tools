/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.mcreator.jujutsucraft.JujutsucraftMod
 *  net.mcreator.jujutsucraft.entity.FushiguroMegumiEntity
 *  net.mcreator.jujutsucraft.entity.FushiguroMegumiShibuyaEntity
 *  net.mcreator.jujutsucraft.entity.GojoSatoruEntity
 *  net.mcreator.jujutsucraft.entity.MahitoEntity
 *  net.mcreator.jujutsucraft.entity.SukunaEntity
 *  net.mcreator.jujutsucraft.entity.SukunaFushiguroEntity
 *  net.mcreator.jujutsucraft.entity.SukunaPerfectEntity
 *  net.mcreator.jujutsucraft.init.JujutsucraftModItems
 *  net.mcreator.jujutsucraft.init.JujutsucraftModMobEffects
 *  net.mcreator.jujutsucraft.network.JujutsucraftModVariables
 *  net.mcreator.jujutsucraft.network.JujutsucraftModVariables$PlayerVariables
 *  net.mcreator.jujutsucraft.procedures.BreakDomainProcedure
 *  net.mcreator.jujutsucraft.procedures.DespawnRangedAmmoProcedure
 *  net.mcreator.jujutsucraft.procedures.KeyChangeTechniqueOnKeyPressedProcedure
 *  net.mcreator.jujutsucraft.procedures.ReturnShadowProcedure
 *  net.minecraft.commands.CommandSource
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraftforge.common.capabilities.Capability
 *  net.minecraftforge.common.capabilities.ForgeCapabilities
 *  net.minecraftforge.items.IItemHandler
 */
package net.mcreator.jujutsucraft.procedures;

import java.util.concurrent.atomic.AtomicReference;
import net.mcreator.jujutsucraft.JujutsucraftMod;
import net.mcreator.jujutsucraft.entity.FushiguroMegumiEntity;
import net.mcreator.jujutsucraft.entity.FushiguroMegumiShibuyaEntity;
import net.mcreator.jujutsucraft.entity.GojoSatoruEntity;
import net.mcreator.jujutsucraft.entity.MahitoEntity;
import net.mcreator.jujutsucraft.entity.SukunaEntity;
import net.mcreator.jujutsucraft.entity.SukunaFushiguroEntity;
import net.mcreator.jujutsucraft.entity.SukunaPerfectEntity;
import net.mcreator.jujutsucraft.init.JujutsucraftModItems;
import net.mcreator.jujutsucraft.init.JujutsucraftModMobEffects;
import net.mcreator.jujutsucraft.network.JujutsucraftModVariables;
import net.mcreator.jujutsucraft.procedures.BreakDomainProcedure;
import net.mcreator.jujutsucraft.procedures.DespawnRangedAmmoProcedure;
import net.mcreator.jujutsucraft.procedures.KeyChangeTechniqueOnKeyPressedProcedure;
import net.mcreator.jujutsucraft.procedures.ReturnShadowProcedure;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;

public class DomainExpansionEffectExpiresProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        LivingEntity _livEnt0;
        if (entity == null) {
            return;
        }
        boolean logic_a = false;
        double old_cnt4 = 0.0;
        double num_timer = 0.0;
        double old_skill = 0.0;
        double NUM1 = 0.0;
        if (!(entity instanceof LivingEntity) || !(_livEnt0 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get())) {
            LivingEntity _entity;
            if (new /* Unavailable Anonymous Inner Class!! */.checkGamemode(entity)) {
                num_timer = 20.0;
            } else {
                num_timer = 600.0;
                if (!(entity instanceof Player)) {
                    entity.getPersistentData().m_128347_("cnt_break", entity.getPersistentData().m_128459_("cnt_break") + 1.0);
                    if (entity instanceof GojoSatoruEntity || entity instanceof SukunaEntity || entity instanceof SukunaFushiguroEntity || entity instanceof SukunaPerfectEntity) {
                        LivingEntity livingEntity;
                        logic_a = false;
                        if (entity instanceof Mob) {
                            Mob _mobEnt = (Mob)entity;
                            livingEntity = _mobEnt.m_5448_();
                        } else {
                            livingEntity = null;
                        }
                        if (livingEntity instanceof LivingEntity) {
                            LivingEntity _livEnt;
                            LivingEntity _livEnt2;
                            LivingEntity livingEntity2;
                            if (entity instanceof Mob) {
                                Mob _mobEnt = (Mob)entity;
                                v1 = _mobEnt.m_5448_();
                            } else {
                                v1 = livingEntity2 = null;
                            }
                            if ((livingEntity2 instanceof LivingEntity && (_livEnt2 = livingEntity2).m_21023_(MobEffects.f_19600_) ? _livEnt2.m_21124_(MobEffects.f_19600_).m_19564_() : 0) > (entity instanceof LivingEntity && (_livEnt = (LivingEntity)entity).m_21023_(MobEffects.f_19600_) ? _livEnt.m_21124_(MobEffects.f_19600_).m_19564_() : 0) - 7) {
                                num_timer = Math.min(num_timer, 300.0);
                                if (entity.getPersistentData().m_128459_("cnt_break") > 3.0) {
                                    LivingEntity _entity2;
                                    entity.getPersistentData().m_128347_("cnt_break", 0.0);
                                    if (entity instanceof LivingEntity && !(_entity2 = (LivingEntity)entity).m_9236_().m_5776_()) {
                                        _entity2.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.BRAIN_DAMAGE.get(), 6000, 0, false, false));
                                    }
                                    logic_a = true;
                                }
                            }
                        }
                        if (!logic_a && entity.getPersistentData().m_128459_("cnt_break") > 3.0) {
                            entity.getPersistentData().m_128347_("cnt_break", 0.0);
                            if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).m_9236_().m_5776_()) {
                                _entity.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.BRAIN_DAMAGE.get(), 6000, 2, false, false));
                            }
                        }
                    } else if (entity.getPersistentData().m_128459_("cnt_break") > 1.0) {
                        entity.getPersistentData().m_128347_("cnt_break", 0.0);
                        if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).m_9236_().m_5776_()) {
                            _entity.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.BRAIN_DAMAGE.get(), 6000, 2, false, false));
                        }
                    }
                }
                if (entity instanceof Player ? ((JujutsucraftModVariables.PlayerVariables)entity.getCapability((Capability)JujutsucraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse((Object)new JujutsucraftModVariables.PlayerVariables())).PlayerCurseTechnique == 15.0 || ((JujutsucraftModVariables.PlayerVariables)entity.getCapability((Capability)JujutsucraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse((Object)new JujutsucraftModVariables.PlayerVariables())).PlayerCurseTechnique2 == 15.0 : entity instanceof MahitoEntity) {
                    num_timer = Math.min(num_timer, 300.0);
                }
            }
            if (entity.getPersistentData().m_128471_("DomainDefeated") || entity.getPersistentData().m_128471_("Failed")) {
                entity.getPersistentData().m_128379_("DomainDefeated", false);
            } else {
                BreakDomainProcedure.execute((LevelAccessor)world, (Entity)entity);
            }
            if (!entity.getPersistentData().m_128471_("attack")) {
                entity.getPersistentData().m_128347_("skill", 0.0);
            }
            NUM1 = 0.0;
            AtomicReference _iitemhandlerref = new AtomicReference();
            entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(_iitemhandlerref::set);
            if (_iitemhandlerref.get() != null) {
                for (int _idx = 0; _idx < ((IItemHandler)_iitemhandlerref.get()).getSlots(); ++_idx) {
                    Entity _ent;
                    ItemStack itemstackiterator = ((IItemHandler)_iitemhandlerref.get()).getStackInSlot(_idx).m_41777_();
                    if (itemstackiterator.m_41720_() == JujutsucraftModItems.SWORD_OKKOTSU_YUTA.get() && itemstackiterator.m_41784_().m_128459_("skill") != 0.0 && !(_ent = entity).m_9236_().m_5776_() && _ent.m_20194_() != null) {
                        _ent.m_20194_().m_129892_().m_230957_(new CommandSourceStack(CommandSource.f_80164_, _ent.m_20182_(), _ent.m_20155_(), _ent.m_9236_() instanceof ServerLevel ? (ServerLevel)_ent.m_9236_() : null, 4, _ent.m_7755_().getString(), _ent.m_5446_(), _ent.m_9236_().m_7654_(), _ent), "/item replace entity @s container." + Math.round(NUM1) + " with air");
                    }
                    NUM1 += 1.0;
                }
            }
            if (entity.getPersistentData().m_128459_("skill_domain") == 6.0 || (entity instanceof Player ? ((JujutsucraftModVariables.PlayerVariables)entity.getCapability((Capability)JujutsucraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse((Object)new JujutsucraftModVariables.PlayerVariables())).PlayerCurseTechnique == 6.0 || ((JujutsucraftModVariables.PlayerVariables)entity.getCapability((Capability)JujutsucraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse((Object)new JujutsucraftModVariables.PlayerVariables())).PlayerCurseTechnique2 == 6.0 : entity instanceof SukunaFushiguroEntity || entity instanceof FushiguroMegumiEntity || entity instanceof FushiguroMegumiShibuyaEntity)) {
                old_skill = entity.getPersistentData().m_128459_("skill");
                old_cnt4 = entity.getPersistentData().m_128459_("cnt4");
                entity.getPersistentData().m_128347_("skill", 1.0);
                ReturnShadowProcedure.execute((LevelAccessor)world, (double)x, (double)y, (double)z, (Entity)entity);
                entity.getPersistentData().m_128347_("skill", old_skill);
                entity.getPersistentData().m_128347_("cnt4", old_cnt4);
            }
            DespawnRangedAmmoProcedure.execute((LevelAccessor)world, (double)x, (double)y, (double)z, (Entity)entity);
            if (num_timer >= 600.0) {
                JujutsucraftMod.queueServerWork((int)1, () -> {
                    LivingEntity _entity;
                    if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).m_9236_().m_5776_()) {
                        _entity.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.UNSTABLE.get(), 600, 0, false, false));
                    }
                    if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).m_9236_().m_5776_()) {
                        LivingEntity _livEnt;
                        _entity.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.FATIGUE.get(), (entity instanceof LivingEntity && (_livEnt = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.FATIGUE.get()) ? _livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.FATIGUE.get()).m_19557_() : 0) + 600, 0, false, false));
                    }
                });
            } else if (num_timer >= 300.0) {
                JujutsucraftMod.queueServerWork((int)1, () -> {
                    LivingEntity _entity;
                    if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).m_9236_().m_5776_()) {
                        _entity.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.UNSTABLE.get(), 300, 0, false, false));
                    }
                    if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).m_9236_().m_5776_()) {
                        LivingEntity _livEnt;
                        _entity.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.FATIGUE.get(), (entity instanceof LivingEntity && (_livEnt = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.FATIGUE.get()) ? _livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.FATIGUE.get()).m_19557_() : 0) + 300, 0, false, false));
                    }
                });
            } else {
                JujutsucraftMod.queueServerWork((int)1, () -> {
                    LivingEntity _entity;
                    if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).m_9236_().m_5776_()) {
                        _entity.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.UNSTABLE.get(), 20, 0, false, false));
                    }
                    if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).m_9236_().m_5776_()) {
                        LivingEntity _livEnt;
                        _entity.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.FATIGUE.get(), (entity instanceof LivingEntity && (_livEnt = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.FATIGUE.get()) ? _livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.FATIGUE.get()).m_19557_() : 0) + 20, 0, false, false));
                    }
                });
            }
            JujutsucraftMod.queueServerWork((int)1, () -> {
                LivingEntity _livEnt;
                LivingEntity _entity;
                if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).m_9236_().m_5776_()) {
                    _entity.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.COOLDOWN_TIME_SIMPLE_DOMAIN.get(), 60, 0, true, true));
                }
                if (entity instanceof LivingEntity) {
                    _entity = (LivingEntity)entity;
                    _entity.m_21195_((MobEffect)JujutsucraftModMobEffects.INFINITY_EFFECT.get());
                }
                if (entity instanceof LivingEntity) {
                    _entity = (LivingEntity)entity;
                    _entity.m_21195_((MobEffect)JujutsucraftModMobEffects.STAR_RAGE.get());
                }
                if (entity instanceof LivingEntity) {
                    _entity = (LivingEntity)entity;
                    _entity.m_21195_((MobEffect)JujutsucraftModMobEffects.COMEDIAN.get());
                }
                if (entity instanceof LivingEntity) {
                    _entity = (LivingEntity)entity;
                    _entity.m_21195_((MobEffect)JujutsucraftModMobEffects.MYTHICAL_BEAST_AMBER_EFFECT.get());
                }
                if (entity instanceof LivingEntity) {
                    _entity = (LivingEntity)entity;
                    _entity.m_21195_((MobEffect)JujutsucraftModMobEffects.INSTANT_SPIRIT_BODYOF_DISTORTED_KILLING_EFFECT.get());
                }
                if (entity instanceof LivingEntity) {
                    _entity = (LivingEntity)entity;
                    _entity.m_21195_((MobEffect)JujutsucraftModMobEffects.INSECT_ARMOR_TECHNIQUE.get());
                }
                if ((entity instanceof LivingEntity && (_livEnt = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.DEATH_PAINTING_BLOOD.get()) ? _livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.DEATH_PAINTING_BLOOD.get()).m_19564_() : 0) > 0 && entity instanceof LivingEntity) {
                    LivingEntity _entity2 = (LivingEntity)entity;
                    _entity2.m_21195_((MobEffect)JujutsucraftModMobEffects.DEATH_PAINTING_BLOOD.get());
                }
            });
            if (entity.getPersistentData().m_128459_("select") != 0.0) {
                entity.getPersistentData().m_128347_("select", 0.0);
                entity.getPersistentData().m_128347_("skill", 0.0);
                if (entity instanceof LivingEntity) {
                    _entity = (LivingEntity)entity;
                    _entity.m_21195_((MobEffect)JujutsucraftModMobEffects.CURSED_TECHNIQUE.get());
                }
            }
            if (entity.getPersistentData().m_128459_("dust_amount") != 0.0) {
                entity.getPersistentData().m_128347_("dust_amount", 0.0);
                String _setval = "";
                entity.getCapability(JujutsucraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.OVERLAY1 = _setval;
                    capability.syncPlayerVariables(entity);
                });
                _setval = "";
                entity.getCapability(JujutsucraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.OVERLAY2 = _setval;
                    capability.syncPlayerVariables(entity);
                });
            }
            entity.getPersistentData().m_128347_("skill_domain", 0.0);
            entity.getPersistentData().m_128379_("Failed", false);
            entity.getPersistentData().m_128379_("Cover", false);
            boolean _setval = true;
            entity.getCapability(JujutsucraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                capability.noChangeTechnique = _setval;
                capability.syncPlayerVariables(entity);
            });
            KeyChangeTechniqueOnKeyPressedProcedure.execute((LevelAccessor)world, (double)x, (double)y, (double)z, (Entity)entity);
        }
    }
}
