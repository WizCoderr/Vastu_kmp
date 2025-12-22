package me.arun.vastu.features.auth.register.domain.model

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)
