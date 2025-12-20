package me.arun.vastu.features.home.courses.screens.enrolled.presentation

import me.arun.vastu.features.home.courses.screens.enrolled.presentation.model.EnrolledUiModel

/**
 * Represents the state of the Enrolled screen.
 *
 * @property isLoading True if data is currently being loaded.
 * @property items The list of UI models to be displayed.
 */
data class EnrolledState(
    val isLoading: Boolean = false,
    val items: List<me.arun.vastu.features.home.courses.screens.enrolled.presentation.model.EnrolledUiModel> = emptyList()
)