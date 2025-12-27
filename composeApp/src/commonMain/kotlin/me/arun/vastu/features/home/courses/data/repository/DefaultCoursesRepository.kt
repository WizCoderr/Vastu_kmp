package me.arun.vastu.features.home.courses.data.repository

import me.arun.vastu.data.remote.StudentRemoteDataSource
import me.arun.vastu.features.home.courses.data.mappers.toDomain
import me.arun.vastu.features.home.courses.domain.model.Courses
import me.arun.vastu.features.home.courses.domain.repository.CoursesRepository


/**
 * Concrete implementation of the repository for the Courses feature.
 */
class DefaultCoursesRepository(
    private val studentRemoteDataSource: StudentRemoteDataSource
) : CoursesRepository {

    override suspend fun getCoursesData(): Result<Courses> {
        return try {
            val courses = studentRemoteDataSource.getCourses().data.map { it.toDomain() }
            val enrolledCourses = courses.filter { it.isEnrolled }
            val domainModel = Courses(allCourses = courses, enrolledCourses = enrolledCourses)
            Result.success(domainModel)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}