package com.pavicontech.api.data.database.queryResults

import kotlinx.serialization.Serializable

@Serializable
data class DeleteUserQueryResult(
    val status:Boolean,
    val message:String
)
