package com.pavicontech.api.domain.useCase

import com.pavicontech.api.data.database.queryResults.UpdateUserQueryResult
import com.pavicontech.api.data.repository.UserRepositoryImpl
import com.pavicontech.api.domain.model.UpdateUser
import com.pavicontech.api.domain.repository.UserRepository

class UpdateUserUseCase(
    private val userRepository: UserRepository = UserRepositoryImpl(),

) {
    suspend fun updateUser(email: String, userData: UpdateUser):UpdateUserQueryResult{
        return userRepository.updateUser(email, userData)
    }
}