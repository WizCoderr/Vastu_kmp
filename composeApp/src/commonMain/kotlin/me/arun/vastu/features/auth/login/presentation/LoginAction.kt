package me.arun.vastu.features.auth.login.presentation

/**
 * Defines the actions that the user can perform on the Login screen.
 */
sealed interface LoginAction {
    data class OnEmailChanged(val email: String) : LoginAction
    data class OnPasswordChanged(val password: String) : LoginAction
    data object OnLoginClicked : LoginAction
    data object OnRegisterClicked : LoginAction
}
