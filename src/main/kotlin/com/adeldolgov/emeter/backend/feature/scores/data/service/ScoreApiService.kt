package com.adeldolgov.emeter.backend.feature.scores.data.service

import com.adeldolgov.emeter.backend.feature.scores.data.service.entities.ScoreApi
import java.math.BigDecimal

interface ScoreApiService {

    suspend fun tickCounter(counterId: String, valuePerTick: BigDecimal): Boolean

    suspend fun getScoresForCounter(counterId: String, from: Long, to: Long): List<ScoreApi>
}