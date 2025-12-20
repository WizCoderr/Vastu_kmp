package me.arun.vastu.features.home.profile.domain.usecase

import me.arun.vastu.features.home.profile.domain.model.Profile
import me.arun.vastu.features.home.profile.domain.repository.ProfileRepository


/**
 * Use case that encapsulates the business logic for fetching the Profile feature data.
 */
class GetProfileDataUseCase  constructor(
    private val repository: ProfileRepository
) {

    /**
     * Executes the use case.
     */
    suspend operator fun invoke(): Result<Profile> {
        return repository.getProfileData()
    }
}