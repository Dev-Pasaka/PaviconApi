package com.pavicontech.api.data.config

import io.ktor.server.application.*


object DatabaseConfig {
    val databaseUrl = Config.databaseUrl
    val databaseName = Config.databaseName
}