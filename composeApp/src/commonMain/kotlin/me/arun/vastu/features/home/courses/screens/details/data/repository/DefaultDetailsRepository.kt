package me.arun.vastu.features.home.courses.screens.details.data.repository

import me.arun.vastu.data.remote.StudentRemoteDataSource
import me.arun.vastu.features.home.courses.screens.details.data.mappers.toDomain
import me.arun.vastu.features.home.courses.screens.details.domain.model.Details
import me.arun.vastu.features.home.courses.screens.details.domain.repository.DetailsRepository

/**
 * Concrete implementation of the repository for the Details feature.
 */
class DefaultDetailsRepository(
    private val studentRemoteDataSource: StudentRemoteDataSource
) : DetailsRepository {

    override suspend fun getDetailsData(courseId: String): Result<Details> {
        return try {
            val courseDetails = studentRemoteDataSource.getCourseDetails(courseId).toDomain()
            val curriculum = studentRemoteDataSource.getCourseCurriculum(courseId).toDomain()
            Result.success(Details(courseDetails, curriculum))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}