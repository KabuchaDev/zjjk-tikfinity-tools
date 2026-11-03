import fs from 'node:fs';
const cfg=JSON.parse(fs.readFileSync('outputs/JJK-Arena/interactions/config.json','utf8'));
const names={miwa:'Miwa',nobara:'Kugisaki Nobara',yuji_school:'Itadori Yuji (School)',inumaki:'Inumaki Toge',panda:'Panda',todo:'Todo Aoi',gojo_school:'Gojo Satoru (School)',hakari:'Hakari Kinji',maki_culling:'Zenin Maki (Culling Games)',yuji_shinjuku:'Itadori Yuji (Shinjuku Showdown)',gojo:'Gojo Satoru',jogo:'Jogo'};
const table=[];for(let i=0;i<cfg.gifts.length;i+=2){const blue=cfg.gifts[i],red=cfg.gifts[i+1];table.push(`| ${blue.coins} | ${blue.name} | ${red.name} | ${names[blue.kind]} |`);}
fs.writeFileSync('outputs/JJK-Arena/HUONG-DAN-TUONG-TAC.md',`# Bình luận và quà tặng — JJK Arena 1.2

## Cách mở mỗi buổi live

1. Bật server JJK, vào **127.0.0.1:25566**, gõ **/jjkarena watch**.
2. Mở **TikFinity Desktop** và kết nối với phiên LIVE.
3. Mở **Streamer.bot.exe** trong **C:\\Users\\PHAT\\Downloads\\Streamer.bot-x64-1.0.7**. Giữ ứng dụng mở.
4. Mở **BAT-KET-NOI-TIKTOK.cmd** trong thư mục đấu trường. Đợi hai dòng kết nối TikFinity và Streamer.bot thành công.
5. Mở **BAT-DAU-TRAN.cmd**. Người xem có thể bình luận và tặng quà.
6. Dùng **XEM-BO-DEM-VA-KET-NOI.cmd** để xem bốn bộ đếm, số quân chờ và tình trạng kết nối.

Không cần tạo thêm Action trong TikFinity. Streamer.bot dùng một Action **JJK Arena - Summon** nhận lệnh từ bộ xử lý trên máy. Bộ xử lý đọc Event API của TikFinity, đếm bình luận, phân biệt quà và quản lý hàng chờ; Streamer.bot thực thi lệnh triệu hồi.

Luồng: **TikFinity → bộ xử lý trên máy → Streamer.bot → server Minecraft**.

## Bình luận cộng chung

| Bình luận đúng từ | Đủ số bình luận | Phe | Nhân vật |
|---|---:|---|---|
| messi | 3 | Xanh | Miwa |
| ronaldo | 3 | Đỏ | Miwa |
| m10 | 5 | Xanh | Kugisaki Nobara |
| cr7 | 5 | Đỏ | Kugisaki Nobara |

- Cộng chung tất cả người xem; mỗi từ có bộ đếm riêng. Cùng một người có thể đóng góp nhiều bình luận nếu TikTok chuyển các bình luận đó đến TikFinity.
- Không phân biệt hoa/thường, bỏ khoảng trắng đầu/cuối. Một tin “messi messi messi” không tính là ba bình luận; cần gửi ba tin, mỗi tin chỉ có từ “messi”.
- Không còn thời gian chờ 8 giây của bản cũ. Sự kiện trùng mã do gửi lại qua kết nối sẽ chỉ tính một lần.
- Đủ mốc thì gọi một quân và bắt đầu lượt đếm tiếp theo. Bộ đếm còn dư được lưu khi đóng/mở bộ nhận và qua các trận.
- **DAT-LAI-BO-DEM-COMMENT.cmd** đưa cả bốn bộ đếm về 0; không xóa các lượt quà đã xếp hàng. Dùng khi muốn bắt đầu buổi live mới từ 0.

## Quà tặng: một quà = một nhân vật

| Xu theo danh sách của bạn | Quà phe xanh | Quà phe đỏ | Nhân vật |
|---:|---|---|---|
${table.join('\n')}

“Little Kiss” và “Candy Bouquet” là cách viết được dùng trong bảng; bộ xử lý cũng chấp nhận các cách viết “Littlle Kiss” và “Candy Bounquet” bạn đã gửi.

Các quà cùng giá vẫn được phân biệt theo tên/mã quà, không dùng số xu để quyết định phe. Giá ở bảng dùng để bạn đối chiếu; không tự chuyển một quà lạ sang cùng nhóm giá.

Quà combo được tính ở sự kiện kết thúc combo. Ví dụ combo 17 Rose tạo đủ 17 lượt Yuji xanh, không tạo 1+2+...+17. Bộ nhận chia thành các đợt nhỏ để server nhận đủ và không bị gọi dồn quá nhiều cùng lúc.

## Hàng chờ và kết nối

- Server giữ tối đa 15 quân gọi trực tiếp và 60 lượt chờ mỗi phe. Lượt chưa gửi được tiếp tục nằm ở hàng chờ trên máy, không bị cắt xuống 15.
- Khi server chưa chạy, trận đang dừng hoặc hàng chờ trong game đầy, các lượt hợp lệ chờ đến khi có chỗ. Có thể có độ trễ khi quá nhiều quà.
- Tắt bộ nhận bằng **TAT-KET-NOI-TIKTOK.cmd** hoặc Ctrl+C trong cửa sổ bộ nhận. Bộ đếm và lượt chờ được lưu lại.
- Chỉ chạy một bộ nhận. Nút BAT-KET-NOI-TIKTOK đã được chuyển sang bản mới; không chạy riêng tệp tikfinity-bridge.mjs cũ.
- Tắt/loại bỏ Action TikFinity cũ nếu nó cũng triệu hồi quân cho cùng một sự kiện, để tránh gọi hai lần.
- Nếu “Luot can kiem tra” lớn hơn 0, có lệnh chưa xác định được đã chạy hay chưa. Bộ nhận giữ bản ghi để kiểm tra, không tự gửi lại và gây trùng. Dữ liệu nằm trong **interactions/state.json**, mục **held**; đừng tự xóa tệp này khi đang live.

## Nhận diện quà thực tế

Bảng đã cấu hình theo tên bạn cung cấp và các tên tương ứng. Mã quà thực tế có thể khác theo khu vực/phiên bản TikTok. Bộ nhận lưu tên và mã nhận được vào **interactions/state.json**, mục **giftCatalog**.

Nếu nhận được quà nhưng chưa khớp tên, cửa sổ bộ nhận ghi **QUA CHUA KHOP** kèm mã/tên/giá; sự kiện đó chưa được triệu hồi. Dùng đúng mã đó điền trường **id** vào dòng quà tương ứng trong **interactions/config.json**, rồi tắt/mở lại bộ nhận. Không đoán mã quà. Danh sách hiện được thử bằng dữ liệu mô phỏng; cần đối chiếu một lần khi nhận quà thật.

Like và follow chưa được gán vì bạn chưa chọn nhân vật/điều kiện cho hai loại này.

## Các tệp quan trọng

- **interactions/config.json**: toàn bộ bảng quà và mốc bình luận.
- **interactions/state.json**: bộ đếm, lượt chờ và lịch sử chống trùng của buổi live.
- **interactions/bridge.log**: kết nối và kết quả nhận quân.
- **interactions/backups**: bản sao cấu hình Action trước khi sửa Streamer.bot.
- **jjkarena-1.2.0.jar**: mod server hỗ trợ đủ 12 nhân vật. Các luật AI gốc, lãnh địa, cổng và bảo vệ map từ bản 1.1 được giữ.

Tham khảo: [TikFinity Event API](https://tikfinity.zerody.one/tiktok/dapi), [Streamer.bot GetActions / DoAction](https://docs.streamer.bot/api/websocket/requests).
`);
console.log('Wrote interaction guide and complete mapping table.');
