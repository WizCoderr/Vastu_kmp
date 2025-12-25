package me.arun.vastu.domain.repository

import me.arun.vastu.core.network.dto.AuthResponse
import me.arun.vastu.core.network.dto.LoginRequest
import me.arun.vastu.core.network.utils.ApiResult
import me.arun.vastu.domain.model.RegisterRequest

interface AuthRepository {
    suspend fun register(registerRequest: RegisterRequest): ApiResult<AuthResponse>
    suspend fun login(loginRequest: LoginRequest): ApiResult<AuthResponse>
    suspend fun saveAuthToken(token: String)
    suspend fun getAuthToken(): String?
    suspend fun clearAuthToken()
}
