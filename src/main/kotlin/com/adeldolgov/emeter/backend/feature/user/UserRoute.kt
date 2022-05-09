package com.adeldolgov.emeter.backend.feature.user

import com.adeldolgov.emeter.backend.base.auth.UserIdPrincipalForUser
import com.adeldolgov.emeter.backend.di.domain.DomainProvider
import com.adeldolgov.emeter.backend.feature.user.domain.entities.UpdateUserRequest
import com.adeldolgov.emeter.backend.feature.user.domain.entities.User
import com.adeldolgov.emeter.backend.util.SuccessResponse
import com.adeldolgov.emeter.backend.util.getBodyContent
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.locations.*
import io.ktor.locations.put
import io.ktor.response.*
import io.ktor.routing.*

@OptIn(KtorExperimentalLocationsAPI::class)
fun Application.userRoutes(domainProvider: DomainProvider) {
    routing {
        authenticate {
            get<CurrentUser> {
                val principal = call.authentication.principal<UserIdPrincipalForUser>()!!
                val response = domainProvider.provideCurrentUserDetailUseCase().invoke(principal.userId)
                call.respond<SuccessResponse<User>>(response as SuccessResponse<User>)
            }
            put<UpdateUser> {
                val principal = call.authentication.principal<UserIdPrincipalForUser>()!!
                val updateUserRequest = getBodyContent<UpdateUserRequest>()
                val response = domainProvider.provideUpdateCurrentUserUseCase().invoke(principal.userId, updateUserRequest)
                call.respond<SuccessResponse<Boolean>>(response as SuccessResponse<Boolean>)
            }
        }
    }
}
