package me.arun.vastu.features.home.courses.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import me.arun.vastu.features.home.courses.domain.usecase.GetCoursesDataUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import me.arun.vastu.features.home.courses.presentation.model.CourseUiModel

/**
 * Manages the business logic and state for the Courses feature.
 */
class CoursesViewModel(
    private val getCoursesDataUseCase: GetCoursesDataUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CoursesState())
    val state: StateFlow<CoursesState> = _state.asStateFlow()

    private val _event = MutableSharedFlow<CoursesEvent>()
    val event: SharedFlow<CoursesEvent> = _event.asSharedFlow()

    fun onAction(action: CoursesAction) {
        when (action) {

            is CoursesAction.OnCourseClick -> {
                emitEvent(
                    CoursesEvent.NavigateToCourse(action.courseId)
                )
            }
        }
    }

    fun loadInitialData() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            getCoursesDataUseCase()
                .onSuccess { courses ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            courses = courses.allCourses.filterNotNull().map { course ->
                                CourseUiModel(
                                    id = course.id,
                                    title = course.title,
                                    tag = course.tag,
                                    imageUrl = course.imageUrl ?: "",
                                    accessType = course.accessType
                                )
                            }
                        )
                    }
                }
                .onFailure { throwable ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = throwable.message ?: "Failed to load courses"
                        )
                    }
                }
        }
    }

    private fun emitEvent(event: CoursesEvent) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }
}
