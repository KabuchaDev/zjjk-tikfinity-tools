package vn.local.jjkarena;

import java.util.ArrayDeque;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

/** Original, mirrored colosseum. Budgeted placement avoids a giant blocking /fill. */
final class ArenaBuilder {
    private record Box(int x1,int y1,int z1,int x2,int y2,int z2,BlockState state) {}
    private final ArrayDeque<Box> boxes=new ArrayDeque<>();
    private Box current;
    private int x,y,z;
    long placed,total;
    private void box(int x1,int y1,int z1,int x2,int y2,int z2,Block b) {
        int a=Math.min(x1,x2),c=Math.min(y1,y2),e=Math.min(z1,z2);
        int d=Math.max(x1,x2),f=Math.max(y1,y2),g=Math.max(z1,z2);
        boxes.add(new Box(a,c,e,d,f,g,b.defaultBlockState()));
        total+=(long)(d-a+1)*(f-c+1)*(g-e+1);
    }
    ArenaBuilder() {
        box(-97,78,-65,97,110,65,Blocks.AIR);
        box(-96,78,-64,96,78,64,Blocks.BEDROCK);
        box(-96,79,-64,96,80,64,Blocks.POLISHED_DEEPSLATE);
        box(-78,80,-40,78,80,40,Blocks.SMOOTH_QUARTZ);
        // Three unobstructed lanes, alternating stone and team color accents.
        for(int lane: new int[]{-18,0,18}) {
            box(-75,80,lane-4,75,80,lane+4,Blocks.POLISHED_ANDESITE);
            box(-73,80,lane-5,-2,80,lane-5,Blocks.RED_CONCRETE);
            box(2,80,lane+5,73,80,lane+5,Blocks.BLUE_CONCRETE);
            for(int px=-64;px<=64;px+=16) box(px,80,lane,px,80,lane,Blocks.SEA_LANTERN);
        }
        box(-1,80,-39,1,80,39,Blocks.POLISHED_BLACKSTONE);
        for(int px=-7;px<=7;px++) for(int pz=-7;pz<=7;pz++) {
            int dist=px*px+pz*pz;
            if(dist>=36 && dist<=49) box(px,80,pz,px,80,pz,Blocks.GOLD_BLOCK);
        }
        // Tiered grandstands and aisles on the north and south sides.
        for(int sign:new int[]{-1,1}) for(int row=0;row<7;row++) {
            int zz=sign*(44+row*3), yy=81+row;
            box(-89,81,zz,89,yy,zz+sign*2,Blocks.DEEPSLATE_BRICKS);
            box(-88,yy+1,zz, -4,yy+1,zz,Blocks.RED_CONCRETE);
            box(4,yy+1,zz,88,yy+1,zz,Blocks.BLUE_CONCRETE);
            box(-2,yy,zz,2,yy,zz+sign*2,Blocks.SMOOTH_QUARTZ);
        }
        // End walls and towers frame both bases; the playing area stays open.
        for(int sign:new int[]{-1,1}) {
            Block color=sign<0?Blocks.RED_CONCRETE:Blocks.BLUE_CONCRETE;
            Block glass=sign<0?Blocks.RED_STAINED_GLASS:Blocks.BLUE_STAINED_GLASS;
            box(sign*94,81,-64,sign*96,91,64,Blocks.DEEPSLATE_BRICKS);
            for(int zz:new int[]{-55,55}) {
                box(sign*84,81,zz-5,sign*92,98,zz+5,Blocks.POLISHED_BLACKSTONE_BRICKS);
                box(sign*83,97,zz-6,sign*93,98,zz+6,Blocks.CHISELED_STONE_BRICKS);
                box(sign*87,89,zz-5,sign*89,94,zz+5,glass);
                box(sign*82,99,zz-7,sign*94,100,zz+7,Blocks.DEEPSLATE_TILES);
                box(sign*85,101,zz-4,sign*91,102,zz+4,color);
                box(sign*88,103,zz,sign*88,104,zz,Blocks.SEA_LANTERN);
            }
            // Team base platform and crystal core; reachable along all lanes.
            box(sign*62,80,-29,sign*75,80,29,color);
            box(sign*67,81,-2,sign*71,82,2,Blocks.POLISHED_BLACKSTONE);
            box(sign*68,83,-1,sign*70,87,1,glass);
            box(sign*69,84,0,sign*69,86,0,Blocks.SEA_LANTERN);
            box(sign*67,88,-2,sign*71,88,2,Blocks.GOLD_BLOCK);
            // Three wide torii gates, pillars outside unit paths.
            for(int lane:new int[]{-18,0,18}) {
                box(sign*58,81,lane-7,sign*60,91,lane-6,color);
                box(sign*58,81,lane+6,sign*60,91,lane+7,color);
                box(sign*57,91,lane-9,sign*61,92,lane+9,Blocks.POLISHED_BLACKSTONE);
                box(sign*58,88,lane-7,sign*60,88,lane+7,color);
                box(sign*54,80,lane-2,sign*56,80,lane+2,Blocks.SEA_LANTERN);
            }
            // Large team emblems on the end facade.
            box(sign*93,86,-12,sign*93,97,12,color);
            box(sign*92,90,-9,sign*92,92,9,Blocks.SEA_LANTERN);
            box(sign*92,86,-2,sign*92,97,2,Blocks.SEA_LANTERN);
        }
        for(int zz:new int[]{-64,64}) {
            box(-96,81,zz,96,88,zz,Blocks.POLISHED_BLACKSTONE_BRICKS);
            for(int px=-80;px<=80;px+=20) {
                box(px,88,zz,px,101,zz,Blocks.DEEPSLATE_BRICKS);
                box(px-2,101,zz-1,px+2,102,zz+1,Blocks.SEA_LANTERN);
            }
        }
        // Spectator deck: elevated south stand overlooking both cores.
        box(-14,98,47,14,98,59,Blocks.SMOOTH_QUARTZ);
        box(-14,99,47,14,100,47,Blocks.GLASS);
        box(-14,99,47,-14,100,59,Blocks.GLASS);
        box(14,99,47,14,100,59,Blocks.GLASS);
        box(-15,105,46,15,106,60,Blocks.POLISHED_BLACKSTONE);
        for(int px:new int[]{-14,14}) for(int zz:new int[]{48,59}) box(px,99,zz,px,104,zz,Blocks.QUARTZ_PILLAR);
    }
    boolean tick(ServerLevel level) {
        BlockPos.MutableBlockPos pos=new BlockPos.MutableBlockPos();
        for(int budget=0;budget<6000;budget++) {
            if(current==null) {
                current=boxes.poll();
                if(current==null) return true;
                x=current.x1; y=current.y1; z=current.z1;
            }
            pos.set(x,y,z);
            // Avoid lighting/network work for the empty part of a fresh void world.
            if(!level.getBlockState(pos).equals(current.state)) level.setBlock(pos,current.state,2);
            placed++;
            if(++x>current.x2) {x=current.x1; if(++z>current.z2) {z=current.z1; if(++y>current.y2) current=null;}}
        }
        return false;
    }
    int percent() {return (int)(placed*100/Math.max(1,total));}
}
