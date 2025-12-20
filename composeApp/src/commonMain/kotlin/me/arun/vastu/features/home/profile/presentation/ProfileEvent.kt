package me.arun.vastu.features.home.profile.presentation

/**
 * Defines the one-off events that the ViewModel can send to the UI.
 * These events are meant to be consumed only once (e.g., navigation, snackbar).
 */
sealed interface ProfileEvent {
    data object NavigateToNotifications : ProfileEvent
    data object NavigateToPrivacy : ProfileEvent
    data object NavigateToTerms : ProfileEvent
    data object Logout : ProfileEvent
}