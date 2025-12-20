package me.arun.vastu.features.home.profile.data.repository

import me.arun.vastu.features.home.profile.data.mappers.toDomain
import me.arun.vastu.features.home.profile.domain.model.Profile
import me.arun.vastu.features.home.profile.domain.repository.ProfileRepository


/**
 * Concrete implementation of the repository for the Profile feature.
 */
class DefaultProfileRepository  constructor() : ProfileRepository {

    override suspend fun getProfileData(): Result<Profile> {
        return try {
            val domainModel = Profile(
                name="Sayam Sharma",
                email = "ssayam200@gmail.com",
                institute = "Arun Vastu",
                studentId = "2026-1-1",
                joinedDate = "1/1/2026",
                appVersion = "1.0"
            )
            Result.success(domainModel)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}