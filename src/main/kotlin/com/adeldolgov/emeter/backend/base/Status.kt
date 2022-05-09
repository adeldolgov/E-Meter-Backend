package com.adeldolgov.emeter.backend.base

import com.adeldolgov.emeter.backend.util.ExceptionResponse
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<AuthenticationException> { cause ->
            call.respond(ExceptionResponse(HttpStatusCode.Unauthorized, cause.message.toString()))
        }
        exception<AuthorizationException> { cause ->
            call.respond(ExceptionResponse(HttpStatusCode.Forbidden, cause.message.toString()))
        }
        exception<BadRequestException> { cause ->
            call.respond(ExceptionResponse(HttpStatusCode.BadRequest, cause.message.toString()))
        }
        exception<NotFoundException> { cause ->
            call.respond(ExceptionResponse(HttpStatusCode.NotFound, cause.message.toString()))
        }
        exception<ConflictException> { cause ->
            call.respond(ExceptionResponse(HttpStatusCode.Conflict, cause.message.toString()))
        }
        exception<SomethingWentWrongException> { cause ->
            call.respond(ExceptionResponse(HttpStatusCode.ExpectationFailed, cause.message.toString()))
        }
    }
}

class AuthenticationException(message: String?) : RuntimeException(message)

class ConflictException(message: String?) : RuntimeException(message)

class AuthorizationException(message: String?) : RuntimeException(message)

class BadRequestException(message: String?) : RuntimeException(message)

class NotFoundException(message: String?) : RuntimeException(message)

class SomethingWentWrongException(message: String?) : RuntimeException(message)
