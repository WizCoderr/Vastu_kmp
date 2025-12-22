package me.arun.vastu.domain.model

data class AuthResult(
    val token: String,
    val userDetails: UserDetails
)
