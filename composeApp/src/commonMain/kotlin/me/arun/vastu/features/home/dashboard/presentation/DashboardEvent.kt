package me.arun.vastu.features.home.dashboard.presentation

/**
 * Defines the one-off events that the ViewModel can send to the UI.
 * These events are meant to be consumed only once (e.g., navigation, snackbar).
 */

sealed interface DashboardEvent {
    data class NavigateToCourse(val courseId: String) : DashboardEvent
    data class NavigateToVideoPlayer(val courseId: String, val lastWatchedPositionMillis: Long) : DashboardEvent
    data object NavigateToProfile : DashboardEvent
}