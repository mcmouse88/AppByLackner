package com.mcmouse.plugins

import com.mcmouse.sendNotification
import io.ktor.server.application.Application
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    routing {
        sendNotification()
    }
}