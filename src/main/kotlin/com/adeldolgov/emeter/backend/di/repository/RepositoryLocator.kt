package com.adeldolgov.emeter.backend.di.repository

import com.adeldolgov.emeter.backend.base.auth.JwtConfig
import com.adeldolgov.emeter.backend.di.ConfigLocator
import com.adeldolgov.emeter.backend.di.service.ServiceLocator
import com.adeldolgov.emeter.backend.feature.auth.domain.repository.AuthRepository
import com.adeldolgov.emeter.backend.feature.auth.data.AuthRepositoryImpl
import com.adeldolgov.emeter.backend.feature.counter.data.CounterRepositoryImpl
import com.adeldolgov.emeter.backend.feature.counter.data.mappers.CounterApiToCounterMapper
import com.adeldolgov.emeter.backend.feature.counter.data.mappers.CreateCounterRequestToCounterApiMapper
import com.adeldolgov.emeter.backend.feature.counter.data.service.CounterApiService
import com.adeldolgov.emeter.backend.feature.counter.domain.repository.CounterRepository
import com.adeldolgov.emeter.backend.feature.scores.data.service.ScoreApiService
import com.adeldolgov.emeter.backend.feature.user.domain.repository.UserRepository
import com.adeldolgov.emeter.backend.feature.user.data.UserRepositoryImpl
import com.adeldolgov.emeter.backend.feature.user.data.mappers.UserApiToUserMapper
import com.adeldolgov.emeter.backend.feature.user.data.service.UserApiService

object RepositoryLocator {

    fun provideAuthRepository(userApiService: UserApiService): AuthRepository {
        return AuthRepositoryImpl(
            userApiService,
            JwtConfig.instance,
            ConfigLocator.provideExceptionHandler()
        )
    }

    fun provideUserRepository(userApiService: UserApiService): UserRepository {
        return UserRepositoryImpl(
            userApiService,
            ConfigLocator.provideExceptionHandler(),
            UserApiToUserMapper(),
        )
    }

    fun provideCounterRepository(counterApiService: CounterApiService, scoreApiService: ScoreApiService): CounterRepository {
        return CounterRepositoryImpl(
            counterApiService,
            scoreApiService,
            CreateCounterRequestToCounterApiMapper(),
            CounterApiToCounterMapper(),
            ConfigLocator.provideExceptionHandler(),
        )
    }

    fun provideRepositoryProvider(): RepositoryProvider {
        return RepositoryProviderImpl(ServiceLocator.provideServiceProvider())
    }
}
