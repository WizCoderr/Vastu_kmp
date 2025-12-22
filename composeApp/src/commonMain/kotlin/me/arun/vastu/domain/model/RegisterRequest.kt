package me.arun.vastu.domain.model

data class RegisterRequest(
    val email: String,
    val password: String,
    val name: String,
)
