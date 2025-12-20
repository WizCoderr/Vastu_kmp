package me.arun.vastu.features.home.courses.domain.model

/**
 * Pure domain model.
 * No framework, no UI, no serialization.
 */
data class Course(
    val id: String,
    val title: String,
    val tag: String,
    val imageUrl: String?,
    val accessType: CourseAccessType
)
enum class CourseAccessType {
    BATCH,
    LIFETIME,
    RECORDED,
    COMBINED
}
data class Courses(
    val allCourses: List<Course>
)