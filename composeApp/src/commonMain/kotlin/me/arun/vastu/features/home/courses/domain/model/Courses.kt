package me.arun.vastu.features.home.courses.domain.model

/**
 * Pure domain model.
 * No framework, no UI, no serialization.
 */
data class Course(
    val id: String,
    val title: String,
    val thumbnail: String,
    val isEnrolled: Boolean = false
)

data class Courses(
    val allCourses: List<Course>,
    val enrolledCourses: List<Course>
)