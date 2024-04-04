package com.pavicontech.api.data.repository

import com.pavicontech.api.data.config.Config
import com.pavicontech.api.data.remote.apiResponses.SendEmailOtpResponse
import com.pavicontech.api.domain.model.EmailOtpBody
import com.pavicontech.api.domain.repository.SendEmailRepository
import com.sendgrid.Method
import com.sendgrid.Request
import com.sendgrid.SendGrid
import com.sendgrid.helpers.mail.Mail
import com.sendgrid.helpers.mail.objects.Content
import com.sendgrid.helpers.mail.objects.Email
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.withContext

import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


class SendEmailRepositoryImpl() : SendEmailRepository {
    override suspend fun sendEmailOtp(email:String, otpCode: String): SendEmailOtpResponse  {

        val properties = Properties()
        properties["mail.smtp.host"] = Config.SMTP_SERVER_ADDRESS // SMTP server address
        properties["mail.smtp.starttls.enable"] = "true"
        properties["mail.smtp.port"] = "587" // Port for sending emails (587 for TLS, 465 for SSL)
        properties["mail.smtp.auth"] = "true" // Enable authentication

        val session = Session.getInstance(properties, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(Config.USERNAME, Config.PASSWORD)
            }
        }
        )

        return try {
            // Create a MimeMessage object
            val message = MimeMessage(session)

            // Set the sender and receiver email addresses
            message.setFrom(InternetAddress(Config.USERNAME, "Coinx"))
            message.addRecipient(Message.RecipientType.TO, InternetAddress(email))

            // Set the email subject and content
            message.subject = "Password Reset Request"
            message.setContent(createPasswordResetEmail(receiverEmail  =email, otp = otpCode), "text/html")

            // Send the email
            Transport.send(message)

            SendEmailOtpResponse(status = true, message = "Email sent successfully, Kindly check your email.")
        } catch (e: MessagingException) {
            SendEmailOtpResponse(status = false, message = "Failed to send email")
        }
    }

   private fun createPasswordResetEmail(receiverEmail: String, otp: String): String {
        val body = """
        <html>
        <head>
            <style>
                body {
                    font-family: Arial, sans-serif;
                }
                .container {
                    max-width: 600px;
                    margin: 0 auto;
                }
                .header {
                    background-color: #007bff;
                    color: white;
                    padding: 20px;
                    text-align: center;
                }
                .content {
                    padding: 20px;
                }
                .footer {
                    background-color: #f8f9fa;
                    padding: 20px;
                    text-align: center;
                }
            </style>
        </head>
        <body>
            <div class="container">
                <div class="header">
                    <h1>Password Reset Request</h1>
                </div>
                <div class="content">
                    <p>Hello,</p>
                    <p>You have requested to reset your password. Below is your one-time password (OTP):</p>
                    <p style="font-size: 24px; font-weight: bold;">$otp</p>
                    <p>Please use this OTP to reset your password.</p>
                    <p>If you didn't request this password reset, you can safely ignore this email.</p>
                    <p>Thank you!</p>
                </div>
                <div class="footer">
                    <p>This email was sent automatically. Please do not reply to this email.</p>
                </div>
            </div>
        </body>
        </html>
    """.trimIndent()

        // If you want to use plain text instead of HTML, you can return the body without HTML tags.
        // Example: return "Hello,\n\nYou have requested to reset your password. Below is your one-time password (OTP):\n$otp\n\nPlease use this OTP to reset your password.\n\nIf you didn't request this password reset, you can safely ignore this email.\n\nThank you!\n\nThis email was sent automatically. Please do not reply to this email."

        return body
    }
}





