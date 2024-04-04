package com.pavicontech.api.presentation.plugins

import com.pavicontech.api.presentation.routes.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause" , status = HttpStatusCode.InternalServerError)
        }
    }
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        deleteUser()
        getUser()
        registerUser()
        resetPassword()
        sendEmailOtp()
        signInUser()
        updateUser()
    }
}
