package com.adeldolgov.emeter.backend.di.service

import com.adeldolgov.emeter.backend.di.database.DatabaseLocator
import com.adeldolgov.emeter.backend.feature.counter.data.service.CounterApiService
import com.adeldolgov.emeter.backend.feature.scores.data.service.ScoreApiService
import com.adeldolgov.emeter.backend.feature.user.data.service.UserApiService

class ServiceProviderImpl : ServiceProvider {

    override fun provideUserApiService(): UserApiService {
        return ServiceLocator.provideUserApiService(DatabaseLocator.provideDatabase())
    }

    override fun provideScoreApiService(): ScoreApiService {
        return ServiceLocator.provideScoreApiService(DatabaseLocator.provideDatabase())
    }

    override fun provideCounterApiService(): CounterApiService {
        return ServiceLocator.provideCounterApiService(DatabaseLocator.provideDatabase())
    }
}
