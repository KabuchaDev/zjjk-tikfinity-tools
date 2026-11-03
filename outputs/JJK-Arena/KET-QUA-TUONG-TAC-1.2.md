# Kết quả cấu hình tương tác — JJK Arena 1.2

- Đã cài mod jjkarena-1.2.0 trên server; hỗ trợ 12 loại nhân vật theo danh sách.
- Đã cài Action **JJK Arena - Summon** trong Streamer.bot, giữ Action cũ và sao lưu cấu hình trước khi sửa.
- Đã thử đủ 4 mốc bình luận cộng chung và 20 món quà bằng sự kiện giả lập → bộ nhận → Action Streamer.bot thật → server Minecraft thật. Tất cả gọi đúng loại nhân vật và phe.
- Combo 17 Rose nhận đủ 17 lượt, không cộng các bước combo và không xử lý trùng sự kiện kết thúc.
- Đã kiểm tra bộ đếm riêng từng từ, lưu trạng thái, phân biệt quà cùng giá và chống trùng sự kiện.
- Kiểm tra sân sau thử nghiệm: không có khối gốc bị thay đổi. Đã dọn quân, đặt lại điểm và lưu thế giới.
- Bộ nối đang chạy nền, đã kết nối TikFinity và Streamer.bot. Server đang chờ mở trận; bộ đếm ban đầu bằng 0.

## Phần cần xác nhận trong LIVE

Chưa thử quà thật trên TikTok. Tên quà đã cấu hình theo danh sách; nếu TikTok gửi tên khác theo khu vực thì cần thêm mã quà thực tế. Bộ nối ghi **QUA CHUA KHOP** và lưu giftCatalog để đối chiếu. Kết nối TikFinity trên máy không tự chứng minh TikFinity đã kết nối một phiên LIVE.

Like và follow chưa có cấu hình vì chưa có yêu cầu nhân vật/điều kiện.

## Sử dụng

Xem **HUONG-DAN-TUONG-TAC.md**. Khi bộ nối đang chạy nền, dùng **XEM-BO-DEM-VA-KET-NOI.cmd** kiểm tra, **TAT-KET-NOI-TIKTOK.cmd** dừng. Lần sau mở **BAT-KET-NOI-TIKTOK.cmd** và giữ cửa sổ đó mở.

Bằng chứng chi tiết: **interactions/pipeline-test-results.json**, **interactions/roster-test-results.json**.