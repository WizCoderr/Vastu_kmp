package me.arun.vastu.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import me.arun.vastu.data.local.AuthLocalDataSource
import me.arun.vastu.data.model.AuthResponse
import me.arun.vastu.data.model.LoginRequest
import me.arun.vastu.data.model.RegisterRequest
import me.arun.vastu.data.remote.AuthRemoteDataSource
import me.arun.vastu.domain.repository.AuthRepository

/**
 * Dummy implementation for demonstration purposes.
 * In a real app, this would check a DataStore or Keychain for a valid auth token.
 */
class AuthRepositoryImpl(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource,
) : AuthRepository {
    private val isLoggedIn = MutableStateFlow(authLocalDataSource.getAuthToken() != null)

    override fun isUserLoggedIn(): Boolean = isLoggedIn.value

    override fun observeAuthState(): Flow<Boolean> = isLoggedIn

    override suspend fun login(loginRequest: LoginRequest): AuthResponse {
        val response = authRemoteDataSource.login(loginRequest)
        saveAuthToken(response.token)
        return response
    }

    override suspend fun register(registerRequest: RegisterRequest): AuthResponse {
        val response = authRemoteDataSource.register(registerRequest)
        saveAuthToken(response.token)
        return response
    }

    override suspend fun logout() {
        authRemoteDataSource.logout()
        clearAuthToken()
    }

    override suspend fun saveAuthToken(token: String) {
        authLocalDataSource.saveAuthToken(token)
        isLoggedIn.value = true
    }

    override suspend fun getAuthToken(): String? = authLocalDataSource.getAuthToken()

    override suspend fun clearAuthToken() {
        authLocalDataSource.clearAuthToken()
        isLoggedIn.value = false
    }
}
