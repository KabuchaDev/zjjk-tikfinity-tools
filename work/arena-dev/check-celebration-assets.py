from pathlib import Path
import imageio_ffmpeg,subprocess,json,zipfile
root=Path(__file__).resolve().parents[2]
source=root/'outputs/JJK-Arena/source'
clip=source/'src/camera/resources/assets/jjkarenacamera/sounds/tet_gift.ogg'
p=subprocess.run([imageio_ffmpeg.get_ffmpeg_exe(),'-v','error','-i',str(clip),'-f','s16le','-ac','1','-ar','44100','pipe:1'],capture_output=True,check=True)
seconds=len(p.stdout)/2/44100
assert abs(seconds-4)<.02,seconds
jar=source/'build/libs/jjkarena-camera-1.2.0.jar'
with zipfile.ZipFile(jar) as z:
 assert 'assets/jjkarenacamera/sounds/tet_gift.ogg' in z.namelist()
 assert 'vn/local/jjkarena/camera/CinemaClient.class' in z.namelist()
 assert 'vn/local/jjkarena/net/CelebrationLink.class' in z.namelist()
 assert 'vn/local/jjkarena/JjkArena.class' not in z.namelist()
with zipfile.ZipFile(source/'build/libs/jjkarena-1.3.0.jar') as z:
 assert not any(n.startswith('vn/local/jjkarena/camera/') for n in z.namelist())
client=Path('C:/Users/PHAT/AppData/Roaming/.minecraft/versions/Zeefski Jujutsu Kaisen Craft ZJJK-3.2.1/mods/jjkarena-camera-1.2.0.jar')
# Camera source unchanged in the second build; compare the actual payload, ignoring ZIP timestamps.
with zipfile.ZipFile(jar) as a,zipfile.ZipFile(client) as b:
 for n in a.namelist(): assert a.read(n)==b.read(n),n
result={'pass':True,'audioSeconds':seconds,'sourceExcerpt':'00:34–00:38 of user-provided local MP4','cameraInstalled':True,'serverClientSeparation':True}
(root/'outputs/JJK-Arena/celebration-assets-results.json').write_text(json.dumps(result,indent=2),encoding='utf-8')
print('PASS audio duration, decodability, packaged resources, client installation, server/client class separation.')
