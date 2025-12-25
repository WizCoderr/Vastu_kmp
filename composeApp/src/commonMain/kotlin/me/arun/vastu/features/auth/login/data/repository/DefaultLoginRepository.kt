package me.arun.vastu.features.auth.login.data.repository

import me.arun.vastu.data.mapper.toDomain
import me.arun.vastu.data.model.LoginRequest as DataLoginRequest
import me.arun.vastu.data.remote.AuthRemoteDataSource
import me.arun.vastu.features.auth.login.domain.model.LoginRequest
import me.arun.vastu.features.auth.login.domain.model.LoginResult
import me.arun.vastu.features.auth.login.domain.repository.LoginRepository


/**
 * Concrete implementation of the repository for the Login feature.
 */
class DefaultLoginRepository(
    private val authRemoteDataSource: AuthRemoteDataSource
) : LoginRepository {

    override suspend fun login(request: LoginRequest): Result<LoginResult> {
        return try {
            val authResponse = authRemoteDataSource.login(
                DataLoginRequest(
                    email = request.email,
                    password = request.password
                )
            )
            Result.success(LoginResult(authResponse.toDomain()))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}