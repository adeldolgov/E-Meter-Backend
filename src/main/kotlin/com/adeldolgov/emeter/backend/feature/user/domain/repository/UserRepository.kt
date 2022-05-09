package com.adeldolgov.emeter.backend.feature.user.domain.repository

import com.adeldolgov.emeter.backend.feature.user.domain.entities.UpdateUserRequest
import com.adeldolgov.emeter.backend.feature.user.domain.entities.User
import com.adeldolgov.emeter.backend.util.BaseResponse
import com.adeldolgov.emeter.backend.util.SuccessResponse

interface UserRepository {

    suspend fun findUserById(userId: String?): SuccessResponse<User>

    suspend fun updateUser(currentUserId: String, updateUserRequest: UpdateUserRequest): SuccessResponse<Boolean>
}
