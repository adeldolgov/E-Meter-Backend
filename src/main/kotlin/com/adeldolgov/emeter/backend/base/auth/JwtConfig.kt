package com.adeldolgov.emeter.backend.base.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm

class JwtConfig private constructor(secret: String) {

    private val algorithm = Algorithm.HMAC256(secret)

    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(ISSUER)
        .withAudience(AUDIENCE)
        .build()

    fun makeAccessTokenForUser(userId: String): String = JWT
        .create()
        .withIssuer(ISSUER)
        .withAudience(AUDIENCE)
        .withClaim(ClAIM_USER_ID, userId)
        .sign(algorithm)

    fun makeAccessTokenForCounter(counterId: String): String = JWT
        .create()
        .withIssuer(ISSUER)
        .withAudience(AUDIENCE)
        .withClaim(ClAIM_COUNTER_ID, counterId)
        .sign(algorithm)

    companion object {

        lateinit var instance: JwtConfig
            private set

        fun initialize(secret: String) {
            synchronized(this) {
                if (!this::instance.isInitialized) {
                    instance = JwtConfig(secret)
                }
            }
        }

        private const val ISSUER = "emeter-backend-issuer"
        private const val AUDIENCE = "emeter-backend-audience"
        const val ClAIM_USER_ID = "userId"
        const val ClAIM_COUNTER_ID = "counterId"
    }
}
