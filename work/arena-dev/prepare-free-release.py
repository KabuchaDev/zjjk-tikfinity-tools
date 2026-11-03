from pathlib import Path
import shutil,re,json

root=Path(__file__).resolve().parents[2]
src=root/'outputs/JJK-Arena'
out=root/'outputs/KabuchaDev-JJK-Arena-Free-1.3.1'
if out.exists(): raise SystemExit('Release directory already exists; refusing to overwrite.')
def copy(a,b):
    b.parent.mkdir(parents=True,exist_ok=True);shutil.copy2(a,b)
def edit(p,old,new):
    s=p.read_text(encoding='utf-8');assert old in s,(p,old);p.write_text(s.replace(old,new),encoding='utf-8')
for p in (src/'source/src/main/java/vn/local/jjkarena').rglob('*.java'):
    if p.name!='ArenaPortraits.java':copy(p,out/'source'/p.relative_to(src/'source'))
for tree in ['src/main/resources','src/camera/resources']:
    for p in (src/'source'/tree).rglob('*'):
        if p.is_file() and '/portraits/' not in p.as_posix() and p.suffix!='.ogg' and p.name!='sounds.json':
            copy(p,out/'source'/p.relative_to(src/'source'))
for name in ['build.gradle','settings.gradle','gradle.properties']:
    copy(src/'source'/name,out/'source'/name)
base=out/'source/src/main/java/vn/local/jjkarena'
p=base/'JjkArena.java';s=p.read_text(encoding='utf-8')
s,n=re.subn(r'        if\(state.built&&state.portraitVersion<ArenaPortraits.VERSION\) \{.*?\n        \}\n','',s,flags=re.S);assert n==1
s=s.replace('ArenaPortraits.install(arena,terrain);state.portraitVersion=ArenaPortraits.VERSION;','')
s,n=re.subn(r'^.*Commands.literal\("portraits"\).*\n','',s,flags=re.M);assert n==1
s=s.replace('celebration.start(cast(),null)','celebration.start(cast())').replace('celebration.start(cast(),mob);','')
p.write_text(s,encoding='utf-8')
p=base/'ArenaState.java';s=p.read_text(encoding='utf-8').replace('maxHealth = 1000','maxHealth = 10000')
s=re.sub(r'^.*portraitVersion.*\n','',s,flags=re.M);p.write_text(s,encoding='utf-8')
p=base/'ArenaCelebration.java';s=p.read_text(encoding='utf-8')
s=s.replace('    private boolean gift;\n','').replace('(gift?"gift":"follow")','"follow"')
s=s.replace('start(List<Mob> cast,Mob star)','start(List<Mob> cast)').replace('gift=star!=null;','')
s=s.replace('cast.size()-(gift?1:0)','cast.size()')
s=s.replace('            if(mob==star)mob.moveTo(0,stageY,8,0,0);\n            else {','            {')
s=s.replace('gift&&mob!=star?(float)Math.toDegrees(Math.atan2(-(0-mob.getX()),8-mob.getZ())):0','0')
s=s.replace('stageY+(gift?5:13),z=gift?22:Math.max(26,cols*4.2)','stageY+13,z=Math.max(26,cols*4.2)').replace('gift?17:22','22')
s=s.replace('true,gift,star==null?new UUID(0,0):star.getUUID()','true,false,new UUID(0,0)')
p.write_text(s,encoding='utf-8')
p=base/'camera/CinemaClient.java';s=p.read_text(encoding='utf-8')
for x in ['import net.minecraft.client.resources.sounds.SimpleSoundInstance;\n','import net.minecraft.resources.ResourceLocation;\n','import net.minecraft.sounds.SoundEvent;\n','    private static SimpleSoundInstance music;\n']:
    s=s.replace(x,'')
s,n=re.subn(r'        if\(next.gift\(\)\) \{.*?\n        \}\n','',s,flags=re.S);assert n==1
s=s.replace('if(!next.active()){','if(!next.active()||next.gift()){').replace('next.gift()?"gift":"follow"','"follow"')
s=re.sub(r'^.*if\(disconnect&&music!=null\).*\n','',s,flags=re.M)
s,n=re.subn(r'        if\(!scene.gift\(\)\) \{(.*?)        \} else if\(scene.star\(\).equals\(id\)\) \{.*?\n        \}',r'        {\1        }',s,flags=re.S);assert n==1
p.write_text(s,encoding='utf-8')
edit(out/'source/build.gradle',"version = '1.3.0'","version = '1.3.1'")
edit(out/'source/build.gradle',"archiveVersion = '1.2.0'","archiveVersion = '1.2.1'")
for tree,old,new in [('main','1.3.0','1.3.1'),('camera','1.2.0','1.2.1')]:
    p=out/f'source/src/{tree}/resources/META-INF/mods.toml'
    edit(p,old,new);edit(p,'authors="Local custom build"','authors="KabuchaDev"\ndisplayURL="https://www.youtube.com/@KabuchaDev"')
edit(out/'source/src/main/resources/META-INF/mods.toml','A private arena','A free community arena')
for name in ['arena-status.mjs','audience.mjs','bridge.mjs','config.json','control.mjs','dispatch.mjs','router.mjs','test-audience.mjs','test-router.mjs']:
    copy(src/'interactions'/name,out/'interactions'/name)
for name in ['server.mjs','index.html']:copy(src/'test-panel'/name,out/'test-panel'/name)
copy(root/'outputs/Zeefski-JJK-3.2.1/rcon.mjs',out/'rcon.mjs')
for p in [out/'interactions/bridge.mjs',out/'interactions/dispatch.mjs',out/'test-panel/server.mjs']:
    edit(p,'../../Zeefski-JJK-3.2.1/','../')
edit(out/'test-panel/index.html','Thử như quà tặng · nhảy và nhạc Tết','Thử như quà tặng · chỉ hiện cảm ơn')
edit(out/'test-panel/index.html','Cảnh chào/nhảy dài 3 giây; nhạc quà dài 4 giây.','Cảnh chào khi follow dài 3 giây. Quà không dừng trận đấu và không phát nhạc.')
edit(out/'interactions/test-audience.mjs','gift celebrations','gift acknowledgements')
edit(out/'interactions/test-audience.mjs','Comments do not play gift scene','Comments do not carry a gift acknowledgement flag')
for p in [out/'interactions/bridge.mjs',out/'test-panel/server.mjs']:
    s=p.read_text(encoding='utf-8').replace('BAT-SERVER.cmd','START-SERVER.cmd');p.write_text(s,encoding='utf-8')
# Only operator-authored values, without the third-party generated comment template.
p=out/'client-config/shouldersurfing-client.toml';p.parent.mkdir(parents=True,exist_ok=True)
p.write_text('\n'.join(x for x in (src/'shouldersurfing-client.toml').read_text(encoding='utf-8').splitlines() if not x.lstrip().startswith('#'))+'\n',encoding='utf-8')
(out/'release.json').write_text(json.dumps({'name':'KabuchaDev JJK Arena','version':'1.3.1','author':'KabuchaDev','youtube':'https://www.youtube.com/@KabuchaDev','donationUrl':None,'price':0,'donorBenefits':[],'minecraft':'1.20.1','forge':'47.4.3','serverMod':'1.3.1','cameraMod':'1.2.1','giftCutscene':False,'bundledMusic':False},indent=2)+'\n',encoding='utf-8')
print(out)
