package com.adeldolgov.emeter.backend.util

import io.ktor.http.*
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

interface BaseResponse<T : Any>

@Serializable
data class SuccessResponse<T : Any>(

    @SerialName("code")
    @Serializable(with = HttpStatusCodeSerializer::class)
    val code: HttpStatusCode,

    @SerialName("data")
    val data: T? = null,

    @SerialName("message")
    val message: String? = null
) : BaseResponse<T>

@Serializable
data class ExceptionResponse(

    @SerialName("code")
    @Serializable(with = HttpStatusCodeSerializer::class)
    val code: HttpStatusCode,

    @SerialName("message")
    val message: String? = null
) : BaseResponse<Nothing>

data class PaginatedResponse<T : Any>(
    val statusCode: HttpStatusCode,
    val prev: Int?,
    val next: Int?,
    val totalCount: Int = 0,
    val totalPages: Int = 0,
    val data: T? = null,
    val message: String? = null
) : BaseResponse<T>

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = HttpStatusCode::class)
object HttpStatusCodeSerializer : KSerializer<HttpStatusCode> {

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("io.ktor.http.HttpStatusCode", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): HttpStatusCode {
        val surrogate = decoder.decodeSerializableValue(HttpStatusCodeSerializable.serializer())
        return HttpStatusCode(surrogate.value, surrogate.description)
    }

    override fun serialize(encoder: Encoder, value: HttpStatusCode) {
        encoder.encodeString(value.toString())
    }
}

@Serializable
@SerialName("HttpStatusCode")
private class HttpStatusCodeSerializable(

    @SerialName("value")
    val value: Int,

    @SerialName("description")
    val description: String
)