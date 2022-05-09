package com.adeldolgov.emeter.backend.feature.counter.domain.usecases

import com.adeldolgov.emeter.backend.feature.counter.domain.entities.Counter
import com.adeldolgov.emeter.backend.feature.counter.domain.repository.CounterRepository
import com.adeldolgov.emeter.backend.util.SuccessResponse

interface GetCountersForUserUseCase {

    suspend operator fun invoke(userId: String, millis: Long): SuccessResponse<List<Counter>>
}

internal class GetCountersForUserUseCaseImpl(private val counterRepository: CounterRepository) : GetCountersForUserUseCase {

    override suspend fun invoke(userId: String, millis: Long): SuccessResponse<List<Counter>> {
        return counterRepository.getCountersForUser(userId, millis)
    }
}
