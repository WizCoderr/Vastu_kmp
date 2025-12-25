package me.arun.vastu.features.home.dashboard.domain.model

data class Dashboard(
    val userName: String,
    val instituteName: String,
    val courses: List<DashboardCourse>
)

data class DashboardCourse(
    val id: String,
    val title: String,
    val subtitle: String,
    val tag: String,
    val progress: Int,
    val isCompleted: Boolean,
    val lastWatchedPositionMillis: Long,
    val videoUrl: String,
    val thumbnail: String
)
