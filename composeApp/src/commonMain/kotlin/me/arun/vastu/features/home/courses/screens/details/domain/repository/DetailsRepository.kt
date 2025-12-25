package me.arun.vastu.features.home.courses.screens.details.domain.repository

import me.arun.vastu.features.home.courses.screens.details.domain.model.Details

/**
 * Interface defining the contract for the Details feature's repository.
 */
interface DetailsRepository {

    /**
     * Fetches the data for the Details feature.
     */
    suspend fun getDetailsData(courseId: String): Result<Details>
}