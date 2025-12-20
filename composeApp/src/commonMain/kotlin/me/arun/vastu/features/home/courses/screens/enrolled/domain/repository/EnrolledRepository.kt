package me.arun.vastu.features.home.courses.screens.enrolled.domain.repository

import me.arun.vastu.features.home.courses.screens.enrolled.domain.model.Enrolled

/**
 * Interface defining the contract for the Enrolled feature's repository.
 */
interface EnrolledRepository {

    /**
     * Retrieves data for the Enrolled feature.
     *
     * @return A Result object containing the Enrolled domain model on success,
     * or an exception on failure.
     */
    suspend fun getEnrolledData(): Result<me.arun.vastu.features.home.courses.screens.enrolled.domain.model.Enrolled>
}