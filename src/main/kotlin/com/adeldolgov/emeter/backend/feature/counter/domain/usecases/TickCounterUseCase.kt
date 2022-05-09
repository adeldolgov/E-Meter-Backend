package com.adeldolgov.emeter.backend.feature.counter.domain.usecases

import com.adeldolgov.emeter.backend.feature.counter.domain.repository.CounterRepository
import com.adeldolgov.emeter.backend.util.SuccessResponse

interface TickCounterUseCase {

    suspend operator fun invoke(counterId: String): SuccessResponse<Boolean>
}

internal class TickCounterUseCaseImpl(private val counterRepository: CounterRepository) : TickCounterUseCase {

    override suspend fun invoke(counterId: String): SuccessResponse<Boolean> {
        return counterRepository.tickCounter(counterId)
    }
}
