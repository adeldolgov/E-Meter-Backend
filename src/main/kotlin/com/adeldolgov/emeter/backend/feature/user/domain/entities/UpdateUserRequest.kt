package com.adeldolgov.emeter.backend.feature.user.domain.entities

@kotlinx.serialization.Serializable
data class UpdateUserRequest(
    val firstname: String,
    val lastname: String,
)