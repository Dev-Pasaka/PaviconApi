package com.pavicontech.api.presentation.routes

import com.pavicontech.api.data.config.Config
import com.pavicontech.api.domain.useCase.GetUserUseCase
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getUser() {
    authenticate {
        get("${Config.apiVersionBranch}getUser") {
            val email =
                call.principal<JWTPrincipal>()?.payload?.getClaim("email").toString().removeSurrounding("\"")
            val result = GetUserUseCase().getUser(email)
            call.respond(result)
        }
    }
}