package me.arun.vastu.features.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.arun.vastu.domain.usecase.auth.GetAuthTokenUseCase

class SplashViewModel(
    private val getAuthTokenUseCase: GetAuthTokenUseCase
) : ViewModel() {

    private val _isUserAuthenticated = MutableStateFlow<Boolean?>(null)
    val isUserAuthenticated = _isUserAuthenticated.asStateFlow()

    init {
        checkUserAuthentication()
    }

    private fun checkUserAuthentication() {
        viewModelScope.launch {
            val authToken = getAuthTokenUseCase()
            _isUserAuthenticated.value = authToken != null
        }
    }
}
