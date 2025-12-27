package me.arun.vastu.features.auth.landing.presentation

/**
 * Defines the one-off events that the ViewModel can send to the UI.
 * These events are meant to be consumed only once (e.g., navigation, snackbar).
 */
sealed interface LandingEvent {
    data object NavigateToLogin : LandingEvent
    data object NavigateToRegister : LandingEvent
    data object NavigateToExplore : LandingEvent
}
