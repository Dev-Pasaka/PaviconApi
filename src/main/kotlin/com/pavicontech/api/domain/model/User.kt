package com.pavicontech.api.domain.model

import com.pavicontech.api.data.repository.EncryptionRepositoryImpl
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import org.mindrot.jbcrypt.BCrypt

data class User(
    @BsonId()
    val id:String = ObjectId().toString(),
    val firstName: String,
    val lastName: String,
    val age:Int,
    val email: String,
    val town:String,
    var gender: Gender,
    val password: String,
    val otpCode:String? = null
)

fun User.encryptPassword():User{
    val hashedPassword = EncryptionRepositoryImpl().hashPassword(password = password)
    return User(
        id = id,
        firstName = firstName,
        lastName =  lastName,
        age = age,
        town = town,
        email = email,
        gender = gender,
        password =hashedPassword
    )
}

fun User.toUserData():UserData{
    return UserData(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email =  email,
        town = town,
        age = age,
        gender = gender
    )
}