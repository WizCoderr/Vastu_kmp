package me.arun.vastu.features.home.courses.screens.details.presentation

import me.arun.vastu.features.home.courses.screens.details.domain.model.Details

/**
 * Represents the state of the Details screen.
 *
 * @property isLoading True if data is currently being loaded.
 * @property details The details of the course.
 * @property error An error message if data loading fails.
 */
data class DetailsState(
    val isLoading: Boolean = false,
    val details: Details? = null,
    val error: String? = null
)