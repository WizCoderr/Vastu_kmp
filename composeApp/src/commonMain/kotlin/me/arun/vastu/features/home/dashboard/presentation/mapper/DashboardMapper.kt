package me.arun.vastu.features.home.dashboard.presentation.mapper

import me.arun.vastu.features.home.dashboard.domain.model.Dashboard
import me.arun.vastu.features.home.dashboard.domain.model.DashboardCourse
import me.arun.vastu.features.home.dashboard.presentation.DashboardState
import me.arun.vastu.features.home.dashboard.presentation.model.DashboardUiCourse

fun Dashboard.toDashboardState(): DashboardState {
    return DashboardState(
        userName = this.userName,
        instituteName = this.instituteName,
        enrolledCourses = this.courses.map { it.toDashboardUiCourse() },
        activeCoursesCount = this.courses.count { !it.isCompleted }
    )
}

fun DashboardCourse.toDashboardUiCourse(): DashboardUiCourse {
    return DashboardUiCourse(
        id = this.id,
        title = this.title,
        subtitle = this.subtitle,
        tag = this.tag,
        progress = this.progress,
        isCompleted = this.isCompleted,
        lastWatchedPositionMillis = this.lastWatchedPositionMillis,
        videoUrl = this.videoUrl
    )
}
