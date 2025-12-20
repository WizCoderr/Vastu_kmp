package me.arun.vastu.features.home.dashboard.data.mappers

import me.arun.vastu.features.home.dashboard.data.model.*
import me.arun.vastu.features.home.dashboard.domain.model.*

fun DashboardDto.toDomain(): Dashboard =
    Dashboard(
        userName = userName,
        instituteName = instituteName,
        courses = courses.map { it.toDomain() }
    )

fun DashboardCourseDto.toDomain(): DashboardCourse =
    DashboardCourse(
        id = id,
        title = title,
        subtitle = subtitle,
        tag = tag,
        progress = progress,
        isCompleted = isCompleted,
        lastWatchedPositionMillis = lastWatchedPositionMillis,
        videoUrl = videoUrl
    )
