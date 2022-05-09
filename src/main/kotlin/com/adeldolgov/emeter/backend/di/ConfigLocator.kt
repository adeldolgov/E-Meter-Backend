package com.adeldolgov.emeter.backend.di

import com.adeldolgov.emeter.backend.base.auth.JwtConfig
import com.adeldolgov.emeter.backend.base.http.ExceptionHandler
import com.adeldolgov.emeter.backend.base.http.ExceptionHandlerImpl

object ConfigLocator {

    fun provideJwtConfig() {
        return JwtConfig.initialize("e-meter-secret-dolgov")
    }

    fun provideExceptionHandler(): ExceptionHandler {
        return ExceptionHandlerImpl()
    }
}
