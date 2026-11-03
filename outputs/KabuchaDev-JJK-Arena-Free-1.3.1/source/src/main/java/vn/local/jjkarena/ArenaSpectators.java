package vn.local.jjkarena;

import java.util.List;
import net.minecraft.network.protocol.game.ClientboundSetCameraPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.phys.Vec3;
import vn.local.jjkarena.net.CameraLink;

final class ArenaSpectators {
    private ArenaSpectators() {}

    static void relocate(Mob mob, Vec3 destination) {
        ServerLevel level=(ServerLevel)mob.level();
        // Capture before moving the target. Keep the same camera entity throughout:
        // ServerPlayer.teleportTo(ServerLevel, ...) would detach the spectator.
        List<ServerPlayer> viewers=level.players().stream()
            .filter(p -> p.isSpectator() && p.getCamera()==mob).toList();
        mob.stopRiding();
        mob.getNavigation().stop();
        mob.teleportTo(destination.x,destination.y,destination.z);
        mob.setDeltaMovement(Vec3.ZERO);
        mob.fallDistance=0;
        mob.hasImpulse=true;
        for(ServerPlayer player:viewers) {
            // Preserve view rotation, and update both client position and chunk tracking
            // in this tick, before the old location can unload the watched entity.
            player.connection.teleport(destination.x,destination.y,destination.z,0,0,RelativeMovement.ROTATION);
            player.connection.resetPosition();
            level.getChunkSource().move(player);
            player.connection.send(new ClientboundSetCameraPacket(mob));
            CameraLink.snap(player,mob);
        }
    }
}
