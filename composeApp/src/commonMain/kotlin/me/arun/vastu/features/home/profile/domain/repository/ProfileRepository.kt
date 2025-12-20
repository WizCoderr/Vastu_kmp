package me.arun.vastu.features.home.profile.domain.repository

import me.arun.vastu.features.home.profile.domain.model.Profile

/**
 * Interface defining the contract for the Profile feature's repository.
 */
interface ProfileRepository {

    /**
     * Retrieves data for the Profile feature.
     *
     * @return A Result object containing the Profile domain model on success,
     * or an exception on failure.
     */
    suspend fun getProfileData(): Result<Profile>
}