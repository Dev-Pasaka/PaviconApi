package com.pavicontech.api.domain.useCase

import com.pavicontech.api.data.repository.UserRepositoryImpl
import com.pavicontech.api.domain.repository.UserRepository

class GetUserUseCase(
    private val userRepository: UserRepository = UserRepositoryImpl(),
) {
    suspend fun getUser(email: String) = userRepository.getUser(email)
}