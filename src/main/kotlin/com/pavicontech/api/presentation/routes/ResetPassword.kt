package com.pavicontech.api.presentation.routes

import com.pavicontech.api.data.config.Config
import com.pavicontech.api.data.database.dto.ResetPasswordCredentialsDto
import com.pavicontech.api.domain.useCase.ResetPasswordUseCase
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.resetPassword() {
    post("${Config.apiVersionBranch}resetPassword") {
        val resetPasswordCredentialsDto = call.receive<ResetPasswordCredentialsDto>()
        val result = ResetPasswordUseCase().resetPassword(resetPasswordCredentialsDto)
        call.respond(result)
    }
}