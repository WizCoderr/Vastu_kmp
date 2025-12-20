package me.arun.vastu.features.home.dashboard.data.repository

import me.arun.vastu.features.home.dashboard.data.mappers.toDomain
import me.arun.vastu.features.home.dashboard.data.model.*
import me.arun.vastu.features.home.dashboard.domain.model.Dashboard
import me.arun.vastu.features.home.dashboard.domain.repository.DashboardRepository

class DefaultDashboardRepository : DashboardRepository {

    override suspend fun getDashboard(): Result<Dashboard> {
         val dummyDashboardDto = DashboardDto(
            userName = "Alex",
            instituteName = "Institute of Excellence",
            courses = listOf(
                DashboardCourseDto(
                    id = "1",
                    title = "Advanced Physics",
                    subtitle = "Next: Quantum Mechanics II",
                    tag = "2024 BATCH",
                    progress = 45,
                    isCompleted = false,
                    lastWatchedPositionMillis = 1200000,
                    videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                ),
                DashboardCourseDto(
                    id = "2",
                    title = "Organic Chemistry II",
                    subtitle = "Next: Carbon Structures",
                    tag = "TERM 2",
                    progress = 12,
                    isCompleted = false,
                    lastWatchedPositionMillis = 300000,
                    videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"
                ),
                DashboardCourseDto(
                    id = "3",
                    title = "Mathematics Foundation",
                    subtitle = "Score: 98/100",
                    tag = "COMPLETED",
                    progress = 100,
                    isCompleted = true,
                    lastWatchedPositionMillis = 0,
                    videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"
                )
            )
        )

        return Result.success(dummyDashboardDto.toDomain())
    }
}
