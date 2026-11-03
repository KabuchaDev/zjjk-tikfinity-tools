/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.exopandora.shouldersurfing.api.client.IShoulderSurfing
 *  com.github.exopandora.shouldersurfing.client.ShoulderSurfing
 *  com.github.exopandora.shouldersurfing.client.renderer.CrosshairRenderer
 *  com.github.exopandora.shouldersurfing.mixinduck.CameraDuck
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.client.Minecraft
 *  net.minecraftforge.client.event.MovementInputUpdateEvent
 *  net.minecraftforge.client.event.RegisterGuiOverlaysEvent
 *  net.minecraftforge.client.event.RenderGuiOverlayEvent$Pre
 *  net.minecraftforge.client.event.RenderLevelStageEvent
 *  net.minecraftforge.client.event.RenderLevelStageEvent$Stage
 *  net.minecraftforge.client.event.ViewportEvent$ComputeCameraAngles
 *  net.minecraftforge.client.gui.overlay.VanillaGuiOverlay
 *  net.minecraftforge.event.TickEvent$ClientTickEvent
 *  net.minecraftforge.event.TickEvent$Phase
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  org.joml.Matrix4f
 */
package com.github.exopandora.shouldersurfing.forge.event;

import com.github.exopandora.shouldersurfing.api.client.IShoulderSurfing;
import com.github.exopandora.shouldersurfing.client.ShoulderSurfing;
import com.github.exopandora.shouldersurfing.client.renderer.CrosshairRenderer;
import com.github.exopandora.shouldersurfing.mixinduck.CameraDuck;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.MovementInputUpdateEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.joml.Matrix4f;

public class ClientEventHandler {
    @SubscribeEvent
    public static void clientTickEvent(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START && Minecraft.m_91087_().f_91073_ != null && !Minecraft.m_91087_().m_91104_()) {
            ShoulderSurfing.getInstance().tick();
        }
    }

    @SubscribeEvent
    public static void preRenderGuiOverlayEvent(RenderGuiOverlayEvent.Pre event) {
        if (VanillaGuiOverlay.CROSSHAIR.id().equals((Object)event.getOverlay().id()) && !IShoulderSurfing.getInstance().getCrosshairRenderer().isCrosshairVisible()) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void registerGuiOverlaysEvent(RegisterGuiOverlaysEvent event) {
        event.registerBelow(VanillaGuiOverlay.CROSSHAIR.id(), "pre_crosshair", (gui, guiGraphics, partialTick, screenWith, screenHeight) -> {
            CrosshairRenderer crosshairRenderer = ShoulderSurfing.getInstance().getCrosshairRenderer();
            if (crosshairRenderer.isCrosshairVisible()) {
                crosshairRenderer.preRenderCrosshair(guiGraphics);
            }
        });
        event.registerAbove(VanillaGuiOverlay.CROSSHAIR.id(), "post_crosshair", (gui, guiGraphics, partialTick, screenWith, screenHeight) -> {
            CrosshairRenderer crosshairRenderer = ShoulderSurfing.getInstance().getCrosshairRenderer();
            if (crosshairRenderer.isCrosshairVisible()) {
                crosshairRenderer.postRenderCrosshair(guiGraphics);
            }
        });
    }

    @SubscribeEvent
    public static void renderLevelStageEvent(RenderLevelStageEvent event) {
        if (RenderLevelStageEvent.Stage.AFTER_SKY.equals(event.getStage())) {
            float partialTick = Minecraft.m_91087_().m_91296_();
            ShoulderSurfing instance = ShoulderSurfing.getInstance();
            instance.getCamera().renderTick(event.getCamera().m_90592_(), partialTick);
            Matrix4f modelViewMatrix = event.getPoseStack().m_85850_().m_252922_();
            Matrix4f projectionMatrix = RenderSystem.getProjectionMatrix();
            instance.getCrosshairRenderer().renderTick(event.getCamera(), modelViewMatrix, projectionMatrix, partialTick);
        }
    }

    @SubscribeEvent
    public static void movementInputUpdateEvent(MovementInputUpdateEvent event) {
        ShoulderSurfing.getInstance().getInputHandler().updateMovementInput(event.getInput());
        ShoulderSurfing.getInstance().updatePlayerRotations();
    }

    @SubscribeEvent
    public static void computeCameraAnglesEvent(ViewportEvent.ComputeCameraAngles event) {
        event.setRoll(event.getRoll() + ((CameraDuck)event.getCamera()).shouldersurfing$getZRot());
    }
}
