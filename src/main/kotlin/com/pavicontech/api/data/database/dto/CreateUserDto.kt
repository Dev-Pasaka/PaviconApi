package com.pavicontech.api.data.database.dto

import com.pavicontech.api.domain.model.Gender
import kotlinx.serialization.Serializable

@Serializable
data class CreateUserDto(
    val email:String,
    val firstName:String,
    val lastName:String,
    val age:Int,
    val gender: Gender,
    val town:String,
    val password:String
)