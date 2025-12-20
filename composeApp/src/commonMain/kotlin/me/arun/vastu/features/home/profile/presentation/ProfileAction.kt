package me.arun.vastu.features.home.profile.presentation

/**
 * Defines the actions that can be sent from the UI to the ViewModel
 * for the Profile feature.
 */
sealed interface ProfileAction {
    data object OnNotificationsClick : ProfileAction
    data object OnPrivacyClick : ProfileAction
    data object OnTermsClick : ProfileAction
    data object OnLogoutClick : ProfileAction
}
