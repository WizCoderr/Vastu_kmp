package me.arun.vastu.features.auth.register.data.repository

import me.arun.vastu.data.mapper.toDomain
import me.arun.vastu.data.model.RegisterRequest as DataRegisterRequest
import me.arun.vastu.data.remote.AuthRemoteDataSource
import me.arun.vastu.features.auth.register.domain.model.RegisterRequest
import me.arun.vastu.features.auth.register.domain.model.RegisterResult
import me.arun.vastu.features.auth.register.domain.repository.RegisterRepository


/**
 * Concrete implementation of the repository for the Register feature.
 */
class DefaultRegisterRepository(
    private val authRemoteDataSource: AuthRemoteDataSource
) : RegisterRepository {

    override suspend fun register(request: RegisterRequest): Result<RegisterResult> {
        return try {
            val authResponse = authRemoteDataSource.register(
                DataRegisterRequest(
                    name = request.name,
                    email = request.email,
                    password = request.password
                )
            )
            Result.success(RegisterResult(authResponse.toDomain()))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}