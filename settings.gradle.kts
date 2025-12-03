pluginManagement {
    repositories {
        gradlePluginPortal()   // REQUIRED for KSP
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "StudySmart"
include(":app")
