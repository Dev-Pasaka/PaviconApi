package com.pavicontech.api.presentation.routes

import com.pavicontech.api.data.config.Config
import com.pavicontech.api.data.database.dto.CreateUserDto
import com.pavicontech.api.domain.model.User
import com.pavicontech.api.domain.useCase.RegisterUserUseCase
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.registerUser(){
    post("${Config.apiVersionBranch}registerUser") {
        val user = call.receive<CreateUserDto>()
        val result = RegisterUserUseCase().register(user = User(
            firstName = user.firstName,
            lastName = user.lastName,
            email = user.email,
            age = user.age,
            town = user.town,
            password = user.password,
            gender = user.gender
        )
        )
        call.respond(result)
    }
}