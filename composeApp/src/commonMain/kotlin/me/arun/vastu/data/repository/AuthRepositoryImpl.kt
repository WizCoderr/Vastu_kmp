package me.arun.vastu.data.repository

import kotlinx.coroutines.flow.firstOrNull
import me.arun.vastu.core.network.utils.ApiResult
import me.arun.vastu.data.model.AuthResponse
import me.arun.vastu.data.model.LoginRequest
import me.arun.vastu.data.model.RegisterRequest
import me.arun.vastu.data.remote.AuthRemoteDataSource
import me.arun.vastu.domain.repository.AuthRepository
import me.arun.vastu.persistence.repository.PreferencesRepository

class AuthRepositoryImpl(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val preferencesRepository: PreferencesRepository
) : AuthRepository {
    override suspend fun register(registerRequest: RegisterRequest): ApiResult<AuthResponse> {
        return try {
            ApiResult(
                success = true,
                data = authRemoteDataSource.register(registerRequest)
            )
        } catch (e: Exception) {
            ApiResult(
                success = false,
                data = null,
                error = e.message ?: "Unknown error"
            )
        }
    }

    override suspend fun login(loginRequest: LoginRequest): ApiResult<AuthResponse> {
        return try {
            ApiResult(
                success = true,
                data = authRemoteDataSource.login(loginRequest)
            )
        } catch (e: Exception) {
            ApiResult(
                success = false,
                data = null,
                error = e.message ?: "Unknown error"
            )
        }
    }

    override suspend fun logout() {
        try {
            authRemoteDataSource.logout()
        } catch (e: Exception) {
            // Even if remote logout fails, clear the token locally
            e.printStackTrace()
        }
        clearAuthToken()
    }

    override suspend fun saveAuthToken(token: String) {
        preferencesRepository.saveAuthToken(token)
    }

    override suspend fun getAuthToken(): String? {
        return preferencesRepository.getAuthToken().firstOrNull()
    }

    override suspend fun clearAuthToken() {
        preferencesRepository.saveAuthToken("")
    }
}