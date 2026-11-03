/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.mcreator.jujutsucraft.entity.FushiguroMegumiEntity
 *  net.mcreator.jujutsucraft.entity.FushiguroMegumiShibuyaEntity
 *  net.mcreator.jujutsucraft.entity.KenjakuEntity
 *  net.mcreator.jujutsucraft.init.JujutsucraftModMobEffects
 *  net.mcreator.jujutsucraft.init.JujutsucraftModParticleTypes
 *  net.mcreator.jujutsucraft.network.JujutsucraftModVariables
 *  net.mcreator.jujutsucraft.network.JujutsucraftModVariables$MapVariables
 *  net.mcreator.jujutsucraft.network.JujutsucraftModVariables$PlayerVariables
 *  net.mcreator.jujutsucraft.procedures.ChangeCurseEnergyProcedure
 *  net.mcreator.jujutsucraft.procedures.DomainExpansionBattleProcedure
 *  net.mcreator.jujutsucraft.procedures.KeyChangeTechniqueOnKeyPressedProcedure
 *  net.mcreator.jujutsucraft.procedures.RotateEntityProcedure
 *  net.minecraft.advancements.Advancement
 *  net.minecraft.advancements.AdvancementProgress
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.SimpleParticleType
 *  net.minecraft.network.chat.Component
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.common.capabilities.Capability
 *  net.minecraftforge.registries.ForgeRegistries
 */
package net.mcreator.jujutsucraft.procedures;

import java.util.List;
import net.mcreator.jujutsucraft.entity.FushiguroMegumiEntity;
import net.mcreator.jujutsucraft.entity.FushiguroMegumiShibuyaEntity;
import net.mcreator.jujutsucraft.entity.KenjakuEntity;
import net.mcreator.jujutsucraft.init.JujutsucraftModMobEffects;
import net.mcreator.jujutsucraft.init.JujutsucraftModParticleTypes;
import net.mcreator.jujutsucraft.network.JujutsucraftModVariables;
import net.mcreator.jujutsucraft.procedures.ChangeCurseEnergyProcedure;
import net.mcreator.jujutsucraft.procedures.DomainExpansionBattleProcedure;
import net.mcreator.jujutsucraft.procedures.KeyChangeTechniqueOnKeyPressedProcedure;
import net.mcreator.jujutsucraft.procedures.RotateEntityProcedure;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.registries.ForgeRegistries;

public class DomainExpansionCreateBarrierProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null) {
            return;
        }
        Object STR1 = "";
        LivingEntity target_entity = null;
        double x_dis = 0.0;
        double distance = 0.0;
        double z_dis = 0.0;
        double x_pos = 0.0;
        double domainRadius = 0.0;
        double z_pos = 0.0;
        double yaw = 0.0;
        double dis = 0.0;
        double loop_num = 0.0;
        double power = 0.0;
        double pitch = 0.0;
        double y_pos = 0.0;
        double y_dis = 0.0;
        boolean old_failed = false;
        boolean reStart = false;
        boolean update_pos = false;
        if (entity instanceof Mob) {
            Mob _mobEnt = (Mob)entity;
            v0 = _mobEnt.m_5448_();
        } else {
            v0 = target_entity = null;
        }
        if (entity.getPersistentData().m_128459_("cnt3") < 20.0) {
            LivingEntity _livEnt;
            LivingEntity _livEnt6;
            entity.getPersistentData().m_128347_("select", 0.0);
            if (entity.getPersistentData().m_128459_("cnt3") == 0.0) {
                ChangeCurseEnergyProcedure.execute((Entity)entity, (double)entity.getPersistentData().m_128459_("used_technique_cost"));
            }
            entity.getPersistentData().m_128347_("cnt3", entity.getPersistentData().m_128459_("cnt3") + (double)(entity instanceof LivingEntity && (_livEnt6 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.ZONE.get()) || (entity instanceof LivingEntity && (_livEnt = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.NEUTRALIZATION.get()) ? _livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.NEUTRALIZATION.get()).m_19564_() : 0) > 0 ? 2 : 1));
            if (entity instanceof Player) {
                if (entity.getPersistentData().m_128471_("PRESS_Z")) {
                    Player _player;
                    Player _player2;
                    power = Math.round((20.0 - entity.getPersistentData().m_128459_("cnt3")) * 0.25);
                    STR1 = Component.m_237115_((String)"jujutsu.message.long_press").getString();
                    for (int index0 = 0; index0 < (int)power; ++index0) {
                        STR1 = "\u25a0" + (String)STR1 + "\u25a0";
                    }
                    if (entity instanceof Player && !(_player2 = (Player)entity).m_9236_().m_5776_()) {
                        _player2.m_5661_((Component)Component.m_237113_((String)STR1), true);
                    }
                    if (entity.getPersistentData().m_128459_("cnt3") >= 20.0 && entity instanceof Player && !(_player = (Player)entity).m_9236_().m_5776_()) {
                        _player.m_5661_((Component)Component.m_237113_((String)""), true);
                    }
                } else {
                    if (entity instanceof Player && !(_player = (Player)entity).m_9236_().m_5776_()) {
                        _player.m_5661_((Component)Component.m_237113_((String)""), true);
                    }
                    entity.getPersistentData().m_128347_("select", 0.0);
                    entity.getPersistentData().m_128347_("skill", 0.0);
                }
            } else if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).m_9236_().m_5776_()) {
                _entity.m_7292_(new MobEffectInstance(MobEffects.f_19597_, 5, 9, false, false));
            }
        } else {
            LivingEntity _livEnt160;
            domainRadius = JujutsucraftModVariables.MapVariables.get((LevelAccessor)world).DomainExpansionRadius;
            entity.getPersistentData().m_128347_("cnt1", entity.getPersistentData().m_128459_("cnt1") + 1.0);
            entity.m_20256_(new Vec3(0.0, Math.min(entity.m_20184_().m_7098_(), 0.0), 0.0));
            if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).m_9236_().m_5776_()) {
                _entity.m_7292_(new MobEffectInstance(MobEffects.f_19597_, 5, 9, false, false));
            }
            if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).m_9236_().m_5776_()) {
                _entity.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.ATTACKING.get(), 5, 9, false, false));
            }
            if (entity.getPersistentData().m_128459_("cnt7") == 0.0) {
                LivingEntity _livEnt60;
                LivingEntity _livEnt57;
                ServerPlayer _plr56;
                Entity _ent;
                LivingEntity _livEnt36;
                entity.getPersistentData().m_128347_("cnt7", 1.0);
                ChangeCurseEnergyProcedure.execute((Entity)entity, (double)(entity.getPersistentData().m_128459_("used_technique_cost") * -1.0));
                if (entity instanceof Player && Math.random() < 0.01 && entity instanceof ServerPlayer) {
                    _player = (ServerPlayer)entity;
                    Advancement _adv = _player.f_8924_.m_129889_().m_136041_(new ResourceLocation("jujutsucraft:mastery_open_barrier_type_domain"));
                    AdvancementProgress _ap = _player.m_8960_().m_135996_(_adv);
                    if (!_ap.m_8193_()) {
                        for (String criteria : _ap.m_8219_()) {
                            _player.m_8960_().m_135988_(_adv, criteria);
                        }
                    }
                }
                if (target_entity instanceof LivingEntity) {
                    RotateEntityProcedure.execute((double)target_entity.m_20185_(), (double)(target_entity.m_20186_() + (double)target_entity.m_20206_() * 0.5), (double)target_entity.m_20189_(), (Entity)entity);
                }
                if (!(entity instanceof LivingEntity) || !(_livEnt36 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get())) {
                    LivingEntity _entity;
                    yaw = entity.m_146908_();
                    pitch = entity.m_146909_();
                    _ent = entity;
                    _ent.m_146922_(entity.m_146908_());
                    _ent.m_146926_(0.0f);
                    _ent.m_5618_(_ent.m_146908_());
                    _ent.m_5616_(_ent.m_146908_());
                    _ent.f_19859_ = _ent.m_146908_();
                    _ent.f_19860_ = _ent.m_146909_();
                    if (_ent instanceof LivingEntity) {
                        _entity = (LivingEntity)_ent;
                        _entity.f_20884_ = _entity.m_146908_();
                        _entity.f_20886_ = _entity.m_146908_();
                    }
                    entity.getPersistentData().m_128347_("x_pos_doma", (double)Math.round(entity.m_20185_() + Math.cos(Math.toRadians(entity.m_146908_() + 90.0f)) * Math.cos(Math.toRadians(0.0)) * domainRadius * 0.5));
                    entity.getPersistentData().m_128347_("y_pos_doma", (double)Math.round(target_entity instanceof LivingEntity ? Math.min(entity.m_20186_(), target_entity.m_20186_()) : entity.m_20186_()));
                    entity.getPersistentData().m_128347_("z_pos_doma", (double)Math.round(entity.m_20189_() + Math.sin(Math.toRadians(entity.m_146908_() + 90.0f)) * Math.cos(Math.toRadians(0.0)) * domainRadius * 0.5));
                    _ent = entity;
                    _ent.m_146922_((float)yaw);
                    _ent.m_146926_((float)pitch);
                    _ent.m_5618_(_ent.m_146908_());
                    _ent.m_5616_(_ent.m_146908_());
                    _ent.f_19859_ = _ent.m_146908_();
                    _ent.f_19860_ = _ent.m_146909_();
                    if (_ent instanceof LivingEntity) {
                        _entity = (LivingEntity)_ent;
                        _entity.f_20884_ = _entity.m_146908_();
                        _entity.f_20886_ = _entity.m_146908_();
                    }
                }
                if ((entity.getPersistentData().m_128459_("select") == 1.0 || entity.getPersistentData().m_128459_("select") == 18.0) && (entity instanceof Player ? (entity instanceof ServerPlayer && (_plr56 = (ServerPlayer)entity).m_9236_() instanceof ServerLevel && _plr56.m_8960_().m_135996_(_plr56.f_8924_.m_129889_().m_136041_(new ResourceLocation("jujutsucraft:mastery_open_barrier_type_domain"))).m_8193_() || entity instanceof LivingEntity && (_livEnt57 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.SUKUNA_EFFECT.get())) && !entity.m_6144_() : entity instanceof KenjakuEntity || entity instanceof LivingEntity && (_livEnt60 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.SUKUNA_EFFECT.get()))) {
                    entity.getPersistentData().m_128347_("cnt2", 1.0);
                } else if (entity instanceof FushiguroMegumiEntity || entity instanceof FushiguroMegumiShibuyaEntity) {
                    entity.getPersistentData().m_128347_("cnt2", -1.0);
                } else {
                    entity.getPersistentData().m_128347_("cnt2", 0.0);
                }
                reStart = false;
                update_pos = false;
                entity.getPersistentData().m_128379_("Failed", false);
                entity.getPersistentData().m_128379_("Cover", false);
                for (int index1 = 0; index1 < 2; ++index1) {
                    entity.getPersistentData().m_128347_("x_pos_doma2", (double)Math.round(entity.m_20185_()));
                    entity.getPersistentData().m_128347_("y_pos_doma2", (double)Math.round(entity.m_20186_()));
                    entity.getPersistentData().m_128347_("z_pos_doma2", (double)Math.round(entity.m_20189_()));
                    Vec3 _center = new Vec3(entity.m_20185_(), entity.m_20186_(), entity.m_20189_());
                    List _entfound = world.m_6443_(Entity.class, new AABB(_center, _center).m_82400_(domainRadius * 2.0 * 2.0 / 2.0), e -> true);
                    for (Entity entityiterator : _entfound) {
                        LivingEntity _livEnt124;
                        LivingEntity _livEnt;
                        LivingEntity _livEnt90;
                        LivingEntity _livEnt89;
                        x_dis = entity.m_20185_() - entityiterator.m_20185_();
                        dis = x_dis * x_dis + (y_dis = entity.m_20186_() - entityiterator.m_20186_()) * y_dis + (z_dis = entity.m_20189_() - entityiterator.m_20189_()) * z_dis;
                        if (dis < domainRadius * domainRadius) {
                            LivingEntity _entity;
                            LivingEntity _livEnt85;
                            LivingEntity _entity2;
                            if (entity != entityiterator && entityiterator instanceof LivingEntity && !(_entity2 = (LivingEntity)entityiterator).m_9236_().m_5776_()) {
                                _entity2.m_7292_(new MobEffectInstance(MobEffects.f_19597_, (int)(domainRadius * 2.0 + 1.0), 9, false, false));
                            }
                            if (!(entityiterator instanceof LivingEntity && (_livEnt85 = (LivingEntity)entityiterator).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()) || entityiterator.getPersistentData().m_128459_("select") != 0.0 || !(entityiterator instanceof LivingEntity) || (_entity = (LivingEntity)entityiterator).m_9236_().m_5776_())) {
                                _entity.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.NEUTRALIZATION.get(), (int)(domainRadius * 2.0 + 20.0), (int)(entity.getPersistentData().m_128459_("select") + 10.0), false, false));
                            }
                        }
                        if (entity instanceof LivingEntity && (_livEnt89 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.BRAIN_DAMAGE.get()) || (!(entityiterator instanceof LivingEntity) || !(_livEnt90 = (LivingEntity)entityiterator).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get())) && entityiterator.getPersistentData().m_128459_("select") == 0.0) continue;
                        x_dis = entity.m_20185_() - entityiterator.getPersistentData().m_128459_("x_pos_doma");
                        dis = x_dis * x_dis + (y_dis = entity.m_20186_() - entityiterator.getPersistentData().m_128459_("y_pos_doma")) * y_dis + (z_dis = entity.m_20189_() - entityiterator.getPersistentData().m_128459_("z_pos_doma")) * z_dis;
                        if (dis < (domainRadius - 1.0) * (domainRadius - 1.0)) {
                            LivingEntity _livEnt120;
                            LivingEntity _livEnt117;
                            if (entity != entityiterator) {
                                LivingEntity _livEnt99;
                                if ((entityiterator instanceof LivingEntity && (_livEnt99 = (LivingEntity)entityiterator).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()) ? (entityiterator instanceof LivingEntity && (_livEnt = (LivingEntity)entityiterator).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()) ? _livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()).m_19564_() : 0) > 0 : entityiterator.getPersistentData().m_128459_("select") != 0.0 && entityiterator.getPersistentData().m_128459_("cnt2") > 0.0) && !update_pos) {
                                    entityiterator.getPersistentData().m_128347_("x_pos_doma", entity.getPersistentData().m_128459_("x_pos_doma"));
                                    entityiterator.getPersistentData().m_128347_("y_pos_doma", entity.getPersistentData().m_128459_("y_pos_doma"));
                                    entityiterator.getPersistentData().m_128347_("z_pos_doma", entity.getPersistentData().m_128459_("z_pos_doma"));
                                    update_pos = true;
                                } else {
                                    entity.getPersistentData().m_128347_("x_pos_doma", entityiterator.getPersistentData().m_128459_("x_pos_doma"));
                                    entity.getPersistentData().m_128347_("y_pos_doma", entityiterator.getPersistentData().m_128459_("y_pos_doma"));
                                    entity.getPersistentData().m_128347_("z_pos_doma", entityiterator.getPersistentData().m_128459_("z_pos_doma"));
                                    update_pos = true;
                                }
                            }
                            if (entity == entityiterator) continue;
                            entity.getPersistentData().m_128379_("Failed", true);
                            if (entityiterator instanceof LivingEntity && (_livEnt117 = (LivingEntity)entityiterator).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()) && (entityiterator instanceof LivingEntity && (_livEnt = (LivingEntity)entityiterator).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()) ? _livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()).m_19564_() : 0) == 0) {
                                entity.getPersistentData().m_128347_("cnt5", 1.0);
                            }
                            if (!(entityiterator instanceof LivingEntity) || !(_livEnt120 = (LivingEntity)entityiterator).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()) || entityiterator.getPersistentData().m_128471_("Cover")) continue;
                            entityiterator.getPersistentData().m_128379_("Failed", true);
                            continue;
                        }
                        if (!(dis < domainRadius * 2.0 * domainRadius * 2.0) || reStart || entity == entityiterator || !(entityiterator instanceof LivingEntity && (_livEnt124 = (LivingEntity)entityiterator).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()) ? (entityiterator instanceof LivingEntity && (_livEnt = (LivingEntity)entityiterator).m_21023_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()) ? _livEnt.m_21124_((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get()).m_19564_() : 0) == 0 : entityiterator.getPersistentData().m_128459_("select") != 0.0 && entityiterator.getPersistentData().m_128459_("cnt2") <= 0.0)) continue;
                        reStart = true;
                        x_pos = entityiterator.getPersistentData().m_128459_("x_pos_doma");
                        y_pos = entityiterator.getPersistentData().m_128459_("y_pos_doma");
                        z_pos = entityiterator.getPersistentData().m_128459_("z_pos_doma");
                    }
                    if (!reStart || entity.getPersistentData().m_128471_("Failed") || update_pos) break;
                    _ent = entity;
                    _ent.m_6021_(x_pos, y_pos, z_pos);
                    if (_ent instanceof ServerPlayer) {
                        ServerPlayer _serverPlayer = (ServerPlayer)_ent;
                        _serverPlayer.f_8906_.m_9774_(x_pos, y_pos, z_pos, _ent.m_146908_(), _ent.m_146909_());
                    }
                    entity.getPersistentData().m_128347_("x_pos_doma", x_pos);
                    entity.getPersistentData().m_128347_("y_pos_doma", y_pos);
                    entity.getPersistentData().m_128347_("z_pos_doma", z_pos);
                    update_pos = true;
                }
                Vec3 _center = new Vec3(entity.m_20185_(), entity.m_20186_(), entity.m_20189_());
                List _entfound = world.m_6443_(Entity.class, new AABB(_center, _center).m_82400_(10.0), e -> true);
                for (Entity entityiterator : _entfound) {
                    if (entity == entityiterator || entityiterator.m_20147_()) continue;
                    x_dis = entityiterator.m_20185_() - entity.m_20185_();
                    y_dis = entityiterator.m_20186_() - entity.m_20186_();
                    z_dis = entityiterator.m_20189_() - entity.m_20189_();
                    dis = x_dis * x_dis + z_dis * z_dis;
                    if (!(dis > 0.0) || !(dis < 100.0)) continue;
                    x_dis /= dis;
                    y_dis /= dis;
                    z_dis /= dis;
                    x_pos = entityiterator.m_20185_();
                    y_pos = entityiterator.m_20186_();
                    z_pos = entityiterator.m_20189_();
                    loop_num = Math.ceil(10.0 - dis);
                    for (int index2 = 0; index2 < (int)Math.round(loop_num) && !((dis = (x_pos - entity.getPersistentData().m_128459_("x_pos_doma")) * (x_pos - entity.getPersistentData().m_128459_("x_pos_doma")) + (y_pos - entity.getPersistentData().m_128459_("y_pos_doma")) * (y_pos - entity.getPersistentData().m_128459_("y_pos_doma")) + (z_pos - entity.getPersistentData().m_128459_("z_pos_doma")) * (z_pos - entity.getPersistentData().m_128459_("z_pos_doma"))) > (JujutsucraftModVariables.MapVariables.get((LevelAccessor)world).DomainExpansionRadius - 3.0) * (JujutsucraftModVariables.MapVariables.get((LevelAccessor)world).DomainExpansionRadius - 3.0)); ++index2) {
                        if (!world.m_8055_(BlockPos.m_274561_((double)(x_pos += x_dis), (double)y_pos, (double)(z_pos += z_dis))).m_60815_()) continue;
                        y_pos += 1.0;
                    }
                    Entity _ent2 = entityiterator;
                    _ent2.m_6021_(x_pos, y_pos, z_pos);
                    if (!(_ent2 instanceof ServerPlayer)) continue;
                    ServerPlayer _serverPlayer = (ServerPlayer)_ent2;
                    _serverPlayer.f_8906_.m_9774_(x_pos, y_pos, z_pos, _ent2.m_146908_(), _ent2.m_146909_());
                }
            }
            if (!(entity instanceof LivingEntity) || !(_livEnt160 = (LivingEntity)entity).m_21023_((MobEffect)JujutsucraftModMobEffects.BRAIN_DAMAGE.get())) {
                if (entity.getPersistentData().m_128471_("Failed") && entity.getPersistentData().m_128459_("cnt5") > 0.0) {
                    if (entity.getPersistentData().m_128459_("cnt1") > 1.0) {
                        entity.getPersistentData().m_128347_("cnt1", entity.getPersistentData().m_128459_("cnt1") < 34.0 ? 34.0 : Math.max(50.0, domainRadius * 2.0) + 5.0);
                    }
                } else {
                    old_failed = entity.getPersistentData().m_128471_("Failed");
                    entity.getPersistentData().m_128379_("Failed", false);
                    x_pos = entity.getPersistentData().m_128459_("x_pos_doma");
                    y_pos = entity.getPersistentData().m_128459_("y_pos_doma");
                    z_pos = entity.getPersistentData().m_128459_("z_pos_doma");
                    entity.getPersistentData().m_128347_("cnt_cover", entity.getPersistentData().m_128459_("cnt1"));
                    DomainExpansionBattleProcedure.execute((LevelAccessor)world, (double)x_pos, (double)y_pos, (double)z_pos, (Entity)entity);
                    entity.getPersistentData().m_128379_("Failed", old_failed);
                }
                if (entity.getPersistentData().m_128459_("cnt1") >= Math.max(34.0, domainRadius * 2.0 + 1.0)) {
                    LivingEntity _entity;
                    double _setval = ((JujutsucraftModVariables.PlayerVariables)entity.getCapability((Capability)JujutsucraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse((Object)new JujutsucraftModVariables.PlayerVariables())).PlayerCursePowerChange + 200.0;
                    entity.getCapability(JujutsucraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                        capability.PlayerCursePowerChange = _setval;
                        capability.syncPlayerVariables(entity);
                    });
                    entity.getPersistentData().m_128379_("StartDomainAttack", true);
                    if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).m_9236_().m_5776_()) {
                        _entity.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.DOMAIN_EXPANSION.get(), entity.getPersistentData().m_128459_("select") == 29.0 ? 3600 : 1200, (int)entity.getPersistentData().m_128459_("cnt2"), true, false));
                    }
                    entity.getPersistentData().m_128347_("skill", 0.0);
                    boolean _setval2 = true;
                    entity.getCapability(JujutsucraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                        capability.noChangeTechnique = _setval2;
                        capability.syncPlayerVariables(entity);
                    });
                    KeyChangeTechniqueOnKeyPressedProcedure.execute((LevelAccessor)world, (double)x, (double)y, (double)z, (Entity)entity);
                }
            } else if (entity.getPersistentData().m_128459_("cnt1") >= 5.0) {
                ServerLevel _level;
                yaw = Math.toRadians(entity.m_146908_() + 90.0f);
                pitch = Math.toRadians(entity.m_146909_());
                distance = 0.5 * (double)entity.m_20205_();
                x_pos = entity.m_20185_() + Math.cos(yaw) * Math.cos(pitch) * distance;
                y_pos = entity.m_20186_() + (double)entity.m_20206_() * 0.9 + Math.sin(pitch) * -1.0 * distance;
                z_pos = entity.m_20189_() + Math.sin(yaw) * Math.cos(pitch) * distance;
                if (world instanceof ServerLevel) {
                    _level = (ServerLevel)world;
                    _level.m_8767_((ParticleOptions)((SimpleParticleType)JujutsucraftModParticleTypes.PARTICLE_BLOOD_RED.get()), x_pos, y_pos, z_pos, (int)(2.0 + distance), 0.1 * distance, 0.1 * distance, 0.1 * distance, 0.1);
                }
                if (entity.getPersistentData().m_128459_("cnt1") >= 20.0) {
                    LivingEntity _entity;
                    if (world instanceof Level) {
                        _level = (Level)world;
                        if (!_level.m_5776_()) {
                            _level.m_5594_(null, BlockPos.m_274561_((double)x, (double)y, (double)z), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("jujutsucraft:crush")), SoundSource.NEUTRAL, (float)(0.5 * (1.0 + distance)), 1.1f);
                        } else {
                            _level.m_7785_(x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("jujutsucraft:crush")), SoundSource.NEUTRAL, (float)(0.5 * (1.0 + distance)), 1.1f, false);
                        }
                    }
                    if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).m_9236_().m_5776_()) {
                        _entity.m_7292_(new MobEffectInstance((MobEffect)JujutsucraftModMobEffects.BRAIN_DAMAGE.get(), 6000, 2, false, false));
                    }
                    entity.getPersistentData().m_128347_("select", 0.0);
                    entity.getPersistentData().m_128347_("skill", 0.0);
                }
            }
        }
    }
}
