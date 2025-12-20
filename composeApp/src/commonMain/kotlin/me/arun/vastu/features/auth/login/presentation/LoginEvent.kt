package me.arun.vastu.features.auth.login.presentation

/**
 * Defines the one-off events that the ViewModel can send to the UI.
 * These events are meant to be consumed only once (e.g., navigation, snackbar).
 */
sealed interface LoginEvent {
    data object NavigateToHome : LoginEvent
    data object NavigateToRegister : LoginEvent
}