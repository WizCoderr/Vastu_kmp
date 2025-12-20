package me.arun.vastu.features.home.dashboard.presentation

import me.arun.vastu.features.home.dashboard.presentation.model.DashboardUiCourse

/**
 * Represents the state of the Dashboard screen.
 *
 * @property isLoading True if data is currently being loaded.
 * @property items The list of UI models to be displayed.
 */
data class DashboardState(
    val isLoading: Boolean = false,
    val userName: String = "",
    val instituteName: String = "",
    val enrolledCourses: List<DashboardUiCourse> = emptyList(),
    val activeCoursesCount: Int = 0,
    val error: String? = null
)



