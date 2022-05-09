package com.adeldolgov.emeter.backend.feature.user.data.mappers

import com.adeldolgov.emeter.backend.feature.user.data.service.entities.UserApi
import com.adeldolgov.emeter.backend.feature.user.domain.entities.User

internal class UserApiToUserMapper : (UserApi) -> User {

    override fun invoke(userApi: UserApi): User {
        return User(
            username = userApi.username,
            id = userApi.id,
            firstname = userApi.firstname,
            lastname = userApi.lastname
        )
    }
}