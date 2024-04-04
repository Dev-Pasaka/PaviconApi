package com.pavicontech.api.data.database.queryResults

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserQueryResult(
    val status:Boolean,
    val message:String
)
