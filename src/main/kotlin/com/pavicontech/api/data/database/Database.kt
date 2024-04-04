package com.pavicontech.api.data.database

import com.mongodb.client.MongoDatabase
import com.pavicontech.api.data.config.DatabaseConfig
import org.litote.kmongo.KMongo

object Database {
    private val client = KMongo.createClient(DatabaseConfig.databaseUrl)
    /** Database Instantiation of client*/
    val database: MongoDatabase = client.getDatabase(DatabaseConfig.databaseName)
}