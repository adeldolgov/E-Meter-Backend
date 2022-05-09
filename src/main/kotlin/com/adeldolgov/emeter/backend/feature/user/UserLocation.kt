package com.adeldolgov.emeter.backend.feature.user

import io.ktor.locations.*

@KtorExperimentalLocationsAPI
@Location(UserConstant.CURRENT_USER)
class CurrentUser

@KtorExperimentalLocationsAPI
@Location(UserConstant.UPDATE_USER)
class UpdateUser
