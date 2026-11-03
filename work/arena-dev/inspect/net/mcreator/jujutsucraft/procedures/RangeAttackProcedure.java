/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.mcreator.jujutsucraft.entity.ItadoriYujiEntity
 *  net.mcreator.jujutsucraft.entity.ItadoriYujiShibuyaEntity
 *  net.mcreator.jujutsucraft.entity.ItadoriYujiShinjukuEntity
 *  net.mcreator.jujutsucraft.init.JujutsucraftModEntities
 *  net.mcreator.jujutsucraft.init.JujutsucraftModGameRules
 *  net.mcreator.jujutsucraft.init.JujutsucraftModMobEffects
 *  net.mcreator.jujutsucraft.init.JujutsucraftModParticleTypes
 *  net.mcreator.jujutsucraft.network.JujutsucraftModVariables
 *  net.mcreator.jujutsucraft.network.JujutsucraftModVariables$MapVariables
 *  net.mcreator.jujutsucraft.network.JujutsucraftModVariables$PlayerVariables
 *  net.mcreator.jujutsucraft.procedures.ChangeDamage1Procedure
 *  net.mcreator.jujutsucraft.procedures.ChangeDamage2Procedure
 *  net.mcreator.jujutsucraft.procedures.DamageFixProcedure
 *  net.mcreator.jujutsucraft.procedures.EffectConfirmProcedure
 *  net.mcreator.jujutsucraft.procedures.EffectProcedure
 *  net.mcreator.jujutsucraft.procedures.GetEntityFromUUIDProcedure
 *  net.mcreator.jujutsucraft.procedures.LogicBetrayalProcedure
 *  net.mcreator.jujutsucraft.procedures.LogicGuardSuccessProcedure
 *  net.minecraft.advancements.Advancement
 *  net.minecraft.advancements.AdvancementProgress
 *  net.minecraft.commands.CommandSource
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Holder
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.SimpleParticleType
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.tags.TagKey
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.registries.ForgeRegistries
 */
package net.mcreator.jujutsucraft.procedures;

import java.util.List;
import net.mcreator.jujutsucraft.entity.ItadoriYujiEntity;
import net.mcreator.jujutsucraft.entity.ItadoriYujiShibuyaEntity;
import net.mcreator.jujutsucraft.entity.ItadoriYujiShinjukuEntity;
import net.mcreator.jujutsucraft.init.JujutsucraftModEntities;
import net.mcreator.jujutsucraft.init.JujutsucraftModGameRules;
import net.mcreator.jujutsucraft.init.JujutsucraftModMobEffects;
import net.mcreator.jujutsucraft.init.JujutsucraftModParticleTypes;
import net.mcreator.jujutsucraft.network.JujutsucraftModVariables;
import net.mcreator.jujutsucraft.procedures.ChangeDamage1Procedure;
import net.mcreator.jujutsucraft.procedures.ChangeDamage2Procedure;
import net.mcreator.jujutsucraft.procedures.DamageFixProcedure;
import net.mcreator.jujutsucraft.procedures.EffectConfirmProcedure;
import net.mcreator.jujutsucraft.procedures.EffectProcedure;
import net.mcreator.jujutsucraft.procedures.GetEntityFromUUIDProcedure;
import net.mcreator.jujutsucraft.procedures.LogicAttackProcedure;
import net.mcreator.jujutsucraft.procedures.LogicBetrayalProcedure;
import net.mcreator.jujutsucraft.procedures.LogicGuardSuccessProcedure;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

public class RangeAttackProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        LivingEntity _entity;
        if (entity == null) {
            return;
        }
        Entity entity_a = null;
        boolean logicDamageEffect = false;
        boolean guard = false;
        boolean betrayal = false;
        boolean BlackFlash = false;
        boolean highPower = false;
        boolean logic_knockback = false;
        boolean blackflashable = false;
        boolean logic_swing = false;
        double range = 0.0;
        double x_pos = 0.0;
        double knockback = 0.0;
        double x_knockback = 0.0;
        double num1 = 0.0;
        double z_knockback = 0.0;
        double y_pos = 0.0;
        double damage_source_player = 0.0;
        double num2 = 0.0;
        double old_health = 0.0;
        double z_pos = 0.0;
        double damage_sorce = 0.0;
        double y_knockback = 0.0;
        double distance = 0.0;
        double H_original = 0.0;
        double M_original = 0.0;
        double Y_limit = 0.0;
        if (world.m_5776_()) {
            return;
        }
        JujutsucraftModVariables.PlayerVariables pVars = null;
        if (entity instanceof Player) {
            pVars = (JujutsucraftModVariables.PlayerVariables)entity.getCapability(JujutsucraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(null);
        }
        blackflashable = entity.getPersistentData().m_128459_("Damage") >= 9.0;
        boolean bl = highPower = entity.getPersistentData().m_128459_("Damage") >= 24.0;
        if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).m_9236_().m_5776_()) {
            _entity.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.ATTACKING.get(), 5, highPower ? 1 : 0, false, false));
        }
        DamageFixProcedure.execute((Entity)entity);
        damage_source_player = entity.getPersistentData().m_128459_("Damage");
        if (entity.getPersistentData().m_128459_("KnockbackFix") != 0.0 && entity.getPersistentData().m_128471_("attack") && entity.getPersistentData().m_128459_("knockback") >= 1.0) {
            entity.getPersistentData().m_128347_("knockback", entity.getPersistentData().m_128459_("knockback") * (1.0 + Math.min(Math.max(entity.getPersistentData().m_128459_("cnt6") * 0.2, entity.getPersistentData().m_128459_("cnt5") * 0.005), 1.0) * entity.getPersistentData().m_128459_("KnockbackFix")));
        }
        BlackFlash = false;
        if (blackflashable && entity.getPersistentData().m_128471_("attack") && (entity instanceof Player ? pVars.PlayerCursePowerFormer > 150.0 : entity.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("forge:black_flash_able")))) && entity.getPersistentData().m_128459_("skill") != 2105.0 && entity.getPersistentData().m_128459_("cnt6") >= 0.0) {
            float f;
            float f2;
            LivingEntity _livEnt;
            ServerPlayer _plr23;
            LivingEntity _livEnt20;
            LivingEntity _livEnt2;
            LivingEntity _livEnt18;
            num1 = 1.0;
            if (entity instanceof LivingEntity && (_livEnt18 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.ZONE.get())) {
                num1 = num1 + 2.0 + (double)(entity instanceof LivingEntity && (_livEnt2 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.ZONE.get()) ? _livEnt2.m_21124_((MobEffect)JujutsucraftModMobEffects.ZONE.get()).m_19564_() : 0);
            }
            if (entity instanceof LivingEntity && (_livEnt20 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.DEEP_CONCENTRATION.get())) {
                num1 = num1 + 75.0 + (double)(5 * ((entity instanceof LivingEntity && (_livEnt2 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.DEEP_CONCENTRATION.get()) ? _livEnt2.m_21124_((MobEffect)JujutsucraftModMobEffects.DEEP_CONCENTRATION.get()).m_19564_() : 0) + 1));
            }
            if (entity instanceof Player ? entity instanceof ServerPlayer && (_plr23 = (ServerPlayer)entity).m_9236_() instanceof ServerLevel && _plr23.m_8960_().m_135996_(_plr23.f_8924_.m_129889_().m_136041_(new ResourceLocation("jujutsucraft:black_flash"))).m_8193_() : entity.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("forge:black_flash_able")))) {
                num1 += 1.0;
            }
            if ((entity instanceof LivingEntity && (_livEnt = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.SPECIAL.get()) ? _livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.SPECIAL.get()).m_19564_() : 0) > 0) {
                num1 += 5.0;
            }
            if (entity instanceof Player ? pVars.PlayerCurseTechnique == 21.0 || pVars.PlayerCurseTechnique2 == 21.0 : entity instanceof ItadoriYujiEntity || entity instanceof ItadoriYujiShibuyaEntity || entity instanceof ItadoriYujiShinjukuEntity) {
                num1 += 3.0;
            }
            if (entity instanceof LivingEntity) {
                _livEnt2 = (LivingEntity)entity;
                f2 = _livEnt2.m_21223_();
            } else {
                f2 = -1.0f;
            }
            float f3 = Math.max(f2, 1.0f);
            if (entity instanceof LivingEntity) {
                _livEnt = (LivingEntity)entity;
                f = _livEnt.m_21233_();
            } else {
                f = -1.0f;
            }
            num1 += (double)((1.0f - f3 / Math.max(f, 1.0f)) * 2.0f);
            num1 += entity.getPersistentData().m_128459_("cnt6") * 2.0;
            for (int index0 = 0; index0 < (int)Math.round(num1); ++index0) {
                if (!(Math.random() > 0.998)) continue;
                BlackFlash = true;
                break;
            }
        }
        if (!entity.getPersistentData().m_128461_("OWNER_UUID").isEmpty()) {
            entity_a = GetEntityFromUUIDProcedure.execute((LevelAccessor)world, (String)entity.getPersistentData().m_128461_("OWNER_UUID"));
        }
        if (!(entity_a instanceof LivingEntity)) {
            entity_a = entity;
        }
        x_pos = x;
        y_pos = y;
        z_pos = z;
        range = entity.getPersistentData().m_128459_("Range");
        Y_limit = 1.5;
        for (int index1 = 0; index1 < 2; ++index1) {
            LivingEntity _livEnt;
            LivingEntity _livEnt124;
            Vec3 _center = new Vec3(x_pos, y_pos, z_pos);
            List _entfound = world.m_6443_(Entity.class, new AABB(_center, _center).m_82400_(range / 2.0), e -> true);
            for (Entity entityiterator : _entfound) {
                LivingEntity _livingEntity114;
                double d;
                ServerLevel _level;
                LivingEntity _livEnt3;
                guard = false;
                logic_knockback = false;
                betrayal = LogicBetrayalProcedure.execute((LevelAccessor)world, (Entity)entity, (Entity)entityiterator);
                knockback = entity.getPersistentData().m_128459_("knockback");
                if (entity == entityiterator && !betrayal) continue;
                if (!(entityiterator instanceof LivingEntity) && entity.getPersistentData().m_128471_("ExtinctionBlock")) {
                    Entity _ent = entityiterator;
                    if (_ent.m_9236_().m_5776_() || _ent.m_20194_() == null) continue;
                    _ent.m_20194_().m_129892_().m_230957_(new CommandSourceStack(CommandSource.f_80164_, _ent.m_20182_(), _ent.m_20155_(), _ent.m_9236_() instanceof ServerLevel ? (ServerLevel)_ent.m_9236_() : null, 4, _ent.m_7755_().getString(), _ent.m_5446_(), _ent.m_9236_().m_7654_(), _ent), "kill @s");
                    continue;
                }
                if (LogicAttackProcedure.execute(world, entity, entityiterator) || betrayal) {
                    EffectConfirmProcedure.execute((LevelAccessor)world, (double)x, (double)y, (double)z, (Entity)entity, (Entity)entityiterator);
                    damage_sorce = damage_source_player;
                    if (BlackFlash && !entityiterator.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("forge:ranged_ammo")))) {
                        float f;
                        double d2;
                        if (entityiterator instanceof LivingEntity) {
                            _livEnt3 = (LivingEntity)entityiterator;
                            d2 = _livEnt3.m_21223_();
                        } else {
                            d2 = -1.0;
                        }
                        old_health = d2;
                        entityiterator.m_6469_(new DamageSource((Holder)world.m_9598_().m_175515_(Registries.f_268580_).m_246971_(ResourceKey.m_135785_((ResourceKey)Registries.f_268580_, (ResourceLocation)new ResourceLocation("jujutsucraft:damage_combat")))), 0.1f);
                        if (entityiterator instanceof LivingEntity) {
                            _livEnt3 = (LivingEntity)entityiterator;
                            f = _livEnt3.m_21223_();
                        } else {
                            f = -1.0f;
                        }
                        if ((double)f < old_health) {
                            damage_sorce *= 4.0;
                            highPower = true;
                        } else {
                            BlackFlash = false;
                        }
                    }
                    num1 = damage_sorce;
                    num2 = damage_sorce;
                    if (LogicGuardSuccessProcedure.execute((Entity)entityiterator, (Entity)entity)) {
                        LivingEntity _livEnt49;
                        guard = true;
                        num1 = Math.max(num1 - entityiterator.getPersistentData().m_128459_("Damage"), 0.0);
                        if (entityiterator instanceof LivingEntity && (_livEnt49 = (LivingEntity)entityiterator).m_21023_((MobEffect)JujutsucraftModMobEffects.GUARD.get()) || entityiterator.getPersistentData().m_128471_("guard") || entityiterator.getPersistentData().m_128471_("attack")) {
                            knockback *= num2 == 0.0 ? 1.0 : Math.max(num1 / num2, 0.1);
                        }
                    }
                    damage_sorce *= ChangeDamage1Procedure.execute((LevelAccessor)world, (Entity)entity, (Entity)entityiterator);
                    if ((damage_sorce *= ChangeDamage2Procedure.execute()) > 0.0) {
                        float f;
                        double d3;
                        if (entityiterator instanceof LivingEntity) {
                            _livEnt3 = (LivingEntity)entityiterator;
                            d3 = _livEnt3.m_21223_();
                        } else {
                            d3 = -1.0;
                        }
                        old_health = d3;
                        entityiterator.m_6469_(new DamageSource((Holder)world.m_9598_().m_175515_(Registries.f_268580_).m_246971_(ResourceKey.m_135785_((ResourceKey)Registries.f_268580_, (ResourceLocation)new ResourceLocation("jujutsucraft:damage_curse"))), entity), (float)damage_sorce);
                        if (entityiterator instanceof LivingEntity) {
                            _livEnt3 = (LivingEntity)entityiterator;
                            f = _livEnt3.m_21223_();
                        } else {
                            f = -1.0f;
                        }
                        if ((double)f != old_health) {
                            logic_knockback = true;
                            EffectProcedure.execute((LevelAccessor)world, (double)x, (double)y, (double)z, (Entity)entity, (Entity)entityiterator);
                            if (BlackFlash) {
                                LivingEntity _livEnt68;
                                LivingEntity _entity2;
                                LivingEntity _livEnt4;
                                LivingEntity _livEnt63;
                                LivingEntity _entity3;
                                BlackFlash = false;
                                if (entity instanceof Player && world.m_6106_().m_5470_().m_46207_(JujutsucraftModGameRules.JUJUTSU_GAIN_FAME)) {
                                    pVars.PlayerFame += 200.0;
                                    pVars.PlayerExperience += 200.0;
                                    pVars.PlayerTechniqueUsedNumber += 2500.0;
                                    pVars.syncPlayerVariables(entity);
                                }
                                if (world instanceof ServerLevel) {
                                    _level = (ServerLevel)world;
                                    Entity entityToSpawn = ((EntityType)JujutsucraftModEntities.ENTITY_BLACK_FLASH.get()).m_262496_(_level, BlockPos.m_274561_((double)entityiterator.m_20185_(), (double)entityiterator.m_20186_(), (double)entityiterator.m_20189_()), MobSpawnType.MOB_SUMMONED);
                                    if (entityToSpawn != null) {
                                        entityToSpawn.m_146922_(world.m_213780_().m_188501_() * 360.0f);
                                    }
                                }
                                if (entity instanceof LivingEntity && !(_entity3 = (LivingEntity)entity).m_9236_().m_5776_()) {
                                    _entity3.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.ZONE.get(), 6000, 0, true, true));
                                }
                                if (entity instanceof LivingEntity && (_livEnt63 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.FATIGUE.get())) {
                                    num1 = Math.max(entity instanceof LivingEntity && (_livEnt4 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.FATIGUE.get()) ? _livEnt4.m_21124_((MobEffect)JujutsucraftModMobEffects.FATIGUE.get()).m_19564_() : 0, 0);
                                    num2 = Math.max((entity instanceof LivingEntity && (_livEnt4 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.FATIGUE.get()) ? _livEnt4.m_21124_((MobEffect)JujutsucraftModMobEffects.FATIGUE.get()).m_19557_() : 0) - 2000, 0);
                                    if (entity instanceof LivingEntity) {
                                        _entity2 = (LivingEntity)entity;
                                        _entity2.m_21195_((MobEffect)JujutsucraftModMobEffects.FATIGUE.get());
                                    }
                                    if (entity instanceof LivingEntity && !(_entity2 = (LivingEntity)entity).m_9236_().m_5776_()) {
                                        _entity2.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.FATIGUE.get(), (int)num2, (int)num1, false, false));
                                    }
                                }
                                if (entity instanceof LivingEntity && (_livEnt68 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.BRAIN_DAMAGE.get())) {
                                    num1 = Math.max(entity instanceof LivingEntity && (_livEnt4 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.BRAIN_DAMAGE.get()) ? _livEnt4.m_21124_((MobEffect)JujutsucraftModMobEffects.BRAIN_DAMAGE.get()).m_19564_() : 0, 0);
                                    num2 = Math.max((entity instanceof LivingEntity && (_livEnt4 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.BRAIN_DAMAGE.get()) ? _livEnt4.m_21124_((MobEffect)JujutsucraftModMobEffects.BRAIN_DAMAGE.get()).m_19557_() : 0) - 2000, 0);
                                    if (entity instanceof LivingEntity) {
                                        _entity2 = (LivingEntity)entity;
                                        _entity2.m_21195_((MobEffect)JujutsucraftModMobEffects.BRAIN_DAMAGE.get());
                                    }
                                    if (entity instanceof LivingEntity && !(_entity2 = (LivingEntity)entity).m_9236_().m_5776_()) {
                                        _entity2.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.BRAIN_DAMAGE.get(), (int)num2, (int)num1, false, false));
                                    }
                                }
                                if (entity instanceof ServerPlayer) {
                                    ServerPlayer _player = (ServerPlayer)entity;
                                    Advancement _adv = _player.f_8924_.m_129889_().m_136041_(new ResourceLocation("jujutsucraft:black_flash"));
                                    AdvancementProgress _ap = _player.m_8960_().m_135996_(_adv);
                                    if (!_ap.m_8193_()) {
                                        for (String criteria : _ap.m_8219_()) {
                                            _player.m_8960_().m_135988_(_adv, criteria);
                                        }
                                    }
                                }
                            }
                            logic_swing = true;
                        }
                    }
                    if (entity.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("forge:ranged_ammo"))) && entity.m_6084_() && entityiterator.m_6095_().m_204039_(TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("forge:ranged_ammo"))) && entityiterator.m_6084_()) {
                        entity.getPersistentData().m_128379_("Stop", true);
                    }
                }
                if (entityiterator instanceof Projectile) {
                    Projectile _projEnt = (Projectile)entityiterator;
                    d = _projEnt.m_20184_().m_82553_();
                } else {
                    d = 0.0;
                }
                if (d > 0.0 && entity.getPersistentData().m_128459_("projectile_type") != 0.0 && entityiterator.getPersistentData().m_128459_("Damage") < entity.getPersistentData().m_128459_("Damage")) {
                    logic_swing = true;
                    logic_knockback = true;
                }
                if (entity == entityiterator || !logic_knockback || !(knockback > 0.0)) continue;
                x_knockback = entityiterator.m_20185_() - entity.m_20185_();
                y_knockback = entityiterator.m_20186_() - entity.m_20186_();
                z_knockback = entityiterator.m_20189_() - entity.m_20189_();
                if (entityiterator.m_20096_()) {
                    y_knockback = Math.abs(y_knockback);
                }
                if (x_knockback == 0.0 && z_knockback == 0.0) {
                    x_knockback = Mth.m_216263_((RandomSource)RandomSource.m_216327_(), (double)(y_knockback * -1.0), (double)y_knockback);
                    z_knockback = Mth.m_216263_((RandomSource)RandomSource.m_216327_(), (double)(y_knockback * -1.0), (double)y_knockback);
                }
                if ((distance = Math.sqrt(x_knockback * x_knockback + y_knockback * y_knockback + z_knockback * z_knockback)) == 0.0) continue;
                x_knockback = x_knockback / distance * (knockback *= 3.0);
                y_knockback = y_knockback / distance * knockback;
                z_knockback = z_knockback / distance * knockback;
                if (y_knockback > Y_limit) {
                    M_original = Math.sqrt(x_knockback * x_knockback + y_knockback * y_knockback + z_knockback * z_knockback);
                    H_original = Math.sqrt(x_knockback * x_knockback + z_knockback * z_knockback);
                    knockback = Math.sqrt(M_original * M_original - Y_limit * Y_limit);
                    x_knockback *= (knockback /= H_original != 0.0 ? H_original : 1.0);
                    y_knockback = Y_limit;
                    z_knockback *= knockback;
                }
                if (JujutsucraftModVariables.MapVariables.get((LevelAccessor)world).BlastGame) {
                    float f;
                    float f4;
                    if (entityiterator instanceof LivingEntity) {
                        LivingEntity _livEnt5 = (LivingEntity)entityiterator;
                        f4 = _livEnt5.m_21223_();
                    } else {
                        f4 = -1.0f;
                    }
                    float f5 = Math.max(f4, 1.0f);
                    if (entityiterator instanceof LivingEntity) {
                        _livEnt3 = (LivingEntity)entityiterator;
                        f = _livEnt3.m_21233_();
                    } else {
                        f = -1.0f;
                    }
                    knockback = 4.0f * (1.0f - f5 / Math.max(f, 1.0f));
                    x_knockback *= knockback;
                    y_knockback *= knockback;
                    z_knockback *= knockback;
                    if ((entityiterator instanceof LivingEntity && (_livEnt3 = (LivingEntity)entityiterator).m_21023_((MobEffect)JujutsucraftModMobEffects.GUARD.get()) ? _livEnt3.m_21124_((MobEffect)JujutsucraftModMobEffects.GUARD.get()).m_19564_() : 0) < 1 && x_knockback * x_knockback + y_knockback * y_knockback + z_knockback * z_knockback > 100.0) {
                        if (world instanceof ServerLevel) {
                            _level = (ServerLevel)world;
                            _level.m_8767_((ParticleOptions)((SimpleParticleType)JujutsucraftModParticleTypes.PARTICLE_BLACK_FLASH_1.get()), entityiterator.m_20185_(), entityiterator.m_20186_() + (double)entityiterator.m_20206_() * 0.5, entityiterator.m_20189_(), 50, 2.0, 2.0, 2.0, 2.0);
                        }
                        if (world instanceof ServerLevel) {
                            _level = (ServerLevel)world;
                            _level.m_8767_((ParticleOptions)((SimpleParticleType)JujutsucraftModParticleTypes.PARTICLE_BROKEN_GLASS_SMALL.get()), entityiterator.m_20185_(), entityiterator.m_20186_() + (double)entityiterator.m_20206_() * 0.5, entityiterator.m_20189_(), 50, 2.0, 2.0, 2.0, 2.0);
                        }
                        if (world instanceof Level) {
                            _level = (Level)world;
                            if (!_level.m_5776_()) {
                                _level.m_5594_(null, BlockPos.m_274561_((double)entityiterator.m_20185_(), (double)(entityiterator.m_20186_() + (double)entityiterator.m_20206_() * 0.5), (double)entityiterator.m_20189_()), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("jujutsucraft:critical")), SoundSource.NEUTRAL, 4.0f, 1.0f);
                            } else {
                                _level.m_7785_(entityiterator.m_20185_(), entityiterator.m_20186_() + (double)entityiterator.m_20206_() * 0.5, entityiterator.m_20189_(), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("jujutsucraft:critical")), SoundSource.NEUTRAL, 4.0f, 1.0f, false);
                            }
                        }
                    }
                }
                x_knockback += entity.getPersistentData().m_128459_("x_knockback");
                y_knockback += entity.getPersistentData().m_128459_("y_knockback");
                z_knockback += entity.getPersistentData().m_128459_("z_knockback");
                if (entity instanceof LivingEntity && (_livingEntity114 = (LivingEntity)entity).m_21204_().m_22171_(Attributes.f_22278_)) {
                    LivingEntity _livingEntity115;
                    knockback = Math.sqrt(Math.max(entityiterator instanceof LivingEntity && (_livingEntity115 = (LivingEntity)entityiterator).m_21204_().m_22171_(Attributes.f_22278_) ? _livingEntity115.m_21051_(Attributes.f_22278_).m_22115_() : 0.0, 0.0) + 1.0);
                    x_knockback /= knockback;
                    y_knockback /= knockback;
                    z_knockback /= knockback;
                }
                entityiterator.m_20256_(new Vec3(x_knockback, y_knockback, z_knockback));
                entityiterator.getPersistentData().m_128347_("old_x_position", entityiterator.getPersistentData().m_128459_("old_x_position") - x_knockback);
                entityiterator.getPersistentData().m_128347_("old_y_position", entityiterator.getPersistentData().m_128459_("old_y_position") - y_knockback);
                entityiterator.getPersistentData().m_128347_("old_z_position", entityiterator.getPersistentData().m_128459_("old_z_position") - z_knockback);
            }
            if (!(entity instanceof LivingEntity) || !(_livEnt124 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()) || entity.getPersistentData().m_128471_("Failed") || entity.getPersistentData().m_128471_("attack") || entity.getPersistentData().m_128471_("DomainAttack")) break;
            x_pos = entity.getPersistentData().m_128459_("x_pos_doma");
            y_pos = entity.getPersistentData().m_128459_("y_pos_doma");
            z_pos = entity.getPersistentData().m_128459_("z_pos_doma");
            range = JujutsucraftModVariables.MapVariables.get((LevelAccessor)world).DomainExpansionRadius * (double)((entity instanceof LivingEntity && (_livEnt = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()) ? _livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()).m_19564_() : 0) > 0 ? 6 : 2);
            entity.getPersistentData().m_128379_("DomainAttack", true);
        }
        entity.getPersistentData().m_128347_("effect", 0.0);
        entity.getPersistentData().m_128347_("effectConfirm", 0.0);
        entity.getPersistentData().m_128347_("projectile_type", 0.0);
        entity.getPersistentData().m_128379_("attack", false);
        entity.getPersistentData().m_128379_("ignore", false);
        entity.getPersistentData().m_128379_("DomainAttack", false);
    }
}
