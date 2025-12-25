package me.arun.vastu.features.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.arun.vastu.core.network.dto.AuthResponse
import me.arun.vastu.core.network.dto.LoginRequest
import me.arun.vastu.core.network.utils.ApiResult
import me.arun.vastu.domain.usecase.auth.LoginUseCase

class AuthViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginScreenState>(LoginScreenState.Idle)
    val loginState = _loginState.asStateFlow()

    fun login(email: String, pass: String) {
        viewModelScope.launch {
            _loginState.value = LoginScreenState.Loading
            val result = loginUseCase(LoginRequest(email, pass))
            _loginState.value = when {
                result.success -> LoginScreenState.Success(result.data!!)
                else -> LoginScreenState.Error(result.error ?: "Unknown error")
            }
        }
    }
}

sealed class LoginScreenState {
    object Idle : LoginScreenState()
    object Loading : LoginScreenState()
    data class Success(val authResponse: AuthResponse) : LoginScreenState()
    data class Error(val message: String) : LoginScreenState()
}
