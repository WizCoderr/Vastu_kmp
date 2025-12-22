package me.arun.vastu.features.vedio.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.arun.vastu.domain.model.Progress
import me.arun.vastu.domain.usecase.home.UpdateProgressUseCase

class VideoPlayerViewModel(
    private val updateProgressUseCase: UpdateProgressUseCase
) : ViewModel() {

    fun onAction(action: VideoPlayerAction) {
        when (action) {
            is VideoPlayerAction.OnVideoPaused -> {
                updateProgress(action.courseId, action.position)
            }
        }
    }

    private fun updateProgress(courseId: String, position: Long) {
        viewModelScope.launch {
            val progress = Progress(courseId, "lesson-1", false)
            updateProgressUseCase(progress)
        }
    }
}

sealed interface VideoPlayerAction {
    data class OnVideoPaused(val courseId: String, val position: Long) : VideoPlayerAction
}