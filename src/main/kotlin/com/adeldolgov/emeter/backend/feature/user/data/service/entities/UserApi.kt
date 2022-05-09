package com.adeldolgov.emeter.backend.feature.user.data.service.entities

import kotlinx.serialization.SerialName
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@kotlinx.serialization.Serializable
data class UserApi(

    @SerialName("username")
    val username: String?,

    @SerialName("passwordHash")
    val passwordHash: String? = null,

    @SerialName("id")
    @BsonId
    val id: String = ObjectId().toString(),

    @SerialName("firstname")
    val firstname: String? = null,

    @SerialName("lastname")
    val lastname: String? = null,

    @SerialName("createdAtMillis")
    val createdAt: Long,

    @SerialName("updatedAt")
    val updatedAt: String? = null,
)