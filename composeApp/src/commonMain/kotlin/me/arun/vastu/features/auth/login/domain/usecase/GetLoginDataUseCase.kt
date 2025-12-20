package me.arun.vastu.features.auth.login.domain.usecase

import me.arun.vastu.features.auth.login.domain.model.Login
import me.arun.vastu.features.auth.login.domain.repository.LoginRepository


/**
 * Use case that encapsulates the business logic for fetching the Login feature data.
 */
class GetLoginDataUseCase(
    private val repository: LoginRepository
) {

    /**
     * Executes the use case.
     */
    suspend operator fun invoke(): Result<Login> {
        return repository.getLoginData()
    }
}