package me.arun.vastu.data.repository

import me.arun.vastu.data.local.AuthLocalDataSource
import me.arun.vastu.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authLocalDataSource: AuthLocalDataSource
) : AuthRepository {
    override fun saveAuthToken(token: String) {
        authLocalDataSource.saveAuthToken(token)
    }

    override fun getAuthToken(): String? {
        return authLocalDataSource.getAuthToken()
    }

    override fun clearAuthToken() {
        authLocalDataSource.clearAuthToken()
    }
}
