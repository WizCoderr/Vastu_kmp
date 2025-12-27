package me.arun.vastu.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import me.arun.vastu.domain.repository.EnrolmentRepository

/**
 * Dummy implementation for demonstration purposes.
 * In a real app, this would fetch enrolled courses from a local database or remote API.
 */
class EnrolmentRepositoryImpl : EnrolmentRepository {
    private val enrolledCourses = MutableStateFlow<Set<String>>(emptySet())

    override fun isEnrolled(courseId: String): Boolean {
        return enrolledCourses.value.contains(courseId)
    }

    override fun observeEnrolledCourses(): Flow<Set<String>> {
        return enrolledCourses
    }

    // Helper for dummy enrollment
    fun enrollInCourse(courseId: String) {
        val updatedSet = enrolledCourses.value.toMutableSet()
        updatedSet.add(courseId)
        enrolledCourses.value = updatedSet
    }
}
