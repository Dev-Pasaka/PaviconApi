package com.pavicontech.api.presentation.routes

import com.pavicontech.api.data.config.Config
import com.pavicontech.api.data.remote.apiResponses.SendEmailOtpResponse
import com.pavicontech.api.domain.model.EmailOtpBody
import com.pavicontech.api.domain.useCase.SendEmailOtpUseCase
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.sendEmailOtp() {
    post("${Config.apiVersionBranch}sendOtp/{channel?}") {
        val channel = call.parameters["channel"] ?: call.respond(
            SendEmailOtpResponse(status = false, message = "Parameter (channel) can't be blank")
        )
        val emailOtpBody = call.receive<EmailOtpBody>()
        when (channel) {
            "email" -> {
                call.respond(SendEmailOtpUseCase().sendEmailOtp(email = emailOtpBody.emailTo))
            }

            else -> {
                call.respond(
                    SendEmailOtpResponse(status = false, message = "Use email as parameter channel property")
                )
            }
        }
    }
}