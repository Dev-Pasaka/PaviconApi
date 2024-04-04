package com.pavicontech.api.data.database.queryResults

import kotlinx.serialization.Serializable

@Serializable
data class ResetPasswordQueryResult(
    val status:Boolean,
    val message:String
)
