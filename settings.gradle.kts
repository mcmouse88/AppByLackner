@file:Suppress("UnstableApiUsage")

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
        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots/")}
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
include(":testing-on-android")
include(":shimmer-effect")
include(":swipe-refresh")
include(":image-slider")
include(":sharing-data-between-screens")
include(":navigate-back-result")
include(":multiple-table-database")
include(":room-guide-full")
include(":room-migration")
include(":mvvm-news-app")