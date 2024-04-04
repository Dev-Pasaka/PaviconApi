package com.pavicontech.api.domain.useCase

import com.pavicontech.api.data.database.queryResults.CreateUserQueryResult
import com.pavicontech.api.data.repository.UserRepositoryImpl
import com.pavicontech.api.domain.model.User
import com.pavicontech.api.domain.repository.UserRepository

class RegisterUserUseCase(
    private val userRepository: UserRepository = UserRepositoryImpl(),
) {
    suspend  fun register(user: User):CreateUserQueryResult{
        return userRepository.createUser(user)
    }
}