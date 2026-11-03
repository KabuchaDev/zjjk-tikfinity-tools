package vn.local.jjkarena;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;

final class ArenaState extends SavedData {
    boolean built;
    int redWins, blueWins, maxHealth = 1000;
    int portraitVersion;
    double redSpawnX=-55, redSpawnZ=0, blueSpawnX=55, blueSpawnZ=0;
    double redCoreX=-69, redCoreZ=0, blueCoreX=69, blueCoreZ=0;
    static ArenaState load(CompoundTag t) {
        ArenaState s=new ArenaState();
        s.built=t.getBoolean("built"); s.redWins=t.getInt("redWins"); s.blueWins=t.getInt("blueWins");
        s.portraitVersion=t.getInt("portraitVersion");
        if(t.contains("maxHealth")) s.maxHealth=Math.max(10,t.getInt("maxHealth"));
        if(t.contains("redSpawnX")) {
            s.redSpawnX=t.getDouble("redSpawnX"); s.redSpawnZ=t.getDouble("redSpawnZ");
            s.blueSpawnX=t.getDouble("blueSpawnX"); s.blueSpawnZ=t.getDouble("blueSpawnZ");
            s.redCoreX=t.getDouble("redCoreX"); s.redCoreZ=t.getDouble("redCoreZ");
            s.blueCoreX=t.getDouble("blueCoreX"); s.blueCoreZ=t.getDouble("blueCoreZ");
        }
        return s;
    }
    @Override public CompoundTag save(CompoundTag t) {
        t.putBoolean("built",built); t.putInt("redWins",redWins); t.putInt("blueWins",blueWins); t.putInt("maxHealth",maxHealth);
        t.putInt("portraitVersion",portraitVersion);
        t.putDouble("redSpawnX",redSpawnX); t.putDouble("redSpawnZ",redSpawnZ);
        t.putDouble("blueSpawnX",blueSpawnX); t.putDouble("blueSpawnZ",blueSpawnZ);
        t.putDouble("redCoreX",redCoreX); t.putDouble("redCoreZ",redCoreZ);
        t.putDouble("blueCoreX",blueCoreX); t.putDouble("blueCoreZ",blueCoreZ);
        return t;
    }
}
