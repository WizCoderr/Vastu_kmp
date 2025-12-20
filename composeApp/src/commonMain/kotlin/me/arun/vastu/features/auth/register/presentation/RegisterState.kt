package me.arun.vastu.features.auth.register.presentation

/**
 * Represents the state of the Register screen.
 *
 * @property isLoading True if data is currently being loaded.
 */
data class RegisterState(
    val isLoading: Boolean = false,
    val userName: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
)