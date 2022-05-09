package com.adeldolgov.emeter.backend.feature.user.domain.usecases

import com.adeldolgov.emeter.backend.feature.user.domain.entities.UpdateUserRequest
import com.adeldolgov.emeter.backend.feature.user.domain.repository.UserRepository
import com.adeldolgov.emeter.backend.util.SuccessResponse

interface UpdateCurrentUserUseCase {

    suspend operator fun invoke(
        currentUserId: String,
        updateUserRequest: UpdateUserRequest
    ): SuccessResponse<Boolean>
}

internal class UpdateCurrentUserUseCaseImpl(private val userRepository: UserRepository) : UpdateCurrentUserUseCase {

    override suspend fun invoke(
        currentUserId: String,
        updateUserRequest: UpdateUserRequest
    ): SuccessResponse<Boolean> {
        return userRepository.updateUser(currentUserId, updateUserRequest)
    }
}
