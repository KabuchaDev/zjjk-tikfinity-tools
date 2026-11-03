# KabuchaDev JJK Arena — Free Edition 1.3.1

**Author: KabuchaDev** · [YouTube](https://www.youtube.com/@KabuchaDev)

A free Java Edition arena mod and local livestream bridge. Build a red/blue battlefield, spawn Jujutsu Craft characters, protect each team's core, and follow the action with a spectator camera.

**Hướng dẫn đầy đủ (có bảng 26 quà và 15 nhân vật):** [Đọc tại đây](HUONG-DAN-DAY-DU.md).

**Complete English guide with all gifts and characters:** [Read the full tutorial](COMPLETE-GUIDE-EN.md).

**Start here:** [English installation](docs/INSTALL-EN.md) · [Hướng dẫn tiếng Việt](docs/CAI-DAT-VI.md)

This download contains only the original arena mod, companion camera mod, source, bridge, operator configuration and documentation. Install Minecraft and external mods separately from their authors. It is **not** a complete Minecraft client/server or an official Minecraft product. It is not approved by or associated with Mojang or Microsoft, the Jujutsu Craft/modpack authors, TikTok, TikFinity, or Streamer.bot.

## Features

- Generated arena in its own dimension; 10,000 HP cores; terrain protection and core gates that push attackers outside when defenders spawn.
- Native Jujutsu Craft attacks, skills and domains; shared comment counters and 26 gift mappings; gift-name matching ignores case and surrounding whitespace.
- Gifts summon units and show a short thank-you message. **No gift cutscene, dancing, battle freeze, Tet music or bundled audio.**
- New arrivals get an actionbar welcome. A new follow retains the three-second bow scene; repeated follow/unfollow does not replay it for the same recognized identity.
- Companion camera follows recovered/teleported units; left Ctrl toggles automatic following of the most expensive surviving unit. Optional Shoulder Surfing configuration is supplied.
- Local test panel, bounded in-game queues, persistent event deduplication and cautious handling of uncertain deliveries.

## Requirements and contents

Windows 10/11, legitimate Minecraft Java Edition **1.20.1**, Java **17**, Forge **47.4.3**, matching **Zeefski Jujutsu Kaisen Craft 3.2.1** client and server packs with **Jujutsu Craft 50.1**. Use Node.js **24+** for the bridge. The existing integration was developed with Streamer.bot **1.0.7**. Allow sufficient RAM for both game and server; default server allocation is 6 GB, configurable locally. This is a compatibility target, not a guarantee of performance on every PC.

| Folder | Purpose |
| --- | --- |
| `mods/server` | Original arena server jar 1.3.1 |
| `mods/client` | Original camera client jar 1.2.1 |
| `source` | Java, original resources and Gradle build files |
| `interactions` | TikFinity → Streamer.bot → Minecraft bridge, mappings and tests |
| `test-panel` | Local troop and audience test interface |
| `client-config` | Optional operator-authored camera settings |
| `docs` | Setup, usage, gift table, rights, privacy and validation notes |

## Free release and support

Every included feature, source file and update released under this edition is available without payment. There is no activation key, subscription, donor rank, early access, priority support, private build or other benefit in return for a project contribution.

**Support link: not configured.** The YouTube link is an author/channel link, not a payment requirement. See [SUPPORT.md](SUPPORT.md) and [third-party terms](docs/RIGHTS.md). An optional contribution label does not itself establish permission under Minecraft's rules.

Only redistribute the clean release ZIP, not an installed copy. Local setup creates credentials, viewer state and machine-specific files which must remain private. See [privacy](docs/PRIVACY.md).
