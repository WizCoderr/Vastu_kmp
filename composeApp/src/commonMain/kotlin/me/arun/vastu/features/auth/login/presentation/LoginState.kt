package me.arun.vastu.features.auth.login.presentation

/**
 * Represents the state of the Login screen.
 *
 * @property isLoading True if data is currently being loaded.
 * @property items The list of UI models to be displayed.
 */
data class LoginState(
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
)