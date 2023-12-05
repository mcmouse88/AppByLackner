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
        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots/") }
    }
}

rootProject.name = "AppByPhilippLackner"
include(
    ":intent-and-intent-filter",
    ":broadcast-and-broadcast-receiver",
    ":foreground-services",
    ":work-manager",
    ":content-providers",
    ":note-app",
    ":room-transaction",
    ":testing-on-android",
    ":shimmer-effect",
    ":swipe-refresh",
    ":image-slider",
    ":sharing-data-between-screens",
    ":navigate-back-result",
    ":multiple-table-database",
    ":room-guide-full",
    ":room-migration",
    ":mvvm-news-app",
    ":graphql-basic",
    ":firebase_cloud_messaging",
    ":local-notification",
    ":notifications-above-api-33",
    ":date-time-api-guide",
    ":error-handling",
    ":alarm-manager",
    ":permissions-handling-guide",
    ":material3",
    ":material3:text-fields",
    ":material3:top-app-bar",
    ":material3:bottom-navigation-bar",
    ":material3:selection-component",
    ":material3:tab-row",
    ":material3:buttons"
)

