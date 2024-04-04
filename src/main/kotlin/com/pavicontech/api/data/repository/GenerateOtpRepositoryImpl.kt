package com.pavicontech.api.data.repository

import com.pavicontech.api.domain.repository.GenerateOtpRepository
import java.security.SecureRandom


class GenerateOtpRepositoryImpl(): GenerateOtpRepository {
    override suspend fun generateOtp(): String {
        val random = SecureRandom()
        val bytes = ByteArray(6)
        random.nextBytes(bytes)
        return  bytes.map { it.toInt() and 6 }.joinToString("")
    }
}