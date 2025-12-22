package me.arun.vastu.domain.model

data class Chapter(
    val id: String,
    val title: String,
    val lessons: List<Lesson>
)
