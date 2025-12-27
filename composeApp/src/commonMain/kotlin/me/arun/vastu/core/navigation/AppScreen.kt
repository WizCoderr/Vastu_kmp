package me.arun.vastu.core.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppScreen : NavKey {
    // Public Routes
    @Serializable
    data object Dashboard : AppScreen
    @Serializable
    data object Courses : AppScreen
    @Serializable
    data class CourseDetails(val courseId: String) : AppScreen
    @Serializable
    data object Stats : AppScreen

    // Auth Routes
    @Serializable
    data class Login(
        val redirectTo: String? = null
    ) : AppScreen
    @Serializable
    data class Register(
        val redirectTo: String? = null
    ) : AppScreen
}

@Serializable
sealed interface ProtectedRoute : AppScreen {
    // Protected Routes
    @Serializable
    data class Enroll(val courseId: String) : ProtectedRoute
    @Serializable
    data class VideoPlayer(
        val courseId: String,
        val lastWatchedPositionMillis: Long = 0L,
    ) : ProtectedRoute
    @Serializable
    data object MyCourses : ProtectedRoute
}
