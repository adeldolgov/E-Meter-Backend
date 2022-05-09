package com.adeldolgov.emeter.backend.base.http

import com.adeldolgov.emeter.backend.base.BadRequestException
import com.adeldolgov.emeter.backend.base.AuthorizationException
import com.adeldolgov.emeter.backend.base.NotFoundException
import com.adeldolgov.emeter.backend.base.ConflictException
import com.adeldolgov.emeter.backend.base.SomethingWentWrongException

/**
 * Handles the Exceptions and implements the interface [ExceptionHandler]
 */
class ExceptionHandlerImpl : ExceptionHandler {

    override fun respondWithBadRequestException(message: String?): Exception {
        return BadRequestException(message)
    }

    override fun respondWithUnauthorizedException(message: String?): Exception {
        return AuthorizationException(message)
    }

    override fun respondWithNotFoundException(message: String?): Exception {
        return NotFoundException(message)
    }

    override fun respondWithAlreadyExistException(message: String?): Exception {
        return ConflictException(message)
    }

    override fun respondWithGenericException(message: String?): Exception {
        return SomethingWentWrongException(message)
    }

    override fun respondWithSomethingWentWrongException(message: String): Exception {
        return SomethingWentWrongException(message)
    }
}
