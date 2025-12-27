package me.arun.vastu.features.home.courses.presentation.model

/**
 * Represents the UI model for a single item in the Courses feature.
 * This class is optimized for display in the Presentation Layer.
 */
data class CourseUiModel(
    val id: String,
    val title: String,
    val thumbnail: String,
    val isEnrolled: Boolean = false
)