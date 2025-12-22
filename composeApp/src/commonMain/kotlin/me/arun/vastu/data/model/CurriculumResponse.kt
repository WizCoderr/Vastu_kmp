package me.arun.vastu.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CurriculumResponse(
    val courseId: String,
    val chapters: List<Chapter>
)
