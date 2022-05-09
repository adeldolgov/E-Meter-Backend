package com.adeldolgov.emeter.backend.base.http

interface ExceptionHandler {

    fun respondWithBadRequestException(message: String?): Exception

    fun respondWithUnauthorizedException(message: String?): Exception

    fun respondWithNotFoundException(message: String?): Exception

    fun respondWithAlreadyExistException(message: String?): Exception

    fun respondWithGenericException(message: String?): Exception

    fun respondWithSomethingWentWrongException(message: String = "Something went wrong"): Exception
}
