package me.arun.vastu.features.home.courses.presentation

/**
 * Defines the actions that can be sent from the UI to the ViewModel
 * for the Courses feature.
 */
sealed interface CoursesAction {
    data class OnCourseClick(val courseId: String) : CoursesAction
    data class OnTabChange(val tab: CoursesTab) : CoursesAction // NEW
}