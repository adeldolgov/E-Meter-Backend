package com.adeldolgov.emeter.backend.feature.counter.data.mappers

import com.adeldolgov.emeter.backend.feature.counter.data.service.entities.CounterApi
import com.adeldolgov.emeter.backend.feature.counter.data.service.entities.CounterTypeApi
import com.adeldolgov.emeter.backend.feature.counter.domain.entities.CreateCounterRequest
import java.util.*

internal class CreateCounterRequestToCounterApiMapper : (String, CreateCounterRequest) -> CounterApi {

    override fun invoke(userId: String, createCounterRequest: CreateCounterRequest): CounterApi {
        return CounterApi(
            userId = userId,
            name = createCounterRequest.name,
            initialValue = createCounterRequest.initialValue,
            valuePerTick = createCounterRequest.valuePerTick,
            pricePerUnit = createCounterRequest.pricePerUnit,
            type = extractCounterTypeApiFromCounterType(createCounterRequest.counterType)
        )
    }

    private fun extractCounterTypeApiFromCounterType(counterType: String): CounterTypeApi {
        return when(counterType.uppercase(Locale.getDefault())) {
            CounterTypeApi.HOT.name -> CounterTypeApi.HOT
            CounterTypeApi.COLD.name -> CounterTypeApi.COLD
            CounterTypeApi.ELECTRIC.name -> CounterTypeApi.ELECTRIC
            else -> throw java.lang.IllegalArgumentException("Wrong counter type param!")
        }
    }
}