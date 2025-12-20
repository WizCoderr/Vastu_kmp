package me.arun.vastu.features.home.courses.screens.details.domain.usecase

import me.arun.vastu.features.home.courses.screens.details.domain.model.Details
import me.arun.vastu.features.home.courses.screens.details.domain.repository.DetailsRepository


/**
 * Use case that encapsulates the business logic for fetching the Details feature data.
 */
class GetDetailsDataUseCase  constructor(
    private val repository: me.arun.vastu.features.home.courses.screens.details.domain.repository.DetailsRepository
) {

    /**
     * Executes the use case.
     */
    suspend operator fun invoke(): Result<me.arun.vastu.features.home.courses.screens.details.domain.model.Details> {
        return repository.getDetailsData()
    }
}