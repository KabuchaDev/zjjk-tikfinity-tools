# KabuchaDev JJK Arena: hướng dẫn cài đặt và sử dụng từ đầu đến cuối

**Tác giả: KabuchaDev** · [Kênh YouTube](https://www.youtube.com/@KabuchaDev)

Áp dụng cho gói **Free Edition 1.3.1**, mod server **1.3.1**, mod camera **1.2.1**. Tài liệu cập nhật ngày **08/10/2026**.

Đây là đấu trường Minecraft Java gồm hai phe: **xanh — Messi** và **đỏ — Ronaldo**. Khán giả bình luận hoặc gửi quà để triệu hồi nhân vật Jujutsu Craft. Quân chiến đấu với đối phương và tấn công lõi; mỗi lõi mới có **10.000 HP**. Khi phe phòng thủ có quân, cổng bảo vệ lõi đóng và đẩy quân địch ở trong vùng lõi ra ngoài.

Gói phát hành miễn phí, có mã nguồn. Hiện chưa có link nhận ủng hộ; người sử dụng không phải trả tiền để mở bất kỳ chức năng nào. Khoản ủng hộ tác giả nếu bổ sung sau này không tạo quân hoặc cung cấp quyền lợi. Quà TikTok dùng cho tương tác trong trận là một cơ chế riêng.

**Lưu ý về bản thử nghiệm:** bản phát hành đã biên dịch và vượt qua các bài kiểm tra tự động/mô phỏng. Chưa thử lại toàn bộ bản cài mới trong một phiên game hoàn chỉnh. Hãy thử trên máy của bạn trước buổi LIVE chính thức; xem [chi tiết kiểm tra](docs/VALIDATION.md).

## 1. Bạn cần chuẩn bị những gì?

| Thành phần | Phiên bản/cách dùng |
| --- | --- |
| Hệ điều hành | Windows 10/11, phù hợp bộ công cụ hiện có |
| Minecraft | Java Edition **1.20.1**, tài khoản có bản quyền |
| Java | **17 x64**; ghi lại đường dẫn `bin\java.exe` |
| Forge | **47.4.3** cho Minecraft 1.20.1 |
| Modpack | **Zeefski Jujutsu Kaisen Craft 3.2.1**, client và server cùng bản |
| Mod nhân vật | **Jujutsu Craft 50.1**, có trong bộ modpack tương ứng |
| Node.js | **24 trở lên**; dùng để chạy bộ kết nối |
| TikFinity | Bản Desktop chạy cùng máy và kết nối LIVE của bạn |
| Streamer.bot | Cấu hình bên dưới dựa trên bản tích hợp **1.0.7** |
| Bộ nhớ | Server mặc định được cấp tối đa 6 GB; phải để RAM cho game, Windows và phần mềm stream |

Tải công cụ từ tác giả: [Java 17](https://adoptium.net/temurin/releases/?version=17), [Node.js](https://nodejs.org/), [Forge 1.20.1](https://files.minecraftforge.net/net/minecraftforge/forge/index_1.20.1.html), [modpack 3.2.1](https://www.curseforge.com/minecraft/modpacks/zjjk/files/8509973), [TikFinity](https://tikfinity.zerody.one/), [Streamer.bot](https://streamer.bot/).

Gói KabuchaDev chỉ chứa phần tự viết và cấu hình. Nó không chứa Minecraft, Forge, các mod nhân vật của bên thứ ba hoặc world của người phát triển. Vì thế ZIP nhỏ là bình thường; người nhận cần tải các phần phụ thuộc riêng. Không nhập ZIP KabuchaDev vào launcher như một modpack CurseForge.

## 2. Tạo các thư mục riêng

Nên tách ba nơi để không chép nhầm:

| Thư mục | Chứa gì? |
| --- | --- |
| Hồ sơ client trong launcher | Game bạn mở để xem trận, bộ mod Zeefski và mod camera |
| Ví dụ `C:\Games\KabuchaServer` | Server pack, thư mục `mods`, world sẽ được tạo và cấu hình server |
| Ví dụ `C:\Games\KabuchaArena` | Các file từ ZIP KabuchaDev: `SETUP.cmd`, `START-SERVER.cmd`, `interactions`, `docs`… |

Đây là đường dẫn ví dụ, bạn có thể chọn ổ đĩa khác. Sau khi cài, giữ các thư mục ở vị trí cố định. Luôn giải nén ZIP trước, không chạy file khi vẫn đang xem bên trong ZIP.

## 3. Cài client và server pack

1. Cài Java 17 và Node.js. Sau khi cài Node, mở lại cửa sổ terminal nếu có; lệnh `node --version` phải trả về phiên bản từ 24 trở lên.
2. Trên trang modpack của tác giả, chọn đúng **3.2.1**. Dùng launcher hỗ trợ modpack để cài bản client vào hồ sơ riêng. Nếu launcher có chức năng tìm kiếm modpack, tìm đúng tên Zeefski Jujutsu Kaisen Craft rồi chọn phiên bản đó. Không chọn phiên bản mới nhất một cách mặc định.
3. Tải server pack tương ứng ở phần file bổ sung: **`ZJJK_3.2.1server-pack.zip`**, file ID tham chiếu **8510031**. Giải nén vào thư mục server mới. Nếu trang tác giả không còn file này, không tự thay bằng bản bất kỳ; cần kiểm tra lại tương thích trước.
4. Kiểm tra thư mục server có `mods\JujutsuCraft-ver50.1-forge-1.20.1.jar` và có các thư mục cấu hình của modpack. Giữ bộ mod/cấu hình đi cùng nhau.
5. Kiểm tra file `libraries\net\minecraftforge\forge\1.20.1-47.4.3\win_args.txt`. Nếu chưa có, tải Forge installer **47.4.3**, chạy bằng Java 17, chọn **Install server**, trỏ vào thư mục server mới.
6. Chưa chạy server ở bước này. Bộ cài KabuchaDev cần một thư mục chưa có world. Không xóa world của server đang chơi để vượt bước kiểm tra này; hãy tạo thư mục mới.

Client/server của gói này dùng **Forge**, không dùng Paper, Fabric, Bedrock hoặc Minecraft 1.21. Các file `.jar` của mod khác nền tảng không thể chép lẫn tùy ý.

## 4. Cài KabuchaDev Arena

1. Giải nén ZIP KabuchaDev vào thư mục riêng, mở `SETUP.cmd`.
2. Khi được hỏi `Full path to a NEW extracted ZJJK 3.2.1 server folder`, nhập đường dẫn thư mục server vừa chuẩn bị.
3. Khi được hỏi `Full path to Java 17 java.exe`, nhập đường dẫn đầy đủ đến `java.exe` trong bộ Java 17 của bạn. Không chỉ nhập thư mục `bin` và không nhập `javaw.exe`.
4. Đợi thông báo **Setup complete**. Bộ cài tự chép mod server, tạo mật khẩu RCON mới và ghi hai file cục bộ: `settings.local.json`, `STREAMERBOT-LOCAL.txt`.
5. Đọc [EULA Minecraft](https://www.minecraft.net/en-us/eula). Nếu đồng ý, mở `eula.txt` trong **thư mục server mới**, sửa `eula=false` thành `eula=true` rồi lưu. Người nhận tự quyết định việc chấp nhận này; bộ cài không chấp nhận thay bạn.
6. Chép `mods\client\jjkarena-camera-1.2.1.jar` từ gói KabuchaDev vào thư mục `mods` của **client launcher**. Mod server đã được SETUP chép vào server; không chép cả hai jar vào cả hai nơi.
7. Nếu hồ sơ client mới đã có bản camera cũ cùng mod ID, thay bản cũ bằng bản hiện tại; đừng giữ nhiều phiên bản cùng lúc.

Bộ cài tạo cấu hình game **127.0.0.1:25565** và RCON **127.0.0.1:25575**, bật xác thực tài khoản Minecraft. Không công khai `settings.local.json` hoặc `server.properties` vì chúng chứa thông tin riêng. Nếu bị báo cổng đang dùng, đóng server khác dùng cùng cổng hoặc xem cách chọn cổng khác trong [hướng dẫn nâng cao](docs/INSTALL-EN.md).

## 5. Mở server và tạo đấu trường lần đầu

1. Chạy `START-SERVER.cmd`, giữ cửa sổ mở. Lần đầu có thể mất thời gian; đợi console hiện dòng **Done**.
2. Mở client modpack tương ứng → **Multiplayer → Direct Connection**, nhập `127.0.0.1:25565`.
3. Ở cửa sổ console server, gõ lệnh sau và thay tên ví dụ bằng tên nhân vật Minecraft thực tế:

```text
op TenNhanVatCuaBan
jjkarena build
```

4. Đợi thông báo đấu trường đã xây xong. Lệnh `build` tạo map bằng mã của mod; không cần tải thêm world.
5. Quay về khung chat trong game và nhập:

```text
/jjkarena watch
/jjkarena start
```

6. Bạn được chuyển vào chế độ quan sát. Kiểm tra hai thanh lõi mới đều có **10.000 HP**. Những lần mở server sau chỉ cần vào xem/bắt đầu trận, không cần xây lại.

**Phân biệt nơi nhập:** console server không dùng dấu `/`; chat trong game dùng `/`. Lệnh quản trị cần quyền OP. `/jjkarena start` mở trận mới và dọn quân hiện tại; nó không phải lệnh tiếp tục trận đang tạm dừng.

## 6. Camera và bảng thử quân

Nhấn **F5** để đổi góc nhìn; **Ctrl trái** bật/tắt tự bám nhân vật. Khi bật, camera ưu tiên nhân vật theo mức giá đã cấu hình trong mod; nếu cùng mức ưu tiên thì chọn nhân vật mới hơn. Giá camera không tự cập nhật theo giá quà TikTok từng khu vực. Có thể dùng `/jjkarena autocamera on` hoặc `/jjkarena autocamera off`. Nếu Ctrl trùng phím mod khác, chỉnh phím JJK Arena Camera trong Controls.

Muốn góc sau cao theo cấu hình đi kèm: tải **Shoulder Surfing Reloaded cho Forge 1.20.1** từ [tác giả](https://www.curseforge.com/minecraft/mc-mods/shoulder-surfing-reloaded), cài vào client. Tắt game, sao lưu cấu hình camera cũ nếu có, chép `client-config\shouldersurfing-client.toml` của gói vào `config` của client rồi mở game lại. Nếu dùng bản mod camera có cấu trúc cấu hình khác, chỉnh giá trị tương ứng qua phần cài đặt của nó.

Chạy `START-PANEL.cmd`, mở **http://127.0.0.1:21215/**. Giữ cửa sổ panel mở. Tại đây bạn chọn nhân vật, phe, số lượng và bấm triệu hồi. Có các nút bắt đầu, tạm dừng, tiếp tục, dọn quân, thử welcome/follow/cảm ơn. Không cần TikFinity để thử phần này.

Muốn thử riêng mà không nhận sự kiện LIVE, chạy `STOP-BRIDGE.cmd`. Đóng tab trình duyệt không tắt server hoặc panel đang chạy.

## 7. Cấu hình Streamer.bot — chỉ một Action

Bộ kết nối nhận các sự kiện từ TikFinity, phân loại bằng `interactions\config.json`, rồi yêu cầu **một Action trong Streamer.bot** thực thi lệnh. Bạn không phải tạo một Action TikFinity cho từng món quà.

### 7.1. Bật WebSocket nội bộ

Trong Streamer.bot, vào **Servers/Clients → WebSocket Server**. Chọn Address **127.0.0.1**, Port **8080**, Endpoint `/`, bật Auto Start và Start Server. Bridge này không thực hiện xác thực WebSocket, nên Authentication để tắt cho kết nối loopback này. Không dùng `0.0.0.0` hoặc mở cổng này ra Internet. [Tài liệu cấu hình chính thức](https://docs.streamer.bot/api/websocket/guide/configuration).

### 7.2. Tạo Action

Trong danh sách Actions, tạo Action bật sẵn tên đúng:

```text
JJK Arena - Summon
```

Không cần thêm Trigger. Tại Sub-Actions, thêm **Core → System → Run a Program**. Điền các giá trị đã sinh trong `STREAMERBOT-LOCAL.txt` của chính máy bạn:

| Ô trong Streamer.bot | Nội dung |
| --- | --- |
| Command / Target | Đường dẫn đầy đủ đến `node.exe` |
| Arguments | Đường dẫn đầy đủ đến `dispatch.mjs` trong dấu ngoặc kép, sau đó là `%arenaJobId%` |
| Working Directory | Đường dẫn thư mục `interactions` |
| Wait for Exit | **20**, đơn vị giây |

Ví dụ hình thức ô Arguments, cần thay đường dẫn nếu máy bạn khác:

```text
"C:\Games\KabuchaArena\interactions\dispatch.mjs" %arenaJobId%
```

Giữ nguyên `%arenaJobId%`. Không chạy thử Action rỗng bằng tay: bridge sẽ tạo và truyền mã tác vụ hợp lệ. Lưu cấu hình. [Tài liệu Run a Program](https://docs.streamer.bot/api/sub-actions/core/system/run-a-program).

## 8. Kết nối TikFinity với buổi LIVE

1. Mở **TikFinity Desktop trên cùng máy**. Kết nối tài khoản/buổi TikTok LIVE của bạn; xem [hướng dẫn LIVE API chính thức](https://tikfinity.zerody.one/tiktok/dapi).
2. Đảm bảo API nội bộ mà bộ kết nối đang cấu hình sử dụng hoạt động tại **ws://127.0.0.1:21213/**. Quyền truy cập/cách bật phụ thuộc phiên bản và nhà cung cấp.
3. Tắt các Action TikFinity cũ cũng gửi lệnh triệu hồi từ những sự kiện này, nếu có, để tránh hai đường gọi quân cùng lúc.
4. Giữ Streamer.bot và server mở. Trong game, trận phải đang chạy, không pause.
5. Chạy `START-BRIDGE.cmd`, sau đó `STATUS.cmd`. Cần thấy TikFinity và Streamer.bot kết nối, sân đang chạy. Có thể xem trạng thái tại **http://127.0.0.1:21214/status**.
6. Thử bình luận từ người xem thật. Kết nối WebSocket thành công mới chứng minh bridge kết nối ứng dụng; chưa chứng minh TikTok đang gửi sự kiện LIVE.

Gói này không mở khóa gói trả phí hoặc sửa giới hạn của TikFinity. Nó dùng API và một action Streamer.bot; việc API có sẵn hay không phụ thuộc dịch vụ.

## 9. Bình luận nào sẽ gọi nhân vật nào?

| Nội dung bình luận | Số bình luận cần cộng | Phe | Nhân vật xuất hiện |
| --- | ---: | --- | --- |
| `messi` | 3 | Xanh — Messi | 1 Miwa Kasumi |
| `ronaldo` | 3 | Đỏ — Ronaldo | 1 Miwa Kasumi |
| `m10` | 5 | Xanh — Messi | 1 Kugisaki Nobara |
| `cr7` | 5 | Đỏ — Ronaldo | 1 Kugisaki Nobara |

Đếm **cộng chung tất cả người xem**, riêng từng từ. Ví dụ ba người mỗi người gửi một comment `messi` sẽ gọi một Miwa phe xanh; một người gửi ba comment riêng cũng đạt mốc đó. Sau khi đủ mốc, phần dư được giữ để đếm tiếp. Sáu comment `messi` hợp lệ tương ứng hai lượt Miwa.

Chữ hoa/thường và khoảng trắng đầu/cuối không ảnh hưởng. Nội dung phải khớp cả từ: `MESSI` được nhận, còn `messi thắng đi` hoặc một comment `messi messi messi` không được tính thành ba lượt.

## 10. Bảng quà tặng → nhân vật → phe

**Một đơn vị quà trong bảng tương ứng một nhân vật** của phe được ghi. Combo được chốt theo số lượng khi sự kiện combo kết thúc; quân có thể xuất hiện lần lượt do giới hạn sân.

| Nhân vật được triệu hồi | Quà phe xanh — Messi | Xu tham khảo | Quà phe đỏ — Ronaldo | Xu tham khảo |
| --- | --- | ---: | --- | ---: |
| **Itadori Yuji (School)** | Rose | 1 | TikTok | 1 |
| **Inumaki Toge** | Finger Heart | 5 | Okay | 5 |
| **Panda** | Perfume | 20 | Little Kiss | 20 |
| **Todo Aoi** | Doughnut | 30 | Warm Cocoa | 30 |
| **Gojo Satoru (School)** | Little Crown | 99 | Paper Crane | 99 |
| **Hakari Kinji** | Hand Heart | 100 | Mini Star | 100 |
| **Zenin Maki (Culling Games)** | Chatting Popcorn | 149 | Fairy Hide | 149 |
| **Itadori Yuji (Shinjuku Showdown)** | Cheering Crab | 199 | Coffee Magic | 199 |
| **Gojo Satoru** | Candy Bouquet | 249 | Forest Elf | 249 |
| **Jogo** | Bowknot | 149 | Big Shout Out | 149 |
| **Sukuna (Itadori's Body)** | Music Bubbles | 249 | Star Goggles | Chưa xác nhận |
| **Sukuna (Megumi's Body)** | Rockstar | 299 | Love Call | 299 |
| **Sukuna (Heian Era)** | Juicy Cap | 349 | Craft Dreamer | 349 |

Giá xu là giá tham khảo từ cấu hình ban đầu, không phải bảng giá TikTok cập nhật. **Star Goggles chưa có giá xu được xác nhận trong cấu hình**, nhưng đã được ánh xạ tới Sukuna (Itadori's Body) phe đỏ. Giá, tên và khả năng xuất hiện của quà có thể khác theo khu vực.

Ví dụ: một **Rose** gọi một Yuji (School) phe xanh; một **TikTok** gọi cùng loại nhân vật ở phe đỏ. Combo năm **Doughnut** gọi năm Todo Aoi phe xanh, không phải một nhân vật mạnh hơn. **Bowknot** và **Chatting Popcorn** cùng giá tham khảo nhưng gọi hai nhân vật khác nhau.

Tên quà được so khớp không phân biệt chữ hoa/thường. Một số ID đã xác minh được ưu tiên; các món còn lại dựa vào tên/alias. Gói không tự nhận mọi tên dịch sang ngôn ngữ khác. Quà không có trong bảng chỉ hiện lời cảm ơn, không gọi quân. Quà trong bảng cũng chỉ hiện cảm ơn bằng chữ: **không dừng trận để nhảy/chào và không có nhạc Tết**.

## 11. Các tương tác khác và toàn bộ 15 nhân vật

| Tương tác | Hành vi mặc định |
| --- | --- |
| Người mới vào LIVE | Hiện lời chào trên thanh vật phẩm; có chống lặp |
| Follow mới | Cảnh cúi chào khoảng 3 giây nếu trên sân có quân; cần client camera mod |
| Unfollow rồi follow lại cùng định danh | Không lặp cảnh khi dữ liệu chống lặp vẫn được giữ |
| Like | **Chưa cấu hình triệu hồi** trong gói này |
| Bình luận không khớp bốn từ | Không triệu hồi |
| Quà chưa khớp bảng | Chỉ cảm ơn bằng chữ |

| Nhân vật | Mã dùng trong lệnh | Nguồn tương tác mặc định |
| --- | --- | --- |
| Miwa Kasumi | `miwa` | Bình luận |
| Kugisaki Nobara | `nobara` | Bình luận |
| Itadori Yuji (School) | `yuji_school` | Quà tặng |
| Inumaki Toge | `inumaki` | Quà tặng |
| Panda | `panda` | Quà tặng |
| Todo Aoi | `todo` | Quà tặng |
| Gojo Satoru (School) | `gojo_school` | Quà tặng |
| Hakari Kinji | `hakari` | Quà tặng |
| Zenin Maki (Culling Games) | `maki_culling` | Quà tặng |
| Itadori Yuji (Shinjuku Showdown) | `yuji_shinjuku` | Quà tặng |
| Gojo Satoru | `gojo` | Quà tặng |
| Jogo | `jogo` | Quà tặng |
| Sukuna (Itadori's Body) | `sukuna_itadori` | Quà tặng |
| Sukuna (Megumi's Body) | `sukuna_megumi` | Quà tặng |
| Sukuna (Heian Era) | `sukuna_heian` | Quà tặng |

Tất cả 15 loại đều có thể thử trong panel hoặc bằng lệnh OP. Ví dụ:

```text
/jjkarena spawn blue miwa 1 Test
/jjkarena spawn red nobara 1 Test
/jjkarena spawn blue gojo 1 Test
/jjkarena spawn red sukuna_heian 1 Test
/jjkarena spawn blue yuji_school 1 Test gift
```

`blue` là xanh; `red` là đỏ; số lượng mỗi lệnh từ 1 đến 15; `Test` là nhãn người triệu hồi. Cờ cuối `gift` chỉ để thử lời cảm ơn bằng chữ. Không có hoạt cảnh quà tặng trong bản này.

## 12. Quy trình mỗi lần livestream và cách tắt

**Mở:** START-SERVER → đợi Done → mở client/vào server → `/jjkarena watch` → `/jjkarena start` → mở Streamer.bot và TikFinity → START-BRIDGE → STATUS. Mở panel nếu cần. Chỉ chạy một bridge cho một bộ cấu hình.

**Tạm dừng:** dùng nút Tạm dừng ở panel hoặc `/jjkarena pause`. Tiếp tục bằng `/jjkarena resume`. Không dùng `start` nếu chỉ muốn tiếp tục vì nó dọn quân và bắt đầu lại.

**Kết thúc:** dừng nhận tương tác bằng `STOP-BRIDGE.cmd`; tắt server bằng `STOP-SERVER.cmd` hoặc gõ `stop` trong console server. Đợi lưu world xong và server thoát mới tắt máy. Đóng cửa sổ panel riêng. File STOP-SERVER cũng gửi yêu cầu dừng bridge; không cần lo nếu bridge đã dừng trước.

| File/lệnh | Mục đích |
| --- | --- |
| `SETUP.cmd` | Cài lần đầu trên server mới |
| `START-SERVER.cmd` | Mở server |
| `START-BRIDGE.cmd` | Nhận tương tác LIVE |
| `STOP-BRIDGE.cmd` | Dừng bộ nhận tương tác |
| `START-PANEL.cmd` | Mở dịch vụ bảng thử quân |
| `STATUS.cmd` | Kiểm tra kết nối, hàng chờ |
| `STOP-SERVER.cmd` | Dừng bridge và lưu/tắt server |
| `TEST-ROUTER.cmd` | Chạy kiểm tra logic sự kiện sau khi đã SETUP; không gửi quà thật |
| `/jjkarena status` | Trạng thái sân |
| `/jjkarena reset` | Dọn quân, hồi lõi, về trạng thái chờ |
| `/jjkarena corehp 10000` | Đặt máu tối đa lõi; reset trận |
| `/jjkarena leave` | Rời chế độ xem đấu trường |

Không dùng `/jjkarena rebuild` trong sân cần giữ nguyên: lệnh này cố ý xây lại các khối của đấu trường.

## 13. Đổi tên quà hoặc tùy chỉnh cấu hình

Dừng bridge, sao lưu `interactions\config.json`, mở bằng trình soạn thảo văn bản. Mỗi mục trong `gifts` có tên, phe và mã nhân vật. `coins` chỉ là thông tin cấu hình; **bridge không dùng cùng giá xu để suy ra cùng quà**.

Ví dụ mục Rose với tên thay thế:

```json
{
  "name": "Rose",
  "aliases": ["Hoa hồng"],
  "coins": 1,
  "side": "blue",
  "kind": "yuji_school",
  "id": "5655"
}
```

Nếu quà của khu vực bạn có tên khác, ghi nhận tên/ID thật từ sự kiện hoặc bridge log rồi bổ sung alias/ID phù hợp. Không bịa ID hoặc dùng ID một món khác. Sau khi sửa, lưu JSON hợp lệ rồi khởi động lại bridge. Thay mã nhân vật chỉ dùng những mã được liệt kê ở phần 11; thêm một mã lạ chưa đủ để mod hỗ trợ nhân vật mới.

Đổi giá tham khảo trong JSON không tự đổi mức ưu tiên camera: mức camera của bản 1.3.1 được khai báo trong mã Java. Chỉnh sửa cấu hình nên được thử trước khi LIVE.

## 14. Kiểm tra trước buổi LIVE và sửa lỗi thường gặp

Lần đầu hãy thử theo thứ tự: panel gọi quân hai phe → camera → bốn mốc comment → các quà qua chức năng test sự kiện của nhà cung cấp nếu có → welcome/follow → tắt/mở lại. Không cần mua quà để chạy các bài kiểm tra tự động trong gói. Kiểm tra mô phỏng không thay thế việc xác nhận sự kiện LIVE thật.

| Hiện tượng | Kiểm tra và xử lý |
| --- | --- |
| `node` không được nhận | Cài Node.js 24+, mở lại SETUP/terminal; kiểm tra `node --version` |
| SETUP báo thiếu Forge hoặc Jujutsu Craft | Kiểm tra đúng thư mục server, Forge 47.4.3 và server pack 3.2.1 |
| SETUP báo world đã có hoặc Already configured | Không cài đè/xóa world; dùng server mới hoặc chỉnh cấu hình riêng đã tồn tại |
| Server yêu cầu EULA | Đọc điều khoản, chỉ đặt eula=true nếu đồng ý, đúng file trong thư mục server |
| Server không chạy/Java sai phiên bản | Kiểm tra đường dẫn Java 17, log lỗi và RAM; không chỉ tăng RAM khi máy đã hết bộ nhớ |
| Không vào được server | Đợi Done, đúng cổng và modpack client; kiểm tra tài khoản Minecraft hợp lệ |
| Cảnh báo mod trùng/khác phiên bản | Chỉ một jar mỗi mod ID ở đúng phía; client/server cần cùng bộ modpack tương thích |
| Panel gọi được nhưng LIVE không ra quân | Kiểm tra TikFinity nhận sự kiện, Streamer.bot WebSocket/action, START-BRIDGE và STATUS |
| Streamer.bot không thực thi | Đúng tên `JJK Arena - Summon`, action bật, đúng Node/dispatch/Working Directory và `%arenaJobId%` |
| Comment không tăng đủ mốc | Phải đúng toàn bộ từ, đủ số comment riêng; đếm chung nhưng từng từ độc lập |
| Quà hiện cảm ơn nhưng không ra quân | Có thể quà chưa khớp tên/ID; kiểm tra bảng, log và alias; cũng kiểm tra trận đang chạy |
| Quân xuất hiện chậm | Tối đa 15 quân sống và 60 lượt chờ server mỗi phe; bridge giữ phần còn lại |
| `held` lớn hơn 0 | Kết quả gửi lệnh chưa chắc chắn; kiểm tra game và Action History trước khi bù quân, tránh gọi hai lần |
| Không có cảnh follow | Cần quân, chế độ spectator trong arena và camera jar trên client; cùng định danh đã follow không phát lại |
| Nhận một quà mà ra gấp đôi | Tắt action/bộ kết nối cũ trùng chức năng; chỉ giữ một đường xử lý sự kiện |
| `EADDRINUSE` hoặc port occupied | Một tiến trình khác dùng cổng; đóng đúng bản đó, không chạy hai bộ trên cùng cổng |

Nhật ký bridge nằm tại `interactions\bridge.log`; nhật ký server ở `logs` trong thư mục server. Không công khai nguyên file dữ liệu/log mà chưa kiểm tra vì có thể chứa thông tin người xem. Gói chỉ cấu hình localhost; không chuyển tiếp RCON, panel hoặc WebSocket ra Internet.

## 15. Chia sẻ, hỗ trợ và phạm vi sử dụng

Tác giả: [KabuchaDev trên YouTube](https://www.youtube.com/@KabuchaDev). Tất cả chức năng của bản miễn phí được cung cấp như nhau cho mọi người; link ủng hộ chưa cấu hình, không có đặc quyền đổi lại.

Chỉ chia sẻ **ZIP sạch phát hành ban đầu**. Thư mục sau khi chạy sẽ có mật khẩu, lịch sử người xem và đường dẫn riêng; đừng nén nguyên bản đang sử dụng để đăng công khai. Các mod, nhân vật và dịch vụ phụ thuộc thuộc tác giả tương ứng; gói này không khẳng định được họ bảo trợ hay cấp phép kiếm tiền. Xem [phạm vi quyền](docs/RIGHTS.md), [chính sách ủng hộ](SUPPORT.md) và [dữ liệu riêng](docs/PRIVACY.md).

Muốn đưa bản phát hành lên GitHub, xem [hướng dẫn GitHub riêng](docs/DANG-LEN-GITHUB.md). Tài liệu này có thể đăng dưới dạng Markdown trong repository để người nhận đọc trước khi tải gói.
