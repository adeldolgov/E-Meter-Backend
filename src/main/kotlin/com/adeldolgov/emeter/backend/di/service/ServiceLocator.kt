package com.adeldolgov.emeter.backend.di.service

import com.adeldolgov.emeter.backend.base.database.DatabaseProvider
import com.adeldolgov.emeter.backend.feature.counter.data.service.CounterApiService
import com.adeldolgov.emeter.backend.feature.counter.data.service.CounterApiServiceImpl
import com.adeldolgov.emeter.backend.feature.scores.data.service.ScoreApiService
import com.adeldolgov.emeter.backend.feature.scores.data.service.ScoreApiServiceImpl
import com.adeldolgov.emeter.backend.feature.user.data.service.UserApiService
import com.adeldolgov.emeter.backend.feature.user.data.service.UserApiServiceImpl

object ServiceLocator {

    fun provideUserApiService(databaseProvider: DatabaseProvider): UserApiService {
        return UserApiServiceImpl(databaseProvider)
    }

    fun provideScoreApiService(databaseProvider: DatabaseProvider): ScoreApiService {
        return ScoreApiServiceImpl(databaseProvider)
    }

    fun provideCounterApiService(databaseProvider: DatabaseProvider): CounterApiService {
        return CounterApiServiceImpl(databaseProvider)
    }

    fun provideServiceProvider(): ServiceProvider {
        return ServiceProviderImpl()
    }
}
