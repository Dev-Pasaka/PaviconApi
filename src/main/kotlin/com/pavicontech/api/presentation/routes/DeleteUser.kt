package com.pavicontech.api.presentation.routes

import com.pavicontech.api.data.config.Config
import com.pavicontech.api.domain.useCase.DeleteUserUseCase
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.deleteUser() {
    authenticate {
        get("${Config.apiVersionBranch}deleteUser") {
            val email =
                call.principal<JWTPrincipal>()?.payload?.getClaim("email").toString().removeSurrounding("\"")
            val result = DeleteUserUseCase().deleteUser(email = email)
            call.respond(result)
        }
    }
}