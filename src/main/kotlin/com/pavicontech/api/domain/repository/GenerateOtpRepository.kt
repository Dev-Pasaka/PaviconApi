package com.pavicontech.api.domain.repository



interface GenerateOtpRepository {
    suspend fun generateOtp():String
}