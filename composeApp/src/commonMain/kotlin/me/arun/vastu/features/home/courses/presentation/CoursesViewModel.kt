package me.arun.vastu.features.home.courses.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.arun.vastu.features.home.courses.domain.usecase.GetCoursesDataUseCase
import me.arun.vastu.features.home.courses.presentation.model.toUiModel

class CoursesViewModel(
    private val getCoursesDataUseCase: GetCoursesDataUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CoursesState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<CoursesEvent>()
    val event = _event.asSharedFlow()

    fun loadInitialData() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            getCoursesDataUseCase()
                .onSuccess { courses ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            allCourses = courses.allCourses.map { it.toUiModel() },
                            enrolledCourses = courses.enrolledCourses.map { it.toUiModel() }
                        )
                    }
                }
                .onFailure { e ->
                    _state.update { it.copy(isLoading = false, error = e.message) }
                }
        }
    }

    fun onAction(action: CoursesAction) {
        when (action) {
            is CoursesAction.OnCourseClick ->
                emit(CoursesEvent.NavigateToCourseDetails(action.courseId))

            is CoursesAction.OnTabChange ->
                _state.update { it.copy(selectedTab = action.tab) }
        }
    }

    private fun emit(event: CoursesEvent) = viewModelScope.launch { _event.emit(event) }
}
