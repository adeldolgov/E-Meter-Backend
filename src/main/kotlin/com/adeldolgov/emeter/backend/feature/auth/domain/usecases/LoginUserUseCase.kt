package com.adeldolgov.emeter.backend.feature.auth.domain.usecases

import com.adeldolgov.emeter.backend.feature.auth.domain.entities.AuthRequest
import com.adeldolgov.emeter.backend.feature.auth.domain.repository.AuthRepository
import com.adeldolgov.emeter.backend.util.SuccessResponse

interface LoginUserUseCase {

    suspend operator fun invoke(authRequest: AuthRequest): SuccessResponse<String>
}

internal class LoginUserUseCaseImpl(
    private val authRepository: AuthRepository
) : LoginUserUseCase {

    override suspend fun invoke(authRequest: AuthRequest): SuccessResponse<String> {
        return authRepository.loginUser(authRequest)
    }
}
