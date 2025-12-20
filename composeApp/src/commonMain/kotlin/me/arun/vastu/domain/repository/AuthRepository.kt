package me.arun.vastu.domain.repository

interface AuthRepository {
    fun saveAuthToken(token: String)
    fun getAuthToken(): String?
    fun clearAuthToken()
}
