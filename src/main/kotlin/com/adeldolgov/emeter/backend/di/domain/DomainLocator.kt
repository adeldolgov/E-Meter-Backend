package com.adeldolgov.emeter.backend.di.domain

import com.adeldolgov.emeter.backend.di.repository.RepositoryLocator
import com.adeldolgov.emeter.backend.feature.auth.domain.repository.AuthRepository
import com.adeldolgov.emeter.backend.feature.auth.domain.usecases.LoginUserUseCase
import com.adeldolgov.emeter.backend.feature.auth.domain.usecases.LoginUserUseCaseImpl
import com.adeldolgov.emeter.backend.feature.auth.domain.usecases.RegisterUserUseCase
import com.adeldolgov.emeter.backend.feature.auth.domain.usecases.RegisterUserUseCaseImpl
import com.adeldolgov.emeter.backend.feature.counter.domain.repository.CounterRepository
import com.adeldolgov.emeter.backend.feature.counter.domain.usecases.*
import com.adeldolgov.emeter.backend.feature.user.domain.repository.UserRepository
import com.adeldolgov.emeter.backend.feature.user.domain.usecases.CurrentUserDetailUseCase
import com.adeldolgov.emeter.backend.feature.user.domain.usecases.CurrentUserDetailUseCaseImpl
import com.adeldolgov.emeter.backend.feature.user.domain.usecases.UpdateCurrentUserUseCase
import com.adeldolgov.emeter.backend.feature.user.domain.usecases.UpdateCurrentUserUseCaseImpl

object DomainLocator {

    fun provideCreateUserAuthTokenUseCase(authRepository: AuthRepository): RegisterUserUseCase {
        return RegisterUserUseCaseImpl(authRepository)
    }

    fun provideLoginUserUseCase(authRepository: AuthRepository): LoginUserUseCase {
        return LoginUserUseCaseImpl(authRepository)
    }

    fun provideCurrentUserDetailUseCase(userRepository: UserRepository): CurrentUserDetailUseCase {
        return CurrentUserDetailUseCaseImpl(userRepository)
    }

    fun provideUpdateCurrentUserUseCase(userRepository: UserRepository): UpdateCurrentUserUseCase {
        return UpdateCurrentUserUseCaseImpl(userRepository)
    }

    fun provideCreateCounterUseCase(counterRepository: CounterRepository): CreateCounterUseCase {
        return CreateCounterUseCaseImpl(counterRepository)
    }

    fun provideGetCountersForUserUseCase(counterRepository: CounterRepository): GetCountersForUserUseCase {
        return GetCountersForUserUseCaseImpl(counterRepository)
    }

    fun provideTickCounterUseCase(counterRepository: CounterRepository): TickCounterUseCase {
        return TickCounterUseCaseImpl(counterRepository)
    }

    fun provideGetCounterScoresForUserUseCase(counterRepository: CounterRepository): GetCounterScoresForUserUseCase {
        return GetCounterScoresForUserUseCaseImpl(counterRepository)
    }

    fun provideDomainProvider(): DomainProvider {
        return DomainProviderImpl(RepositoryLocator.provideRepositoryProvider())
    }
}
