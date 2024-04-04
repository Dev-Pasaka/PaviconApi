package com.pavicontech.api.data.remote

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json
import org.litote.kmongo.json

object HttpClient {

    val client = HttpClient(CIO) {
        /*install(ContentNegotiation) {

            *//*Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true // Ignore unknown keys in JSON
            }*//*
        }*/
        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
        engine {
            // Set timeout
            requestTimeout = 10000 // 5000 milliseconds = 5 seconds

        }
    }
}