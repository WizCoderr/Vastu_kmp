package me.arun.vastu.domain.model

data class Progress(
    val courseId: String,
    val lessonId: String,
    val completed: Boolean
)
