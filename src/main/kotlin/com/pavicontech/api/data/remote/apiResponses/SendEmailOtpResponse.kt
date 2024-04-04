package com.pavicontech.api.data.remote.apiResponses

import kotlinx.serialization.Serializable

@Serializable
data class SendEmailOtpResponse(
    val status:Boolean,
    val message:String
)
