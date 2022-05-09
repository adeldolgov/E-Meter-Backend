package com.adeldolgov.emeter.backend.feature.counter.domain.repository

import com.adeldolgov.emeter.backend.feature.counter.domain.entities.Counter
import com.adeldolgov.emeter.backend.feature.counter.domain.entities.CounterScore
import com.adeldolgov.emeter.backend.feature.counter.domain.entities.CounterWithToken
import com.adeldolgov.emeter.backend.feature.counter.domain.entities.CreateCounterRequest
import com.adeldolgov.emeter.backend.util.SuccessResponse

interface CounterRepository {

    suspend fun createCounterForUser(userId: String, createCounterRequest: CreateCounterRequest): SuccessResponse<CounterWithToken>

    suspend fun tickCounter(counterId: String): SuccessResponse<Boolean>

    suspend fun getCountersForUser(userId: String, millis: Long): SuccessResponse<List<Counter>>

    suspend fun getCounterScoresForInterval(counterId: String, from: Long, to: Long): SuccessResponse<List<CounterScore>>
}