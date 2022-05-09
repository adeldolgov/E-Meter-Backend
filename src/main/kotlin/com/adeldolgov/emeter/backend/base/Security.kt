package com.adeldolgov.emeter.backend.base

import com.adeldolgov.emeter.backend.base.auth.JwtConfig
import com.adeldolgov.emeter.backend.base.auth.UserIdPrincipalForUser
import io.ktor.application.*
import io.ktor.auth.Authentication
import io.ktor.auth.jwt.jwt

fun Application.configureSecurity() {
    install(Authentication) {
        jwt {
            verifier(JwtConfig.instance.verifier)
            validate {
                val claim = it.payload.getClaim(JwtConfig.ClAIM).asString()
                if (claim != null) {
                    UserIdPrincipalForUser(claim)
                } else {
                    null
                }
            }
        }
    }
}
