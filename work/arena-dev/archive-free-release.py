from pathlib import Path
import zipfile,hashlib,json,shutil,re
root=Path(__file__).resolve().parents[2]
out=root/'outputs/KabuchaDev-JJK-Arena-Free-1.3.1'
evidence=root/'work/arena-dev/free-release-build-artifacts'
evidence.mkdir(exist_ok=True)
# Move only verified children of the release source into non-distributed evidence.
for name in ['build','.gradle']:
    p=(out/'source'/name).resolve()
    assert p.is_relative_to((out/'source').resolve()) and p.name==name
    if p.exists():
        dest=evidence/name
        assert not dest.exists(),dest
        shutil.move(str(p),str(dest))
private=json.loads((root/'outputs/Zeefski-JJK-3.2.1/settings.local.json').read_text(encoding='utf-8-sig'))
secret=private['rconPassword'].encode()
forbidden_names={'settings.local.json','state.json','state-test.json','server.properties','STREAMERBOT-LOCAL.txt','eula.txt','level.dat','ops.json','whitelist.json','usercache.json'}
forbidden_parts={'build','.gradle','world','backups','spool','portraits','node_modules','libraries','com/example'}
forbidden_suffixes={'.ogg','.mp3','.mp4','.jpg','.jpeg','.png','.jfif','.mca','.dat','.log','.exe','.dll'}
files=sorted(p for p in out.rglob('*') if p.is_file())
def check_bytes(data,label):
    for bad in [secret,b'C:\\Users\\PHAT',b'C:/Users/PHAT',b'Dogalive67',b'tet_gift',b'ArenaPortraits']:
        assert bad not in data,(label,'private or excluded marker')
for p in files:
    rel=p.relative_to(out)
    assert p.name not in forbidden_names,rel
    assert not any(x in forbidden_parts for x in rel.parts),rel
    assert p.suffix.lower() not in forbidden_suffixes,rel
    data=p.read_bytes();check_bytes(data,str(rel))
    if p.suffix=='.jar':
        assert str(rel).replace('\\','/') in ['mods/server/jjkarena-1.3.1.jar','mods/client/jjkarena-camera-1.2.1.jar']
        with zipfile.ZipFile(p) as z:
            assert z.testzip() is None
            for n in z.namelist():
                assert not any(x in n for x in ['portraits','tet_gift','ArenaPortraits','com/example/'])
                assert not n.endswith(('.ogg','.mp3','.jpg','.png'))
                if n.endswith('.class'):assert n.startswith('vn/local/jjkarena/'),n
                check_bytes(z.read(n),n)
            assert b'KabuchaDev' in z.read('META-INF/mods.toml')
            assert 'META-INF/LICENSE' in z.namelist()
            if 'server' in rel.parts:assert not any('/camera/' in n for n in z.namelist())
src=(out/'source/src/main/java/vn/local/jjkarena/JjkArena.java').read_text(encoding='utf-8')
assert src.count('celebration.start(')==1 and 'theo dõi! ❤");celebration.start(cast());' in src
assert re.search(r'if\(req.gift\)\{actionbar\([^\n]+;\}',src)
assert 'maxHealth = 10000' in (out/'source/src/main/java/vn/local/jjkarena/ArenaState.java').read_text()
cinema=(out/'source/src/main/java/vn/local/jjkarena/camera/CinemaClient.java').read_text()
assert 'getSoundManager' not in cinema and 'next.gift()' in cinema and 'scene.star().equals(id)' not in cinema
manifest=out/'SHA256SUMS.txt'
manifest.write_text(''.join(hashlib.sha256(p.read_bytes()).hexdigest()+'  '+p.relative_to(out).as_posix()+'\n' for p in files if p!=manifest),encoding='utf-8')
archive=out.with_suffix('.zip') # version suffix is not a file extension; override below
archive=out.parent/(out.name+'.zip')
with zipfile.ZipFile(archive,'w',zipfile.ZIP_DEFLATED,compresslevel=9) as z:
    for p in sorted(out.rglob('*')):
        if p.is_file():z.write(p,out.name+'/'+p.relative_to(out).as_posix())
with zipfile.ZipFile(archive) as z:
    assert z.testzip() is None
    for line in manifest.read_text().splitlines():
        digest,name=line.split('  ',1)
        assert hashlib.sha256(z.read(out.name+'/'+name)).hexdigest()==digest
digest=hashlib.sha256(archive.read_bytes()).hexdigest()
archive.with_suffix('.zip.sha256').write_text(digest+'  '+archive.name+'\n')
print(json.dumps({'archive':str(archive),'bytes':archive.stat().st_size,'files':sum(p.is_file() for p in out.rglob('*')),'sha256':digest,'audit':'passed'},indent=2))
