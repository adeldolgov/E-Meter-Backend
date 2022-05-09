package com.adeldolgov.emeter.backend.feature.auth.data

import com.adeldolgov.emeter.backend.base.auth.JwtConfig
import com.adeldolgov.emeter.backend.base.http.ExceptionHandler
import com.adeldolgov.emeter.backend.feature.auth.domain.entities.AuthRequest
import com.adeldolgov.emeter.backend.feature.auth.domain.repository.AuthRepository
import com.adeldolgov.emeter.backend.feature.user.data.service.UserApiService
import com.adeldolgov.emeter.backend.feature.user.data.service.entities.UserApi
import com.adeldolgov.emeter.backend.util.SuccessResponse
import com.adeldolgov.emeter.backend.util.checkHashForPassword
import com.adeldolgov.emeter.backend.util.getHashWithSalt
import io.ktor.http.*
import java.util.*

internal class AuthRepositoryImpl(
    private val userApiService: UserApiService,
    private val jwtConfig: JwtConfig,
    private val exceptionHandler: ExceptionHandler
) : AuthRepository {

    override suspend fun registerUser(authRequest: AuthRequest): SuccessResponse<String> {
        return if (userApiService.findUserByUsername(authRequest.username) != null) {
            throw exceptionHandler.respondWithAlreadyExistException(USER_ALREADY_EXIST_MESSAGE)
        } else {
            val hashPassword = getHashWithSalt(authRequest.password)
            val userApi = UserApi(username = authRequest.username, passwordHash = hashPassword, createdAt = Date().toInstant().toEpochMilli())
            val responseIsSuccessful = userApiService.insertUser(userApi)
            when {
                responseIsSuccessful -> SuccessResponse(data = jwtConfig.makeAccessTokenForUser(userApi.id), code = HttpStatusCode.Created)
                else -> throw exceptionHandler.respondWithGenericException(SOMETHING_WENT_WRONG)
            }
        }
    }

    override suspend fun loginUser(authRequest: AuthRequest): SuccessResponse<String> {
        val userApi = userApiService.findUserByUsername(authRequest.username)
        return if (userApi?.passwordHash?.let { checkHashForPassword(authRequest.password, it) } == true) {
            SuccessResponse(data = jwtConfig.makeAccessTokenForUser(userApi.id), code = HttpStatusCode.OK)
        } else {
            throw exceptionHandler.respondWithUnauthorizedException(EITHER_USERNAME_PASSWORD_INCORRECT)
        }
    }

    companion object {

        private const val USER_ALREADY_EXIST_MESSAGE = "User already exists, Please login"
        private const val EITHER_USERNAME_PASSWORD_INCORRECT = "Either username or password is incorrect"
        private const val SOMETHING_WENT_WRONG = "Something went wrong. Please try again"
    }
}
