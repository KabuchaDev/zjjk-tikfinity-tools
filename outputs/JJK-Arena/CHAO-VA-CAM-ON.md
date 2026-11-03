# Chào người xem và cảm ơn — 07/10/2026

## Cách sử dụng

1. Thoát hẳn Minecraft rồi mở lại **Zeefski Jujutsu Kaisen Craft ZJJK-3.2.1** để nạp phần camera mới.
2. Vào server **127.0.0.1:25566**, gõ `/jjkarena watch`.
3. Mở TikFinity kết nối LIVE, Streamer.bot và **BAT-KET-NOI-TIKTOK.cmd** như trước. Nếu bộ kết nối đã chạy thì không cần mở thêm.

Không cần thêm action TikFinity. Bộ kết nối nhận người vào, follow và quà từ sự kiện TikFinity; các lượt triệu hồi vẫn đi qua action **JJK Arena - Summon** trong Streamer.bot.

## Hành vi

- Người mới vào: lời chào phía trên thanh vật phẩm. Hạn chế lặp khi cùng tài khoản ra/vào lại trong buổi LIVE.
- Follow: các quân còn sống tạm dừng, xếp hàng và cúi chào camera trong 3 giây. Tài khoản đã được xử lý được ghi nhớ cả sau khi khởi động lại; unfollow/follow không phát lại.
- Quà có trong 26 ánh xạ đã đặt: nhân vật đầu tiên của combo xuất hiện, đứng nhảy với kiểu chuyển động theo nhân vật. Các quân khác nhìn về nhân vật đó. Một combo chỉ có một cảnh; số quân vẫn theo số quà.
- Quà chưa có ánh xạ: chỉ hiện cảm ơn, không thêm quân hay cảnh nhảy.
- Nhạc quà lấy từ **0:34–0:38** trong video bạn cung cấp. Cảnh nhảy dài **3 giây**, nhạc dài **4 giây**. Nếu cảnh quà tiếp theo bắt đầu sớm, bản nhạc trước được dừng để không chồng tiếng.
- Hết cảnh, vị trí và chuyển động của quân được trả lại; không sửa máu, sát thương hay dữ liệu kỹ năng. Camera trở về mục tiêu/chế độ cũ, tự bám ưu tiên quân đắt nhất tiếp tục hoạt động. Bấm Ctrl trái trong cảnh sẽ áp dụng ngay khi cảnh kết thúc.
- Khi sân không có quân, follow chỉ hiện cảm ơn. Khi quân đang chờ chỗ trong đội, cảnh quà chờ đến lúc nhân vật thật sự xuất hiện.

## Tự thử, không tốn quà

Mở **BANG-THU-NHAN-VAT.cmd** hoặc http://127.0.0.1:21215/ rồi tải lại trang:

1. Gọi vài quân bình thường.
2. Bấm **Người mới vào** để thử dòng chào.
3. Bấm **Follow · cúi chào** để thử camera tổng và cúi chào. Mỗi lần bấm là một tài khoản thử riêng.
4. Chọn nhân vật, đánh dấu **Thử như quà tặng · nhảy và nhạc Tết**, bấm triệu hồi.
5. Bấm **Quà ngoài danh sách · chỉ cảm ơn** để kiểm tra không thêm quân.

Nếu không nghe nhạc, kiểm tra âm lượng Master của Minecraft. Camera/cúi chào/nhảy cần bản đồng hành **jjkarena-camera-1.2.0.jar** đã cài trong bộ mod; người xem qua LIVE không cần cài gì.

## Đã kiểm tra

- Build thành công server 1.3.0 và camera 1.2.0.
- 26 ánh xạ quà, chữ hoa/thường, combo, bộ đếm bình luận, bỏ follow lặp và quà lạ chỉ cảm ơn.
- Trên server: chặn sát thương trong cảnh, trả lại vị trí/máu/trạng thái kỹ năng, combo chỉ chạy một cảnh, dọn trận giữa cảnh không để lại quân bị đóng băng, map giữ nguyên và điểm thắng không đổi.
- Sự kiện mô phỏng qua bộ kết nối và action Streamer.bot thật đã gọi đúng Yuji School xanh và chạy cảnh quà; follow và quà lạ đúng hành vi.
- Cần xác nhận hình ảnh camera/động tác và âm thanh bằng Minecraft sau khi mở lại bộ mod. Kiểm tra sự kiện mô phỏng không thay thế kiểm tra một buổi LIVE thật.

Bản sao lưu trước thay đổi nằm trong `backups/celebrations-20261007`.
