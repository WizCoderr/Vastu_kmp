package me.arun.vastu.features.home.courses.screens.enrolled.data.repository

import me.arun.vastu.features.home.courses.screens.enrolled.data.mappers.toDomain
import me.arun.vastu.features.home.courses.screens.enrolled.domain.model.Enrolled
import me.arun.vastu.features.home.courses.screens.enrolled.domain.repository.EnrolledRepository


/**
 * Concrete implementation of the repository for the Enrolled feature.
 */
class DefaultEnrolledRepository  constructor() :
    me.arun.vastu.features.home.courses.screens.enrolled.domain.repository.EnrolledRepository {

    override suspend fun getEnrolledData(): Result<me.arun.vastu.features.home.courses.screens.enrolled.domain.model.Enrolled> {
        return try {
            val domainModel =
                _root_ide_package_.me.arun.vastu.features.home.courses.screens.enrolled.domain.model.Enrolled(
                    id = "1",
                    data = "Sample data from repository"
                )
            Result.success(domainModel)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}