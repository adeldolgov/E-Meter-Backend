package com.adeldolgov.emeter.backend.feature.user.data.service

import com.adeldolgov.emeter.backend.base.database.DatabaseProvider
import com.adeldolgov.emeter.backend.feature.user.data.service.entities.UserApi
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

internal class UserApiServiceImpl(
    private val databaseProvider: DatabaseProvider
) : UserApiService {

    private val userCollection: CoroutineCollection<UserApi>
        get() = databaseProvider.database.getCollection(collectionName = "user")

    override suspend fun insertUser(user: UserApi): Boolean {
        return userCollection.insertOne(user).wasAcknowledged()
    }

    override suspend fun findUserByUsername(username: String): UserApi? {
        return userCollection.findOne(UserApi::username eq username)
    }

    override suspend fun findUserByUserId(userId: String): UserApi? {
        return userCollection.find(UserApi::id eq userId).first()
    }

    override suspend fun updateUserById(user: UserApi, userId: String): Boolean {
        return userCollection.updateOneById(userId, user, updateOnlyNotNullProperties = true).wasAcknowledged()
    }
}
