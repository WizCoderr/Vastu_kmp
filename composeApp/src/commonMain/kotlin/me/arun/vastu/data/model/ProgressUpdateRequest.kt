package me.arun.vastu.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ProgressUpdateRequest(
    val courseId: String,
    val lessonId: String,
    val completed: Boolean
)
