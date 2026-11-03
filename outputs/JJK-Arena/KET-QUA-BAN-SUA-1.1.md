# Kết quả bản sửa JJK Arena 1.1

- Đã cài trên server Forge 1.20.1 / Zeefski JJK 3.2.1. Không cần thay modpack client.
- Đã bỏ khóa AI, sức mạnh cố định, NoGravity và giới hạn sống 3 phút của bản cũ; khởi tạo nhân vật bằng quy trình spawn gốc của JJK.
- AI nhận mục tiêu phe địch; không cho đổi mục tiêu sang đồng đội. Kỹ năng, hồi máu, miễn nhiễm và hồi chiêu giữ theo bộ mod đang cài.
- Ba Miwa và ba chú linh đã tiến quân, dùng kỹ năng và gây sát thương thực.
- Đã thử lõi xanh khi còn quân phòng thủ: giữ 1.000 máu. Sau khi quân xanh cuối cùng chết: cổng mở, lõi giảm còn 964 máu sau ba đòn của chú linh.
- Sukuna và Gojo tự triển khai lãnh địa trong hai trận thử, không cần ép hiệu ứng bằng lệnh.
- Bản cài cuối đã chặn hơn 64.000 thao tác thay đổi khối trong trận thử. Đối chiếu ngay khi lãnh địa còn hoạt động: **129.695 khối gốc, 0 khối thay đổi**. Sau reset: **0 khối tạm**, các khối gốc vẫn nguyên vẹn.
- Chặn thành công thao tác ghi khối qua Level, ghi trực tiếp qua LevelChunk và destroyBlock. Bảo vệ chỉ giới hạn trong chiều đấu trường.
- Phép thử hai boss không có người chơi đạt 20 TPS, thời gian tick trung bình khoảng 24 ms. Đây chưa phải phép thử khi livestream và nhiều boss đồng thời.
- Server cuối cùng ở trạng thái chờ bắt đầu, hai lõi đầy máu, không còn quân thử. Bộ nhận TikFinity không thay đổi. Chưa gán thêm quà, like hoặc follow.

File bản cài: **jjkarena-1.1.0.jar**.

SHA-256: 9369769085C76F5BCAB8ADFDF099EE3F07232917A66C5A2E6AA2BD252E22AC1D

Log kiểm tra: **native-test-results.json**, **domain-test-results.json**.
