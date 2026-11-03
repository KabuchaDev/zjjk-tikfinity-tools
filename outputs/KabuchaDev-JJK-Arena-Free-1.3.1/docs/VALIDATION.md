# Release validation — 2026-10-07

## Passed

- Java 17 / ForgeGradle / Gradle 8.8 compilation and reobfuscation of server **1.3.1** and client camera **1.2.1**. Compiler emits existing Forge deprecation warnings; build succeeds.
- Node syntax checks and PowerShell launcher parsing.
- Router tests: four shared comment counters, all 26 gift mappings, lowercase/uppercase/whitespace/aliases, verified IDs, same-price gift separation, combos, event deduplication, serialized state and sanitized viewer names.
- Audience tests: welcomes, persistent follow deduplication, mapped-gift acknowledgement, unknown-gift text-only behavior and completed-combo deduplication.
- Setup fixture test with Unicode/spaces in paths, actual Java 17, random private RCON secret, authenticated Minecraft default, EULA left false and refusal to overwrite a previous setup. The fixture contains placeholders for third-party files; it verifies installer logic, not third-party downloads.
- Integration test using the actual release bridge and dispatcher against synthetic localhost TikFinity/Streamer.bot/RCON protocol peers: **30 correct deliveries** (four comment thresholds plus 26 gifts), repeated-event suppression, one welcome, one follow acknowledgement, unknown-gift thanks, no held deliveries and no duplicate execution of an already completed job.
- Source and archive audit: gift spawns no longer invoke the celebration controller; only follow calls it. Client rejects gift scenes and has no audio/dance playback code. Default new-world core HP is 10,000. Both jars and the release ZIP are checked for unapproved assets, private data and unrelated dependencies; SHA-256 checksums accompany the release.

## Limits

The integration test uses synthetic services, not paid gifts or a live TikTok session. A second full modpack server/client was not launched for this edition because only approximately 3 GB RAM was free during packaging; the current private installation was left unchanged. In-game rendering, fresh-world gameplay and live service delivery therefore still require recipient acceptance testing on the documented dependency versions. An automated test pass is not a guarantee of no bugs or future service compatibility.

## Recipient acceptance check

1. Install both matching packs and this release, generate a fresh arena, verify 10,000 HP per core and test both teams.
2. Send the four comment types to their respective thresholds using several viewer identities. Confirm one correct spawn per threshold and separate counters.
3. Test each gift rule using available platform test events. Confirm team/type, final combo count and no gift cutscene/music. Check the actual regional gift name/ID before relying on it in a stream.
4. Verify new-member actionbar, new-follow three-second bow with troops present, no repeated-follow scene for the same identity and camera restoration.
5. Test left-Ctrl auto camera, core-gate ejection, protected terrain and domain skills; check server logs and performance under the intended load.
6. Stop cleanly, restart and verify private follow/counter state persists. Recheck any deliveries marked `held` before compensating.

No money was sent and no content was published by these release checks.
