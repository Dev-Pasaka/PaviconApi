package com.pavicontech.api.data.database

import com.mongodb.client.MongoDatabase
import com.pavicontech.api.domain.model.User
import org.litote.kmongo.getCollection

object Entries {
    private val database:MongoDatabase = Database.database


    val dbUser = database.getCollection<User>()
}