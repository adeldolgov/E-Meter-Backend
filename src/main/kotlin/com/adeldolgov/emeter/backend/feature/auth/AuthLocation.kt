package com.adeldolgov.emeter.backend.feature.auth

import io.ktor.locations.*

@KtorExperimentalLocationsAPI
@Location(AuthConstant.LOGIN)
class LoginUser

@KtorExperimentalLocationsAPI
@Location(AuthConstant.REGISTER)
class RegisterUser
