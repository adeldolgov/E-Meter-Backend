package com.adeldolgov.emeter.backend.feature.counter.domain.entities

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import org.bson.codecs.pojo.annotations.BsonId
import java.math.BigDecimal

@kotlinx.serialization.Serializable
data class CounterScore(

    @SerialName("scoreId")
    val scoreId: String,

    @SerialName("value")
    val value: @Contextual BigDecimal,

    @SerialName("createdAtMillis")
    val createdAtMillis: Long,
)