package com.adeldolgov.emeter.backend.base

import com.adeldolgov.emeter.backend.base.serializers.BigDecimalAsStringSerializer
import com.adeldolgov.emeter.backend.di.domain.DomainLocator
import com.adeldolgov.emeter.backend.feature.auth.authRoutes
import com.adeldolgov.emeter.backend.feature.user.userRoutes
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.locations.*
import io.ktor.routing.*
import io.ktor.serialization.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual

val domainLocator = DomainLocator

fun Application.configureRoutingAndSerialization() {
    install(Locations)
    install(Routing)
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                serializersModule = SerializersModule {
                    contextual(BigDecimalAsStringSerializer)
                }
            }
        )
    }

    routing {
        userRoutes(domainLocator.provideDomainProvider())
        authRoutes(domainLocator.provideDomainProvider())
    }
}
