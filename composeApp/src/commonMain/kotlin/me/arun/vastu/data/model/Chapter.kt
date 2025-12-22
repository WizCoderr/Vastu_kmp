package me.arun.vastu.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Chapter(
    val id: String,
    val title: String,
    val lessons: List<Lesson>
)
