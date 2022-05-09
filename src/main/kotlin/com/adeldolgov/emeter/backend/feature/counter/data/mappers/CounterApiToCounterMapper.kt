package com.adeldolgov.emeter.backend.feature.counter.data.mappers

import com.adeldolgov.emeter.backend.feature.counter.data.service.entities.CounterApi
import com.adeldolgov.emeter.backend.feature.counter.data.service.entities.CounterTypeApi
import com.adeldolgov.emeter.backend.feature.counter.domain.entities.Counter
import com.adeldolgov.emeter.backend.feature.counter.domain.entities.CounterType
import com.adeldolgov.emeter.backend.feature.scores.data.service.entities.ScoreApi
import java.math.BigDecimal

internal class CounterApiToCounterMapper : (CounterApi, List<ScoreApi>) -> Counter {

    override fun invoke(counterApi: CounterApi, scores: List<ScoreApi>): Counter {
        return Counter(
            id = counterApi.id,
            name = counterApi.name,
            value = scores.lastOrNull()?.counterValue ?: BigDecimal.ZERO,
            price = BigDecimal.valueOf(scores.size.toDouble()) * counterApi.pricePerUnit,
            valueDiff = scores.firstOrNull()?.counterValue?.minus(scores.lastOrNull()?.counterValue ?: BigDecimal.ZERO) ?: BigDecimal.ZERO,
            type = mapCounterTypeApiToCounterType(counterApi.type)
        )
    }

    private fun mapCounterTypeApiToCounterType(counterTypeApi: CounterTypeApi): CounterType {
        return when (counterTypeApi) {
            CounterTypeApi.HOT -> CounterType.HOT
            CounterTypeApi.COLD -> CounterType.COLD
            CounterTypeApi.ELECTRIC -> CounterType.ELECTRIC
        }
    }
}