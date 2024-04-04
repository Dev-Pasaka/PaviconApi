package com.pavicontech.api.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class EmailOtpBody(
    val emailTo: String,
)

