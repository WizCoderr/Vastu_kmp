package me.arun.vastu.features.home.courses.screens.enrolled.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope


import me.arun.vastu.features.home.courses.screens.enrolled.domain.usecase.GetEnrolledDataUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Manages the business logic and state for the Enrolled feature.
 */

class EnrolledViewModel  constructor(
    private val getEnrolledDataUseCase: me.arun.vastu.features.home.courses.screens.enrolled.domain.usecase.GetEnrolledDataUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(_root_ide_package_.me.arun.vastu.features.home.courses.screens.enrolled.presentation.EnrolledState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<me.arun.vastu.features.home.courses.screens.enrolled.presentation.EnrolledEvent>()
    val event = _event.asSharedFlow()

    init {
        loadInitialData()
    }

    fun onAction(action: me.arun.vastu.features.home.courses.screens.enrolled.presentation.EnrolledAction) {
        when (action) {
            else -> {
            }
        }
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            getEnrolledDataUseCase()
                .onSuccess {
                }
                .onFailure {
                }

            _state.update { it.copy(isLoading = false) }
        }
    }
}