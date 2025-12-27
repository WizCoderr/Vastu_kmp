package me.arun.vastu.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CoursesResponse(
    val success: Boolean,
    val data: List<Course>
)