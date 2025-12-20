package me.arun.vastu.features.home.dashboard.presentation.model

/**
 * Represents the UI model for a single item in the Dashboard feature.
 * This class is optimized for display in the Presentation Layer.
 */
data class DashboardUiCourse(
    val id: String,
    val title: String,
    val subtitle: String,
    val tag: String,
    val progress: Int,
    val isCompleted: Boolean,
    val lastWatchedPositionMillis: Long,
    val videoUrl: String
)