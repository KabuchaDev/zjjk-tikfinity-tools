from pathlib import Path
import shutil,json
root=Path(__file__).resolve().parents[2]
out=root/'outputs/KabuchaDev-JJK-Arena-Free-1.3.1'
for kind,name in [('server','jjkarena-1.3.1.jar'),('client','jjkarena-camera-1.2.1.jar')]:
    dest=out/'mods'/kind/name;dest.parent.mkdir(parents=True,exist_ok=True)
    shutil.copy2(out/'source/build/libs'/name,dest)
for file,mode in [('START-SERVER','server'),('START-BRIDGE','bridge'),('START-PANEL','panel'),('STOP-SERVER','stop'),('STATUS','status'),('TEST-ROUTER','test')]:
    (out/(file+'.cmd')).write_bytes(('@echo off\r\npowershell.exe -NoProfile -ExecutionPolicy Bypass -File "%~dp0launch.ps1" -Mode '+mode+'\r\npause\r\n').encode())
license='''MIT License

Copyright (c) 2026 KabuchaDev

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
'''
(out/'LICENSE').write_text(license,encoding='utf-8')
# Include the license in both ready-to-use jars without any external content.
import zipfile
for jar in (out/'mods').rglob('*.jar'):
    with zipfile.ZipFile(jar,'a',zipfile.ZIP_DEFLATED) as z:z.writestr('META-INF/LICENSE',license)
config=json.loads((out/'interactions/config.json').read_text(encoding='utf-8'))
lines=['# Interaction mappings / Bảng tương tác','','Comments match the entire trimmed message, case-insensitively. Each word has its own counter shared across viewers. / Đếm chung, riêng từng từ, nội dung comment phải khớp cả từ sau khi bỏ khoảng trắng đầu/cuối.','','| Comment | Count / Số lần | Team / Phe | Character ID |','| --- | ---: | --- | --- |']
for word,r in config['comments'].items():lines.append(f"| {word} | {r['threshold']} | {r['side']} | `{r['kind']}` |")
lines+=['','Each listed gift item spawns one character; a completed combo uses its repeat count. Names are normalized; IDs are used only where explicitly configured. Unknown gifts get a text thank-you without units. / Mỗi đơn vị quà ra một nhân vật; combo chốt theo số lượng; quà chưa có chỉ hiện cảm ơn.','','| Gift | Coins (reference only) | Team | Character ID |','| --- | ---: | --- | --- |']
for g in config['gifts']:lines.append(f"| {g['name']} | {g.get('coins','not specified')} | {g['side']} | `{g['kind']}` |")
lines+=['','`blue` = Messi / xanh. `red` = Ronaldo / đỏ. Prices are the original operator-provided reference, not a live price list. Gift availability, localized names, IDs and prices can change. / Giá chỉ tham khảo từ cấu hình ban đầu, không phải bảng giá cập nhật.','','Edit `interactions/config.json`, then restart the bridge. Keep distinct same-price gifts separate. Add observed aliases/IDs rather than guessing from price. / Sửa tên thay thế hoặc ID đã quan sát, không đoán theo số xu.','','Manual tests / Lệnh thử (OP):','','```text','/jjkarena spawn blue miwa 1 Test','/jjkarena spawn red sukuna_heian 1 Test','/jjkarena spawn blue yuji_school 1 Test gift','```','','The final `gift` flag only enables the text acknowledgement; it never starts a scene or music in this edition. Like events have no spawn rule in this supplied configuration. / Cờ gift chỉ hiện cảm ơn; lượt like chưa có quy tắc triệu hồi trong cấu hình này.']
(out/'docs/MAPPINGS.md').write_text('\n'.join(lines)+'\n',encoding='utf-8')
p=out/'interactions/control.mjs';p.write_text(p.read_text(encoding='utf-8').replace('BAT-KET-NOI-TIKTOK.cmd','START-BRIDGE.cmd'),encoding='utf-8')
p=out/'interactions/arena-status.mjs';p.write_text(p.read_text(encoding='utf-8').replace('mo TIEP-TUC-TRAN.cmd','bam Tiep tuc trong bang dieu khien').replace('mo BAT-DAU-TRAN.cmd','bam Bat dau trong bang dieu khien'),encoding='utf-8')
print('Added ready jars, launchers, license and mappings.')
