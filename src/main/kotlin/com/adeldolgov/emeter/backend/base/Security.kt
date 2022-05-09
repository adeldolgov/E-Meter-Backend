package com.adeldolgov.emeter.backend.base

import com.adeldolgov.emeter.backend.base.auth.CounterIdPrincipalForCounter
import com.adeldolgov.emeter.backend.base.auth.JwtConfig
import com.adeldolgov.emeter.backend.base.auth.UserIdPrincipalForUser
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*

fun Application.configureSecurity() {
    install(Authentication) {
        jwt {
            verifier(JwtConfig.instance.verifier)
            validate {
                val claim = it.payload.getClaim(JwtConfig.ClAIM_USER_ID).asString()
                if (claim != null) {
                    UserIdPrincipalForUser(claim)
                } else {
                    null
                }
            }
        }
        jwt(AUTH_NAME_COUNTER) {
            verifier(JwtConfig.instance.verifier)
            validate {
                val claim = it.payload.getClaim(JwtConfig.ClAIM_COUNTER_ID).asString()
                if (claim != null) {
                    CounterIdPrincipalForCounter(claim)
                } else {
                    null
                }
            }
        }
    }
}

internal const val AUTH_NAME_COUNTER = "auth-counter"
