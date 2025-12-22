package me.arun.vastu.features.auth.login.domain.repository

import me.arun.vastu.features.auth.login.domain.model.LoginRequest
import me.arun.vastu.features.auth.login.domain.model.LoginResult

/**
 * Interface defining the contract for the Login feature's repository.
 */
interface LoginRepository {

    /**
     * Retrieves data for the Login feature.
     *
     * @return A Result object containing the Login domain model on success,
     * or an exception on failure.
     */
    suspend fun login(request: LoginRequest): Result<LoginResult>
}