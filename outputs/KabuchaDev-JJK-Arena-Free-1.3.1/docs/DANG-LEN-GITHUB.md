# Đăng KabuchaDev JJK Arena lên GitHub

Tài liệu dành cho người phát hành; cập nhật 08/10/2026. Đây là hướng dẫn thao tác, không tự đăng gì lên tài khoản của bạn.

## 1. ZIP này có được GitHub chấp nhận không?

GitHub có thể chứa ZIP. Bản gói trước khi bổ sung tài liệu này có dung lượng 175.034 byte (khoảng 175 KB), thấp hơn nhiều so với giới hạn **25 MiB/file** khi upload qua giao diện repository. Vì vậy chưa có cơ sở kết luận lỗi bạn gặp là do dung lượng hoặc đuôi ZIP. Quyền ghi, quy tắc repository, trình duyệt hoặc nơi bạn đang thả file cũng có thể ảnh hưởng. [Giới hạn và cách upload của GitHub](https://docs.github.com/en/repositories/working-with-files/managing-files/adding-a-file-to-a-repository).

Đối với gói để người khác tải về sử dụng, nên đính kèm ở **Releases**. Mã nguồn và bài hướng dẫn nên xuất hiện trực tiếp trong repository để người khác đọc được. Upload một ZIP vào tab Code không tự giải nén mã nguồn bên trong.

## 2. Tạo repository nếu chưa có

1. Đăng nhập GitHub bằng tài khoản của bạn.
2. Mở trang [tạo repository](https://github.com/new), chọn tên ví dụ `kabuchadev-jjk-arena`.
3. Chọn Public nếu muốn ai cũng có thể tải và xem mã nguồn. Chọn khởi tạo README để repository có commit/nhánh ban đầu, rồi Create repository.
4. Dùng repository bạn sở hữu hoặc có quyền ghi. Tên YouTube KabuchaDev không tự chứng minh tên tài khoản GitHub của bạn cũng như vậy. [Hướng dẫn tạo repository](https://docs.github.com/en/repositories/creating-and-managing-repositories/creating-a-new-repository).

## 3. Đưa hướng dẫn và mã nguồn lên tab Code

Giải nén **ZIP sạch chưa chạy SETUP**, mở repository → **Add file → Upload files**. Upload các file/thư mục bên trong gói, giữ cấu trúc `README.md`, `HUONG-DAN-DAY-DU.md`, `source`, `docs`, `interactions`, `test-panel` và các file liên quan ở gốc. Đừng lồng thêm một thư mục ngoài nếu muốn README hiện ngay.

Có thể bỏ hai jar trong `mods` khỏi phần mã nguồn nếu chỉ muốn phân phối binary qua Release; người tải để chạy nên lấy ZIP đính kèm Release. Nếu trình duyệt không nhận kéo thư mục, dùng GitHub Desktop để thêm nội dung vào repository cục bộ, commit rồi push. Không giải nén nhầm server modpack của bên thứ ba vào repository này.

Nhập commit message, hoàn tất Commit changes/Propose changes tùy quyền và quy tắc nhánh. Nếu nhánh được bảo vệ, làm theo luồng nhánh/PR của repository thay vì tắt bảo vệ. GitHub cho upload tối đa 100 file mỗi lần qua giao diện web. [Tài liệu upload](https://docs.github.com/en/repositories/working-with-files/managing-files/adding-a-file-to-a-repository).

Không upload thư mục server đang chạy, world, `settings.local.json`, `STREAMERBOT-LOCAL.txt`, `server.properties`, logs, state hoặc spool. Chỉ lấy nội dung gói phát hành sạch.

## 4. Tạo Release và đính kèm ZIP

1. Trong repository, mở **Releases → Draft a new release** hoặc nút tạo release đầu tiên.
2. Chọn/tạo tag **`v1.3.1`** nếu tag đó chưa dùng; chọn nhánh chứa mã nguồn và tài liệu vừa đăng. Nếu đã phát hành tag này rồi, cập nhật release hiện có khi được phép hoặc tạo tag mới cho tài liệu sửa đổi, không tùy tiện ghi đè lịch sử phát hành.
3. Đặt tiêu đề **KabuchaDev JJK Arena 1.3.1 — Public Beta** và dùng nội dung ở `RELEASE-NOTES.md` đi kèm làm mô tả.
4. Ở vùng **đính kèm binary/file**, chọn ZIP **KabuchaDev-JJK-Arena-Free-1.3.1.zip** từ máy; có thể đính kèm thêm file `.zip.sha256`. Đừng thả ZIP vào ô mô tả hoặc chỉ dán đường dẫn máy tính.
5. Đợi tải xong, chọn **This is a pre-release** vì chưa kiểm tra lại đầy đủ bản cài mới trong game. Lưu draft để xem lại, rồi Publish release khi bạn sẵn sàng công khai.

Các bước trên dựa trên [hướng dẫn quản lý Releases của GitHub](https://docs.github.com/en/repositories/releasing-projects-on-github/managing-releases-in-a-repository).

Nếu hộp chọn file không tìm thấy gói, mở File Explorer tới thư mục `outputs` nơi gói được tạo, chọn đúng file ZIP. Link file trong cuộc trò chuyện không phải URL tải công khai; dán link đó vào GitHub không tải nội dung lên GitHub.

## 5. Kiểm tra sau khi đăng

Mở trang release, kiểm tra phần **Assets** có đúng tên ZIP KabuchaDev. GitHub cũng tự sinh mục “Source code (zip)”; mục đó không nhất thiết chứa bản binary và cấu trúc cài đặt giống gói ZIP bạn đính kèm. Hướng dẫn người dùng chọn đúng ZIP KabuchaDev trong Assets.

Tải thử file đó về một thư mục mới, mở ZIP và kiểm tra có `SETUP.cmd`, `HUONG-DAN-DAY-DU.md` và cả hai jar trong `mods/server`, `mods/client`. Chia sẻ URL trang release thực tế; không có URL cụ thể nào được tạo sẵn bởi tài liệu này.

## 6. Nếu vẫn không upload được

| Hiện tượng | Việc nên làm |
| --- | --- |
| Không có nút tạo release | Kiểm tra đúng repository và quyền ghi; repository mới cần nhánh/commit ban đầu |
| Báo file quá lớn | Kiểm tra đang chọn ZIP KabuchaDev nhỏ, không phải server/modpack nhiều GB |
| Báo thiếu tag/target | Tạo/chọn tag và nhánh có commit |
| Thả ZIP vào ô mô tả không được | Dùng vùng binary attachments phía dưới phần mô tả |
| File cùng tên đã tồn tại | Xem asset hiện có; với release sửa được, thay có chủ đích hoặc đặt bản mới; release immutable có giới hạn sửa asset |
| Báo ruleset/protected branch/permission | Làm theo quyền/quy tắc repository; không mặc định coi đây là lỗi file |
| Báo phát hiện secret | Dừng, kiểm tra lại đang dùng ZIP sạch; không bỏ qua cảnh báo để upload dữ liệu riêng |
| Báo network/upload failed | Giữ bản gốc, tải lại trang hoặc thử trình duyệt khác; nếu vẫn lỗi cần nguyên văn thông báo để chẩn đoán |

Chưa có nguyên văn lỗi upload của bạn nên tài liệu không khẳng định một nguyên nhân cụ thể. Việc tạo ZIP và hướng dẫn chưa đồng nghĩa dự án đã được đăng lên GitHub.
