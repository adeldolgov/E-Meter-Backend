package com.adeldolgov.emeter.backend

import com.adeldolgov.emeter.backend.base.http.configureHTTP
import com.adeldolgov.emeter.backend.base.configureRoutingAndSerialization
import com.adeldolgov.emeter.backend.base.configureSecurity
import com.adeldolgov.emeter.backend.base.configureStatusPages
import com.adeldolgov.emeter.backend.di.ConfigLocator
import io.ktor.application.Application

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

private val serviceLocator = ConfigLocator

fun Application.module() {
    ConfigLocator.provideJwtConfig()
    configureStatusPages()
    configureSecurity()
    configureRoutingAndSerialization()
    configureHTTP()
}
