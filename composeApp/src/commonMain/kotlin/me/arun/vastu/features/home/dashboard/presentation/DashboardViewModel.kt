package me.arun.vastu.features.home.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import me.arun.vastu.features.home.dashboard.domain.usecase.GetDashboardDataUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import me.arun.vastu.features.home.dashboard.presentation.mapper.toDashboardState
import me.arun.vastu.features.home.dashboard.presentation.model.DashboardUiCourse

class DashboardViewModel(
    private val getDashboardDataUseCase: GetDashboardDataUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(DashboardState())
    val state: StateFlow<DashboardState> = _state.asStateFlow()

    private val _event = MutableSharedFlow<DashboardEvent>()
    val event: SharedFlow<DashboardEvent> = _event.asSharedFlow()

    init {
        loadInitialData()
    }

    fun onAction(action: DashboardAction) {
        when (action) {
            is DashboardAction.OnCourseClick ->
                emitEvent(DashboardEvent.NavigateToCourse(action.courseId))

            is DashboardAction.OnContinueLearning ->
                emitEvent(DashboardEvent.NavigateToVideoPlayer(action.courseId, action.lastWatchedPositionMillis))

            is DashboardAction.OnVideoCompleted -> {
                updateCourseProgress(action.courseId, 100)
                updateCourseCompletion(action.courseId, true)
            }

            is DashboardAction.OnVideoPaused -> {
                updateLastWatchedPosition(action.courseId, action.position)
            }

            is DashboardAction.OnRefresh -> {
                loadInitialData()
            }

            DashboardAction.OnProfileClick ->
                emitEvent(DashboardEvent.NavigateToProfile)
        }
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            getDashboardDataUseCase()
                .onSuccess { dashboard ->
                    _state.update {
                        dashboard.toDashboardState().copy(isLoading = false)
                    }
                }
                .onFailure { throwable ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = throwable.message
                        )
                    }
                }
        }
    }

    private fun updateCourseProgress(courseId: String, progress: Int) {
        _state.update { currentState ->
            val updatedCourses = currentState.enrolledCourses.map { course ->
                if (course.id == courseId) {
                    course.copy(progress = progress)
                } else {
                    course
                }
            }
            currentState.copy(enrolledCourses = updatedCourses)
        }
    }

    private fun updateCourseCompletion(courseId: String, isCompleted: Boolean) {
        _state.update { currentState ->
            val updatedCourses = currentState.enrolledCourses.map { course ->
                if (course.id == courseId) {
                    course.copy(isCompleted = isCompleted)
                } else {
                    course
                }
            }
            currentState.copy(enrolledCourses = updatedCourses, activeCoursesCount = updatedCourses.count { !it.isCompleted })
        }
    }

    private fun updateLastWatchedPosition(courseId: String, position: Long) {
        _state.update { currentState ->
            val updatedCourses = currentState.enrolledCourses.map { course ->
                if (course.id == courseId) {
                    course.copy(lastWatchedPositionMillis = position)
                } else {
                    course
                }
            }
            currentState.copy(enrolledCourses = updatedCourses)
        }
    }

    private fun emitEvent(event: DashboardEvent) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }
}
