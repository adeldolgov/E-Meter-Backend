package com.adeldolgov.emeter.backend.di.domain

import com.adeldolgov.emeter.backend.di.repository.RepositoryProvider
import com.adeldolgov.emeter.backend.feature.auth.domain.usecases.LoginUserUseCase
import com.adeldolgov.emeter.backend.feature.auth.domain.usecases.RegisterUserUseCase
import com.adeldolgov.emeter.backend.feature.counter.domain.usecases.CreateCounterUseCase
import com.adeldolgov.emeter.backend.feature.counter.domain.usecases.GetCountersForUserUseCase
import com.adeldolgov.emeter.backend.feature.counter.domain.usecases.TickCounterUseCase
import com.adeldolgov.emeter.backend.feature.user.domain.usecases.CurrentUserDetailUseCase
import com.adeldolgov.emeter.backend.feature.user.domain.usecases.UpdateCurrentUserUseCase

class DomainProviderImpl(private val repositoryProvider: RepositoryProvider) : DomainProvider {

    override fun provideRegisterUserAuthTokenUseCase(): RegisterUserUseCase {
        return DomainLocator.provideCreateUserAuthTokenUseCase(repositoryProvider.provideAuthRepository())
    }

    override fun provideLoginUserUseCase(): LoginUserUseCase {
        return DomainLocator.provideLoginUserUseCase(repositoryProvider.provideAuthRepository())
    }

    override fun provideCurrentUserDetailUseCase(): CurrentUserDetailUseCase {
        return DomainLocator.provideCurrentUserDetailUseCase(repositoryProvider.provideUserRepository())
    }

    override fun provideUpdateCurrentUserUseCase(): UpdateCurrentUserUseCase {
        return DomainLocator.provideUpdateCurrentUserUseCase(repositoryProvider.provideUserRepository())
    }

    override fun provideCreateCounterUseCase(): CreateCounterUseCase {
        return DomainLocator.provideCreateCounterUseCase(repositoryProvider.provideCounterRepository())
    }

    override fun provideGetCountersForUserUseCase(): GetCountersForUserUseCase {
        return DomainLocator.provideGetCountersForUserUseCase(repositoryProvider.provideCounterRepository())
    }

    override fun provideTickCounterUseCase(): TickCounterUseCase {
        return DomainLocator.provideTickCounterUseCase(repositoryProvider.provideCounterRepository())
    }
}
