package com.adeldolgov.emeter.backend.feature.user.data

import com.adeldolgov.emeter.backend.base.http.ExceptionHandler
import com.adeldolgov.emeter.backend.feature.user.data.mappers.UserApiToUserMapper
import com.adeldolgov.emeter.backend.feature.user.domain.entities.UpdateUserRequest
import com.adeldolgov.emeter.backend.feature.user.domain.entities.User
import com.adeldolgov.emeter.backend.feature.user.data.service.entities.UserApi
import com.adeldolgov.emeter.backend.feature.user.domain.repository.UserRepository
import com.adeldolgov.emeter.backend.feature.user.data.service.UserApiService
import com.adeldolgov.emeter.backend.util.SuccessResponse
import io.ktor.http.*
import java.util.*

internal class UserRepositoryImpl(
    private val userApiService: UserApiService,
    private val exceptionHandler: ExceptionHandler,
    private val userApiToUserMapper: UserApiToUserMapper,
) : UserRepository {

    override suspend fun findUserById(userId: String?): SuccessResponse<User> {
        val userApi: UserApi? = userId?.let { userApiService.findUserByUserId(it) }
        if (userId != null && userApi != null) {
            return SuccessResponse(HttpStatusCode.Found, userApiToUserMapper(userApi))
        } else {
            throw exceptionHandler.respondWithNotFoundException(USER_NOT_FOUND)
        }
    }

    override suspend fun updateUser(currentUserId: String, updateUserRequest: UpdateUserRequest): SuccessResponse<Boolean> {
        val userApi: UserApi? = userApiService.findUserByUserId(currentUserId)
        if (userApi != null) {
            val updatedUser = userApi.copy(
                firstname = updateUserRequest.firstname,
                lastname = updateUserRequest.lastname,
                updatedAt = Date().toInstant().toString()
            )
            val isUpdated = userApiService.updateUserById(updatedUser, currentUserId)
            return SuccessResponse(HttpStatusCode.OK, isUpdated)
        } else {
            throw exceptionHandler.respondWithGenericException(USER_NOT_FOUND)
        }
    }

    companion object {

        private const val USER_NOT_FOUND = "User not found"
    }
}
