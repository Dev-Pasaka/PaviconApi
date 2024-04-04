package com.pavicontech.api.data.database.queryResults

import com.pavicontech.api.domain.model.UserData
import kotlinx.serialization.Serializable

@Serializable
data class GetUserQueryResult(
    val status:Boolean,
    val message:String,
    val user:UserData? = null
)
