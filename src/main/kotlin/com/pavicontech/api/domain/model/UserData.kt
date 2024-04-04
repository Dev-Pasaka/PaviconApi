package com.pavicontech.api.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val id:String,
    val email:String,
    val firstName:String,
    val lastName:String,
    val age:Int,
    val gender: Gender,
    val town:String

)
