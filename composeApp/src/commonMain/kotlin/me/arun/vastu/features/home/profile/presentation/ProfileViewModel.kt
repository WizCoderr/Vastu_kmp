package me.arun.vastu.features.home.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import me.arun.vastu.domain.usecase.auth.GetAuthTokenUseCase
import me.arun.vastu.domain.usecase.auth.LogoutUseCase
import me.arun.vastu.features.home.profile.domain.usecase.GetProfileDataUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Manages the business logic and state for the Profile feature.
 */
class ProfileViewModel(
    private val getProfileDataUseCase: GetProfileDataUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val getAuthTokenUseCase: GetAuthTokenUseCase // Inject GetAuthTokenUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state.asStateFlow()

    private val _event = MutableSharedFlow<ProfileEvent>()
    val event: SharedFlow<ProfileEvent> = _event.asSharedFlow()

    init {
        loadInitialData()
    }

    fun onAction(action: ProfileAction) {
        when (action) {
            ProfileAction.OnNotificationsClick -> {
                emitEvent(ProfileEvent.NavigateToNotifications)
            }

            ProfileAction.OnPrivacyClick -> {
                emitEvent(ProfileEvent.NavigateToPrivacy)
            }

            ProfileAction.OnTermsClick -> {
                emitEvent(ProfileEvent.NavigateToTerms)
            }

            ProfileAction.OnLogoutClick -> {
                viewModelScope.launch {
                    logoutUseCase()
                    emitEvent(ProfileEvent.Logout)
                }
            }
            ProfileAction.OnSignInClick -> { // Handle new action
                emitEvent(ProfileEvent.NavigateToLogin)
            }
        }
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            val authToken = getAuthTokenUseCase()
            val isAuthenticated = !authToken.isNullOrEmpty()

            if (isAuthenticated) {
                getProfileDataUseCase()
                    .onSuccess { profile ->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                name = profile.name,
                                email = profile.email,
                                institute = profile.institute,
                                studentId = profile.studentId,
                                joinedDate = profile.joinedDate,
                                appVersion = profile.appVersion,
                                isAuthenticated = true // Set isAuthenticated to true
                            )
                        }
                    }
                    .onFailure { throwable ->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = throwable.message ?: "Something went wrong",
                                isAuthenticated = true // Still authenticated, but failed to load profile
                            )
                        }
                    }
            } else {
                _state.update {
                    it.copy(
                        isLoading = false,
                        isAuthenticated = false // Not authenticated
                    )
                }
            }
        }
    }

    private fun emitEvent(event: ProfileEvent) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }
}
