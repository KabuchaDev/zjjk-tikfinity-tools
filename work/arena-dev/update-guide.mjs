import fs from 'node:fs';
fs.writeFileSync('outputs/JJK-Arena/HUONG-DAN.md',`# Đấu trường Ronaldo vs Messi — JJK Arena 1.1

## Vào và thử trận

1. Bật server bằng **BAT-SERVER.cmd** trong thư mục **Zeefski-JJK-3.2.1** nếu server chưa chạy.
2. Mở **Zeefski Jujutsu Kaisen Craft ZJJK-3.2.1**, vào **127.0.0.1:25566**.
3. Gõ **/jjkarena watch** để vào khu quan sát. Gõ **/jjkarena leave** để trở về vị trí và chế độ chơi cũ.
4. Mở **BAT-DAU-TRAN.cmd**. Dùng **THU-QUAN-DO.cmd** / **THU-QUAN-XANH.cmd** để gọi quân thường; hoặc **THU-SUKUNA-DO.cmd** / **THU-GOJO-XANH.cmd** để thử boss.
5. **DON-VA-DAT-LAI.cmd** dọn quân, kỹ năng còn lại, khối lãnh địa tạm và hồi đầy lõi. Bấm bắt đầu để mở trận tiếp theo.

## Bản sửa 1.1

- Bỏ sát thương cố định và khóa AI của bản 1.0. Quân dùng chỉ số, sát thương, hồi phục, hoạt ảnh và kỹ năng của bộ mod JJK đang cài.
- Bộ điều khiển giao mục tiêu phe địch và hỗ trợ tiến quân từ điểm xuất hiện. AI gốc quyết định đánh, phòng thủ, hồi chiêu và triển khai lãnh địa.
- Sukuna và Gojo đã tự triển khai **Bành Trướng Lãnh Địa** trong phép thử. Miwa có **Giản Dịch Lãnh Địa** theo bộ kỹ năng gốc. Không phải nhân vật nào cũng có mọi loại lãnh địa; việc sử dụng vẫn phụ thuộc điều kiện của mod.
- Hai phe không còn được cân bằng máu/sát thương nhân tạo. Trong phép thử, chú linh có 30 máu, Miwa 60, Sukuna 800 và Gojo 850. Hiệu ứng và mod phụ có thể làm thay đổi chỉ số trong trận.
- Đã bỏ giới hạn tồn tại 3 phút của bản cũ. Vẫn giới hạn 15 quân gọi trực tiếp và 60 lượt chờ mỗi phe; các thực thể kỹ năng do JJK tạo được phép hoạt động.

## Cổng bảo vệ lõi

- Khi phe còn quân sống, cổng/vòm kính màu đóng quanh lõi; thanh trạng thái ghi **CONG DONG**.
- Lõi không mất máu khi phe còn quân phòng thủ, kể cả khi đối phương ở sát lõi hoặc dùng chiêu xuyên công trình.
- Quân cuối cùng bị tiêu diệt → cổng mở, đối phương mới có thể đánh lõi. Quân mới xuất hiện → cổng đóng lại.
- Quân triệu hồi phụ xác định được phe cũng được tính là phòng thủ; đạn và thực thể hiệu ứng lãnh địa không được tính là quân.
- Lõi có 1.000 máu. Khi không còn quân phòng thủ, quân địch đứng gần gây sát thương lõi theo thuộc tính tấn công hiện tại. Lõi là mục tiêu tính điểm của đấu trường, không phải mob JJK.
- Lõi hết máu → ghi điểm thắng, dọn trận; 15 giây sau tự mở trận mới.

## Bảo vệ map

Các khối công trình gốc được lưu thành bản chuẩn riêng. Lớp bảo vệ chặn phá, nổ và ghi đè trực tiếp lên các khối này, kể cả khi chiêu JJK thay khối bằng mã. Chỉ chiều không gian **jjkarena:battlefield** áp dụng luật này.

Lãnh địa vẫn có thể dựng khối tạm trong khoảng trống. Những khối đó được dọn khi đặt lại/kết thúc trận; nền sân, khán đài và công trình gốc được giữ nguyên. Vì nền gốc không thể bị thay thế, phần sàn lãnh địa có thể nhìn khác khi dùng ở thế giới bình thường; kỹ năng và hiệu ứng chiến đấu vẫn chạy.

Không chỉnh map bằng /fill hoặc WorldEdit khi lớp bảo vệ đang hoạt động. Các lệnh quản trị của đấu trường tự xử lý việc di chuyển lõi và xây lại.

## Kết nối bình luận TikFinity

1. Giữ server và Minecraft mở, vào đấu trường và bắt đầu trận.
2. Mở **TikFinity Desktop**, kết nối tài khoản với LIVE.
3. Mở **BAT-KET-NOI-TIKTOK.cmd**, giữ cửa sổ đó mở. Dòng **Da ket noi TikFinity** xác nhận kết nối ứng dụng trên máy.
4. Người xem bình luận đúng **ronaldo** → quân đỏ; **messi** → quân xanh.

Không phân biệt hoa/thường, bỏ khoảng trắng đầu/cuối; “messi ơi” không gọi quân. Mỗi tài khoản được gọi bằng bình luận một lần mỗi 8 giây, tính chung hai phe. Bộ nhận đọc Event API trên máy, không cần tạo riêng từng Action trong TikFinity. Không bật thêm Action trùng chức năng để tránh gọi hai lần.

Chỉ mở một bộ nhận. Nhấn **Ctrl+C** trong cửa sổ bộ nhận để dừng; việc này không tắt server. Cần thử bình luận từ tài khoản xem LIVE để xác nhận toàn bộ đường truyền thật.

Quà tặng, like và follow chưa được gán thêm trong bản này, chờ danh sách của bạn. bridge-config.json vẫn giữ giftMappings trống.

## Điều khiển

| Tệp | Tác dụng |
|---|---|
| BAT-DAU-TRAN.cmd | Dọn trận cũ, hồi lõi và bắt đầu |
| TAM-DUNG-TRAN.cmd | Tạm dừng, ngừng nhận quân mới |
| TIEP-TUC-TRAN.cmd | Tiếp tục |
| DON-VA-DAT-LAI.cmd | Dọn trận, trở về trạng thái chờ |
| THU-QUAN-DO.cmd / THU-QUAN-XANH.cmd | Gọi quân thường |
| THU-SUKUNA-DO.cmd / THU-GOJO-XANH.cmd | Gọi boss với kỹ năng gốc |
| TRANG-THAI.cmd | Xem máu lõi, quân, cổng và trạng thái |
| BAT-KET-NOI-TIKTOK.cmd | Mở bộ nhận bình luận |

Tạm dừng giữ bộ đếm và dừng tick các nhân vật sống; một số đạn/hoạt ảnh đã phát ra có thể còn chạy. Nếu cần dừng sạch mọi kỹ năng, dùng **DON-VA-DAT-LAI.cmd**.

Lệnh quản trị trong cửa sổ server (thêm dấu / khi gõ trong game, cần OP):

~~~text
jjkarena setspawn red -55.0 81 0.0
jjkarena setspawn blue 55.0 81 0.0
jjkarena setcore red -69.0 81 0.0
jjkarena setcore blue 69.0 81 0.0
jjkarena corehp 1000
jjkarena spawn red boss 1 Viewer
jjkarena spawn blue boss 1 Viewer
jjkarena clearscore
jjkarena auditterrain
jjkarena testprotection
~~~

Thay vị trí/máu lõi sẽ đặt lại trận. Giữ điểm xuất hiện nằm ngoài vòm bảo vệ lõi, trên mặt sân y=81. “elite” hiện dùng cùng nhân vật và chỉ số gốc như “basic”; việc chia cấp/quà sẽ được chỉnh theo danh sách sắp tới.

## Tắt và sao lưu

Dùng **TAT-SERVER.cmd** trong thư mục server JJK để lưu và tắt, rồi chạy **SAO-LUU.cmd** sau khi server đã dừng. Sau khi mở lại, map và điểm thắng còn nguyên; bấm **BAT-DAU-TRAN.cmd** để mở trận.

Đã sao lưu thế giới trước bản sửa tại **../Zeefski-JJK-3.2.1/backups/JJK-2026-10-05-093708.zip**. Mã nguồn và mod cũ nằm trong **backups/arena-before-native-combat.zip**. Không cài đồng thời hai bản jjkarena vào thư mục mods.

## Mã nguồn và kiểm tra

- Mod Forge: thư mục **source**; file cài server: **jjkarena-1.1.0.jar**. Client giữ nguyên ZJJK 3.2.1.
- **native-test-results.json**: AI gốc gây sát thương, cổng đóng/mở đúng, chặn ba cách phá khối trực tiếp.
- **domain-test-results.json**: trận Sukuna–Gojo tự dùng lãnh địa, kiểm tra bảo vệ map và dọn khối tạm.
- **bridge.log**: kết nối và lệnh bình luận. Bộ nhận không thay đổi trong lần sửa này.
- [Event API chính thức của TikFinity](https://tikfinity.zerody.one/tiktok/dapi).
`);
