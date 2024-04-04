package com.pavicontech.api.data.database.dto

import kotlinx.serialization.Serializable

@Serializable
class ResetPasswordCredentialsDto(
    val email: String,
    val newPassword:String,
    val otpCode: String
)