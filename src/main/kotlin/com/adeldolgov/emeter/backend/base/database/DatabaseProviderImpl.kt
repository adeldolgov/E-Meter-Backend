package com.adeldolgov.emeter.backend.base.database

import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

internal class DatabaseProviderImpl(private val clientName: String) : DatabaseProvider {

    private val mongoClient: CoroutineClient
        get() = KMongo.createClient().coroutine

    override val initializeName: String
        get() = clientName

    override val database: CoroutineDatabase by lazy {
        mongoClient.getDatabase(initializeName)
    }
}
