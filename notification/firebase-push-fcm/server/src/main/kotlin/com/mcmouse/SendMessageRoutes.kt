package com.mcmouse

import com.google.firebase.messaging.FirebaseMessaging
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.sendNotification() {
    route("send") {
        post {
            val body = call.receiveNullable<SendMessageDto>() ?: run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            FirebaseMessaging.getInstance().send(body.toMessage())
            call.respond(HttpStatusCode.OK)
        }
    }

    route("broadcast") {
        post {
            val body = call.receiveNullable<SendMessageDto>() ?: run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            FirebaseMessaging.getInstance().send(body.toMessage())
            call.respond(HttpStatusCode.OK)
        }
    }
}