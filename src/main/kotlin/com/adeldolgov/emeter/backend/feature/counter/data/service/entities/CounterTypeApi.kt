package com.adeldolgov.emeter.backend.feature.counter.data.service.entities

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
enum class CounterTypeApi {

    @SerialName("HOT")
    HOT,

    @SerialName("COLD")
    COLD,

    @SerialName("ELECTRIC")
    ELECTRIC
}