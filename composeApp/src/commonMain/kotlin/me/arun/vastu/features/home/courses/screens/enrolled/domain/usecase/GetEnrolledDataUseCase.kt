package me.arun.vastu.features.home.courses.screens.enrolled.domain.usecase

import me.arun.vastu.features.home.courses.screens.enrolled.domain.model.Enrolled
import me.arun.vastu.features.home.courses.screens.enrolled.domain.repository.EnrolledRepository


/**
 * Use case that encapsulates the business logic for fetching the Enrolled feature data.
 */
class GetEnrolledDataUseCase  constructor(
    private val repository: me.arun.vastu.features.home.courses.screens.enrolled.domain.repository.EnrolledRepository
) {

    /**
     * Executes the use case.
     */
    suspend operator fun invoke(): Result<me.arun.vastu.features.home.courses.screens.enrolled.domain.model.Enrolled> {
        return repository.getEnrolledData()
    }
}