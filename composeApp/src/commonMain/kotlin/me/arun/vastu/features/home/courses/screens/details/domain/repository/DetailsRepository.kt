package me.arun.vastu.features.home.courses.screens.details.domain.repository

import me.arun.vastu.features.home.courses.screens.details.domain.model.Details

/**
 * Interface defining the contract for the Details feature's repository.
 */
interface DetailsRepository {

    /**
     * Retrieves data for the Details feature.
     *
     * @return A Result object containing the Details domain model on success,
     * or an exception on failure.
     */
    suspend fun getDetailsData(): Result<me.arun.vastu.features.home.courses.screens.details.domain.model.Details>
}