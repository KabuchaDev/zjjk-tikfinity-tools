# Interaction mappings / Bảng tương tác

Comments match the entire trimmed message, case-insensitively. Each word has its own counter shared across viewers. / Đếm chung, riêng từng từ, nội dung comment phải khớp cả từ sau khi bỏ khoảng trắng đầu/cuối.

| Comment | Count / Số lần | Team / Phe | Character ID |
| --- | ---: | --- | --- |
| messi | 3 | blue | `miwa` |
| ronaldo | 3 | red | `miwa` |
| m10 | 5 | blue | `nobara` |
| cr7 | 5 | red | `nobara` |

Each listed gift item spawns one character; a completed combo uses its repeat count. Names are normalized; IDs are used only where explicitly configured. Unknown gifts get a text thank-you without units. / Mỗi đơn vị quà ra một nhân vật; combo chốt theo số lượng; quà chưa có chỉ hiện cảm ơn.

| Gift | Coins (reference only) | Team | Character ID |
| --- | ---: | --- | --- |
| Rose | 1 | blue | `yuji_school` |
| TikTok | 1 | red | `yuji_school` |
| Finger Heart | 5 | blue | `inumaki` |
| Okay | 5 | red | `inumaki` |
| Perfume | 20 | blue | `panda` |
| Little Kiss | 20 | red | `panda` |
| Doughnut | 30 | blue | `todo` |
| Warm Cocoa | 30 | red | `todo` |
| Little Crown | 99 | blue | `gojo_school` |
| Paper Crane | 99 | red | `gojo_school` |
| Hand Heart | 100 | blue | `hakari` |
| Mini Star | 100 | red | `hakari` |
| Chatting Popcorn | 149 | blue | `maki_culling` |
| Fairy Hide | 149 | red | `maki_culling` |
| Cheering Crab | 199 | blue | `yuji_shinjuku` |
| Coffee Magic | 199 | red | `yuji_shinjuku` |
| Candy Bouquet | 249 | blue | `gojo` |
| Forest Elf | 249 | red | `gojo` |
| Bowknot | 149 | blue | `jogo` |
| Big Shout Out | 149 | red | `jogo` |
| Music Bubbles | 249 | blue | `sukuna_itadori` |
| Star Goggles | not specified | red | `sukuna_itadori` |
| Rockstar | 299 | blue | `sukuna_megumi` |
| Love Call | 299 | red | `sukuna_megumi` |
| Juicy Cap | 349 | blue | `sukuna_heian` |
| Craft Dreamer | 349 | red | `sukuna_heian` |

`blue` = Messi / xanh. `red` = Ronaldo / đỏ. Prices are the original operator-provided reference, not a live price list. Gift availability, localized names, IDs and prices can change. / Giá chỉ tham khảo từ cấu hình ban đầu, không phải bảng giá cập nhật.

Edit `interactions/config.json`, then restart the bridge. Keep distinct same-price gifts separate. Add observed aliases/IDs rather than guessing from price. / Sửa tên thay thế hoặc ID đã quan sát, không đoán theo số xu.

Manual tests / Lệnh thử (OP):

```text
/jjkarena spawn blue miwa 1 Test
/jjkarena spawn red sukuna_heian 1 Test
/jjkarena spawn blue yuji_school 1 Test gift
```

The final `gift` flag only enables the text acknowledgement; it never starts a scene or music in this edition. Like events have no spawn rule in this supplied configuration. / Cờ gift chỉ hiện cảm ơn; lượt like chưa có quy tắc triệu hồi trong cấu hình này.
