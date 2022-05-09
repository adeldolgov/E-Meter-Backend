package com.adeldolgov.emeter.backend.feature.counter

import io.ktor.locations.*

@KtorExperimentalLocationsAPI
@Location(CounterConstant.CREATE_COUNTER)
class CreateCounter

@KtorExperimentalLocationsAPI
@Location(CounterConstant.GET_COUNTERS_FOR_USER)
class GetCountersForUser

@KtorExperimentalLocationsAPI
@Location(CounterConstant.TICK_COUNTER)
class TickCounter

@KtorExperimentalLocationsAPI
@Location(CounterConstant.GET_COUNTER_SCORES_FOR_USER)
class GetCounterScoresForUser
