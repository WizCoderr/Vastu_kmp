package me.arun.vastu.features.home.courses.screens.details.presentation

/**
 * Defines the actions that can be sent from the UI to the ViewModel
 * for the Details feature.
 */
sealed interface DetailsAction {
    data object OnEnrollClick : DetailsAction
}