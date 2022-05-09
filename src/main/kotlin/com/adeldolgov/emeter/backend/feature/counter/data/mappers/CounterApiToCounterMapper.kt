package com.adeldolgov.emeter.backend.feature.counter.data.mappers

import com.adeldolgov.emeter.backend.feature.counter.data.service.entities.CounterApi
import com.adeldolgov.emeter.backend.feature.counter.data.service.entities.CounterTypeApi
import com.adeldolgov.emeter.backend.feature.counter.domain.entities.Counter
import com.adeldolgov.emeter.backend.feature.counter.domain.entities.CounterType
import com.adeldolgov.emeter.backend.feature.scores.data.service.entities.ScoreApi
import java.math.BigDecimal

internal class CounterApiToCounterMapper : (CounterApi, Int, Int, List<ScoreApi>) -> Counter {

    override fun invoke(
        counterApi: CounterApi,
        monthScoresCount: Int,
        previousDayScoresCount: Int,
        currentDayScores: List<ScoreApi>
    ): Counter {
        return Counter(
            id = counterApi.id,
            name = counterApi.name,
            value = BigDecimal.valueOf(monthScoresCount.toLong()) * counterApi.valuePerTick,
            totalValue = currentDayScores.lastOrNull()?.counterValue ?: counterApi.initialValue,
            price = BigDecimal.valueOf(monthScoresCount.toLong()) * counterApi.valuePerTick * counterApi.pricePerUnit,
            valueDiff = BigDecimal.valueOf(currentDayScores.size.toLong()) * counterApi.valuePerTick -
                    BigDecimal.valueOf(previousDayScoresCount.toLong()) * counterApi.valuePerTick,
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