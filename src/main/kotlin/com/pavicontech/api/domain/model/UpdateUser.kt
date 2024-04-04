package com.pavicontech.api.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUser(
    val firstName: String?,
    val lastName: String?,
    val age:Int?,
    val town:String?,
    var gender: Gender?,

)