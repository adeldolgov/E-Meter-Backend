package com.adeldolgov.emeter.backend.di.repository

import com.adeldolgov.emeter.backend.di.service.ServiceProvider
import com.adeldolgov.emeter.backend.feature.auth.domain.repository.AuthRepository
import com.adeldolgov.emeter.backend.feature.counter.domain.repository.CounterRepository
import com.adeldolgov.emeter.backend.feature.user.domain.repository.UserRepository

class RepositoryProviderImpl(private val serviceProvider: ServiceProvider) : RepositoryProvider {

    override fun provideAuthRepository(): AuthRepository {
        return RepositoryLocator.provideAuthRepository(
            serviceProvider.provideUserApiService()
        )
    }

    override fun provideUserRepository(): UserRepository {
        return RepositoryLocator.provideUserRepository(serviceProvider.provideUserApiService())
    }

    override fun provideCounterRepository(): CounterRepository {
        return RepositoryLocator.provideCounterRepository(serviceProvider.provideCounterApiService(), serviceProvider.provideScoreApiService())
    }
}
