# Server Zeefski Jujutsu Kaisen Craft 3.2.1

## Vào chơi

1. Mở `BAT-SERVER.cmd` và chờ dòng `Done`. Lần đầu có thể cần khoảng 1–2 phút; máy đang chạy nhiều ứng dụng có thể lâu hơn.
2. Trong TLauncher, chọn **Zeefski Jujutsu Kaisen Craft ZJJK-3.2.1**, rồi vào game.
3. Chọn **Multiplayer → Direct Connection** và nhập **127.0.0.1:25566**.
4. Nếu server đã được bật sẵn, chỉ cần vào game. Không mở thêm một bản server nữa.

Đây là Minecraft **1.20.1 / Forge 47.4.3 / Java 17**, không chọn Fabric 1.21.4 hoặc Forge 1.21.4 để vào server này. 86 tệp mod của server đã được đối chiếu SHA-256 và khớp với bản 3.2.1 hiện có trong TLauncher trên máy này.

## Tắt và mở lại

- Tắt: mở `TAT-SERVER.cmd`, hoặc gõ `stop` rồi Enter vào cửa sổ server.
- Chờ thông báo `All dimensions are saved` trong nhật ký và cửa sổ server kết thúc trước khi tắt máy.
- Mở lại: mở `BAT-SERVER.cmd`, chờ `Done`, rồi kết nối lại cùng địa chỉ.
- Thoát Minecraft hoặc TikFinity không tự tắt server.
- Nếu đang chạy bản server nền do trợ lý mở, dùng `TAT-SERVER.cmd` để tắt. Nhật ký nằm ở `server/console-output.log` và `server/logs/latest.log`.

## Sao lưu

Tắt server và chờ lưu xong, sau đó mở `SAO-LUU.cmd`. Bản sao thế giới và cấu hình sẽ nằm trong thư mục `backups`.

Để phục hồi, tắt server, đổi tên thư mục thế giới hiện tại để giữ lại nó, rồi giải nén bản sao lưu vào thư mục `server`. Không phục hồi khi server còn chạy. Bản sao lưu này chứa thế giới và cấu hình; giữ nguyên bộ mod và thư viện của server để dùng cùng nó.

## Cấu hình phù hợp với máy

- Máy có 16 GB RAM và CPU Intel Core i5-12450H.
- Server được cấp tối đa 4 GB, khởi đầu 1 GB.
- Khoảng cách nhìn 6 chunk, mô phỏng 4 chunk, tối đa 4 người.
- Khi vừa chơi vừa livestream, nên chỉ mở một cửa sổ Minecraft. Có thể đặt RAM cho game khoảng 4 GB trước, rồi tăng nếu thực tế cần và máy còn đủ bộ nhớ.
- Đây là server dùng riêng trên máy: `127.0.0.1`. Máy khác chưa thể kết nối qua mạng LAN hoặc Internet.
- Server Paper 1.21.4 trước đây nằm trong thư mục riêng. Khi chơi bộ mod này, nên tắt server cũ để dành RAM.

## Mod, thế giới và TikTok

- Server dùng gói server chính thức của tác giả, gồm 86 tệp mod, bao gồm Jujutsu Craft 50.1 và các mod đi kèm.
- Thế giới mới có tên thư mục `world-jujutsu`. Không đưa thế giới Minecraft 1.21.4 vào server 1.20.1 này.
- Giữ phiên bản modpack 3.2.1 ở cả game và server. Trước khi nâng cấp hoặc thêm mod, tắt server và sao lưu.
- Bộ kết nối ServerTap của server Paper cũ không chạy trực tiếp trên Forge này. **Quà TikTok chưa được nối vào server JJK.** Server đã có kênh RCON nội bộ để phục vụ điều khiển và việc nối thêm sau đó; không nhập cổng RCON vào ô ServerTap trong TikFinity vì đó là hai giao thức khác nhau.
- `settings.local.json` chứa thông tin điều khiển riêng của máy; không chia sẻ tệp này công khai.

## Nếu không vào được

- `Connection refused`: server chưa sẵn sàng, đã tắt, hoặc nhập thiếu `:25566`.
- Thông báo thiếu mod/sai phiên bản: chọn đúng profile **Zeefski Jujutsu Kaisen Craft ZJJK-3.2.1**; không dùng profile Minecraft thông thường.
- Server dừng: xem cuối `server/logs/latest.log` và thư mục `server/crash-reports` nếu có. Một số mod ghi cảnh báo khi khởi động; dòng `Done` và việc vào chơi thành công mới xác nhận server đã sẵn sàng.

## Nguồn

- [Bản modpack 3.2.1 và gói server đi kèm của tác giả](https://www.curseforge.com/minecraft/modpacks/zjjk/files/8509973).
- Server file ID: 8510031, tên `ZJJK_3.2.1server-pack.zip`.
- `source-and-versions.json`: nguồn, phiên bản và mã SHA-256 của gói tải.
- `test-results.json`: kết quả thử lệnh, triệu hồi, lưu và TPS.
- `ZJJK-3.2.1-client-import.zip` là gói manifest client của tác giả để nhập bằng launcher hỗ trợ CurseForge; nó không chứa toàn bộ mod và không cần chép vào server.
