package com.pavicontech.api.domain.repository

interface EncryptionRepository {
    fun hashPassword(password:String):String
    fun verifyHashedPassword(password: String,hashedPassword:String):Boolean
}