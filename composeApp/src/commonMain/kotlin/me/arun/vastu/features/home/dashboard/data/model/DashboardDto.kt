package me.arun.vastu.features.home.dashboard.data.model

data class DashboardDto(
    val userName: String,
    val instituteName: String,
    val courses: List<DashboardCourseDto>
)

data class DashboardCourseDto(
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
