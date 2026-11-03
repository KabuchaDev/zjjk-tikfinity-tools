# Building the original mods

Install a Java 17 JDK and Gradle 8.8 from their official sites. Set `JAVA_HOME` to the JDK directory, then run from `source`:

```text
gradle build cameraJar
```

ForgeGradle downloads its own build dependencies, including the required Minecraft development artifacts. A network connection is required for the initial build. These downloads are local build inputs, not part of this release. Do not redistribute `source/build`, `.gradle` or dependency caches.

Outputs after both reobfuscation tasks finish:

```text
source/build/libs/jjkarena-1.3.1.jar
source/build/libs/jjkarena-camera-1.2.1.jar
```

The server jar excludes client camera implementation; the client jar contains the camera/network classes and client resources. Keep them in their respective server/client `mods` directories, not both on both sides.

Bridge tests need Node.js 24 and no npm packages:

```text
node interactions/test-router.mjs
node interactions/test-audience.mjs
```

The tests use generated events and do not send gifts or connect to a real livestream. No source/binary modification of a third-party mod is needed.
