package com.adeldolgov.emeter.backend.base.auth

import io.ktor.auth.*

data class UserIdPrincipalForUser(val userId: String) : Principal
