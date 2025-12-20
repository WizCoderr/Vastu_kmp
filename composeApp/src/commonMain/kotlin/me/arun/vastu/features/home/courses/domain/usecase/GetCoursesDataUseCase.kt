package me.arun.vastu.features.home.courses.domain.usecase

import me.arun.vastu.features.home.courses.domain.model.Courses
import me.arun.vastu.features.home.courses.domain.repository.CoursesRepository


/**
 * Use case that encapsulates the business logic for fetching the Courses feature data.
 */
class GetCoursesDataUseCase  constructor(
    private val repository: CoursesRepository
) {

    /**
     * Executes the use case.
     */
    suspend operator fun invoke(): Result<Courses> {
        return repository.getCoursesData()
    }
}