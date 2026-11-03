# Minecraft nhận quà TikTok — Java 1.21.4

Bộ máy chủ riêng dành cho Minecraft Java 1.21.4 trên máy này. Cấu hình cho phép nhân vật offline từ TLauncher và chỉ nhận kết nối game tại `127.0.0.1`.

## Mở Minecraft

1. Mở `BAT-MAY-CHU.cmd`.
2. Lần đầu, đọc EULA Minecraft được hiển thị; chỉ nhập `DONG Y` nếu bạn chấp nhận. Chờ máy chủ hiện `Done`.
3. Trong TLauncher, chọn Minecraft **1.21.4**. Bản Forge/Fabric có mod thêm vật phẩm/quái riêng có thể yêu cầu bộ mod phía server tương ứng; hãy dùng bản 1.21.4 thường nếu gặp lỗi không tương thích.
4. Chọn **Multiplayer → Direct Connection**, nhập **127.0.0.1**.
5. Mở `THU-TRIEU-HOI.cmd` để chọn zombie, bộ xương, creeper hoặc nhện. Bước này thử đường truyền lệnh vào game, chưa cần gửi quà thật.

Đây là thế giới mới `world-tiktok`. Các thế giới có sẵn của bạn không được chuyển vào đây.

## Kết nối TikFinity

1. Đăng nhập https://tikfinity.zerody.one/tiktok/setup bằng tài khoản của bạn.
2. Trong **Setup → Minecraft Connection**, nhập các giá trị trong `THONG-TIN-KET-NOI.txt`. Giữ mật khẩu kết nối riêng tư.
3. Nhập đúng tên nhân vật Minecraft, rồi bấm **Test Connection**.
4. Vào **Actions & Events**, tạo Action có tên `Zombie`, chọn **Exec Minecraft Command** và dán lệnh tương ứng trong `LENH-QUAI.txt`.
5. Tạo Event nhận một loại quà cụ thể (ví dụ Hoa hồng), rồi liên kết với Action `Zombie`.
6. Làm tương tự với `Skeleton`, `Creeper`, `Spider`. Bạn chọn quà từ danh sách hiện có trong phòng LIVE; tên và danh sách quà có thể khác nhau theo tài khoản/khu vực.
7. Thêm URL **My Actions / Screen 1** của TikFinity vào LIVE Studio bằng nguồn **Link**. Không chia sẻ URL riêng của tài khoản.
8. Bấm nút thử của từng Action. Khi LIVE, kết nối TikFinity với đúng @TikTok đang phát và giữ TikFinity cùng máy chủ hoạt động.

Đặt thời gian chờ giữa các lần triệu hồi, ví dụ 1–2 giây khi thử. Kiểm tra riêng quà combo để tránh một combo bị tính lặp cả từng lần tăng và tổng kết. Chỉ nâng số lượng quái sau khi đã thử một quà → một quái.

## Tắt máy chủ

Trong cửa sổ máy chủ, nhập `stop` rồi Enter và đợi lưu xong trước khi đóng cửa sổ. Nếu máy chủ đang chạy nền, mở `TAT-MAY-CHU.cmd` để yêu cầu lưu thế giới và tắt.

## Thành phần và nguồn tải

- Paper **1.21.4 build 232**: https://papermc.io/downloads/paper — tệp tải đã được đối chiếu SHA-256 với API chính thức.
- ServerTap **0.6.1**: https://github.com/servertap-io/servertap/releases/tag/v0.6.1
- Java **21** có sẵn trong thư mục runtime của Minecraft trên máy này; đường dẫn lưu ở `settings.local.json`.
- EULA Minecraft: https://www.minecraft.net/eula

Minecraft dùng cổng `25565`; ServerTap dùng cổng `4567` và một khóa riêng đã tạo cho bộ này. Bộ cấu hình không mở cổng trên router hay sửa tường lửa Windows.

## Trạng thái bàn giao

Máy chủ và plugin đã chuẩn bị. Hãy xem `KIEM-TRA.txt` để biết chính xác những phần đã được kiểm tra và phần còn chờ đăng nhập/tương tác trực tiếp.
