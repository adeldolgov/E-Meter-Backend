package com.adeldolgov.emeter.backend.feature.auth.domain.repository

import com.adeldolgov.emeter.backend.feature.auth.domain.entities.AuthRequest
import com.adeldolgov.emeter.backend.util.SuccessResponse

/**
 * [AuthRepository] is a collection of all the functions to Auth module
 */
interface AuthRepository {

    suspend fun registerUser(authRequest: AuthRequest): SuccessResponse<String>

    suspend fun loginUser(authRequest: AuthRequest): SuccessResponse<String>
}
