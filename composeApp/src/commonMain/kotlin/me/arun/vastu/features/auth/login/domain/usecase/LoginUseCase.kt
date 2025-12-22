package me.arun.vastu.features.auth.login.domain.usecase

import me.arun.vastu.features.auth.login.domain.model.LoginRequest
import me.arun.vastu.features.auth.login.domain.model.LoginResult
import me.arun.vastu.features.auth.login.domain.repository.LoginRepository


/**
 * Use case that encapsulates the business logic for fetching the Login feature data.
 */
class LoginUseCase(
    private val repository: LoginRepository
) {

    /**
     * Executes the use case.
     */
    suspend operator fun invoke(request: LoginRequest): Result<LoginResult> {
        return repository.login(request)
    }
}