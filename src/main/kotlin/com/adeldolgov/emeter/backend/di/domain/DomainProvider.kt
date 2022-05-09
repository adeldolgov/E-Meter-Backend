package com.adeldolgov.emeter.backend.di.domain

import com.adeldolgov.emeter.backend.feature.auth.domain.usecases.RegisterUserUseCase
import com.adeldolgov.emeter.backend.feature.auth.domain.usecases.LoginUserUseCase
import com.adeldolgov.emeter.backend.feature.user.domain.usecases.CurrentUserDetailUseCase
import com.adeldolgov.emeter.backend.feature.user.domain.usecases.UpdateCurrentUserUseCase

interface DomainProvider {

    fun provideRegisterUserAuthTokenUseCase(): RegisterUserUseCase

    fun provideLoginUserUseCase(): LoginUserUseCase

    fun provideCurrentUserDetailUseCase(): CurrentUserDetailUseCase

    fun provideUpdateCurrentUserUseCase(): UpdateCurrentUserUseCase
}
