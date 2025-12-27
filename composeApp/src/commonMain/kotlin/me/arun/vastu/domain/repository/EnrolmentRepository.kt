package me.arun.vastu.domain.repository

import kotlinx.coroutines.flow.Flow

interface EnrolmentRepository {
    /**
     * Checks if the user is enrolled in a specific course.
     * @param courseId The ID of the course to check.
     */
    fun isEnrolled(courseId: String): Boolean

    /**
     * A reactive flow to observe the list of enrolled course IDs.
     */
    fun observeEnrolledCourses(): Flow<Set<String>>
}
