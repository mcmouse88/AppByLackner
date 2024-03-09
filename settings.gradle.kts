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
    ":notification:firebase_cloud_messaging",
    ":notification:firebase-push-fcm",
    ":notification:firebase-push-fcm:server",
    ":compose-lists",
    ":compose-lists:lazy-grid-full-guide",
    ":compose-lists:lazy-staggered-grid",
    ":widgets",
    ":widgets:context-drop-down-menu",
    ":widgets:swipe-refresh",
    ":widgets:shimmer-effect",
    ":widgets:image-slider",
    ":widgets:search-field-compose",
    ":widgets:custom-compose-layout",
    ":widgets:motion-layout-compose",
    ":work-manager-pack",
    ":work-manager-pack:work-manager-guide",
    ":work-manager-pack:work-manager",
    ":note-app",
    ":navigation",
    ":navigation:navigate-back-result",
    ":navigation:nested-graph-compose",
    ":navigation:deeplink-compose",
    ":testing-on-android",
    ":sharing-data-between-screens",
    ":mvvm-news-app",
    ":graphql-basic",
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
    ":material3:navigation-rail",
    ":material3:swipe-to-delete",
    ":material3:basic-text-field-2",
    ":zoom-gestures-compose",
    ":animation-compose",
    ":calculator",
    ":custom-pagination",
    ":koin-guide",
    ":shortcuts",
    ":custom-shape",
    ":camera",
    ":camera:camera-x",
    ":camera:tensor-flow-landmark"
)

