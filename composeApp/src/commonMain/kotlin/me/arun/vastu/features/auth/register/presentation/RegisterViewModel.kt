package me.arun.vastu.features.auth.register.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import me.arun.vastu.domain.usecase.auth.SaveAuthTokenUseCase
import me.arun.vastu.features.auth.register.domain.model.RegisterRequest
import me.arun.vastu.features.auth.register.domain.usecase.RegisterUseCase

/**
 * Manages the business logic and state for the Register feature.
 */
class RegisterViewModel(
    private val registerUseCase: RegisterUseCase,
    private val saveAuthTokenUseCase: SaveAuthTokenUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<RegisterEvent>()
    val event = _event.asSharedFlow()

    fun onAction(action: RegisterAction) {
        when (action) {
            is RegisterAction.OnUserNameChanged -> _state.update { it.copy(userName = action.userName) }
            is RegisterAction.OnEmailChanged -> _state.update { it.copy(email = action.email) }
            is RegisterAction.OnPasswordChanged -> _state.update { it.copy(password = action.password) }
            is RegisterAction.OnConfirmPasswordChanged -> _state.update { it.copy(confirmPassword = action.confirmPassword) }
            is RegisterAction.OnRegisterClicked -> registration()
            is RegisterAction.OnLoginClicked -> navLogin()
        }
    }

    private fun navLogin() {
        viewModelScope.launch {
            _event.emit(RegisterEvent.NavigateToLogin)
        }
    }
    private fun registration() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val currentState = _state.value
            val request = RegisterRequest(
                name = currentState.userName,
                email = currentState.email,
                password = currentState.password
            )
            registerUseCase(request).onSuccess { result ->
                saveAuthTokenUseCase(result.authResult.token)
                _state.update { it.copy(isLoading = false) }
                _event.emit(RegisterEvent.NavigateToHome)
            }.onFailure {
                _state.update { it.copy(isLoading = false) }
            }
        }
    }
}
