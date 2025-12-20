package me.arun.vastu.features.auth.register.data.repository

import me.arun.vastu.features.auth.register.domain.model.Register
import me.arun.vastu.features.auth.register.domain.repository.RegisterRepository


/**
 * Concrete implementation of the repository for the Register feature.
 */
class DefaultRegisterRepository : RegisterRepository {

    override suspend fun getRegisterData(): Result<Register> {
        return try {
            val domainModel =
                Register(name = "Wizcoderr", email = "ssayam200@gmail.com", password = "Sayam123")
            Result.success(domainModel)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}