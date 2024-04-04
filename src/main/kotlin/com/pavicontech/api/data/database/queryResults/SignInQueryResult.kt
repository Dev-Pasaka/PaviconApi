package com.pavicontech.api.data.database.queryResults

import kotlinx.serialization.Serializable

@Serializable
data class SignInQueryResult(
    val status:Boolean,
    val message:String,
    val token:String
)
