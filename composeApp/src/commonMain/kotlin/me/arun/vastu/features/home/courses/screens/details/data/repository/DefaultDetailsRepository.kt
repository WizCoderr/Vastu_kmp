package me.arun.vastu.features.home.courses.screens.details.data.repository

import me.arun.vastu.features.home.courses.screens.details.data.mappers.toDomain
import me.arun.vastu.features.home.courses.screens.details.domain.model.Details
import me.arun.vastu.features.home.courses.screens.details.domain.repository.DetailsRepository


/**
 * Concrete implementation of the repository for the Details feature.
 */
class DefaultDetailsRepository  constructor() :
    me.arun.vastu.features.home.courses.screens.details.domain.repository.DetailsRepository {

    override suspend fun getDetailsData(): Result<me.arun.vastu.features.home.courses.screens.details.domain.model.Details> {
        return try {
            val domainModel =
                _root_ide_package_.me.arun.vastu.features.home.courses.screens.details.domain.model.Details(
                    id = "1",
                    data = "Sample data from repository"
                )
            Result.success(domainModel)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}