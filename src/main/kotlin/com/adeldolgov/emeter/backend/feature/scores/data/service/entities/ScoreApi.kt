package com.adeldolgov.emeter.backend.feature.scores.data.service.entities

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import java.math.BigDecimal
import java.util.*

@kotlinx.serialization.Serializable
data class ScoreApi(

    @SerialName("id")
    @BsonId
    val id: String = ObjectId().toString(),

    @SerialName("counterId")
    val counterId: String,

    @SerialName("counterValue")
    val counterValue: @Contextual BigDecimal,

    @SerialName("createdAtMillis")
    val createdAtMillis: Long = Date().toInstant().toEpochMilli()
)