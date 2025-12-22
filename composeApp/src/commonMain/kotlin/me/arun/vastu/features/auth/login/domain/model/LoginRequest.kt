package me.arun.vastu.features.auth.login.domain.model

data class LoginRequest(
    val email: String,
    val password: String
)
