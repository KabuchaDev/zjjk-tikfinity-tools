from pathlib import Path
import subprocess, imageio_ffmpeg, json
root=Path(__file__).resolve().parents[2]
source=root/'outputs/JJK-Arena/source'
f=source/'src/main/java/vn/local/jjkarena/JjkArena.java'
s=f.read_text(encoding='utf-8')
old='.then(Commands.argument("viewer",StringArgumentType.word()).executes(c->enqueue(c.getSource(),StringArgumentType.getString(c,"team"),StringArgumentType.getString(c,"kind"),IntegerArgumentType.getInteger(c,"count"),StringArgumentType.getString(c,"viewer"))))))));'
new='.then(Commands.argument("viewer",StringArgumentType.word()).executes(c->enqueue(c.getSource(),StringArgumentType.getString(c,"team"),StringArgumentType.getString(c,"kind"),IntegerArgumentType.getInteger(c,"count"),StringArgumentType.getString(c,"viewer")))\n                .then(Commands.literal("gift").executes(c->enqueue(c.getSource(),StringArgumentType.getString(c,"team"),StringArgumentType.getString(c,"kind"),IntegerArgumentType.getInteger(c,"count"),StringArgumentType.getString(c,"viewer"),true))))))));'
assert s.count(old)==1
f.write_text(s.replace(old,new),encoding='utf-8')
f=source/'build.gradle';s=f.read_text();f.write_text(s.replace("version = '1.2.6'","version = '1.3.0'").replace("archiveVersion = '1.1.0'","archiveVersion = '1.2.0'"))
for rel,a,b in [('src/camera/resources/META-INF/mods.toml','1.1.0','1.2.0'),('src/main/resources/META-INF/mods.toml','1.2.6','1.3.0')]:
 f=source/rel;s=f.read_text(encoding='utf-8');f.write_text(s.replace(a,b),encoding='utf-8')
assets=source/'src/camera/resources/assets/jjkarenacamera';(assets/'sounds').mkdir(parents=True,exist_ok=True)
(assets/'sounds.json').write_text(json.dumps({'tet_gift':{'sounds':[{'name':'jjkarenacamera:tet_gift','stream':False}]}}),encoding='utf-8')
files=list(Path('C:/Users/PHAT/Downloads').glob('*y6996wjnge0*.mp4'));assert len(files)==1
subprocess.run([imageio_ffmpeg.get_ffmpeg_exe(),'-hide_banner','-loglevel','error','-y','-ss','34','-i',str(files[0]),'-t','4','-vn','-ac','1','-ar','44100','-c:a','libvorbis','-q:a','5',str(assets/'sounds/tet_gift.ogg')],check=True)
print('Prepared versions, gift command, and 4-second user-provided audio clip.')
