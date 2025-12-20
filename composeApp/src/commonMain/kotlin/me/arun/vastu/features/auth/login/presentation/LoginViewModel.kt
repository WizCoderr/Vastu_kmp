package me.arun.vastu.features.auth.login.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Manages the business logic and state for the Login feature.
 */

class LoginViewModel(
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<LoginEvent>()
    val event = _event.asSharedFlow()

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnEmailChanged -> _state.update { it.copy(email = action.email) }
            is LoginAction.OnPasswordChanged -> _state.update { it.copy(password = action.password) }
            LoginAction.OnLoginClicked -> login()
            LoginAction.OnRegisterClicked -> navRegister()
        }
    }

    private fun login() {
        viewModelScope.launch {
            // TODO: Implement login logic
            _event.emit(LoginEvent.NavigateToHome)
        }
    }

    private fun navRegister() {
        viewModelScope.launch {
            _event.emit(LoginEvent.NavigateToRegister)
        }
    }
}