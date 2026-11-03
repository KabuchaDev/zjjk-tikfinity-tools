import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.*;
import java.util.*;
import java.util.List;
import java.util.zip.ZipFile;
import javax.imageio.ImageIO;

/** Converts reference pixels to ordinary Minecraft block choices, and previews the block grid. */
public class MakePortraits {
    record Material(String id,BufferedImage texture,double[] lab) {}
    static final List<Material> palette=new ArrayList<>();
    static double linear(double c){c/=255;return c<=.04045?c/12.92:Math.pow((c+.055)/1.055,2.4);}
    static double f(double t){return t>.008856?Math.cbrt(t):7.787*t+16.0/116;}
    static double[] lab(int rgb){
        double r=linear(rgb>>16&255),g=linear(rgb>>8&255),b=linear(rgb&255);
        double x=f((.4124564*r+.3575761*g+.1804375*b)/.95047),y=f(.2126729*r+.7151522*g+.072175*b),z=f((.0193339*r+.119192*g+.9503041*b)/1.08883);
        return new double[]{116*y-16,500*(x-y),200*(y-z)};
    }
    static void add(ZipFile zip,String id,String texture)throws Exception{
        var entry=zip.getEntry("assets/minecraft/textures/block/"+texture+".png");if(entry==null)throw new Exception("Missing texture "+texture);
        BufferedImage img=ImageIO.read(zip.getInputStream(entry));long r=0,g=0,b=0,n=0;
        for(int y=0;y<Math.min(img.getWidth(),img.getHeight());y++)for(int x=0;x<img.getWidth();x++){int c=img.getRGB(x,y);r+=c>>16&255;g+=c>>8&255;b+=c&255;n++;}
        palette.add(new Material("minecraft:"+id,img,lab((int)(r/n)<<16|(int)(g/n)<<8|(int)(b/n))));
    }
    static int[][] convert(String path,int w,int h)throws Exception{
        BufferedImage input=ImageIO.read(Path.of(path).toFile()),small=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        Graphics2D g=small.createGraphics();g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);g.drawImage(input,0,0,w,h,null);g.dispose();
        int[][] grid=new int[h][w];
        for(int y=0;y<h;y++)for(int x=0;x<w;x++){
            double[] c=lab(small.getRGB(x,y));double best=Double.MAX_VALUE;int at=0;
            for(int i=0;i<palette.size();i++){double[] p=palette.get(i).lab;double d=Math.pow(c[0]-p[0],2)+Math.pow(c[1]-p[1],2)+Math.pow(c[2]-p[2],2);if(d<best){best=d;at=i;}}
            grid[y][x]=at;
        }return grid;
    }
    static void save(int[][] grid,Path path)throws Exception{
        StringBuilder s=new StringBuilder("{\"width\":"+grid[0].length+",\"height\":"+grid.length+",\"palette\":[");
        for(int i=0;i<palette.size();i++){if(i>0)s.append(',');s.append('"').append(palette.get(i).id).append('"');}
        s.append("],\"rows\":[\n");for(int y=0;y<grid.length;y++){if(y>0)s.append(",\n");s.append(Arrays.toString(grid[y]));}s.append("\n]}\n");Files.writeString(path,s);
    }
    static void preview(Graphics2D g,int ox,int oy,int[][] grid,Color team,String name){
        int scale=7,w=83*scale,h=78*scale;
        g.setColor(team);g.fillRect(ox,oy,w,h);g.setColor(new Color(12,13,17));g.fillRect(ox+14,oy+14,w-28,h-28);
        int x0=ox+(83-grid[0].length)/2*scale,y0=oy+(3+(64-grid.length)/2)*scale;
        for(int y=0;y<grid.length;y++)for(int x=0;x<grid[0].length;x++)g.drawImage(palette.get(grid[y][x]).texture,x0+x*scale,y0+y*scale,x0+(x+1)*scale,y0+(y+1)*scale,0,0,16,16,null);
        g.setColor(Color.WHITE);g.setFont(new Font("Monospaced",Font.BOLD,25));g.drawString(name,ox+(w-g.getFontMetrics().stringWidth(name))/2,oy+h-28);
    }
    public static void main(String[] a)throws Exception{
        try(ZipFile zip=new ZipFile(a[0])){
            for(String type:List.of("concrete","wool","terracotta"))for(String color:List.of("white","orange","magenta","light_blue","yellow","lime","pink","gray","light_gray","cyan","purple","blue","brown","green","red","black"))add(zip,color+"_"+type,color+"_"+type);
            for(String id:List.of("terracotta","clay","calcite","snow_block","end_stone","sandstone","red_sandstone","bricks","red_nether_bricks","nether_bricks","moss_block","oak_planks","birch_planks","spruce_planks","dark_oak_planks","polished_andesite","polished_diorite","polished_granite","polished_blackstone")){
                String texture=id.equals("snow_block")?"snow":id;add(zip,id,texture);
            }
            add(zip,"quartz_block","quartz_block_side");
        }
        Path out=Path.of(a[3]);Files.createDirectories(out);
        int[][] r=convert(a[1],48,64),m=convert(a[2],72,48);
        save(r,out.resolve("ronaldo.json"));save(m,out.resolve("messi.json"));
        BufferedImage preview=new BufferedImage(1238,610,BufferedImage.TYPE_INT_RGB);Graphics2D g=preview.createGraphics();g.setColor(new Color(25,28,35));g.fillRect(0,0,1238,610);
        preview(g,24,24,r,new Color(164,33,40),"RONALDO 7");preview(g,633,24,m,new Color(36,82,172),"MESSI 10");g.dispose();ImageIO.write(preview,"png",Path.of(a[4]).toFile());
        System.out.println("Generated 48x64 Ronaldo, 72x48 Messi; "+palette.size()+" vanilla block materials.");
    }
}
