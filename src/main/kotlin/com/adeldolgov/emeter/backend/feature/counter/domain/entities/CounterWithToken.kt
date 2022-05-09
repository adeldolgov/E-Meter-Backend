package com.adeldolgov.emeter.backend.feature.counter.domain.entities

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class CounterWithToken(

    @SerialName("counter")
    val counter: Counter,

    @SerialName("counterToken")
    val token: String
)