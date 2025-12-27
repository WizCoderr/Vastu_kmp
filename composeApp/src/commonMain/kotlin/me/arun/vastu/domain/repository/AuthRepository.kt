package me.arun.vastu.domain.repository

import kotlinx.coroutines.flow.Flow
import me.arun.vastu.data.model.AuthResponse
import me.arun.vastu.data.model.LoginRequest
import me.arun.vastu.data.model.RegisterRequest

interface AuthRepository {
    /**
     * Checks if the user is currently logged in.
     * This could be a simple check of whether a token exists in memory or DataStore.
     */
    fun isUserLoggedIn(): Boolean

    /**
     * A reactive flow to observe the authentication state.
     * Emits true if the user is logged in, false otherwise.
     */
    fun observeAuthState(): Flow<Boolean>

    suspend fun login(loginRequest: LoginRequest): AuthResponse

    suspend fun register(registerRequest: RegisterRequest): AuthResponse

    suspend fun logout()

    suspend fun saveAuthToken(token: String)

    suspend fun getAuthToken(): String?

    suspend fun clearAuthToken()
}
