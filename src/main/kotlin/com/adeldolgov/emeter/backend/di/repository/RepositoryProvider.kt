package com.adeldolgov.emeter.backend.di.repository

import com.adeldolgov.emeter.backend.feature.auth.domain.repository.AuthRepository
import com.adeldolgov.emeter.backend.feature.counter.domain.repository.CounterRepository
import com.adeldolgov.emeter.backend.feature.user.domain.repository.UserRepository

interface RepositoryProvider {

    fun provideAuthRepository(): AuthRepository

    fun provideUserRepository(): UserRepository

    fun provideCounterRepository(): CounterRepository
}
