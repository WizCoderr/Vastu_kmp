package me.arun.vastu.features.home.courses.domain.repository

import me.arun.vastu.features.home.courses.domain.model.Courses

/**
 * Interface defining the contract for the Courses feature's repository.
 */
interface CoursesRepository {

    /**
     * Retrieves data for the Courses feature.
     *
     * @return A Result object containing the Courses domain model on success,
     * or an exception on failure.
     */
    suspend fun getCoursesData(): Result<Courses>
}