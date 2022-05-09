package com.adeldolgov.emeter.backend.feature.user.domain.usecases

import com.adeldolgov.emeter.backend.feature.user.domain.entities.User
import com.adeldolgov.emeter.backend.feature.user.domain.repository.UserRepository
import com.adeldolgov.emeter.backend.util.SuccessResponse

interface CurrentUserDetailUseCase {

    suspend operator fun invoke(id: String): SuccessResponse<User>
}

internal class CurrentUserDetailUseCaseImpl(private val userRepository: UserRepository) : CurrentUserDetailUseCase {

    override suspend fun invoke(id: String): SuccessResponse<User> {
        return userRepository.findUserById(id)
    }
}
