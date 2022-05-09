package com.adeldolgov.emeter.backend.base.database

import org.litote.kmongo.coroutine.CoroutineDatabase

interface DatabaseProvider {

    val initializeName: String

    val database: CoroutineDatabase
}
