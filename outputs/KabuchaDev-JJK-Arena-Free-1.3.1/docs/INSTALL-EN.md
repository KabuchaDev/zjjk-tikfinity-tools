# Windows installation

Author: [KabuchaDev](https://www.youtube.com/@KabuchaDev). Read [rights](RIGHTS.md) and [voluntary support](../SUPPORT.md). All included features work without payment.

## 1. Install the external game and tools

1. Use a legitimate Minecraft Java Edition account. Install **Java 17 x64** from [Adoptium](https://adoptium.net/temurin/releases/?version=17) and **Node.js 24 LTS** from [nodejs.org](https://nodejs.org/). Keep the full Java `bin\java.exe` path. Node's installer should add `node` to PATH; reopen terminals after installation.
2. From [the author's ZJJK 3.2.1 page](https://www.curseforge.com/minecraft/modpacks/zjjk/files/8509973), install the client pack in a separate launcher profile. Select Minecraft **1.20.1 / Forge 47.4.3**. Do not use Fabric, Paper or Minecraft 1.21 with this package.
3. Download the corresponding server pack from the author's additional files (`ZJJK_3.2.1server-pack.zip`, ID 8510031). Extract it into a **new folder** such as `C:\Games\KabuchaServer`. Keep its mods and configuration together. The required Jujutsu Craft file is `JujutsuCraft-ver50.1-forge-1.20.1.jar`.
4. If `libraries\net\minecraftforge\forge\1.20.1-47.4.3\win_args.txt` is missing, download the **Forge 47.4.3 installer** from [Forge's 1.20.1 page](https://files.minecraftforge.net/net/minecraftforge/forge/index_1.20.1.html). Run it with Java 17 and choose **Install server**, targeting that same folder. Do not run the Minecraft server yet. Do not install server-only files into the client profile.
5. For live interaction, separately install [TikFinity Desktop](https://tikfinity.zerody.one/) and [Streamer.bot](https://streamer.bot/). Version 1.0.7 was used for the existing integration. Their UI/terms may change. A working TikTok LIVE session/account is your responsibility.

## 2. Configure this package

1. Extract the clean KabuchaDev ZIP into its own permanent folder. Do not run from inside the ZIP and do not move it after configuring Streamer.bot unless you update the paths.
2. Run `SETUP.cmd`. Enter the full new server folder and Java 17 `java.exe` path. Setup refuses an existing world or arena installation. It checks ports, copies **only our server jar**, generates a new RCON password, configures localhost ports **25565 / 25575**, enables account authentication, and writes private `settings.local.json` and `STREAMERBOT-LOCAL.txt`. It does not download or redistribute third-party mods.
3. Read the [Minecraft EULA](https://www.minecraft.net/en-us/eula). Only if you agree, edit the **new server's** `eula.txt` from `eula=false` to `eula=true`. Every recipient makes this decision themselves; the setup script resets this file to false.
4. Copy `mods\client\jjkarena-camera-1.2.1.jar` into the **client profile's** `mods` folder. The server jar belongs only on the server. Remove older copies of these same mod IDs from that new installation to avoid duplicate mod errors.
5. Optional: for the raised rear camera, install a matching **Shoulder Surfing Reloaded Forge 1.20.1** client version. Close the game, back up its existing `config\shouldersurfing-client.toml` if present, then copy the supplied file from `client-config` there. Restart the game. If using another version, translate the values through that version's settings rather than assuming the schema matches.

Advanced noninteractive setup, with your own paths and free ports:

```powershell
node .\setup.mjs --server "C:\Games\KabuchaServer" --java "C:\Path\To\jdk-17\bin\java.exe" --port 25565 --rcon-port 25575
```

Set `maxMemoryGB` in private `settings.local.json` if needed. Default is 6; leave memory for Windows, Minecraft client and streaming software. Do not copy another person's password. To relocate, update `serverDir`, `javaPath`, `nodePath` and Streamer.bot's command paths together. Port changes must match `server.properties` and `settings.local.json`.

## 3. Generate the arena once

1. Run `START-SERVER.cmd`; wait for the server's `Done` message. Keep that console open.
2. Join `127.0.0.1:25565` from the matching modpack client (use your selected port if changed).
3. In the server console, enter `op YourMinecraftName`, then `jjkarena build` (console commands have no `/`). Wait for the arena-built message. The arena is generated in `jjkarena:battlefield`; no downloadable world is necessary.
4. In the game: `/jjkarena watch`, then `/jjkarena start`. OP permission is required for administrative commands. New cores start at **10,000 HP**.
5. Use F5 for rear view. Left Ctrl toggles automatic following; if a key conflicts, rebind **JJK Arena Camera** in Minecraft Controls. `/jjkarena autocamera on` and `off` are alternatives. `/jjkarena leave` exits the arena spectator mode.
6. `START-PANEL.cmd` serves [the test panel](http://127.0.0.1:21215/). Keep its console open and open that URL. Start a match and test several characters on both teams. You can test without TikFinity/Streamer.bot.

Never use `/jjkarena rebuild` in a map you wish to preserve: it deliberately replaces arena blocks. `start`/`reset` clear current arena troops; they are not pause buttons.

## 4. Connect one Streamer.bot action

1. Open Streamer.bot. Under **Servers/Clients → WebSocket Server**, bind to **127.0.0.1**, port **8080**, enable **Auto Start**, and start the server. This bridge uses a local connection without authentication; disable authentication for this loopback server if enabled. **Do not bind to all interfaces or expose this port.** [Official configuration](https://docs.streamer.bot/api/websocket/guide/configuration).
2. Under **Actions**, add an enabled action named exactly **JJK Arena - Summon**. It needs no event trigger and no random sub-action selection.
3. Add **Core → System → Run a Program**. Copy the values from the generated `STREAMERBOT-LOCAL.txt`: Target/Command is your Node executable; Arguments contain the full quoted `dispatch.mjs` path followed by `%arenaJobId%`; Working Directory is the `interactions` directory; Wait for Exit is **20 seconds**. [Official sub-action documentation](https://docs.streamer.bot/api/sub-actions/core/system/run-a-program).
4. Save the action. Do not manually run it with an empty job ID; the bridge supplies a UUID for each validated request.
5. Open TikFinity Desktop, connect your own TikTok LIVE account/session, and make its local WebSocket LIVE API available at **ws://127.0.0.1:21213/**. [Official API guide](https://tikfinity.zerody.one/tiktok/dapi). This API's availability depends on the provider; this release does not remove product limits or guarantee future access.
6. Disable overlapping old TikFinity actions that already send the same gift/comment to this server. The bridge consumes raw events and uses **one Streamer.bot action**; it does not need a TikFinity action for each gift.
7. Start the match, then run `START-BRIDGE.cmd`. `STATUS.cmd` must show TikFinity and Streamer.bot connected and the arena ready. The status endpoint is [localhost status](http://127.0.0.1:21214/status). Connected means the local sockets work; it does not prove TikTok is delivering live events.
8. Verify comments from several viewers and use TikFinity's event testing when available. A real live event should appear in the bridge log and produce the expected unit. See [the mappings](MAPPINGS.md). Real paid gifts are not required for the included automated tests.

No npm installation, paid activation or author account is required by this code. TikFinity/Streamer.bot must still run and be configured correctly for the live path.

## 5. Daily use and troubleshooting

Start server → wait for `Done` → join/watch → start match → open TikFinity and Streamer.bot → start bridge → optionally start panel. Closing the bridge/panel console stops that local helper. Use **STOP-SERVER.cmd** or type `stop` in the server console; wait for world saving and process exit before shutting down Windows. STOP-SERVER also requests the bridge to stop; close the panel separately.

- **No units:** check `STATUS.cmd`, match running/unpaused, exact action name, Java/mod versions, live event reception and `interactions\bridge.log`. A full arena queues units rather than spawning indefinitely: up to 15 living units and 60 server-queued units per team, with remaining bridge jobs waiting.
- **Unknown gift:** only verified IDs take precedence; otherwise normalized name/alias matching is used. Add the actual received name to `aliases` in `interactions\config.json`, or an observed stable gift ID. Do not map solely by coin value: opposing gifts can cost the same. Restart bridge after edits. Prices/names vary by region.
- **`held` greater than zero:** a delivery result is uncertain. Inspect the game, Streamer.bot history and the local spool result before manually compensating. The bridge deliberately does not blindly resend and risk duplicates. Never publish those private files.
- **Camera/follow scene missing:** check that the camera jar is on the client, you are in spectator mode inside the arena, and there are troops for a follow scene. Gift events intentionally never play a scene or music.
- **Port in use:** close the other copy. Running two packs against the same bridge, RCON or panel ports is unsupported.
- **Crash/mismatched entities:** use the exact matching client/server pack, avoid extra combat-mod changes, inspect server logs, and back up the world before any mod upgrade. Compatibility beyond the documented versions has not been verified.

This is a local Windows setup. Do not forward RCON/panel/WebSocket ports. Remote multiplayer hosting requires its own network/security design.
