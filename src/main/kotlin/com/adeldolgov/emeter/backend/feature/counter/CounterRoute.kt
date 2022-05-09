package com.adeldolgov.emeter.backend.feature.counter

import com.adeldolgov.emeter.backend.base.AUTH_NAME_COUNTER
import com.adeldolgov.emeter.backend.base.auth.CounterIdPrincipalForCounter
import com.adeldolgov.emeter.backend.base.auth.UserIdPrincipalForUser
import com.adeldolgov.emeter.backend.di.domain.DomainProvider
import com.adeldolgov.emeter.backend.feature.counter.domain.entities.Counter
import com.adeldolgov.emeter.backend.feature.counter.domain.entities.CounterScore
import com.adeldolgov.emeter.backend.feature.counter.domain.entities.CounterWithToken
import com.adeldolgov.emeter.backend.feature.counter.domain.entities.CreateCounterRequest
import com.adeldolgov.emeter.backend.util.SuccessResponse
import com.adeldolgov.emeter.backend.util.getBodyContent
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.locations.*
import io.ktor.locations.post
import io.ktor.response.*
import io.ktor.routing.*

@OptIn(KtorExperimentalLocationsAPI::class)
fun Application.counterRoutes(domainProvider: DomainProvider) {
    routing {
        authenticate {
            post<CreateCounter> {
                val principal = call.authentication.principal<UserIdPrincipalForUser>()!!
                val createCounterRequest = getBodyContent<CreateCounterRequest>()
                val response = domainProvider.provideCreateCounterUseCase().invoke(principal.userId, createCounterRequest)
                call.respond<SuccessResponse<CounterWithToken>>(response as SuccessResponse<CounterWithToken>)
            }
            get<GetCountersForUser> {
                val principal = call.authentication.principal<UserIdPrincipalForUser>()!!
                val response = domainProvider.provideGetCountersForUserUseCase().invoke(principal.userId, call.request.queryParameters["millis"]?.toLong() ?: 0)
                call.respond<SuccessResponse<List<Counter>>>(response as SuccessResponse<List<Counter>>)
            }
            get<GetCounterScoresForUser> {
                val principal = call.authentication.principal<UserIdPrincipalForUser>()!!
                val counterId = call.request.queryParameters["counterId"].orEmpty()
                val from = call.request.queryParameters["from"]?.toLong() ?: 0
                val to = call.request.queryParameters["to"]?.toLong() ?: 0
                val response = domainProvider.provideGetCounterScoresForUserUseCase().invoke(principal.userId, counterId, from, to)
                call.respond<SuccessResponse<List<CounterScore>>>(response as SuccessResponse<List<CounterScore>>)
            }
        }
        authenticate(AUTH_NAME_COUNTER) {
            post<TickCounter> {
                val principal = call.authentication.principal<CounterIdPrincipalForCounter>()!!
                val response = domainProvider.provideTickCounterUseCase().invoke(principal.counterId)
                call.respond<SuccessResponse<Boolean>>(response as SuccessResponse<Boolean>)
            }
        }
    }
}
