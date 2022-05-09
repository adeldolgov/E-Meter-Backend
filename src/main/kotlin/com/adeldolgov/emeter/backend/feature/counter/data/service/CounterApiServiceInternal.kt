package com.adeldolgov.emeter.backend.feature.counter.data.service

import com.adeldolgov.emeter.backend.base.database.DatabaseProvider
import com.adeldolgov.emeter.backend.feature.counter.data.service.entities.CounterApi
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

internal class CounterApiServiceImpl(
    private val databaseProvider: DatabaseProvider
) : CounterApiService {

    private val countersCollection: CoroutineCollection<CounterApi>
        get() = databaseProvider.database.getCollection(collectionName = "counter")

    override suspend fun insertCounter(counter: CounterApi): Boolean {
        return countersCollection.insertOne(counter).wasAcknowledged()
    }

    override suspend fun updateCounterById(counter: CounterApi, counterId: String): Boolean {
        return countersCollection.updateOneById(counterId, counter, updateOnlyNotNullProperties = true).wasAcknowledged()
    }

    override suspend fun findCountersByUserId(userId: String): List<CounterApi> {
        return countersCollection.find(CounterApi::userId eq userId).toList()
    }

    override suspend fun findCounterById(counterId: String): CounterApi? {
        return countersCollection.findOneById(counterId)
    }
}