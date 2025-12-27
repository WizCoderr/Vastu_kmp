package me.arun.vastu.features.home.courses.presentation

import me.arun.vastu.features.home.courses.presentation.model.CourseUiModel

/**
 * Represents the state of the Courses screen.
 *
 * @property isLoading True if data is currently being loaded.
 * @property allCourses  The list of UI models to be displayed.
 * @property error it will give error
 */
data class CoursesState(
    val isLoading: Boolean = false,
    val allCourses: List<CourseUiModel> = emptyList(),
    val enrolledCourses: List<CourseUiModel> = emptyList(),
    val selectedTab: CoursesTab = CoursesTab.All, // NEW
    val error: String? = null
)

enum class CoursesTab { All, Enrolled }
