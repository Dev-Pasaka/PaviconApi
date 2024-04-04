package com.pavicontech.api.domain.useCase

import com.pavicontech.api.data.database.queryResults.SignInQueryResult
import com.pavicontech.api.data.repository.UserRepositoryImpl
import com.pavicontech.api.domain.model.UserCredentials
import com.pavicontech.api.domain.repository.UserRepository

class SignInUserUseCase(
    private val userRepository: UserRepository = UserRepositoryImpl(),
) {
    suspend fun signIn(userCredentials: UserCredentials):SignInQueryResult{
        return userRepository.signIn(email = userCredentials.email, password = userCredentials.password)
    }
}