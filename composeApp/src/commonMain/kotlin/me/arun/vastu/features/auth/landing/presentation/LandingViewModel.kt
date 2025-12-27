package me.arun.vastu.features.auth.landing.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LandingViewModel : ViewModel() {

    // ---------- STATE ----------
    private val _state = MutableStateFlow(LandingState())
    val state = _state.asStateFlow()

    // ---------- EVENT ----------
    private val _event = MutableSharedFlow<LandingEvent>()
    val event = _event.asSharedFlow()

    init {
        loadInitialData()
    }

    // ---------- ACTION ----------
    fun onAction(action: LandingAction) {
        when (action) {

            // Navigation
            LandingAction.OnLoginClick -> emitEvent(LandingEvent.NavigateToLogin)

            LandingAction.OnCreateAccountClick ->
                emitEvent(LandingEvent.NavigateToRegister)

            LandingAction.OnExploreClick ->
                emitEvent(LandingEvent.NavigateToExplore)

            // Pager updates
            is LandingAction.OnPageChanged -> {
                _state.update {
                    it.copy(currentPage = action.page)
                }
            }

            // Stop auto-scroll on user interaction
            LandingAction.OnUserInteracted -> {
                _state.update {
                    it.copy(isAutoScrollEnabled = false)
                }
            }

            // Resume auto-scroll (optional)
            LandingAction.OnAutoScrollResumed -> {
                _state.update {
                    it.copy(isAutoScrollEnabled = true)
                }
            }
        }
    }

    // ---------- PRIVATE ----------
    private fun loadInitialData() {
        _state.update {
            it.copy(
                totalPages = 3,
                currentPage = 0,
                isAutoScrollEnabled = true
            )
        }
    }

    private fun emitEvent(event: LandingEvent) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }
}
