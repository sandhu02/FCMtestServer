package com.example

import com.google.firebase.messaging.FirebaseMessaging
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.util.reflect.*

fun Route.sendNotification() {
    route("/send") {
        post{
            val body = call.receiveNullable<SendMessageDTO>(typeInfo<SendMessageDTO>()) ?: kotlin.run {
                call.respond(
                    HttpStatusCode.BadRequest,
                    typeInfo = typeInfo<SendMessageDTO>()
                )
                return@post
            }

            FirebaseMessaging.getInstance().send(body.toMessage())

            call.respond(
                HttpStatusCode.OK,
                typeInfo = typeInfo<SendMessageDTO>()
            )
        }
    }

    route("/broadcast") {
        post{
            val body = call.receiveNullable<SendMessageDTO>(typeInfo<SendMessageDTO>()) ?: kotlin.run {
                call.respond(
                    HttpStatusCode.BadRequest,
                    typeInfo = typeInfo<SendMessageDTO>()
                )
                return@post
            }

            FirebaseMessaging.getInstance().send(body.toMessage())

            call.respond(
                HttpStatusCode.OK,
                typeInfo = typeInfo<SendMessageDTO>()
            )
        }
    }
    
    route("/test") {
        get {
            call.respond(
                "hello from the ktor server!",
                typeInfo = typeInfo<String>()
            )
        }
    }
}