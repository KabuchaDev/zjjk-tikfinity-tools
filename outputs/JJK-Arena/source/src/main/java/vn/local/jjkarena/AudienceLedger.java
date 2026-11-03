package vn.local.jjkarena;
import java.util.*;
import net.minecraft.nbt.*;
import net.minecraft.world.level.saveddata.SavedData;

final class AudienceLedger extends SavedData {
    final Set<String> follows=new HashSet<>();
    final LinkedHashSet<String> notices=new LinkedHashSet<>();
    static AudienceLedger load(CompoundTag t) {
        AudienceLedger ledger=new AudienceLedger();
        for(Tag s:t.getList("follows",8))ledger.follows.add(s.getAsString());
        for(Tag s:t.getList("notices",8))ledger.notices.add(s.getAsString());
        return ledger;
    }
    boolean contains(String type,String key){return (type.equals("follow")?follows:notices).contains(key);}
    void accept(String type,String key){
        (type.equals("follow")?follows:notices).add(key);
        while(notices.size()>4096)notices.remove(notices.iterator().next());setDirty();
    }
    public CompoundTag save(CompoundTag t) {
        ListTag f=new ListTag(),n=new ListTag();follows.forEach(s->f.add(StringTag.valueOf(s)));notices.forEach(s->n.add(StringTag.valueOf(s)));
        t.put("follows",f);t.put("notices",n);return t;
    }
}
