package com.pavicontech.api.presentation.routes

import com.pavicontech.api.data.config.Config
import com.pavicontech.api.domain.model.UserCredentials
import com.pavicontech.api.domain.useCase.SignInUserUseCase
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.signInUser(){
    post("${Config.apiVersionBranch}signIn") {
        val credentials = call.receive<UserCredentials>()
        val result = SignInUserUseCase().signIn(credentials)
        call.respond(result)
    }
}