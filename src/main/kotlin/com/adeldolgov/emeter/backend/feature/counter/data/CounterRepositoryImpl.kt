package com.adeldolgov.emeter.backend.feature.counter.data

import com.adeldolgov.emeter.backend.base.http.ExceptionHandler
import com.adeldolgov.emeter.backend.feature.counter.data.mappers.CounterApiToCounterMapper
import com.adeldolgov.emeter.backend.feature.counter.data.mappers.CreateCounterRequestToCounterApiMapper
import com.adeldolgov.emeter.backend.feature.counter.data.service.CounterApiService
import com.adeldolgov.emeter.backend.feature.counter.data.service.entities.CounterApi
import com.adeldolgov.emeter.backend.feature.counter.domain.entities.Counter
import com.adeldolgov.emeter.backend.feature.counter.domain.entities.CreateCounterRequest
import com.adeldolgov.emeter.backend.feature.counter.domain.repository.CounterRepository
import com.adeldolgov.emeter.backend.feature.scores.data.service.ScoreApiService
import com.adeldolgov.emeter.backend.util.SuccessResponse
import io.ktor.http.*
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

internal class CounterRepositoryImpl(
    private val counterApiService: CounterApiService,
    private val scoreApiService: ScoreApiService,
    private val createCounterRequestToCounterApiMapper: CreateCounterRequestToCounterApiMapper,
    private val counterApiToCounterMapper: CounterApiToCounterMapper,
    private val exceptionHandler: ExceptionHandler,
) : CounterRepository {

    override suspend fun createCounterForUser(userId: String, createCounterRequest: CreateCounterRequest): SuccessResponse<Counter> {
        val counter: CounterApi = createCounterRequestToCounterApiMapper(userId, createCounterRequest)
        val responseIsSuccessful: Boolean = counterApiService.insertCounter(counter)
        return when {
            responseIsSuccessful -> SuccessResponse(data = counterApiToCounterMapper(counter, emptyList()), code = HttpStatusCode.Created)
            else -> throw exceptionHandler.respondWithGenericException(SOMETHING_WENT_WRONG)
        }
    }

    override suspend fun tickCounter(counterId: String): SuccessResponse<Boolean> {
        val counter: CounterApi = counterApiService.findCounterById(counterId) ?: throw exceptionHandler.respondWithNotFoundException(COUNTER_NOT_FOUND)
        val responseIsSuccessful: Boolean = scoreApiService.tickCounter(counter.id, counter.valuePerTick)
        return when {
            responseIsSuccessful -> SuccessResponse(data = true, code = HttpStatusCode.Created)
            else -> throw exceptionHandler.respondWithGenericException(SOMETHING_WENT_WRONG)
        }
    }

    override suspend fun getCountersForUser(userId: String, millis: Long): SuccessResponse<List<Counter>> {
        val counters: List<CounterApi> = counterApiService.findCountersByUserId(userId)
        val scoresByCounters = counters.associateBy {
            val startOfDay: Date = Date.from(LocalDate.ofEpochDay(millis).atStartOfDay(ZoneId.systemDefault()).toInstant())
            scoreApiService.getScoresForCounter(it.id, startOfDay.time, millis)
        }
        return scoresByCounters
            .map { entry -> counterApiToCounterMapper(entry.value, entry.key) }
            .let { SuccessResponse(data = it, code = HttpStatusCode.Found) }
    }

    companion object {

        private const val SOMETHING_WENT_WRONG = "Something went wrong. Please try again"
        private const val COUNTER_NOT_FOUND = "Counter not found. Please try another counterId"
    }
}