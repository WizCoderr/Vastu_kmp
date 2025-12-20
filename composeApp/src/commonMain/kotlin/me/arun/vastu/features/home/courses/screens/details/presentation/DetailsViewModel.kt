package me.arun.vastu.features.home.courses.screens.details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope


import me.arun.vastu.features.home.courses.screens.details.domain.usecase.GetDetailsDataUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Manages the business logic and state for the Details feature.
 */

class DetailsViewModel  constructor(
    private val getDetailsDataUseCase: me.arun.vastu.features.home.courses.screens.details.domain.usecase.GetDetailsDataUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(_root_ide_package_.me.arun.vastu.features.home.courses.screens.details.presentation.DetailsState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<me.arun.vastu.features.home.courses.screens.details.presentation.DetailsEvent>()
    val event = _event.asSharedFlow()

    init {
        loadInitialData()
    }

    fun onAction(action: DetailsAction) {
        when (action) {
            else -> {
            }
        }
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            getDetailsDataUseCase()
                .onSuccess {
                }
                .onFailure {
                }

            _state.update { it.copy(isLoading = false) }
        }
    }
}