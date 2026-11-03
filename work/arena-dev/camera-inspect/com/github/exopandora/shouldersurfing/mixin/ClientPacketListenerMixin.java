/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.exopandora.shouldersurfing.api.client.IShoulderSurfing
 *  com.github.exopandora.shouldersurfing.api.client.IShoulderSurfingCamera
 *  com.github.exopandora.shouldersurfing.config.Config
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.ClientPacketListener
 *  net.minecraft.client.player.LocalPlayer
 *  net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket
 *  net.minecraft.network.protocol.game.ClientboundRespawnPacket
 *  net.minecraft.world.entity.RelativeMovement
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.github.exopandora.shouldersurfing.mixin;

import com.github.exopandora.shouldersurfing.api.client.IShoulderSurfing;
import com.github.exopandora.shouldersurfing.api.client.IShoulderSurfingCamera;
import com.github.exopandora.shouldersurfing.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.network.protocol.game.ClientboundRespawnPacket;
import net.minecraft.world.entity.RelativeMovement;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ClientPacketListener.class})
abstract class ClientPacketListenerMixin {
    @Shadow
    @Final
    private Minecraft f_104888_;

    ClientPacketListenerMixin() {
    }

    @Inject(at={@At(value="TAIL")}, method={"handleLogin"})
    private void handleLogin(CallbackInfo ci) {
        IShoulderSurfing.getInstance().resetState();
    }

    @Inject(at={@At(value="HEAD")}, method={"handleRespawn"})
    private void handleRespawn(ClientboundRespawnPacket packet, CallbackInfo ci) {
        if (!packet.m_263558_((byte)3)) {
            IShoulderSurfing.getInstance().resetState();
        }
    }

    @Inject(method={"handleMovePlayer"}, at={@At(value="INVOKE", target="net/minecraft/network/protocol/PacketUtils.ensureRunningOnSameThread(Lnet/minecraft/network/protocol/Packet;Lnet/minecraft/network/PacketListener;Lnet/minecraft/util/thread/BlockableEventLoop;)V", shift=At.Shift.AFTER)})
    private void handleMovePlayer(ClientboundPlayerPositionPacket packet, CallbackInfo ci) {
        IShoulderSurfing instance = IShoulderSurfing.getInstance();
        if (instance.isShoulderSurfing() && Config.CLIENT.getCameraConfig().isCameraOrientedOnTeleport()) {
            IShoulderSurfingCamera camera;
            LocalPlayer player = this.f_104888_.f_91074_;
            boolean isRelativeXRot = packet.m_132826_().contains(RelativeMovement.X_ROT);
            boolean isRelativeYRot = packet.m_132826_().contains(RelativeMovement.Y_ROT);
            if (isRelativeXRot && packet.m_132824_() != 0.0f || !isRelativeXRot && player.m_146909_() != packet.m_132824_()) {
                camera = instance.getCamera();
                camera.setXRot(isRelativeXRot ? camera.getXRot() + packet.m_132824_() : packet.m_132824_());
            }
            if (isRelativeYRot && packet.m_132823_() != 0.0f || !isRelativeYRot && player.m_146908_() != packet.m_132823_()) {
                camera = instance.getCamera();
                camera.setYRot(isRelativeYRot ? camera.getYRot() + packet.m_132823_() : packet.m_132823_());
            }
        }
    }
}
