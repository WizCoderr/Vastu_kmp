package me.arun.vastu.features.vedio

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import platform.UIKit.UIView
import platform.AVFoundation.* // Import entire AVFoundation
import platform.Foundation.NSURL
import androidx.compose.ui.interop.UIKitView
import platform.AVKit.AVPlayerViewController
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun VideoPlayer(
    url: String,
    modifier: Modifier,
    onCompleted: () -> Unit
) {
    UIKitView(
        modifier = modifier,
        factory = {
            val nsUrl = NSURL(string = url)
            val player = nsUrl?.let { AVPlayer(uRL = it) }

            val controller = AVPlayerViewController()
            controller.player = player

            player?.play() // Call play safely on the AVPlayer instance

            controller.view as UIView
        },
        update = {
            // Optional: update logic for the UIKitView if needed
        }
    )
}
