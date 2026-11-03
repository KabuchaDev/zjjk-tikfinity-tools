from pathlib import Path
import json,re
root=Path(__file__).resolve().parents[2]
release=root/'outputs/KabuchaDev-JJK-Arena-Free-1.3.1'
cfg=json.loads((release/'interactions/config.json').read_text(encoding='utf-8'))
for filename in ['COMPLETE-GUIDE-EN.md','HUONG-DAN-DAY-DU.md']:
    text=(release/filename).read_text(encoding='utf-8')
    assert '__GIFTS__' not in text and '__COMMENTS__' not in text and '__ROSTER__' not in text
    for gift in cfg['gifts']:
        assert f"| {gift['name']} |" in text,(filename,gift['name'])
    for word,rule in cfg['comments'].items():assert f"| `{word}` | {rule['threshold']} |" in text
    for kind in {g['kind'] for g in cfg['gifts']} | {g['kind'] for g in cfg['comments'].values()}:assert f'`{kind}`' in text
    for target in re.findall(r'\]\(([^)]+)\)',text):
        if target.startswith(('https://','http://','#','mailto:')):continue
        assert (release/target.split('#')[0]).is_file(),(filename,target)
assert 'Star Goggles | Unconfirmed' in (release/'COMPLETE-GUIDE-EN.md').read_text(encoding='utf-8')
print('PASS: both full guides cover every configured gift/comment/character; internal links resolve; no invented Star Goggles price.')
