package me.arun.vastu.features.auth.register.presentation

/**
 * Defines the actions that the user can perform on the Register screen.
 */
sealed interface RegisterAction {
    data class OnUserNameChanged(val userName: String) : RegisterAction
    data class OnEmailChanged(val email: String) : RegisterAction
    data class OnPasswordChanged(val password: String) : RegisterAction
    data class OnConfirmPasswordChanged(val confirmPassword: String) : RegisterAction
    data object OnRegisterClicked : RegisterAction
    data object OnLoginClicked : RegisterAction
}
