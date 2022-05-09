package com.adeldolgov.emeter.backend.feature.counter.domain.usecases

import com.adeldolgov.emeter.backend.feature.counter.domain.entities.Counter
import com.adeldolgov.emeter.backend.feature.counter.domain.entities.CreateCounterRequest
import com.adeldolgov.emeter.backend.feature.counter.domain.repository.CounterRepository
import com.adeldolgov.emeter.backend.util.SuccessResponse

interface CreateCounterUseCase {

    suspend operator fun invoke(userId: String, createCounterRequest: CreateCounterRequest): SuccessResponse<Counter>
}

internal class CreateCounterUseCaseImpl(private val counterRepository: CounterRepository) : CreateCounterUseCase {

    override suspend fun invoke(userId: String, createCounterRequest: CreateCounterRequest): SuccessResponse<Counter> {
        return counterRepository.createCounterForUser(userId, createCounterRequest)
    }
}
