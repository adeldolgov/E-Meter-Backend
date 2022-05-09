package com.adeldolgov.emeter.backend.feature.auth

import com.adeldolgov.emeter.backend.di.domain.DomainProvider
import com.adeldolgov.emeter.backend.feature.auth.domain.entities.AuthRequest
import com.adeldolgov.emeter.backend.util.SuccessResponse
import com.adeldolgov.emeter.backend.util.getBodyContent
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.locations.post
import io.ktor.response.*
import io.ktor.routing.*

@OptIn(KtorExperimentalLocationsAPI::class)
fun Application.authRoutes(domainProvider: DomainProvider) {
    routing {
        post<LoginUser> {
            val authRequest = getBodyContent<AuthRequest>()
            val response = domainProvider.provideLoginUserUseCase().invoke(authRequest)
            call.respond<SuccessResponse<String>>(response as SuccessResponse<String>)
        }
        post<RegisterUser> {
            val authRequest = getBodyContent<AuthRequest>()
            val response = domainProvider.provideRegisterUserAuthTokenUseCase().invoke(authRequest)
            call.respond<SuccessResponse<String>>(response as SuccessResponse<String>)
        }
    }
}
