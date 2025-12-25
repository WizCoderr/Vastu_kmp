package me.arun.vastu.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.flow.firstOrNull
import me.arun.vastu.core.network.ApiEndpoints
import me.arun.vastu.core.network.dto.AuthResponse
import me.arun.vastu.core.network.dto.LoginRequest
import me.arun.vastu.core.network.utils.ApiResult
import me.arun.vastu.domain.model.RegisterRequest
import me.arun.vastu.domain.repository.AuthRepository
import me.arun.vastu.persistence.repository.PreferencesRepository

class AuthRepositoryImpl(
    private val httpClient: HttpClient,
    private val preferencesRepository: PreferencesRepository
) : AuthRepository {
    override suspend fun register(registerRequest: RegisterRequest): ApiResult<AuthResponse> {
        return try {
            httpClient.post(ApiEndpoints.REGISTER) {
                setBody(registerRequest)
            }.body()
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
            httpClient.post(ApiEndpoints.LOGIN) {
                setBody(loginRequest)
            }.body()
        } catch (e: Exception) {
            ApiResult(
                success = false,
                data = null,
                error = e.message ?: "Unknown error"
            )
        }
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