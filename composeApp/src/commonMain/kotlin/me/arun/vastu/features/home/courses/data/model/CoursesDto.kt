package me.arun.vastu.features.home.courses.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Mirrors backend JSON.
 */
@Serializable
data class CourseDto(
    @SerialName("id")
    val id: String,

    @SerialName("title")
    val title: String,

    @SerialName("tag")
    val tag: String,

    @SerialName("image_url")
    val imageUrl: String? = null,

    @SerialName("access_type")
    val accessType: String
)
