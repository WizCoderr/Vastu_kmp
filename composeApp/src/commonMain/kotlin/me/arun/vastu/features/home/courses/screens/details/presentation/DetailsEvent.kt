package me.arun.vastu.features.home.courses.screens.details.presentation

/**
 * Defines the one-off events that the ViewModel can send to the UI.
 * These events are meant to be consumed only once (e.g., navigation, snackbar).
 */
sealed interface DetailsEvent {
    // Example: data class NavigateToDetails(val screenId: String) : DetailsEvent
}