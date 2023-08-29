pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AppByPhilippLackner"
include(":intent-and-intent-filter")
include(":broadcast-and-broadcast-receiver")
include(":foreground-services")
include(":work-manager")
include(":content-providers")
include(":note-app")
