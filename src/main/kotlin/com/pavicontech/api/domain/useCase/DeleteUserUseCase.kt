package com.pavicontech.api.domain.useCase

import com.pavicontech.api.data.database.queryResults.DeleteUserQueryResult
import com.pavicontech.api.data.repository.UserRepositoryImpl
import com.pavicontech.api.domain.repository.UserRepository

class DeleteUserUseCase(
    private val userRepository: UserRepository = UserRepositoryImpl(),
) {
    suspend fun deleteUser(email: String):DeleteUserQueryResult{
        return userRepository.deleteUser(email)
    }
}