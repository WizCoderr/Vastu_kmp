package me.arun.vastu.data.local

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set

class AuthLocalDataSource(private val settings: Settings) {

    fun saveAuthToken(token: String) {
        settings[AUTH_TOKEN_KEY] = token
    }

    fun getAuthToken(): String? {
        return settings.getStringOrNull(AUTH_TOKEN_KEY)
    }

    fun clearAuthToken() {
        settings.remove(AUTH_TOKEN_KEY)
    }

    companion object {
        private const val AUTH_TOKEN_KEY: String = "auth_token"
    }
}