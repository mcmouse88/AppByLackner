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
    ":main-component",
    ":main-component:broadcast-and-broadcast-receiver",
    ":main-component:content-providers",
    ":main-component:foreground-services",
    ":main-component:intent-and-intent-filter",
    ":room",
    ":room:room-guide-full",
    ":room:room-migration",
    ":room:room-transaction",
    ":room:multiple-table-database",
    ":notification",
    ":notification:notifications-above-api-33",
    ":notification:local-notification",
    ":notification:alarm-manager",
    ":compose-lists",
    ":compose-lists:lazy-grid-full-guide",
    ":widgets",
    ":widgets:context-drop-down-menu",
    ":widgets:swipe-refresh",
    ":widgets:shimmer-effect",
    ":widgets:image-slider",
    ":work-manager",
    ":note-app",
    ":navigation",
    ":navigation:navigate-back-result",
    ":navigation:nested-graph-compose",
    ":testing-on-android",
    ":sharing-data-between-screens",
    ":mvvm-news-app",
    ":graphql-basic",
    ":firebase_cloud_messaging",
    ":date-time-api-guide",
    ":error-handling",
    ":permissions-handling-guide",
    ":material3",
    ":material3:text-fields",
    ":material3:top-app-bar",
    ":material3:bottom-navigation-bar",
    ":material3:selection-component",
    ":material3:tab-row",
    ":material3:buttons",
    ":material3:bottom-sheet",
    ":material3:navigation-drawer",
    ":material3:navigation-rail"
)

