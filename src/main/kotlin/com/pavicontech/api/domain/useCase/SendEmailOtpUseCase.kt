package com.pavicontech.api.domain.useCase

import com.pavicontech.api.data.remote.apiResponses.SendEmailOtpResponse
import com.pavicontech.api.data.repository.UserRepositoryImpl
import com.pavicontech.api.domain.model.EmailOtpBody
import com.pavicontech.api.domain.repository.UserRepository

class SendEmailOtpUseCase(
    private val userRepository: UserRepository = UserRepositoryImpl()
) {
    suspend fun sendEmailOtp(email:String):SendEmailOtpResponse{
        return userRepository.sendEmailOtp(email = email)
    }
}