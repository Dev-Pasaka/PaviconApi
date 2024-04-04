package com.pavicontech.api.data.repository

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.pavicontech.api.data.config.JWTConfig
import com.pavicontech.api.data.database.Entries
import com.pavicontech.api.data.database.queryResults.*
import com.pavicontech.api.data.remote.apiResponses.SendEmailOtpResponse
import com.pavicontech.api.domain.model.*
import com.pavicontech.api.domain.repository.EncryptionRepository
import com.pavicontech.api.domain.repository.GenerateOtpRepository
import com.pavicontech.api.domain.repository.SendEmailRepository
import com.pavicontech.api.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.litote.kmongo.*
import java.util.*

class UserRepositoryImpl(
    private val entries: Entries = Entries,
    private val encryptionRepository: EncryptionRepository = EncryptionRepositoryImpl(),
    private val sendEmailRepository: SendEmailRepository = SendEmailRepositoryImpl(),
    private val generateOtpRepository: GenerateOtpRepository = GenerateOtpRepositoryImpl()
) : UserRepository {
    override suspend fun createUser(user: User): CreateUserQueryResult = withContext(Dispatchers.IO) {
        val doesUserExists = entries.dbUser.findOne(User::email eq user.email)
        if (doesUserExists != null) return@withContext CreateUserQueryResult(
            status = false,
            message = "User already exists"
        )
        val result = entries.dbUser.insertOne(user.encryptPassword()).wasAcknowledged()
        if (!result) return@withContext CreateUserQueryResult(status = false, message = "Failed to create user")
        return@withContext CreateUserQueryResult(status = true, message = "User created successfully")
    }

    override suspend fun updateUser(email: String, user: UpdateUser): UpdateUserQueryResult =
        withContext(Dispatchers.IO) {
            val getUser = entries.dbUser.findOne(User::email eq email) ?: return@withContext UpdateUserQueryResult(
                status = false,
                message = "User account not found"
            )
            entries.dbUser.findOneAndUpdate(
                User::email eq email,
                listOf(
                    setValue(User::firstName, user.firstName ?: getUser.firstName),
                    setValue(User::lastName, user.lastName ?: getUser.lastName),
                    setValue(User::age, user.age ?: getUser.age),
                    setValue(User::town, user.town ?: getUser.town),
                    setValue(User::gender, user.gender ?: getUser.gender),
                ),
            ) ?: return@withContext UpdateUserQueryResult(status = false, message = "Failed to update user")
            return@withContext UpdateUserQueryResult(status = true, message = "User updated successfully")
        }

    override suspend fun deleteUser(email: String): DeleteUserQueryResult = withContext(Dispatchers.IO) {
        entries.dbUser.findOneAndDelete(User::email eq email) ?: return@withContext DeleteUserQueryResult(
            status = false,
            message = "Failed to delete user:User not found"
        )
        return@withContext DeleteUserQueryResult(status = true, "User was deleted successfully")
    }

    override suspend fun getUser(email: String): GetUserQueryResult = withContext(Dispatchers.IO) {
        val userData = entries.dbUser.findOne(User::email eq email)
            ?: return@withContext GetUserQueryResult(
                status = false,
                message = "User not found, kindly create an account",
                user = null
            )
        return@withContext GetUserQueryResult(
            status = true,
            message = "User data successfully fetched",
            user = userData.toUserData()
        )
    }

    override suspend fun resetPassword(email: String, newPassword: String, otpCode: String): ResetPasswordQueryResult =
        withContext(Dispatchers.IO) {
            val isOtpCodeValid = entries.dbUser.findOne(User::email eq email)?.otpCode == otpCode
            if (!isOtpCodeValid) return@withContext ResetPasswordQueryResult(
                status = false,
                message = "Otpcode is not valid"
            )
            entries.dbUser.findOneAndUpdate(
                User::email eq email,
                    setValue(User::password, encryptionRepository.hashPassword(newPassword)),
                )
             ?: return@withContext ResetPasswordQueryResult(status = false, message = "Failed to reset password")
            launch {
                entries.dbUser.findOneAndUpdate(
                    User::email eq email,
                    setValue(User::otpCode, null),
                )
            }


            return@withContext ResetPasswordQueryResult(status = true, message = "Password reset was successful")

        }

    override suspend fun signIn(email: String, password: String): SignInQueryResult = withContext(Dispatchers.IO) {
        val getUser = entries.dbUser.findOne(User::email eq email) ?: return@withContext SignInQueryResult(
            status = false,
            message = "Wrong email or password",
            token = ""
        )
        val isPasswordValid = encryptionRepository.verifyHashedPassword(password, getUser.password)
        if (!isPasswordValid) return@withContext SignInQueryResult(status = false, message = "Wrong email or password", token = "")

        val token = JWT.create()
            .withAudience(JWTConfig.jwtAudience)
            .withIssuer(JWTConfig.jwtDomain)
            .withClaim("email", email)
            .withExpiresAt(Date(System.currentTimeMillis() + (60000 * 3600)))
            .sign(Algorithm.HMAC256(JWTConfig.jwtSecret))

        return@withContext SignInQueryResult(status = true, message = "Sign in was successful", token = token)
    }

    override suspend fun sendEmailOtp(email: String): SendEmailOtpResponse = withContext(Dispatchers.IO) {
        val code = generateOtpRepository.generateOtp()
        val result = entries.dbUser.updateOne(User::email eq email, setValue(User::otpCode, code)).wasAcknowledged()
        if (!result) return@withContext SendEmailOtpResponse(status = false, message = "Email entered does not exists")
        return@withContext sendEmailRepository.sendEmailOtp(email = email, otpCode = code)
    }


}

suspend fun main() {
   // println(UserRepositoryImpl().sendEmailOtp(email = "dev.pasaka@gmail.com"))

    //println(UserRepositoryImpl().signIn(email = "dev.pasaka@gmail.com", "pasaka001"))
}
