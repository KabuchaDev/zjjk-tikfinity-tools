from pathlib import Path
import json
root=Path(__file__).resolve().parents[2]
release=root/'outputs/KabuchaDev-JJK-Arena-Free-1.3.1'
config=json.loads((release/'interactions/config.json').read_text(encoding='utf-8'))
names={'miwa':'Miwa Kasumi','nobara':'Kugisaki Nobara','yuji_school':'Itadori Yuji (School)','inumaki':'Inumaki Toge','panda':'Panda','todo':'Todo Aoi','gojo_school':'Gojo Satoru (School)','hakari':'Hakari Kinji','maki_culling':'Zenin Maki (Culling Games)','yuji_shinjuku':'Itadori Yuji (Shinjuku Showdown)','gojo':'Gojo Satoru','jogo':'Jogo','sukuna_itadori':"Sukuna (Itadori's Body)",'sukuna_megumi':"Sukuna (Megumi's Body)",'sukuna_heian':'Sukuna (Heian Era)'}
comments=['| Exact comment | Comments required | Team | Summoned character |','| --- | ---: | --- | --- |']
for word,r in config['comments'].items():comments.append(f"| `{word}` | {r['threshold']} | {'Blue — Messi' if r['side']=='blue' else 'Red — Ronaldo'} | 1 × {names[r['kind']]} |")
gifts=['| Summoned character | Blue team gift — Messi | Reference coins | Red team gift — Ronaldo | Reference coins |','| --- | --- | ---: | --- | ---: |']
for kind in dict.fromkeys(g['kind'] for g in config['gifts']):
 group={g['side']:g for g in config['gifts'] if g['kind']==kind};assert set(group)=={'blue','red'}
 b,r=group['blue'],group['red'];gifts.append(f"| **{names[kind]}** | {b['name']} | {b.get('coins','Unconfirmed')} | {r['name']} | {r.get('coins','Unconfirmed')} |")
roster=['| Character | Command ID | Default interaction |','| --- | --- | --- |']
for kind,name in names.items():roster.append(f"| {name} | `{kind}` | {'Comments' if kind in ['miwa','nobara'] else 'Gifts'} |")
article=r'''# KabuchaDev JJK Arena — Complete Setup and Gift Guide

**Created by KabuchaDev** · [YouTube](https://www.youtube.com/@KabuchaDev)

**Free Edition 1.3.1 · Public Beta · Guide updated October 8, 2026**

Turn your livestream chat into a two-team Jujutsu Craft battle. Viewers support **Blue — Messi** or **Red — Ronaldo** by commenting or sending mapped TikTok gifts. Characters spawn for the corresponding team, fight the opposing army and attack its core.

This guide takes you from a fresh installation to your first interactive stream. It includes every default comment rule, all **26 gift mappings**, the full **15-character roster**, manual testing and troubleshooting.

The project is free. There is no activation key, subscription or donor-only feature. The optional project-support link is not configured. See [SUPPORT.md](SUPPORT.md) for the distinction between voluntary project contributions and TikTok gifts that trigger gameplay.

## 1. What is included?

- A generated red/blue arena in its own dimension, with **10,000 HP per new core**.
- Core shields while a defending team has troops, including ejection of attackers trapped inside a closing shield.
- Arena terrain protection and native Jujutsu Craft combat, skills and domains.
- A server mod, client camera companion, original source, bridge scripts and configuration.
- Automatic spectator camera following, toggled with **left Ctrl**.
- A local panel for testing troops and audience events without TikTok.
- New-viewer actionbar greetings and a short, deduplicated follow bow scene.

**Gift celebrations are disabled in this edition.** A mapped gift summons troops and displays a text thank-you; it does not freeze the battle for a gift cutscene, make characters dance or play music. No Tet song or other audio is bundled. The separate three-second **follow** bow scene remains enabled.

This is an add-on package, not a complete Minecraft installation. Download Minecraft, Forge and third-party mods separately from their authors. The small ZIP size is expected. Do not import the KabuchaDev ZIP into your launcher as if it were a CurseForge modpack.

**Beta status:** builds, routing tests and synthetic integration checks passed. A fresh full in-game acceptance test of this release has not yet been completed. Test on your own setup before streaming; see [validation details](docs/VALIDATION.md).

## 2. Requirements and official downloads

| Component | Required setup |
| --- | --- |
| Operating system | Windows 10/11 for the supplied launchers |
| Minecraft | Java Edition **1.20.1**, using your own legitimate account |
| Java | **Java 17 x64** |
| Mod loader | **Forge 47.4.3** |
| Modpack | **Zeefski Jujutsu Kaisen Craft 3.2.1**, matching client and server packs |
| Character mod | **Jujutsu Craft 50.1**, supplied by the matching third-party pack |
| Node.js | **24 or later**, for the bridge and setup tools |
| TikFinity | Desktop app on the same PC, connected to your LIVE session |
| Streamer.bot | Integration originally developed with **1.0.7** |
| Memory | The server launcher defaults to a 6 GB heap; leave additional memory for Windows, the client and streaming software |

Download from the official publishers: [Java 17](https://adoptium.net/temurin/releases/?version=17), [Node.js](https://nodejs.org/), [Forge for 1.20.1](https://files.minecraftforge.net/net/minecraftforge/forge/index_1.20.1.html), [Zeefski pack 3.2.1](https://www.curseforge.com/minecraft/modpacks/zjjk/files/8509973), [TikFinity](https://tikfinity.zerody.one/) and [Streamer.bot](https://streamer.bot/).

This package targets **Forge**. Do not substitute Paper, Fabric, Bedrock or Minecraft 1.21. Other dependency versions have not been verified.

## 3. Keep the client, server and tools separate

Use three distinct locations:

| Location | What belongs there |
| --- | --- |
| Client modpack profile in your launcher | The game you open to watch the battle, the matching Zeefski client pack and camera companion |
| Example: `C:\Games\KabuchaServer` | Extracted server pack, server mods, server properties and the new world |
| Example: `C:\Games\KabuchaArena` | Extracted KabuchaDev files such as `SETUP.cmd`, `interactions`, `test-panel` and this guide |

These paths are examples. Choose your own folders, extract all files before running anything and keep the folders in a stable location after setup. Moving them later requires updating local configuration and Streamer.bot paths.

## 4. Install the external client and server pack

1. Install Java 17 x64 and Node.js 24+. Reopen any terminal after installing Node. Running `node --version` should report version 24 or later.
2. Install **Zeefski Jujutsu Kaisen Craft 3.2.1** in a separate profile using a launcher that supports the pack. Select that exact release rather than automatically choosing the latest version. Keep its supplied mods and configuration together.
3. On the author's file page, obtain the matching server pack from the additional files: **`ZJJK_3.2.1server-pack.zip`**, reference file ID **8510031**. Extract it into a new server directory. If that file is no longer available, do not assume an arbitrary mirror or newer release is compatible.
4. Check that the server has `mods\JujutsuCraft-ver50.1-forge-1.20.1.jar` and the pack's configuration directories.
5. Check for `libraries\net\minecraftforge\forge\1.20.1-47.4.3\win_args.txt`. If it is missing, download the **Forge 47.4.3 installer**, run it with Java 17, choose **Install server** and select this server directory.
6. Do not start the server yet. KabuchaDev setup expects a fresh folder without a generated `world`. Use a new installation rather than deleting an existing world to pass that check.

## 5. Install KabuchaDev Arena

1. Extract **KabuchaDev-JJK-Arena-Free-1.3.1.zip** into your tools folder. Open `SETUP.cmd`.
2. At `Full path to a NEW extracted ZJJK 3.2.1 server folder`, enter the new server directory.
3. At `Full path to Java 17 java.exe`, enter the full path to the Java 17 executable. Select `java.exe`, not just its `bin` directory and not `javaw.exe`.
4. Wait for **Setup complete**. Setup installs our server jar, generates a fresh random RCON password and writes private `settings.local.json` and `STREAMERBOT-LOCAL.txt` files.
5. Read the [Minecraft EULA](https://www.minecraft.net/en-us/eula). If you agree, edit the **server directory's** `eula.txt`, change `eula=false` to `eula=true`, then save. Setup intentionally leaves acceptance to you and resets this file to false.
6. Copy `mods\client\jjkarena-camera-1.2.1.jar` from the KabuchaDev package into your **client profile's** `mods` folder. Setup already copied the server jar to the server. Do not install both jars on both sides.
7. If the new client profile already contains an older copy of the camera mod, replace it rather than leaving duplicate mod IDs installed.

The default game address is **127.0.0.1:25565** and the RCON endpoint is **127.0.0.1:25575**. Minecraft account authentication is enabled. Keep these services local; do not expose RCON or the panel to the Internet.

If the default ports are occupied, stop the conflicting installation or specify free ports during setup. For example, from the extracted tools directory:

```powershell
node .\setup.mjs --server "C:\Games\KabuchaServer" --java "C:\Path\To\jdk-17\bin\java.exe" --port 25567 --rcon-port 25577
```

Replace the example Java path. Join the selected game port instead of 25565 when using custom ports. Adjust `maxMemoryGB` in private `settings.local.json` if necessary; allocating more than your PC can comfortably spare can make performance worse.

## 6. Start the server and generate the arena

1. Run `START-SERVER.cmd`. Leave its console open and wait for the server's **Done** message.
2. Start your matching client profile, select **Multiplayer → Direct Connection**, and join **127.0.0.1:25565** (or your selected game port).
3. In the **server console**, grant your actual Minecraft username operator permission, then generate the arena:

```text
op YourMinecraftName
jjkarena build
```

4. Wait for the arena-complete message. The mod builds its own arena in `jjkarena:battlefield`; no prebuilt world download is required.
5. In **Minecraft chat**, enter:

```text
/jjkarena watch
/jjkarena start
```

You should enter spectator mode with a new match and **10,000 HP cores**. Console commands omit the slash; in-game chat commands use it. Administrative commands require OP permission.

Generate the arena only once. On later server starts, join and start a match; you do not need to rebuild. `/jjkarena start` clears current troops and starts a new match. Use pause/resume when you want to preserve an ongoing match.

## 7. Set up the camera and test troops locally

Press **F5** to select a rear view. Press **left Ctrl** to toggle automatic following while spectating inside the arena. The camera prefers the highest configured per-character gift tier among surviving units; a newer unit wins equal-priority ties. It uses tiers defined in this mod, not live regional TikTok pricing.

You can also use `/jjkarena autocamera on` or `/jjkarena autocamera off`. If left Ctrl conflicts with another mod, rebind **JJK Arena Camera** in Minecraft Controls. `/jjkarena leave` exits arena spectator mode.

For the raised rear-camera configuration, separately install a matching **Shoulder Surfing Reloaded Forge 1.20.1** client build from [its author](https://www.curseforge.com/minecraft/mc-mods/shoulder-surfing-reloaded). Close the game, back up any existing camera configuration and copy `client-config\shouldersurfing-client.toml` into the client profile's `config` directory. If your camera-mod version uses a different schema, set corresponding values through its settings instead of assuming compatibility.

Run `START-PANEL.cmd`, keep its console open, then visit **http://127.0.0.1:21215/**. The supplied panel's interface is in Vietnamese:

| Panel label | Meaning |
| --- | --- |
| Nhân vật | Character |
| Đỏ / Xanh | Red / Blue |
| Số lượng mỗi lần | Number of troops per request |
| Triệu hồi | Summon |
| Bắt đầu trận mới | Start a new match; clears existing troops |
| Tạm dừng / Tiếp tục | Pause / Resume |
| Dọn quân & hồi đầy hai lõi | Clear troops, restore cores and return to a waiting state |
| Người mới vào | Simulate a new-viewer welcome |
| Follow · cúi chào | Simulate a follow bow scene |

Choose a character, team and amount, then summon. Test both teams before connecting your stream. The panel works without TikFinity or Streamer.bot. Run `STOP-BRIDGE.cmd` if you want to test without receiving live events. Closing the browser tab does not stop the panel service or Minecraft server.

## 8. Configure one Streamer.bot action

The bridge classifies raw events using `interactions\config.json` and routes all valid summon requests through **one Streamer.bot action**. You do not need one TikFinity action per gift.

### WebSocket server

In Streamer.bot, open **Servers/Clients → WebSocket Server**. Set Address to **127.0.0.1**, Port to **8080**, Endpoint to `/`, enable Auto Start, and start the server. This bridge does not perform WebSocket authentication, so leave Authentication disabled for this loopback-only connection. Do not bind to all network interfaces or forward this port. [Official configuration documentation](https://docs.streamer.bot/api/websocket/guide/configuration).

### Action and sub-action

Create an enabled action named exactly:

```text
JJK Arena - Summon
```

No event trigger is required. Add **Core → System → Run a Program** under Sub-Actions and copy values from the `STREAMERBOT-LOCAL.txt` generated on your own machine:

| Field | Value |
| --- | --- |
| Command / Target | Full path to your `node.exe` |
| Arguments | Full quoted path to `dispatch.mjs`, followed by `%arenaJobId%` |
| Working Directory | Full path to the package's `interactions` directory |
| Wait for Exit | **20 seconds** |

Example Arguments field, if the tools were extracted at the example location:

```text
"C:\Games\KabuchaArena\interactions\dispatch.mjs" %arenaJobId%
```

Keep `%arenaJobId%` unchanged. Save the action. Do not manually execute it without a valid job ID; the bridge supplies that argument. [Official Run a Program reference](https://docs.streamer.bot/api/sub-actions/core/system/run-a-program).

## 9. Connect TikFinity to your LIVE session

1. Open **TikFinity Desktop on the same PC** and connect your TikTok LIVE session. Refer to [TikFinity's LIVE API documentation](https://tikfinity.zerody.one/tiktok/dapi).
2. Make sure the local LIVE API used by the supplied configuration is available at **ws://127.0.0.1:21213/**. Availability and enablement can depend on the provider and installed version.
3. Disable older TikFinity actions or duplicate bridges that already summon troops from these same events.
4. Keep Streamer.bot and Minecraft server open. Start a match and make sure it is not paused.
5. Run `START-BRIDGE.cmd`, then `STATUS.cmd`. Both applications should show connected and the arena should be ready. You can inspect the JSON status at **http://127.0.0.1:21214/status**.
6. Test comments from real viewers. A connected local socket does not by itself prove TikTok is delivering live events; confirm activity in the bridge log and the expected in-game result.

This integration uses the available API. It does not unlock a paid product, modify TikFinity limits or guarantee future API access.

## 10. Comment-to-character rules

__COMMENTS__

Counters are **shared across all viewers**, with an independent counter for each keyword. Three different viewers posting `messi` once each will reach the same threshold as one viewer posting it in three separate comments. After reaching a threshold, counting continues toward the next summon. Six eligible `messi` comments therefore produce two Miwa summons.

Matching ignores capitalization and surrounding whitespace, but requires the entire message to match the keyword. `MESSI` is accepted; `messi wins` is not. One message containing `messi messi messi` does not count as three comments.

## 11. Complete gift-to-character table

**One unit of a listed gift summons one character for the indicated team.** A completed gift combo uses its repeat count; troops may spawn gradually because of arena limits.

__GIFTS__

Coin values are the original operator-provided reference, not a current TikTok price list. **Star Goggles has no confirmed coin value in this configuration**, but it is mapped to Sukuna (Itadori's Body) for Red. Availability, localized names, IDs and prices may differ by region or change over time.

Examples:

- **1 Rose → 1 Itadori Yuji (School), Blue.**
- **1 TikTok → 1 Itadori Yuji (School), Red.**
- **A completed combo of 5 Doughnuts → 5 Todo Aoi, Blue**, subject to the spawn queue.
- **Bowknot and Chatting Popcorn summon different characters**, despite having the same reference price.

Names are matched case-insensitively. Explicitly verified gift IDs take priority where configured; other gifts use their normalized name or aliases. Not every regional translation is automatically recognized. An unknown gift displays a thank-you without spawning troops. A recognized gift summons troops and thanks the viewer in text; **no gift cutscene, dance, music or gift-triggered battle freeze occurs** in this edition.

## 12. Other audience events and the full character roster

| Event | Default behavior |
| --- | --- |
| New viewer joins | Actionbar welcome with duplicate suppression |
| First recognized follow | Approximately three-second bow scene when troops are present; camera companion required to view it |
| Same identity unfollows and follows again | No repeated follow scene while deduplication history is retained |
| Like | **No summon rule configured** in this package |
| Unmatched comment | No summon |
| Unmapped gift | Text thank-you only |

All **15 character types** can be selected in the test panel or summoned with an OP command:

__ROSTER__

Manual examples, entered in Minecraft chat:

```text
/jjkarena spawn blue miwa 1 Test
/jjkarena spawn red nobara 1 Test
/jjkarena spawn blue gojo 1 Test
/jjkarena spawn red sukuna_heian 1 Test
/jjkarena spawn blue yuji_school 1 Test gift
```

`blue` and `red` select the team; the number is 1–15 per command; `Test` is the summoner label. The optional final `gift` flag tests the text acknowledgement only. Each side permits up to **15 living troops** and **60 server-queued troops**, while additional bridge jobs wait for capacity.

## 13. Daily startup, shutdown and controls

**Start:** run START-SERVER → wait for Done → join/watch/start the match → open Streamer.bot and TikFinity → run START-BRIDGE → check STATUS. Open the test panel if needed. Keep one bridge instance for this installation.

**Pause:** use the panel or `/jjkarena pause`; continue with `/jjkarena resume`. Do not use `start` to resume an existing battle because it clears troops.

**Finish:** stop live processing with `STOP-BRIDGE.cmd`, then use `STOP-SERVER.cmd` or type `stop` in the server console. Wait for world saving and process exit before shutting down Windows. Close the panel console separately. STOP-SERVER also attempts to stop the bridge, so it is fine if you already stopped it.

| File or command | Purpose |
| --- | --- |
| `SETUP.cmd` | First-time setup for a fresh server |
| `START-SERVER.cmd` | Launch Minecraft server |
| `START-BRIDGE.cmd` / `STOP-BRIDGE.cmd` | Start/stop LIVE event processing |
| `START-PANEL.cmd` | Run the local test panel |
| `STATUS.cmd` | Check connections and queues |
| `STOP-SERVER.cmd` | Request bridge shutdown and save/stop Minecraft |
| `TEST-ROUTER.cmd` | Run event logic tests after setup; does not send paid gifts |
| `/jjkarena status` | Display arena state |
| `/jjkarena reset` | Clear troops, restore cores and return to a waiting state |
| `/jjkarena corehp 10000` | Set maximum core HP; resets the round |
| `/jjkarena leave` | Leave arena spectator mode |

Avoid `/jjkarena rebuild` if you need to preserve arena construction: it deliberately replaces arena blocks.

## 14. Customize gifts safely

Stop the bridge and back up `interactions\config.json` before editing it. Each gift entry specifies a name, optional aliases/ID, team and supported character ID. The bridge does **not** guess gifts by coin price.

For example:

```json
{
  "name": "Rose",
  "aliases": ["Hoa hồng"],
  "coins": 1,
  "side": "blue",
  "kind": "yuji_school",
  "id": "5655"
}
```

If a regional gift name differs, inspect the received event/log and add the observed alias or correct ID. Do not invent an ID or reuse another gift's ID. Save valid JSON and restart the bridge. Use character IDs from the roster; inventing a new string does not make another character supported.

Changing `coins` in JSON does not automatically change camera priorities: those tiers are defined in Java for this version. Test configuration changes before going live.

## 15. Acceptance checks and troubleshooting

First verify panel spawning on both teams, camera behavior, all four comment thresholds, mapped gifts using available provider test events, then welcome/follow and a clean restart. The included automated tests do not require paid gifts. Synthetic checks do not replace confirming actual LIVE delivery.

| Problem | What to check |
| --- | --- |
| Node command not found | Install Node.js 24+ and reopen SETUP/terminal; check `node --version` |
| Missing Forge or Jujutsu Craft | Correct server directory, exact Forge 47.4.3 files and matching server pack |
| Existing world / Already configured | Use a fresh server or edit the existing local configuration; do not delete a valued world to force setup |
| EULA prompt | Read the EULA and set true only if you agree, in the correct server directory |
| Java error or server crash | Check Java 17 path, server logs and available RAM |
| Cannot join | Wait for Done; verify address, port, matching mods and a legitimate Minecraft account |
| Duplicate/mismatched mods | Keep one jar per mod ID and install each on its intended side |
| Panel works, LIVE does not | Check TikFinity receiving events, Streamer.bot WebSocket/action, bridge and STATUS |
| Action does not execute | Exact action name, enabled action, valid Node/dispatch paths, working directory and `%arenaJobId%` |
| Comments do not summon | Entire comment must match a keyword; separate messages must reach that keyword's threshold |
| Gift only displays thanks | Possibly an unrecognized name/ID; inspect mappings/logs; also check match state and queues |
| Troops arrive slowly | Living/server queue limits may be full; bridge jobs wait for capacity |
| `held` is above zero | Delivery result is uncertain; inspect the game and Streamer.bot Action History before compensation to avoid duplicates |
| Follow scene missing | Requires troops, spectator mode inside the arena and the client companion; an already-seen follow identity will not replay |
| Double summons | Disable overlapping older actions or duplicate bridge instances |
| Port occupied / EADDRINUSE | Stop the conflicting local instance; do not run two installations on the same ports |

Bridge logs are in `interactions\bridge.log`; Minecraft logs are under the server's `logs` directory. Review and redact logs before sharing them. Never publish private settings, passwords, viewer state, spool jobs or worlds.

## 16. Sharing and project support

Creator: **KabuchaDev** — [YouTube](https://www.youtube.com/@KabuchaDev).

Share the **clean release ZIP**, not a copy of an installation you have already run. Local setup and runtime generate credentials and viewer-related data. All functions in this free edition are available equally; there is no configured support/payment link or donor benefit.

Third-party mods, characters and services belong to their respective authors. This project is not an official Minecraft product and is not approved by or associated with Mojang or Microsoft. The package does not certify permission to monetize a mod, server or stream. See [rights and external downloads](docs/RIGHTS.md), [support policy](SUPPORT.md) and [privacy](docs/PRIVACY.md).

For GitHub downloads, select **KabuchaDev-JJK-Arena-Free-1.3.1.zip** from the release's **Assets**. GitHub's automatically generated “Source code (zip)” can be different from the prepared installable package. No public repository URL is assumed in this guide; use the actual release page supplied by the author.
'''
article=article.replace('__COMMENTS__','\n'.join(comments)).replace('__GIFTS__','\n'.join(gifts)).replace('__ROSTER__','\n'.join(roster))
(release/'COMPLETE-GUIDE-EN.md').write_text(article,encoding='utf-8')
p=release/'README.md';s=p.read_text(encoding='utf-8')
needle='**Start here:**'
if '[Complete English guide' not in s:s=s.replace(needle,'**Complete English guide with all gifts and characters:** [Read the full tutorial](COMPLETE-GUIDE-EN.md).\n\n'+needle)
p.write_text(s,encoding='utf-8')
p=release/'docs/RELEASE-NOTES.md';s=p.read_text(encoding='utf-8').replace('**docs/INSTALL-EN.md** (English)','**COMPLETE-GUIDE-EN.md** (English)').replace('an end-to-end Vietnamese guide','end-to-end English and Vietnamese guides');p.write_text(s,encoding='utf-8')
print('English complete guide generated; tables derived from release config, all 26 gifts and 15 characters.')
