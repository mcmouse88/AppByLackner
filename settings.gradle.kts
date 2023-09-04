@file:Suppress("UnstableApiUsage")

include(":shimmer-effect")


include(":swipe-refresh")


include(":image-slider")


include(":sharing-data-between-screens")


include(":navigate-back-result")


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
include(":room-transaction")
