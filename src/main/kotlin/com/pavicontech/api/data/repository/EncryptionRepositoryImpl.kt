package com.pavicontech.api.data.repository

import com.pavicontech.api.domain.repository.EncryptionRepository
import org.mindrot.jbcrypt.BCrypt

class EncryptionRepositoryImpl():EncryptionRepository {
    override fun hashPassword(password: String): String  =
        BCrypt.hashpw(password, BCrypt.gensalt())

    override fun verifyHashedPassword(password:String,hashedPassword: String): Boolean  = BCrypt.checkpw(password, hashedPassword)
}