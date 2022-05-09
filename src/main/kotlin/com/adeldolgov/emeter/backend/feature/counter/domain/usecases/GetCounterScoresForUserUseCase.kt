package com.adeldolgov.emeter.backend.feature.counter.domain.usecases

import com.adeldolgov.emeter.backend.feature.counter.domain.entities.CounterScore
import com.adeldolgov.emeter.backend.feature.counter.domain.repository.CounterRepository
import com.adeldolgov.emeter.backend.util.SuccessResponse

interface GetCounterScoresForUserUseCase {

    suspend operator fun invoke(userId: String, counterId: String, from: Long, to: Long): SuccessResponse<List<CounterScore>>
}

internal class GetCounterScoresForUserUseCaseImpl(private val counterRepository: CounterRepository) : GetCounterScoresForUserUseCase {

    override suspend fun invoke(userId: String, counterId: String, from: Long, to: Long): SuccessResponse<List<CounterScore>> {
        return counterRepository.getCounterScoresForInterval(userId, counterId, from, to)
    }
}
