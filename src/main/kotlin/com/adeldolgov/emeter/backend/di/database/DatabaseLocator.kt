package com.adeldolgov.emeter.backend.di.database

import com.adeldolgov.emeter.backend.base.database.DatabaseProvider
import com.adeldolgov.emeter.backend.base.database.DatabaseProviderImpl

object DatabaseLocator {

    private fun provideClientName(): String {
        return "e-meter-mongo"
    }

    fun provideDatabase(): DatabaseProvider {
        return DatabaseProviderImpl(provideClientName())
    }
}
