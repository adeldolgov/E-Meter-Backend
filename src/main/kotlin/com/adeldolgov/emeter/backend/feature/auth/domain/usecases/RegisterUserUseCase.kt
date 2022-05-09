package com.adeldolgov.emeter.backend.feature.auth.domain.usecases

import com.adeldolgov.emeter.backend.feature.auth.domain.entities.AuthRequest
import com.adeldolgov.emeter.backend.feature.auth.domain.repository.AuthRepository
import com.adeldolgov.emeter.backend.util.SuccessResponse

interface RegisterUserUseCase {

    suspend operator fun invoke(authRequest: AuthRequest): SuccessResponse<String>
}

internal class RegisterUserUseCaseImpl(
    private val authRepository: AuthRepository
) : RegisterUserUseCase {

    override suspend fun invoke(authRequest: AuthRequest): SuccessResponse<String> {
        return authRepository.registerUser(authRequest)
    }
}
