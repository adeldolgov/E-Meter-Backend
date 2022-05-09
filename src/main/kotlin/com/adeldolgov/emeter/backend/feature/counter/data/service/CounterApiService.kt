package com.adeldolgov.emeter.backend.feature.counter.data.service

import com.adeldolgov.emeter.backend.feature.counter.data.service.entities.CounterApi

interface CounterApiService {

    suspend fun insertCounter(counter: CounterApi): Boolean

    suspend fun updateCounterById(counter: CounterApi, counterId: String): Boolean

    suspend fun findCountersByUserId(userId: String): List<CounterApi>

    suspend fun findCounterById(counterId: String): CounterApi?
}