package me.arun.vastu.features.home.dashboard.presentation

/**
 * Defines the actions that can be sent from the UI to the ViewModel
 * for the Dashboard feature.
 */
sealed interface DashboardAction {
    data class OnCourseClick(val courseId: String) : DashboardAction
    data class OnContinueLearning(val courseId: String, val lastWatchedPositionMillis: Long) : DashboardAction
    data object OnProfileClick : DashboardAction
    data class OnVideoCompleted(val courseId: String) : DashboardAction
    data class OnVideoPaused(val courseId: String, val position: Long) : DashboardAction
    data object OnRefresh : DashboardAction
}
