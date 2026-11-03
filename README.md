# ZJJK & TikFinity Tools

A collection of Zeefski JJK server files, Minecraft livestream integrations, JJK arena source and supporting TikFinity tools.

**[Download data & runtime](https://github.com/KabuchaDev/zjjk-tikfinity-tools/releases/latest)** · **[Restoration guide](docs/PACKAGE-RESTORE.md)** · **[Original guide](outputs/JJK-Arena)**

## Tổng quan

Zeefski JJK dùng Minecraft Java 1.20.1 và Forge 47.4.3. Bộ Minecraft-TikTok 1.21.4 là thư mục riêng. Bộ cài TikFinity được đính kèm riêng trong Releases để lưu cùng bộ công cụ; TikFinity là phần mềm bên thứ ba, không phải phần mềm do repository này phát triển. Giữ các giấy phép và ghi công gốc.

## Nội dung dự án

| Thành phần | Vị trí |
|---|---|
| Zeefski JJK 3.2.1 server | [outputs/Zeefski-JJK-3.2.1](outputs/Zeefski-JJK-3.2.1) |
| JJK Arena add-on and controls | [outputs/JJK-Arena](outputs/JJK-Arena) |
| KabuchaDev JJK Arena Free package | [outputs/KabuchaDev-JJK-Arena-Free-1.3.1](outputs/KabuchaDev-JJK-Arena-Free-1.3.1) |
| Minecraft TikTok 1.21.4 setup | [outputs/Minecraft-TikTok-1.21.4](outputs/Minecraft-TikTok-1.21.4) |
| Arena development source | [work/arena-dev](work/arena-dev) |
| Original ZJJK server pack files | [work/zjjk](work/zjjk) |

## Tải về và khôi phục

1. Clone repository hoặc tải mã nguồn bằng nút **Code → Download ZIP**. Nên dùng đường dẫn ngắn như `C:\Projects\zjjk-tikfinity-tools`.
2. Mở **[Releases](https://github.com/KabuchaDev/zjjk-tikfinity-tools/releases/latest)** và tải đủ các phần `*-data.zip.partNNN` vào cùng một thư mục. Các phần không giải nén riêng lẻ.
3. Mở PowerShell tại thư mục mã nguồn và chạy:

```powershell
.\Restore-Data.ps1 -AssetsDirectory 'D:\Downloads\zjjk-tikfinity-tools-release'
.\Configure-Local.ps1
```

4. Đọc hướng dẫn gốc của từng ứng dụng trước khi khởi chạy. Công cụ khôi phục và cấu hình không tự chạy game, server hoặc bộ cài.

## Mã nguồn và tệp lớn

- Mã, cấu hình mẫu, tài liệu và tài sản nhỏ nằm trong repository.
- Mô hình, runtime, mod/server pack, thế giới và bản sao lưu lớn nằm trong **Releases**; `Restore-Data.ps1` đưa chúng về đúng vị trí.
- `package-manifest.json` ghi SHA-256 của từng tệp đóng gói; `SHA256SUMS.txt` trên Release kiểm tra các tệp tải về.
- Các mật khẩu/khóa cấu hình gốc đã chuyển thành mẫu; `Configure-Local.ps1` tạo cấu hình và khóa mới trên máy sử dụng. Không commit các cấu hình riêng vừa tạo.
- Cache, log và tệp tạm được bỏ qua có ghi trong `PACKAGING-REPORT.json`.

## Ghi công và phạm vi kiểm tra

Các thành phần bên thứ ba giữ giấy phép và tác giả gốc. Repository này không thay thế giấy phép Minecraft, modpack, Unity, mô hình AI hoặc phần mềm bên thứ ba. Xem các tệp LICENSE/THIRD-PARTY có sẵn trong từng thư mục.

Bản lưu ngày **03/11/2026** đã qua kiểm tra SHA-256/ZIP và thử công cụ khôi phục/cấu hình. Chưa chạy thử toàn bộ trên một máy sạch. Một số script lịch sử trong `work/` còn tham chiếu đường dẫn máy phát triển; chúng được giữ để tham khảo.
