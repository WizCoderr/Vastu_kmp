package me.arun.vastu.data.model

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Course(
    val id: String,
    val title: String,
    val description: String,
    @SerialName("instructorId") val instructor: String,
    val price: Double,
    val published: Boolean,
    @SerialName("thumbnail") @EncodeDefault val imageUrl: String? = null,
    @EncodeDefault val tag: String? = null,
    @EncodeDefault val accessType: String? = null
)
