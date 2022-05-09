package com.adeldolgov.emeter.backend.di.service

import com.adeldolgov.emeter.backend.feature.counter.data.service.CounterApiService
import com.adeldolgov.emeter.backend.feature.scores.data.service.ScoreApiService
import com.adeldolgov.emeter.backend.feature.user.data.service.UserApiService

interface ServiceProvider {

    fun provideUserApiService(): UserApiService

    fun provideScoreApiService(): ScoreApiService

    fun provideCounterApiService(): CounterApiService
}
