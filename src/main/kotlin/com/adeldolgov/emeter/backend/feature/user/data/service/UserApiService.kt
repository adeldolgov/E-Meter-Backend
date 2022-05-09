package com.adeldolgov.emeter.backend.feature.user.data.service

import com.adeldolgov.emeter.backend.feature.user.data.service.entities.UserApi

interface UserApiService {

    suspend fun insertUser(user: UserApi): Boolean

    suspend fun updateUserById(user: UserApi, userId: String): Boolean

    suspend fun findUserByUsername(username: String): UserApi?

    suspend fun findUserByUserId(userId: String): UserApi?
}
