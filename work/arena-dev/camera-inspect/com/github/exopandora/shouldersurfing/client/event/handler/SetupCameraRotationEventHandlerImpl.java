/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.exopandora.shouldersurfing.api.client.event.SetupCameraRotationEvent
 *  com.github.exopandora.shouldersurfing.api.client.event.handler.SetupCameraRotationEventHandler
 *  com.github.exopandora.shouldersurfing.api.math.Vec2f
 *  com.github.exopandora.shouldersurfing.api.util.EntityHelper
 *  net.minecraft.world.entity.player.Player
 */
package com.github.exopandora.shouldersurfing.client.event.handler;

import com.github.exopandora.shouldersurfing.api.client.event.SetupCameraRotationEvent;
import com.github.exopandora.shouldersurfing.api.client.event.handler.SetupCameraRotationEventHandler;
import com.github.exopandora.shouldersurfing.api.math.Vec2f;
import com.github.exopandora.shouldersurfing.api.util.EntityHelper;
import net.minecraft.world.entity.player.Player;

public enum SetupCameraRotationEventHandlerImpl implements SetupCameraRotationEventHandler
{
    INSTANCE;


    public void handle(SetupCameraRotationEvent event) {
        if (event.getPlayer().m_20159_()) {
            event.setResult(EntityHelper.applyPassengerRotationConstraints((Player)event.getPlayer(), (Vec2f)event.getResult(), (Vec2f)event.getCameraRotO()));
        }
    }
}
