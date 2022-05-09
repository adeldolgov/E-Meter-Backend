package com.adeldolgov.emeter.backend.feature.counter.data.service.entities

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import java.math.BigDecimal

@kotlinx.serialization.Serializable
class CounterApi(

    @SerialName("id")
    @BsonId
    val id: String = ObjectId().toString(),

    @SerialName("userId")
    val userId: String,

    @SerialName("name")
    val name: String,

    @SerialName("value")
    val initialValue: @Contextual BigDecimal,

    @SerialName("valuePerTick")
    val valuePerTick: @Contextual BigDecimal,

    @SerialName("price")
    val pricePerUnit: @Contextual BigDecimal,

    @SerialName("type")
    val type: CounterTypeApi,
)