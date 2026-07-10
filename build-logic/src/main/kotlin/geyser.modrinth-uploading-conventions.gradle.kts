plugins {
    id("com.modrinth.minotaur")
}

// Ensure that the readme is synced
tasks.modrinth.get().dependsOn(tasks.modrinthSyncBody)

modrinth {
    token.set(System.getenv("MODRINTH_TOKEN") ?: "") // Even though this is the default value, apparently this prevents GitHub Actions caching the token?
    debugMode.set(System.getenv("MODRINTH_TOKEN") == null)
    projectId.set("geyser")
    versionName.set(versionName(project))
    versionNumber.set(projectVersion(project))
    versionType.set("beta")
    changelog.set(System.getenv("CHANGELOG") ?: "")
    gameVersions.addAll(
        "1.20.5", "1.20.6",
        "1.21", "1.21.1", "1.21.2", "1.21.3", "1.21.4", "1.21.5", "1.21.6", "1.21.7", "1.21.8", "1.21.9", "1.21.10", "1.21.11",
        "26.1", "26.1.1", libs.minecraft.get().version as String
    )
    failSilently.set(true)

    syncBodyFrom.set(rootProject.file("README.md").readText())
}
