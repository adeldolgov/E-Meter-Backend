package com.adeldolgov.emeter.backend.feature.counter.domain.entities

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
enum class CounterType {

    @SerialName("HOT")
    HOT,

    @SerialName("COLD")
    COLD,

    @SerialName("ELECTRIC")
    ELECTRIC
}