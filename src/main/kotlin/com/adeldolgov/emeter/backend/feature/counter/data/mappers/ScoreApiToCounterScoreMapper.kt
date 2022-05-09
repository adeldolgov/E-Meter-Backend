package com.adeldolgov.emeter.backend.feature.counter.data.mappers

import com.adeldolgov.emeter.backend.feature.counter.domain.entities.CounterScore
import com.adeldolgov.emeter.backend.feature.scores.data.service.entities.ScoreApi

internal class ScoreApiToCounterScoreMapper : (List<ScoreApi>) -> List<CounterScore> {

    override fun invoke(scoresApi: List<ScoreApi>): List<CounterScore> {
        return scoresApi.map {
            CounterScore(
                scoreId = it.id,
                value = it.counterValue,
                createdAtMillis = it.createdAtMillis
            )
        }
    }
}