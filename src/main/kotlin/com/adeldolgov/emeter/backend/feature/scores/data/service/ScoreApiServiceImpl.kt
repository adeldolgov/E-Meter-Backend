package com.adeldolgov.emeter.backend.feature.scores.data.service

import com.adeldolgov.emeter.backend.base.database.DatabaseProvider
import com.adeldolgov.emeter.backend.feature.scores.data.service.entities.ScoreApi
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq
import org.litote.kmongo.gte
import org.litote.kmongo.`in`
import org.litote.kmongo.lte
import java.math.BigDecimal

internal class ScoreApiServiceImpl(
    private val databaseProvider: DatabaseProvider
) : ScoreApiService {

    private val scoresCollection: CoroutineCollection<ScoreApi>
        get() = databaseProvider.database.getCollection(collectionName = "score")

    override suspend fun tickCounter(counterId: String, valuePerTick: BigDecimal): Boolean {
        val latestValue: BigDecimal = scoresCollection.findOne(ScoreApi::counterId eq counterId)?.counterValue ?: BigDecimal.ZERO
        return scoresCollection.insertOne(ScoreApi(counterId = counterId, counterValue = latestValue + valuePerTick)).wasAcknowledged()
    }

    override suspend fun getScoresForCounter(counterId: String, from: Long, to: Long): List<ScoreApi> {
        return scoresCollection.find(
            ScoreApi::counterId eq counterId,
            ScoreApi::createdAtMillis gte from,
            ScoreApi::createdAtMillis lte to
        ).toList()
    }
}