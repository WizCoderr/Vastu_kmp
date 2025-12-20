package me.arun.vastu.features.home.courses.screens.enrolled.presentation

/**
 * Defines the one-off events that the ViewModel can send to the UI.
 * These events are meant to be consumed only once (e.g., navigation, snackbar).
 */
sealed interface EnrolledEvent {
    // Example: data class NavigateToDetails(val screenId: String) : EnrolledEvent
}