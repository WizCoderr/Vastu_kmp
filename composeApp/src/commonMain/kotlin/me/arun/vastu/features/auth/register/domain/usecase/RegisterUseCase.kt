package me.arun.vastu.features.auth.register.domain.usecase

import me.arun.vastu.features.auth.register.domain.model.RegisterRequest
import me.arun.vastu.features.auth.register.domain.model.RegisterResult
import me.arun.vastu.features.auth.register.domain.repository.RegisterRepository


/**
 * Use case that encapsulates the business logic for fetching the Register feature data.
 */
class RegisterUseCase(
    private val repository: RegisterRepository
) {

    /**
     * Executes the use case.
     */
    suspend operator fun invoke(request: RegisterRequest): Result<RegisterResult> {
        return repository.register(request)
    }
}