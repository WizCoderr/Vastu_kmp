package me.arun.vastu.features.auth.landing.presentation

/**
 * Defines the actions that can be sent from the UI to the ViewModel
 * for the Landing feature.
 */

sealed interface LandingAction {

    // User actions
    data object OnLoginClick : LandingAction
    data object OnCreateAccountClick : LandingAction
    data object OnExploreClick : LandingAction

    // Pager actions
    data class OnPageChanged(val page: Int) : LandingAction
    data object OnUserInteracted : LandingAction

    // Auto-scroll control
    data object OnAutoScrollResumed : LandingAction
}
