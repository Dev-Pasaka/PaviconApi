package com.pavicontech.api.domain.repository

import com.pavicontech.api.data.database.queryResults.*
import com.pavicontech.api.data.remote.apiResponses.SendEmailOtpResponse
import com.pavicontech.api.domain.model.EmailOtpBody
import com.pavicontech.api.domain.model.UpdateUser
import com.pavicontech.api.domain.model.User

interface UserRepository {
    suspend fun createUser(user:User):CreateUserQueryResult
    suspend fun updateUser(email: String, user:UpdateUser):UpdateUserQueryResult
    suspend fun deleteUser(email: String):DeleteUserQueryResult
    suspend fun getUser(email:String):GetUserQueryResult
    suspend fun resetPassword(email:String, newPassword:String, otpCode: String):ResetPasswordQueryResult
    suspend fun signIn(email:String, password:String):SignInQueryResult

    suspend fun sendEmailOtp(email:String):SendEmailOtpResponse

}