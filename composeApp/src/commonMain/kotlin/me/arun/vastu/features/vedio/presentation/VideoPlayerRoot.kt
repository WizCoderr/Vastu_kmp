package me.arun.vastu.features.vedio.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.arun.vastu.features.vedio.VideoPlayer

@Composable
fun VideoPlayerRoot(
    courseId: String,
    lastWatchedPositionMillis: Long,
    onEvent: (VideoPlayerEvent) -> Unit
) {
    Text("Video Player for course: $courseId at $lastWatchedPositionMillis ms")

    VideoPlayer(
        url="https://vastu-media-prod.s3.ap-south-1.amazonaws.com/vastu-courses/videos/1766737204499-Javascript_in_1_shot_in_Hindi_part_1_720P.mp4?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAR4UVTH7ZRQO6GUUN%2F20251226%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20251226T082431Z&X-Amz-Expires=3600&X-Amz-Signature=a0ac6a802992bafc86eaea2eab2a25fd9d36f15f8be7ca6e760d5de4a378da2d&X-Amz-SignedHeaders=host&x-amz-checksum-mode=ENABLED&x-id=GetObject",
        modifier = Modifier,
        onCompleted={

        }
    )
}

sealed interface VideoPlayerEvent {
    // Define video player specific events here
}