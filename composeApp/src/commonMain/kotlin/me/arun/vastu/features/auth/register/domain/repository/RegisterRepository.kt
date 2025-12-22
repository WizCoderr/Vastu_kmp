package me.arun.vastu.features.auth.register.domain.repository

import me.arun.vastu.features.auth.register.domain.model.RegisterRequest
import me.arun.vastu.features.auth.register.domain.model.RegisterResult

/**
 * Interface defining the contract for the Register feature's repository.
 */
interface RegisterRepository {

    /**
     * Retrieves data for the Register feature.
     *
     * @return A Result object containing the Register domain model on success,
     * or an exception on failure.
     */
    suspend fun register(request: RegisterRequest): Result<RegisterResult>
}