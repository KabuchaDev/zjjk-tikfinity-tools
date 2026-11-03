package vn.local.jjkarena.net;

import java.util.UUID;
import java.util.function.Consumer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.Connection;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

/** Optional client companion; players without it can still join normally. */
public final class CameraLink {
    private static final SimpleChannel CHANNEL=NetworkRegistry.newSimpleChannel(
        new ResourceLocation("jjkarena","camera"),()->"1",
        v->v.equals("1")||v.equals(NetworkRegistry.ABSENT)||v.equals(NetworkRegistry.ACCEPTVANILLA),
        v->v.equals("1")||v.equals(NetworkRegistry.ABSENT)||v.equals(NetworkRegistry.ACCEPTVANILLA));
    private static boolean registered;
    private static final SimpleChannel AUTO=NetworkRegistry.newSimpleChannel(
        new ResourceLocation("jjkarena","autocamera"),()->"1",
        v->v.equals("1")||v.equals(NetworkRegistry.ABSENT)||v.equals(NetworkRegistry.ACCEPTVANILLA),
        v->v.equals("1")||v.equals(NetworkRegistry.ABSENT)||v.equals(NetworkRegistry.ACCEPTVANILLA));
    private CameraLink() {}

    public record Snap(int entityId,UUID uuid,double x,double y,double z,float yaw,float pitch,float head,float body) {
        static Snap read(FriendlyByteBuf b) {
            return new Snap(b.readVarInt(),b.readUUID(),b.readDouble(),b.readDouble(),b.readDouble(),b.readFloat(),b.readFloat(),b.readFloat(),b.readFloat());
        }
        void write(FriendlyByteBuf b) {
            b.writeVarInt(entityId);b.writeUUID(uuid);b.writeDouble(x);b.writeDouble(y);b.writeDouble(z);
            b.writeFloat(yaw);b.writeFloat(pitch);b.writeFloat(head);b.writeFloat(body);
        }
    }

    public record Toggle() {}
    public record AutoState(boolean enabled,int targetId,UUID targetUuid) {
        static AutoState read(FriendlyByteBuf b) {return new AutoState(b.readBoolean(),b.readInt(),b.readUUID());}
        void write(FriendlyByteBuf b) {b.writeBoolean(enabled);b.writeInt(targetId);b.writeUUID(targetUuid);}
    }

    public static void init(Consumer<Snap> handler,Consumer<ServerPlayer> toggle,Consumer<AutoState> state) {
        if(registered)return;
        registered=true;
        CHANNEL.messageBuilder(Snap.class,0,NetworkDirection.PLAY_TO_CLIENT)
            .encoder(Snap::write).decoder(Snap::read)
            .consumerMainThread((snap,context)->handler.accept(snap)).add();
        AUTO.messageBuilder(Toggle.class,0,NetworkDirection.PLAY_TO_SERVER)
            .encoder((message,b)->{}).decoder(b->new Toggle())
            .consumerMainThread((message,context)->{ServerPlayer sender=context.get().getSender();if(sender!=null)toggle.accept(sender);}).add();
        AUTO.messageBuilder(AutoState.class,1,NetworkDirection.PLAY_TO_CLIENT)
            .encoder(AutoState::write).decoder(AutoState::read)
            .consumerMainThread((message,context)->state.accept(message)).add();
    }

    public static boolean toggle(Connection connection) {
        if(!AUTO.isRemotePresent(connection))return false;
        AUTO.sendToServer(new Toggle());return true;
    }
    public static void autoState(ServerPlayer player,boolean enabled,Mob target) {
        if(AUTO.isRemotePresent(player.connection.connection))
            AUTO.send(PacketDistributor.PLAYER.with(()->player),new AutoState(enabled,target==null?-1:target.getId(),target==null?new UUID(0,0):target.getUUID()));
    }

    public static void snap(ServerPlayer viewer,Mob mob) {
        if(present(viewer))
            CHANNEL.send(PacketDistributor.PLAYER.with(()->viewer),
                new Snap(mob.getId(),mob.getUUID(),mob.getX(),mob.getY(),mob.getZ(),mob.getYRot(),mob.getXRot(),mob.yHeadRot,mob.yBodyRot));
    }
    public static boolean present(ServerPlayer viewer) {return CHANNEL.isRemotePresent(viewer.connection.connection);}
}
