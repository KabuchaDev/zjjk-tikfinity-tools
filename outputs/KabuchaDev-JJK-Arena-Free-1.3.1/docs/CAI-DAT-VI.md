# Cài đặt KabuchaDev JJK Arena miễn phí

Tác giả: **KabuchaDev** — [YouTube](https://www.youtube.com/@KabuchaDev). Bản này có đủ tính năng, không cần trả tiền. Link ủng hộ để trống. Xem [chính sách ủng hộ](../SUPPORT.md) và [phạm vi quyền](RIGHTS.md).

## Chuẩn bị

1. Dùng Minecraft Java có bản quyền. Cài [Java 17 x64](https://adoptium.net/temurin/releases/?version=17) và [Node.js 24 LTS](https://nodejs.org/). Ghi lại đường dẫn đầy đủ đến `bin\java.exe` của Java 17.
2. Tải **Zeefski Jujutsu Kaisen Craft 3.2.1** từ [trang của tác giả](https://www.curseforge.com/minecraft/modpacks/zjjk/files/8509973). Cài bản client vào một hồ sơ launcher riêng. Lấy thêm server pack tương ứng `ZJJK_3.2.1server-pack.zip` ở phần file bổ sung; giải nén vào thư mục server mới, ví dụ `C:\Games\KabuchaServer`.
3. Hai phía phải dùng Minecraft **1.20.1 / Forge 47.4.3**, Jujutsu Craft **50.1**. Không dùng Paper, Fabric hay bản 1.21. Giữ nguyên bộ mod và cấu hình của modpack tương ứng.
4. Nếu server chưa có `libraries\net\minecraftforge\forge\1.20.1-47.4.3\win_args.txt`, tải installer **Forge 47.4.3** từ [trang Forge](https://files.minecraftforge.net/net/minecraftforge/forge/index_1.20.1.html), mở bằng Java 17, chọn **Install server** và trỏ vào thư mục server mới. Chưa chạy server lúc này.

Gói KabuchaDev không kèm Minecraft, Forge, bộ mod của người khác hoặc world cũ. Bạn tải các phần đó từ tác giả; không cần tự xây đấu trường bằng tay.

## Cài gói và mở sân lần đầu

1. Giải nén gói KabuchaDev vào thư mục cố định, chạy `SETUP.cmd`. Nhập đường dẫn server mới và đường dẫn `java.exe` của Java 17. Bộ cài từ chối thư mục đã có world để tránh sửa nhầm server đang chơi.
2. Bộ cài chép mod server do KabuchaDev cung cấp, đặt lõi mới mặc định 10.000 HP, tạo mật khẩu RCON ngẫu nhiên, cấu hình localhost và ghi `settings.local.json`. Các cổng mặc định là game **25565**, RCON **25575**. Không chia sẻ file cấu hình riêng này.
3. Đọc [EULA Minecraft](https://www.minecraft.net/en-us/eula). Nếu đồng ý, sửa `eula.txt` **trong thư mục server mới** thành `eula=true`. Bộ cài không thay bạn chấp nhận điều khoản và đặt file này về false lúc cài.
4. Chép `mods\client\jjkarena-camera-1.2.1.jar` vào thư mục `mods` của **hồ sơ game client**. Không chép jar client vào server. Nếu có bản cũ cùng mod ID trong bản cài mới, bỏ bản cũ trước để tránh trùng.
5. Muốn góc sau cao như bản demo: tự cài **Shoulder Surfing Reloaded cho Forge 1.20.1** từ tác giả vào client; tắt game, sao lưu cấu hình cũ rồi chép `client-config\shouldersurfing-client.toml` vào thư mục `config` của hồ sơ game. Nếu bản mod camera khác cấu trúc cấu hình, chỉnh các giá trị tương ứng trong phần cài đặt của mod đó.
6. Chạy `START-SERVER.cmd`, đợi dòng **Done**. Vào game → Multiplayer → `127.0.0.1:25565`.
7. Ở console server, nhập `op TenNhanVatMinecraft`, rồi `jjkarena build`. Đợi thông báo xây xong. Trong game nhập `/jjkarena watch`, sau đó `/jjkarena start`.
8. F5 đổi góc nhìn; **Ctrl trái** bật/tắt tự bám quân có giá cao nhất. Có thể dùng `/jjkarena autocamera on` hoặc `off`; `/jjkarena leave` để rời chế độ xem.
9. Chạy `START-PANEL.cmd`, mở **http://127.0.0.1:21215/**. Bấm bắt đầu trận rồi thử triệu hồi cả hai phe. Bảng này không cần kết nối TikTok.

Không dùng `rebuild` nếu muốn giữ phần xây dựng hiện có trong sân. `start` và `reset` dọn quân hiện tại; muốn dừng tạm thời dùng `pause`/`resume`.

## TikFinity → Streamer.bot → Minecraft

1. Cài và mở [TikFinity Desktop](https://tikfinity.zerody.one/) và [Streamer.bot](https://streamer.bot/). Bản tích hợp ban đầu dùng Streamer.bot 1.0.7.
2. Streamer.bot → **Servers/Clients → WebSocket Server**: địa chỉ **127.0.0.1**, cổng **8080**, bật Auto Start và Start Server. Bridge dùng kết nối nội bộ không xác thực; nếu bật authentication thì tắt cho server loopback này. Không mở cổng ra Internet, không đặt địa chỉ mọi giao diện mạng. [Tài liệu chính thức](https://docs.streamer.bot/api/websocket/guide/configuration).
3. Tạo một Action bật sẵn có tên đúng **JJK Arena - Summon**. Không cần Trigger.
4. Thêm Sub-Action **Core → System → Run a Program**. Mở file `STREAMERBOT-LOCAL.txt` do SETUP tạo rồi chép đúng các ô: Target/Command = đường dẫn Node; Arguments = đường dẫn `dispatch.mjs` có dấu ngoặc kép, tiếp theo là `%arenaJobId%`; Working Directory = thư mục `interactions`; Wait for Exit = **20 giây**. [Hướng dẫn Run a Program](https://docs.streamer.bot/api/sub-actions/core/system/run-a-program).
5. Trong TikFinity, kết nối LIVE của tài khoản bạn và bật/sử dụng local LIVE API **ws://127.0.0.1:21213/** theo [tài liệu TikFinity](https://tikfinity.zerody.one/tiktok/dapi). Nhà cung cấp có thể thay đổi quyền truy cập API; gói này không mở khóa hay sửa giới hạn sản phẩm.
6. Tắt các action TikFinity cũ đang gửi cùng một quà/comment vào server này để tránh gọi quân hai lần. Bridge đọc sự kiện API và dùng **một action trong Streamer.bot**, không cần một action TikFinity cho mỗi món quà.
7. Mở trận trong game, chạy `START-BRIDGE.cmd`, rồi `STATUS.cmd`. Phải thấy TikFinity, Streamer.bot kết nối và sân sẵn sàng. Trạng thái socket kết nối chưa chứng minh LIVE đã có sự kiện; hãy test comment thật và kiểm tra log.

Danh sách đầy đủ ở [MAPPINGS.md](MAPPINGS.md). Comment được cộng chung mọi người, đếm riêng từng từ. Quà nhận diện không phân biệt chữ hoa/thường; tên hoặc ID vùng khác cần cập nhật ở `interactions\config.json`, không tự suy theo số xu. Khởi động lại bridge sau khi sửa cấu hình.

## Sử dụng mỗi ngày

Mở server, đợi Done → vào game/watch/start trận → mở TikFinity và Streamer.bot → mở bridge → mở bảng test nếu cần. Mỗi console cần để mở trong lúc sử dụng.

Tắt bằng `STOP-SERVER.cmd`, hoặc gõ `stop` ở console server. Đợi lưu world xong mới tắt máy. File STOP cũng yêu cầu bridge dừng; đóng cửa sổ panel riêng. Không bấm dấu X console server khi đang lưu.

Nếu không ra quân, kiểm tra STATUS, trận có bị pause không, tên action, đường dẫn Node/dispatch, bridge log và sự kiện LIVE. Tối đa 15 quân sống và 60 quân trong hàng chờ server mỗi phe; lượng còn lại chờ ở bridge. `held` lớn hơn 0 nghĩa là kết quả gửi chưa chắc chắn: kiểm tra trong game và lịch sử Streamer.bot trước khi bù quân, tránh gọi trùng.

Quà chỉ triệu hồi/cảm ơn bằng chữ, **không nhảy, không dừng trận, không phát nhạc**. Follow mới vẫn có cảnh cúi chào 3 giây, cần quân có mặt và client camera mod. Refollow của cùng định danh không phát lại; dữ liệu chống lặp được giữ qua lần mở lại.

Không chạy đồng thời bản này và một bộ kết nối khác dùng chung cổng. Muốn đổi cổng lúc cài, xem lệnh nâng cao trong [hướng dẫn tiếng Anh](INSTALL-EN.md). Nếu chuyển thư mục sau khi cài, cập nhật cả `settings.local.json` và các đường dẫn action Streamer.bot.

Chỉ chia sẻ ZIP sạch ban đầu. Không nén lại thư mục đã chạy vì có thể chứa mật khẩu, đường dẫn máy và dữ liệu người xem. Link ủng hộ tác giả hiện chưa cấu hình và không liên quan cơ chế quà TikTok triệu hồi quân.
