@file:Suppress("UnstableApiUsage")

include(":date-time-api-guide")


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
include(":graphql-basic")
include(":firebase_cloud_messaging")
include(":local-notification")
include(":notifications-above-api-33")