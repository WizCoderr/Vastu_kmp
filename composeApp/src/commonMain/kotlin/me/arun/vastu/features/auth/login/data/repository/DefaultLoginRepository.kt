package me.arun.vastu.features.auth.login.data.repository

import me.arun.vastu.features.auth.login.domain.model.Login
import me.arun.vastu.features.auth.login.domain.repository.LoginRepository


/**
 * Concrete implementation of the repository for the Login feature.
 */
class DefaultLoginRepository() : LoginRepository {

    override suspend fun getLoginData(): Result<Login> {
        return try {
            val domainModel = Login(id = "1", data = "Sample data from repository")
            Result.success(domainModel)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}