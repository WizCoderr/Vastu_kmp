package me.arun.vastu.features.home.courses.screens.details.presentation

import me.arun.vastu.features.home.courses.screens.details.presentation.model.DetailsUiModel

/**
 * Represents the state of the Details screen.
 *
 * @property isLoading True if data is currently being loaded.
 * @property items The list of UI models to be displayed.
 */
data class DetailsState(
    val isLoading: Boolean = false,
    val items: List<me.arun.vastu.features.home.courses.screens.details.presentation.model.DetailsUiModel> = emptyList()
)