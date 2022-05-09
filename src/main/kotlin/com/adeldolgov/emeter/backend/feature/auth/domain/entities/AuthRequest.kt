package com.adeldolgov.emeter.backend.feature.auth.domain.entities

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class AuthRequest(

    @SerialName("username")
    val username: String,

    @SerialName("password")
    val password: String
)
