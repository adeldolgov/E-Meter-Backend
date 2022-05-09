package com.adeldolgov.emeter.backend.di.domain

import com.adeldolgov.emeter.backend.di.repository.RepositoryLocator
import com.adeldolgov.emeter.backend.feature.auth.domain.usecases.RegisterUserUseCase
import com.adeldolgov.emeter.backend.feature.auth.domain.usecases.LoginUserUseCase
import com.adeldolgov.emeter.backend.feature.auth.domain.usecases.LoginUserUseCaseImpl
import com.adeldolgov.emeter.backend.feature.auth.domain.usecases.RegisterUserUseCaseImpl
import com.adeldolgov.emeter.backend.feature.auth.domain.repository.AuthRepository
import com.adeldolgov.emeter.backend.feature.user.domain.usecases.CurrentUserDetailUseCase
import com.adeldolgov.emeter.backend.feature.user.domain.usecases.UpdateCurrentUserUseCase
import com.adeldolgov.emeter.backend.feature.user.domain.repository.UserRepository
import com.adeldolgov.emeter.backend.feature.user.domain.usecases.CurrentUserDetailUseCaseImpl
import com.adeldolgov.emeter.backend.feature.user.domain.usecases.UpdateCurrentUserUseCaseImpl

/**
 * [DomainLocator] creates a collection of instances of all the domains
 */
object DomainLocator {
    /**
     * [provideCreateUserAuthTokenUseCase] provides the [RegisterUserUseCase] instance to [DomainProvider]
     */
    fun provideCreateUserAuthTokenUseCase(authRepository: AuthRepository): RegisterUserUseCase {
        return RegisterUserUseCaseImpl(authRepository)
    }

    /**
     * [provideLoginUserUseCase] provides the [LoginUserUseCase] instance to [DomainProvider]
     */
    fun provideLoginUserUseCase(authRepository: AuthRepository): LoginUserUseCase {
        return LoginUserUseCaseImpl(authRepository)
    }

    /**
     * [provideCurrentUserDetailUseCase] provides the [CurrentUserDetailUseCase] instance to [DomainProvider]
     */
    fun provideCurrentUserDetailUseCase(userRepository: UserRepository): CurrentUserDetailUseCase {
        return CurrentUserDetailUseCaseImpl(userRepository)
    }

    /**
     * [provideUpdateCurrentUserUseCase] provides the [UpdateCurrentUserUseCase] instance to [DomainProvider]
     */
    fun provideUpdateCurrentUserUseCase(userRepository: UserRepository): UpdateCurrentUserUseCase {
        return UpdateCurrentUserUseCaseImpl(userRepository)
    }

    /**
     * [provideDomainProvider] provides the [DomainProvider] instance
     */
    fun provideDomainProvider(): DomainProvider {
        return DomainProviderImpl(RepositoryLocator.provideRepositoryProvider())
    }
}
