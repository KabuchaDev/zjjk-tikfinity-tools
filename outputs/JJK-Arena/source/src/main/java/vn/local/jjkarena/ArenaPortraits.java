package vn.local.jjkarena;

import com.google.gson.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

/** Permanent, inward-facing vanilla-block portraits. No client resource pack or entities. */
final class ArenaPortraits {
    static final int VERSION=1;
    private static final Map<BlockPos,BlockState> PLAN=createPlan();
    private static final Map<Character,String[]> FONT=Map.ofEntries(
        Map.entry('R',new String[]{"11110","10001","10001","11110","10100","10010","10001"}),
        Map.entry('O',new String[]{"01110","10001","10001","10001","10001","10001","01110"}),
        Map.entry('N',new String[]{"10001","11001","11001","10101","10011","10011","10001"}),
        Map.entry('A',new String[]{"01110","10001","10001","11111","10001","10001","10001"}),
        Map.entry('L',new String[]{"10000","10000","10000","10000","10000","10000","11111"}),
        Map.entry('D',new String[]{"11110","10001","10001","10001","10001","10001","11110"}),
        Map.entry('M',new String[]{"10001","11011","10101","10101","10001","10001","10001"}),
        Map.entry('E',new String[]{"11111","10000","10000","11110","10000","10000","11111"}),
        Map.entry('S',new String[]{"01111","10000","10000","01110","00001","00001","11110"}),
        Map.entry('I',new String[]{"11111","00100","00100","00100","00100","00100","11111"}),
        Map.entry('7',new String[]{"11111","00001","00010","00100","01000","01000","01000"}),
        Map.entry('1',new String[]{"00100","01100","00100","00100","00100","00100","01110"}),
        Map.entry('0',new String[]{"01110","10001","10011","10101","11001","10001","01110"}));

    private static Map<BlockPos,BlockState> createPlan() {
        // Plan initialization is lazy below so the glyph table is initialized first.
        return new LinkedHashMap<>();
    }
    private static void put(Map<BlockPos,BlockState> plan,int x,int y,int z,BlockState state){plan.put(new BlockPos(x,y,z),state);}
    private static synchronized Map<BlockPos,BlockState> plan() {
        if(!PLAN.isEmpty())return PLAN;
        Map<BlockPos,BlockState> next=new LinkedHashMap<>();
        for(int sign:new int[]{-1,1}) {
            BlockState color=(sign<0?Blocks.RED_CONCRETE:Blocks.BLUE_CONCRETE).defaultBlockState();
            for(int y=92;y<=97;y++)for(int z=-12;z<=12;z++)for(int x:new int[]{92,93})put(next,sign*x,y,z,Blocks.AIR.defaultBlockState());
            for(int col=0;col<83;col++)for(int y=92;y<=169;y++) {
                boolean edge=col<2||col>80||y<94||y>167;
                put(next,sign*94,y,sign*(col-41),edge?color:Blocks.BLACK_CONCRETE.defaultBlockState());
                if(col==0||col==82||y==92||y==169)put(next,sign*93,y,sign*(col-41),color);
            }
            String name=sign<0?"ronaldo":"messi";
            String resource="/data/jjkarena/portraits/"+name+".json";
            try(InputStream input=ArenaPortraits.class.getResourceAsStream(resource)) {
                if(input==null)throw new IllegalStateException("Missing portrait "+resource);
                JsonObject image=JsonParser.parseReader(new InputStreamReader(input,StandardCharsets.UTF_8)).getAsJsonObject();
                int w=image.get("width").getAsInt(),h=image.get("height").getAsInt();
                if(w<1||w>79||h<1||h>64)throw new IllegalStateException("Invalid portrait dimensions");
                List<BlockState> palette=new ArrayList<>();
                for(JsonElement item:image.getAsJsonArray("palette")) {
                    var block=ForgeRegistries.BLOCKS.getValue(new ResourceLocation(item.getAsString()));
                    if(block==null||block==Blocks.AIR)throw new IllegalStateException("Unknown portrait block "+item);
                    palette.add(block.defaultBlockState());
                }
                JsonArray rows=image.getAsJsonArray("rows");
                if(rows.size()!=h)throw new IllegalStateException("Invalid portrait rows");
                for(int row=0;row<h;row++) {
                    JsonArray pixels=rows.get(row).getAsJsonArray();if(pixels.size()!=w)throw new IllegalStateException("Invalid portrait row width");
                    for(int col=0;col<w;col++)put(next,sign*94,166-(64-h)/2-row,sign*((83-w)/2+col-41),palette.get(pixels.get(col).getAsInt()));
                }
            }catch(IOException e){throw new IllegalStateException("Cannot load portrait",e);}
            String title=sign<0?"RONALDO 7":"MESSI 10";int start=(83-(title.length()*6-1))/2;
            for(int i=0;i<title.length();i++) {
                String[] glyph=FONT.get(title.charAt(i));if(glyph==null)continue;
                for(int row=0;row<7;row++)for(int col=0;col<5;col++)if(glyph[row].charAt(col)=='1')
                    put(next,sign*94,101-row,sign*(start+i*6+col-41),Blocks.WHITE_CONCRETE.defaultBlockState());
            }
            // Invisible, non-solid lights illuminate the wall without hanging entities.
            for(int y=95;y<=167;y+=6)for(int z=-39;z<=39;z+=6)put(next,sign*93,y,z,Blocks.LIGHT.defaultBlockState());
            for(int z:new int[]{-40,-24,-8,8,24,40})for(int y:new int[]{92,169})put(next,sign*93,y,z,Blocks.SEA_LANTERN.defaultBlockState());
        }
        PLAN.putAll(next);return PLAN;
    }
    static int install(ServerLevel level,ArenaTerrain terrain) {
        Map<BlockPos,BlockState> plan=plan();
        terrain.edit(()->plan.forEach((pos,state)->{if(!level.getBlockState(pos).equals(state))level.setBlock(pos,state,2);terrain.remember(pos,state);}));
        return (int)plan.values().stream().filter(s->!s.isAir()).count();
    }
    static String audit(ServerLevel level) {
        int blocks=0,changed=0,unprotected=0;
        for(var entry:plan().entrySet())if(!entry.getValue().isAir()) {
            blocks++;
            if(!level.getBlockState(entry.getKey()).equals(entry.getValue()))changed++;
            if(!ArenaTerrain.protectedAt(level,entry.getKey()))unprotected++;
        }
        return "Portraits: revision="+VERSION+" blocks="+blocks+" changed="+changed+" unprotected="+unprotected+" red=ronaldo blue=messi";
    }
}
