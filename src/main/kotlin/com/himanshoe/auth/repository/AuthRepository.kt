package com.himanshoe.auth.repository

import com.himanshoe.auth.AuthRequest
import com.himanshoe.util.BaseResponse

/**
 * [AuthRepository] is a collection of all the functions to Auth module
 */
interface AuthRepository {

    suspend fun createToken(authRequest: AuthRequest): BaseResponse<Any>

    suspend fun loginUser(authRequest: AuthRequest): BaseResponse<Any>
}
