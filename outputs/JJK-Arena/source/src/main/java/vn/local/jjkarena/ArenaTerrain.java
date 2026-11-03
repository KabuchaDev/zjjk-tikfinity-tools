package vn.local.jjkarena;

import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import java.util.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.saveddata.SavedData;

/** Persistent original terrain; native domains can write only into original air. */
public final class ArenaTerrain extends SavedData {
    private static ArenaTerrain active;
    private final Long2ObjectOpenHashMap<BlockState> original=new Long2ObjectOpenHashMap<>();
    private final LongOpenHashSet temporary=new LongOpenHashSet(),gate=new LongOpenHashSet();
    private boolean initialized;
    private int editing;
    private long blocked;
    private ServerLevel level;
    public static boolean levelHook,chunkHook,destroyHook;
    public static ArenaTerrain load(CompoundTag tag) {
        ArenaTerrain t=new ArenaTerrain();t.initialized=tag.getBoolean("initialized");
        for(Tag entry:tag.getList("palette",10)) {
            CompoundTag p=(CompoundTag)entry;
            BlockState state=NbtUtils.readBlockState(BuiltInRegistries.BLOCK.asLookup(),p.getCompound("state"));
            for(long pos:p.getLongArray("positions"))t.original.put(pos,state);
        }
        for(long pos:tag.getLongArray("temporary"))t.temporary.add(pos);
        return t;
    }
    @Override public CompoundTag save(CompoundTag tag) {
        tag.putBoolean("initialized",initialized);
        Map<BlockState,LongOpenHashSet> groups=new HashMap<>();
        original.long2ObjectEntrySet().forEach(e->groups.computeIfAbsent(e.getValue(),k->new LongOpenHashSet()).add(e.getLongKey()));
        ListTag palette=new ListTag();
        groups.forEach((state,positions)->{CompoundTag p=new CompoundTag();p.put("state",NbtUtils.writeBlockState(state));p.putLongArray("positions",positions.toLongArray());palette.add(p);});
        tag.put("palette",palette);tag.putLongArray("temporary",temporary.toLongArray());return tag;
    }
    static ArenaTerrain open(ServerLevel level) {
        ArenaTerrain t=level.getDataStorage().computeIfAbsent(ArenaTerrain::load,ArenaTerrain::new,"jjkarena_terrain");
        t.level=level;active=t;return t;
    }
    static void close(){active=null;}
    void edit(Runnable work){editing++;try{work.run();}finally{editing--;}}
    void capture() {
        original.clear();temporary.clear();gate.clear();
        for(BlockPos pos:BlockPos.betweenClosed(-97,78,-65,97,110,65)) {
            BlockState state=level.getBlockState(pos);if(!state.isAir())original.put(pos.asLong(),state);
        }
        initialized=true;setDirty();
    }
    void initialize(){if(!initialized)capture();else clean();}
    void remember(BlockPos pos,BlockState state) {
        if(state.isAir())original.remove(pos.asLong());else original.put(pos.asLong(),state);
        temporary.remove(pos.asLong());setDirty();
    }
    void gateBlock(BlockPos pos,boolean closed,BlockState glass) {
        long key=pos.asLong();
        if(closed){gate.add(key);temporary.add(key);}else{gate.remove(key);temporary.remove(key);}
        edit(()->level.setBlock(pos,closed?glass:original.getOrDefault(key,Blocks.AIR.defaultBlockState()),2));setDirty();
    }
    void clean() {
        edit(()->{
            for(long key:temporary.toLongArray())level.setBlock(BlockPos.of(key),original.getOrDefault(key,Blocks.AIR.defaultBlockState()),2);
            for(var e:original.long2ObjectEntrySet()) {
                BlockPos p=BlockPos.of(e.getLongKey());if(!level.getBlockState(p).equals(e.getValue()))level.setBlock(p,e.getValue(),2);
            }
        });temporary.clear();gate.clear();setDirty();
    }
    long blocked(){return blocked;}
    int size(){return original.size();}
    int temporarySize(){return temporary.size();}
    String audit() {
        int changed=0;
        for(var e:original.long2ObjectEntrySet())if(!gate.contains(e.getLongKey())&&!level.getBlockState(BlockPos.of(e.getLongKey())).equals(e.getValue()))changed++;
        return "Original terrain: blocks="+original.size()+" changed="+changed+" temporary="+temporary.size()+" blocked="+blocked;
    }
    String verify() {
        BlockPos p=new BlockPos(0,80,0);BlockState base=level.getBlockState(p);
        if(base.isAir())return "FAIL: test floor is missing";
        level.setBlock(p,Blocks.AIR.defaultBlockState(),3);boolean a=level.getBlockState(p).equals(base);
        level.getChunkAt(p).setBlockState(p,Blocks.AIR.defaultBlockState(),false);boolean b=level.getBlockState(p).equals(base);
        level.destroyBlock(p,false);boolean c=level.getBlockState(p).equals(base);
        edit(()->level.setBlock(p,base,3));
        return "Terrain protection: level="+a+" chunk="+b+" destroy="+c+" hooks="+levelHook+","+chunkHook+","+destroyHook;
    }
    static boolean protectedAt(Level world,BlockPos pos) {
        ArenaTerrain t=active;return t!=null&&world==t.level&&t.editing==0&&t.initialized&&(t.original.containsKey(pos.asLong())||t.gate.contains(pos.asLong()));
    }
    public static boolean deny(Level world,BlockPos pos,BlockState next) {
        levelHook=true;ArenaTerrain t=active;
        if(t==null||world!=t.level||t.editing!=0||!t.initialized)return false;
        long key=pos.asLong();BlockState original=t.original.get(key);
        if(t.gate.contains(key)||(original!=null&&!original.equals(next))){t.blocked++;return true;}
        if(original==null&&!next.isAir()&&t.temporary.add(key))t.setDirty();
        return false;
    }
    public static boolean denyChunk(LevelChunk chunk,BlockPos pos,BlockState next){chunkHook=true;return deny(chunk.getLevel(),pos,next);}
    public static boolean denyDestroy(Level world,BlockPos pos){destroyHook=true;if(protectedAt(world,pos)){active.blocked++;return true;}return false;}
}
