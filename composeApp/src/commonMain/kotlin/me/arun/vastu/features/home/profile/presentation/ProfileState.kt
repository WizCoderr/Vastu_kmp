package me.arun.vastu.features.home.profile.presentation

import me.arun.vastu.features.home.profile.presentation.model.ProfileUiModel

/**
 * Represents the state of the Profile screen.
 *
 * @property isLoading True if data is currently being loaded.
 * @property items The list of UI models to be displayed.
 */
data class ProfileState(
    val name: String = "",
    val email: String = "",
    val institute: String = "",
    val studentId: String = "",
    val joinedDate: String = "",
    val appVersion: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)