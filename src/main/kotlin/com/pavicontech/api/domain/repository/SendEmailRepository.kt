package com.pavicontech.api.domain.repository

import com.pavicontech.api.data.remote.apiResponses.SendEmailOtpResponse
import com.pavicontech.api.domain.model.EmailOtpBody

interface SendEmailRepository {
    suspend fun sendEmailOtp(email:String, otpCode: String):SendEmailOtpResponse
}