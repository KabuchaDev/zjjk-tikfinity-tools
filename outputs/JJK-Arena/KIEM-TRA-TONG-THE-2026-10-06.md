# Kết quả kiểm tra ngày 06/10/2026

Đã cài bản hỗ trợ đấu trường **1.2.3**. Lần kiểm tra cuối: server và trận đang chạy, bộ nối đã kết nối TikFinity và Streamer.bot, hai lõi đều **10.000 máu**, điểm thắng cũ **đỏ 2 : xanh 6** được giữ nguyên. Bộ đếm bình luận, hàng chờ và danh sách cần kiểm tra đều trống.

## Những lỗi đã sửa

- Quân đang chờ được giữ lại khi trận tự chuyển sang vòng tiếp theo.
- Quân không giữ các hiệu ứng như đường chém hoặc sét làm mục tiêu đối phương. Panda vẫn được nhận diện là quân hợp lệ.
- Quân bị hất ra mép sân được đưa về trước khi lọt vào vùng không còn được cập nhật, tránh đứng im ngoài sân. Khả năng nhảy và bay trong sân vẫn được giữ.

## Các kiểm tra đã đạt

- 4 mốc bình luận: `messi` ×3 và `ronaldo` ×3 gọi Miwa; `m10` ×5 và `cr7` ×5 gọi Nobara. Cộng chung mọi người, tính riêng từng từ.
- 26 loại quà, mỗi loại thử ba biến thể tên viết hoa, viết thường hoặc khoảng trắng: đúng nhân vật, đúng phe qua Action thật của Streamer.bot và thực thể thật trong Minecraft.
- Combo 17 quà, chống nhận trùng và lưu bộ đếm/hàng chờ qua lần khởi động lại bộ nối.
- Thử 200 quà: 15 quân hoạt động, 60 quân chờ trong game và 125 quân chờ bên ngoài được tính đủ. Hàng chờ đầy của một phe không chặn phe còn lại.
- Khi trận dừng hoặc tạm dừng, lượt triệu hồi được giữ để gửi sau. Chuyển vòng tự động giữ đủ 14 quân chưa xuất hiện trong phép thử.
- Cổng cả hai phe đẩy quân địch ra, bảo vệ máu lõi và mở khi hết quân phòng thủ; quân thân thiện không bị đẩy.
- Đã quan sát bành trướng lãnh địa do AI gốc kích hoạt trong phép thử có kiểm soát giữa Sukuna Heian và Gojo. Quân thử được hạ máu và cho bất tử để kéo dài quan sát; không ép chạy chiêu lãnh địa. Quân thử đã được dọn. Thời điểm dùng chiêu vẫn phụ thuộc AI và điều kiện của mod.
- 129.695 khối gốc của sân được kiểm tra bảo vệ; khối tạm được dọn khi đặt lại trận.
- Nút mở server đã thử cả khi server tắt lẫn khi đang chạy: mở được trận và không khởi động trùng server.

Kết quả chi tiết: `interactions/gift-case-sukuna-test-results.json`, `delivery-resilience-test-results.json`, `gate-exit-test-results.json`, `boundary-test-results.json`, `controlled-domain-test-results.json`.

## Khởi động và sử dụng

1. Nhấp đúp **BAT-SERVER-VA-DAU-TRUONG.cmd** và đợi báo sẵn sàng.
2. Vào Minecraft bằng modpack hiện tại, kết nối **127.0.0.1:25566**, gõ `/jjkarena watch`.
3. Để nhận tương tác: mở TikFinity và kết nối đúng LIVE, mở Streamer.bot, rồi mở **BAT-KET-NOI-TIKTOK.cmd**.
4. Xem tình trạng bằng **XEM-BO-DEM-VA-KET-NOI.cmd**. Thử quân riêng bằng **BANG-THU-NHAN-VAT.cmd**.
5. Khi kết thúc, mở **TAT-KET-NOI-TIKTOK.cmd**, rồi **TAT-SERVER.cmd** để lưu và tắt.

Nút mở server không dọn trận đang chạy. Nếu trận đang tạm dừng, dùng **TIEP-TUC-TRAN.cmd**. Các thao tác đặt lại trận thủ công hoặc tắt server vẫn xóa quân và hàng chờ trong game; hàng chờ bên ngoài do bộ nối lưu riêng.

## Phạm vi xác nhận

Các phép thử toàn bộ danh sách dùng sự kiện mô phỏng, đi qua Streamer.bot và Minecraft thật. Không phải đã trả tiền gửi đủ 26 loại quà trên LIVE. Hoa hồng đã được bạn xác nhận hoạt động trong lần thử LIVE trước đó.

Đã gắn ID quà từ danh mục TikFinity cho Rose (5655), TikTok (5269), Finger Heart (5487); ID được ưu tiên hơn tên hiển thị. 23 loại còn lại nhận diện bằng tên và tên thay thế đã cấu hình. Cần kiểm chứng sự kiện thực nếu TikTok đổi tên hoặc danh mục quà của tài khoản.

Kết nối với ứng dụng TikFinity không tự chứng minh tài khoản đang kết nối LIVE. Không thể bảo đảm tuyệt đối trước mất mạng hoặc sự kiện TikTok không chuyển đến bộ nối; các lượt đã nhận được xử lý theo cơ chế lưu hàng chờ và chống trùng đã kiểm tra.

Đã có bản sao lưu thế giới trước kiểm tra tại `../Zeefski-JJK-3.2.1/backups/JJK-2026-10-06-141411.zip`.
