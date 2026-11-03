/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.protocol.game.ServerboundClientInformationPacket
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.stats.Stat
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.player.Player
 *  net.minecraftforge.common.util.FakePlayer$FakePlayerNetHandler
 *  net.minecraftforge.server.ServerLifecycleHooks
 *  org.jetbrains.annotations.Nullable
 */
package net.minecraftforge.common.util;

import com.mojang.authlib.GameProfile;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerboundClientInformationPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stat;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.jetbrains.annotations.Nullable;

public class FakePlayer
extends ServerPlayer {
    public FakePlayer(ServerLevel level, GameProfile name) {
        super(level.getServer(), level, name);
        this.connection = new FakePlayerNetHandler(level.getServer(), (ServerPlayer)this);
    }

    public void displayClientMessage(Component chatComponent, boolean actionBar) {
    }

    public void awardStat(Stat stat, int amount) {
    }

    public boolean isInvulnerableTo(DamageSource source) {
        return true;
    }

    public boolean canHarmPlayer(Player player) {
        return false;
    }

    public void die(DamageSource source) {
    }

    public void tick() {
    }

    public void updateOptions(ServerboundClientInformationPacket packet) {
    }

    @Nullable
    public MinecraftServer getServer() {
        return ServerLifecycleHooks.getCurrentServer();
    }
}
