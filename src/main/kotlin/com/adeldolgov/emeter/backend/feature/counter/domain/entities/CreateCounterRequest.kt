package com.adeldolgov.emeter.backend.feature.counter.domain.entities

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import java.math.BigDecimal

@kotlinx.serialization.Serializable
data class CreateCounterRequest(

    @SerialName("name")
    val name: String,

    @SerialName("initialValue")
    val initialValue: @Contextual BigDecimal,

    @SerialName("valuePerTick")
    val valuePerTick: @Contextual BigDecimal,

    @SerialName("nextStatementDayOfMonth")
    val nextStatementDayOfMonth: Int,

    @SerialName("price")
    val pricePerUnit: @Contextual BigDecimal,

    @SerialName("counterType")
    val counterType: String,
)