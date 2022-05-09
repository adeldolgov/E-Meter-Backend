package com.adeldolgov.emeter.backend.base.auth

import io.ktor.auth.*

data class CounterIdPrincipalForCounter(val counterId: String) : Principal
