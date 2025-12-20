package me.arun.vastu.features.vedio.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun VideoPlayerRoot(
    courseId: String,
    lastWatchedPositionMillis: Long,
    onEvent: (VideoPlayerEvent) -> Unit
) {
    Text("Video Player for course: $courseId at $lastWatchedPositionMillis ms")
}

sealed interface VideoPlayerEvent {
    // Define video player specific events here
}