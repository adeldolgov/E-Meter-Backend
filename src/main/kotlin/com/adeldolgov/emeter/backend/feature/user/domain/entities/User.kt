package com.adeldolgov.emeter.backend.feature.user.domain.entities

import kotlinx.serialization.SerialName
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@kotlinx.serialization.Serializable
data class User(

    @SerialName("username")
    val username: String?,

    @SerialName("id")
    val id: String,

    @SerialName("firstname")
    val firstname: String?,

    @SerialName("lastname")
    val lastname: String?,
)
