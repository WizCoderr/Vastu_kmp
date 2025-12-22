package me.arun.vastu.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Lesson(
    val id: String,
    val title: String,
    val videoUrl: String? = null
)
