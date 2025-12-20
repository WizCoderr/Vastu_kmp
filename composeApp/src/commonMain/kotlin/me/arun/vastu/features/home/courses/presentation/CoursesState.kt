package me.arun.vastu.features.home.courses.presentation

import me.arun.vastu.features.home.courses.presentation.model.CourseUiModel

/**
 * Represents the state of the Courses screen.
 *
 * @property isLoading True if data is currently being loaded.
 * @property items The list of UI models to be displayed.
 */

data class CoursesState(
    val isLoading: Boolean = false,
    val courses: List<CourseUiModel> = emptyList(),
    val error: String? = null
)






