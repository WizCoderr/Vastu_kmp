package me.arun.vastu.features.auth.register.domain.usecase

import me.arun.vastu.features.auth.register.domain.model.Register
import me.arun.vastu.features.auth.register.domain.repository.RegisterRepository


/**
 * Use case that encapsulates the business logic for fetching the Register feature data.
 */
class GetRegisterDataUseCase(
    private val repository: RegisterRepository
) {

    /**
     * Executes the use case.
     */
    suspend operator fun invoke(): Result<Register> {
        return repository.getRegisterData()
    }
}