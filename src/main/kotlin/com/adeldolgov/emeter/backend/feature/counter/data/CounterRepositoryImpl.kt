package com.adeldolgov.emeter.backend.feature.counter.data

import com.adeldolgov.emeter.backend.base.auth.JwtConfig
import com.adeldolgov.emeter.backend.base.http.ExceptionHandler
import com.adeldolgov.emeter.backend.feature.counter.data.mappers.CounterApiToCounterMapper
import com.adeldolgov.emeter.backend.feature.counter.data.mappers.CreateCounterRequestToCounterApiMapper
import com.adeldolgov.emeter.backend.feature.counter.data.mappers.ScoreApiToCounterScoreMapper
import com.adeldolgov.emeter.backend.feature.counter.data.service.CounterApiService
import com.adeldolgov.emeter.backend.feature.counter.data.service.entities.CounterApi
import com.adeldolgov.emeter.backend.feature.counter.domain.entities.Counter
import com.adeldolgov.emeter.backend.feature.counter.domain.entities.CounterScore
import com.adeldolgov.emeter.backend.feature.counter.domain.entities.CounterWithToken
import com.adeldolgov.emeter.backend.feature.counter.domain.entities.CreateCounterRequest
import com.adeldolgov.emeter.backend.feature.counter.domain.repository.CounterRepository
import com.adeldolgov.emeter.backend.feature.scores.data.service.ScoreApiService
import com.adeldolgov.emeter.backend.feature.scores.data.service.entities.ScoreApi
import com.adeldolgov.emeter.backend.util.SuccessResponse
import io.ktor.http.*
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime

internal class CounterRepositoryImpl(
    private val counterApiService: CounterApiService,
    private val scoreApiService: ScoreApiService,
    private val createCounterRequestToCounterApiMapper: CreateCounterRequestToCounterApiMapper,
    private val counterApiToCounterMapper: CounterApiToCounterMapper,
    private val scoreApiToCounterScoreMapper: ScoreApiToCounterScoreMapper,
    private val jwtConfig: JwtConfig,
    private val exceptionHandler: ExceptionHandler,
) : CounterRepository {

    override suspend fun createCounterForUser(userId: String, createCounterRequest: CreateCounterRequest): SuccessResponse<CounterWithToken> {
        val counter: CounterApi = createCounterRequestToCounterApiMapper(userId, createCounterRequest)
        val responseIsSuccessful: Boolean = counterApiService.insertCounter(counter)
        return when {
            responseIsSuccessful -> SuccessResponse(
                data = CounterWithToken(
                    counter = counterApiToCounterMapper(counter, 0, emptyList()),
                    token = jwtConfig.makeAccessTokenForCounter(counter.id),
                ),
                code = HttpStatusCode.Created
            )
            else -> throw exceptionHandler.respondWithGenericException(SOMETHING_WENT_WRONG)
        }
    }

    override suspend fun tickCounter(counterId: String): SuccessResponse<Boolean> {
        val counter: CounterApi = counterApiService.findCounterById(counterId) ?: throw exceptionHandler.respondWithNotFoundException(COUNTER_NOT_FOUND)
        val responseIsSuccessful: Boolean = scoreApiService.tickCounter(counter.id, counter.valuePerTick, counter.initialValue)
        return when {
            responseIsSuccessful -> SuccessResponse(data = true, code = HttpStatusCode.Created)
            else -> throw exceptionHandler.respondWithGenericException(SOMETHING_WENT_WRONG)
        }
    }

    override suspend fun getCountersForUser(userId: String, millis: Long): SuccessResponse<List<Counter>> {
        val counters: List<CounterApi> = counterApiService.findCountersByUserId(userId)
        return counters.map {
            val startOfDay: ZonedDateTime = getTodayDateAtStart(millis)
            val startOfPreviousDay: ZonedDateTime = getPreviousDayAtStart(millis)
            val previousStatementDate: ZonedDateTime = getLastStatementDateForCounter(it)

            val currentDayScores = scoreApiService.getScoresForCounter(it.id, startOfDay.toInstant().toEpochMilli(), millis)
            val previousDayScores = scoreApiService.getScoresForCounter(it.id, startOfPreviousDay.toInstant().toEpochMilli(), startOfDay.toInstant().toEpochMilli() - 1)
            val monthScores = scoreApiService.getScoresForCounter(it.id, previousStatementDate.toInstant().toEpochMilli(), millis)
            counterApiToCounterMapper(it, monthScores.size, /* previousDayScores.size */, currentDayScores)
        }.let { SuccessResponse(data = it, code = HttpStatusCode.Found) }
    }

    private fun getTodayDateAtStart(millis: Long) =
        LocalDate.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault()).atStartOfDay(ZoneId.systemDefault())

    private fun getPreviousDayAtStart(millis: Long) =
        LocalDate.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault()).minusDays(1).atStartOfDay(ZoneId.systemDefault())

    private fun getLastStatementDateForCounter(counter: CounterApi): ZonedDateTime {
        val localDateNow: LocalDate = LocalDate.now()
        return LocalDate.of(localDateNow.year, localDateNow.month - 1, counter.nextStatementDayOfMonth).atStartOfDay(ZoneId.systemDefault())
    }

    override suspend fun getCounterScoresForInterval(counterId: String, from: Long, to: Long): SuccessResponse<List<CounterScore>> {
        val counter: CounterApi = counterApiService.findCounterById(counterId) ?: throw exceptionHandler.respondWithNotFoundException(COUNTER_NOT_FOUND)
        return SuccessResponse(
            data = scoreApiService.getScoresForCounter(counter.id, from, to).let(scoreApiToCounterScoreMapper),
            code = HttpStatusCode.Found
        )
    }

    companion object {

        private const val SOMETHING_WENT_WRONG = "Something went wrong. Please try again"
        private const val COUNTER_NOT_FOUND = "Counter not found. Please try another counterId"
    }
}