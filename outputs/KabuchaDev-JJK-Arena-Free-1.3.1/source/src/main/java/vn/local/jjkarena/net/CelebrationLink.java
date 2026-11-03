package vn.local.jjkarena.net;

import java.util.*;
import java.util.function.Consumer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.*;
import net.minecraftforge.network.simple.SimpleChannel;

public final class CelebrationLink {
    private static final SimpleChannel CHANNEL=NetworkRegistry.newSimpleChannel(new ResourceLocation("jjkarena","celebration"),()->"1",
        v->v.equals("1")||v.equals(NetworkRegistry.ABSENT)||v.equals(NetworkRegistry.ACCEPTVANILLA),
        v->v.equals("1")||v.equals(NetworkRegistry.ABSENT)||v.equals(NetworkRegistry.ACCEPTVANILLA));
    private static boolean registered;
    public record Actor(UUID uuid,int style) {}
    public record Scene(boolean active,boolean gift,UUID star,List<Actor> actors,double x,double y,double z,float yaw,float pitch) {
        static Scene read(FriendlyByteBuf b) {
            boolean active=b.readBoolean(),gift=b.readBoolean();UUID star=b.readUUID();
            int size=b.readVarInt();if(size<0||size>30)throw new IllegalArgumentException("Invalid cast");
            List<Actor> actors=new ArrayList<>();for(int i=0;i<size;i++)actors.add(new Actor(b.readUUID(),b.readVarInt()));
            return new Scene(active,gift,star,actors,b.readDouble(),b.readDouble(),b.readDouble(),b.readFloat(),b.readFloat());
        }
        void write(FriendlyByteBuf b) {
            b.writeBoolean(active);b.writeBoolean(gift);b.writeUUID(star);b.writeVarInt(actors.size());
            for(Actor a:actors){b.writeUUID(a.uuid);b.writeVarInt(a.style);}
            b.writeDouble(x);b.writeDouble(y);b.writeDouble(z);b.writeFloat(yaw);b.writeFloat(pitch);
        }
    }
    public static void init(Consumer<Scene> handler) {
        if(registered)return;registered=true;
        CHANNEL.messageBuilder(Scene.class,0,NetworkDirection.PLAY_TO_CLIENT).encoder(Scene::write).decoder(Scene::read)
            .consumerMainThread((scene,context)->handler.accept(scene)).add();
    }
    public static void send(ServerPlayer p,Scene scene) {
        if(CHANNEL.isRemotePresent(p.connection.connection))CHANNEL.send(PacketDistributor.PLAYER.with(()->p),scene);
    }
}
