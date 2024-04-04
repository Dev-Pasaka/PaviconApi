package com.pavicontech.api.presentation.routes

import com.pavicontech.api.data.config.Config
import com.pavicontech.api.domain.model.UpdateUser
import com.pavicontech.api.domain.model.UserData
import com.pavicontech.api.domain.useCase.UpdateUserUseCase
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.updateUser() {
    authenticate {
        post("${Config.apiVersionBranch}updateUser") {
            val email =
                call.principal<JWTPrincipal>()?.payload?.getClaim("email").toString().removeSurrounding("\"")

            val newUserData = call.receive<UpdateUser>()
            val result = UpdateUserUseCase().updateUser(email = email, userData = newUserData)
            call.respond(result)

        }
    }
}