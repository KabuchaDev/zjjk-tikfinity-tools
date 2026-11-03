# Góc nhìn theo nhân vật

Đã cài server hỗ trợ đấu trường 1.2.5 và mod camera 1.1.0 trong modpack **Zeefski Jujutsu Kaisen Craft ZJJK-3.2.1**.

## Tự bám quân bằng Ctrl trái

- Mở lại game sau khi cập nhật mod, vào server, gõ `/jjkarena watch` và chọn góc phía sau bằng **F5**.
- Bấm **Ctrl trái** một lần để bật. Dòng thông báo **Tự bám camera: BẬT** xuất hiện phía trên thanh vật phẩm.
- Camera chọn quân có giá quà cao nhất đang sống, tính cả hai phe. Quân mới chỉ giành camera khi đắt hơn, hoặc bằng giá nhưng xuất hiện sau.
- Khi quân đang theo dõi chết, tự chọn quân đắt nhất còn sống. Nếu sân trống thì đợi đợt triệu hồi tiếp theo.
- Bấm **Ctrl trái** lần nữa để tắt và trở về camera tự do. Giữ phím không bật/tắt liên tục; phím không kích hoạt khi đang mở chat/menu.
- Chế độ bật riêng cho từng người quan sát. Giữ qua các vòng đấu; tự tắt khi thoát server hoặc rời chế độ khán giả trong đấu trường. Mặc định tắt khi mới đăng nhập.
- Khi đang bật, dùng **Ctrl trái** để thoát tự bám; chỉ nhấn Shift sẽ được tự bám lại. Khi đã tắt, có thể nhập vai thủ công như trước.

Giá ưu tiên theo danh sách quà hiện tại, tính **mỗi quân**, không nhân số lượng combo: Miwa/Nobara 0; Yuji School 1; Inumaki 5; Panda 20; Todo 30; Gojo School 99; Hakari 100; Maki Culling/Jogo 149; Yuji Shinjuku 199; Gojo/Sukuna Itadori 249; Sukuna Megumi 299; Sukuna Heian 349 xu. Quân thử dùng cùng mức giá nhân vật; các nút thử `basic/elite` là 0, `boss` là 249.

Lệnh thay thế: `/jjkarena autocamera on`, `/jjkarena autocamera off`. Xem tình trạng cá nhân bằng `/jjkarena camera`; xem lựa chọn ưu tiên bằng `/jjkarena autocamera status`. Có thể đổi phím trong **Options → Controls → Key Binds → JJK Arena Camera**.

Đã kiểm tra 15 tình huống chọn quân thật trên server, gồm giá cao/thấp, bằng giá giữa hai phe, quân chết và đặt lại trận. Kết quả: `autocamera-test-results.json`. Cần mở game bản mới để kiểm chứng thao tác Ctrl trái trên máy người chơi; chưa có kết quả quan sát phím trực tiếp trong lần cập nhật này.

## Cách dùng

1. Mở lại Minecraft bằng đúng modpack trên để nạp bản camera mới.
2. Vào server `127.0.0.1:25566`, gõ `/jjkarena watch`.
3. Trong chế độ khán giả, nhấp chuột trái vào quân muốn nhập vai để theo dõi.
4. Bấm **F5** đến góc nhìn từ phía sau. Camera được nâng thêm **2 khối** so với thiết lập cũ.
5. Giữ **Shift** để rời góc nhìn của quân và tự bay quan sát.

Shoulder Surfing đang dùng phím **Page Up / Page Down** để nâng hoặc hạ camera. Độ cao hiện tại là 2; giới hạn trên là 3. Camera vẫn có thể thu lại khi vướng tường hoặc vật cản.

## Những thay đổi

- Giữ nguyên tốc độ chuyển camera 0,25, độ trễ kéo theo bằng 0 ở cả ba trục và chế độ bám hướng nhân vật.
- Tắt tự đưa camera xuống giữa khi nhân vật nhìn xuống để duy trì góc cao.
- Góc nhìn phía sau mặc định dùng Shoulder Surfing, tránh nhầm với góc phía sau nguyên bản không có độ cao đã chỉnh.
- Khi quân được đưa về từ mép sân/dưới sàn hoặc bị đẩy ra khỏi cổng bảo vệ, server di chuyển người đang theo dõi cùng lúc, giữ nguyên nhân vật đang được theo dõi và cập nhật vùng tải.
- Mod camera xóa vị trí nội suy cũ đúng lúc dịch chuyển; chuyển động thường vẫn do Minecraft và Shoulder Surfing xử lý như trước.
- Lệnh `/jjkarena camera` cho biết đang theo dõi ai và server có nhận ra mod camera hay không (`companion=true`).

## Kiểm tra

- Biên dịch server và mod camera thành công.
- Đã thử đưa quân qua bốn mép sân và xuống dưới sàn; tất cả trở về đúng điểm xuất hiện. Bay cao trong sân vẫn được giữ.
- Đã thử cổng hai phe: đẩy quân địch ra, giữ quân thân thiện và bảo vệ lõi đúng.
- Nhật ký Minecraft ghi nhận camera áp dụng dịch chuyển tại cổng lúc **21:58:14** và về điểm xuất hiện lúc **21:59:52 ngày 06/10/2026**, trên bản camera 1.0.0. Bản 1.0.1 bổ sung metadata và kiểm tra góc quay không hợp lệ; đã biên dịch và cài xong, cần lần mở game mới để nạp.
- Chưa xác nhận bằng quan sát hình ảnh vì công cụ chụp cửa sổ Windows hết thời gian chờ. Chi tiết máy đọc được nằm trong `camera-test-results.json`.

Thiết lập cũ và các bản mod trước được giữ trong `backups/camera-20261006/`. Lõi vẫn 10.000 máu; bộ đếm tương tác được giữ nguyên qua lần bảo trì.
