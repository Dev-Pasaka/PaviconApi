package com.pavicontech.api.domain.useCase

import com.pavicontech.api.data.database.queryResults.ResetPasswordQueryResult
import com.pavicontech.api.data.repository.UserRepositoryImpl
import com.pavicontech.api.data.database.dto.ResetPasswordCredentialsDto
import com.pavicontech.api.domain.repository.UserRepository

class ResetPasswordUseCase(
    private val userRepository: UserRepository = UserRepositoryImpl()
) {
    suspend fun resetPassword(resetPasswordCredentialsDto: ResetPasswordCredentialsDto):ResetPasswordQueryResult{
        return userRepository.resetPassword(email = resetPasswordCredentialsDto.email, newPassword = resetPasswordCredentialsDto.newPassword, otpCode = resetPasswordCredentialsDto.otpCode)
    }
}