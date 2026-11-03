# Local data

The bridge receives the livestream events exposed by TikFinity. Counters, delivery jobs, viewer labels and deduplication identifiers can be retained in `interactions/state.json`, `interactions/spool/`, logs and the Minecraft world. Follow identifiers are hashed for deduplication; this is not a claim that all data is anonymous. Follow history persists so repeatedly unfollowing/refollowing does not retrigger the scene.

The supplied code communicates with services on localhost. TikFinity, Streamer.bot, Minecraft and their providers have their own network behavior and privacy terms. This package adds no author telemetry or payment checking.

Never upload `settings.local.json`, `STREAMERBOT-LOCAL.txt`, server properties, worlds, logs, spool jobs or viewer state. The clean ZIP contains none of these. Back them up privately before upgrades. Deleting event state can cause old events/follows to be processed again; stop the bridge and understand that consequence before resetting it.

RCON is bound through the Minecraft server's loopback address. Keep the server, panel and WebSocket endpoints local. The supplied bridge uses Streamer.bot without WebSocket authentication on loopback only; do not expose that port or RCON to the Internet. Internet multiplayer hosting is outside this local setup.
