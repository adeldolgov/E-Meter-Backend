package com.adeldolgov.emeter.backend.feature.counter.domain.entities

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import org.bson.codecs.pojo.annotations.BsonId
import java.math.BigDecimal

@kotlinx.serialization.Serializable
data class Counter(

    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("value")
    val value: @Contextual BigDecimal,

    @SerialName("price")
    val price: @Contextual BigDecimal,

    @SerialName("valueDiff")
    val valueDiff: @Contextual BigDecimal,

    @SerialName("type")
    val type: CounterType,
)