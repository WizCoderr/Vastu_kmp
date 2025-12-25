package me.arun.vastu.data.mapper

import me.arun.vastu.data.model.AuthResponse
import me.arun.vastu.data.model.User
import me.arun.vastu.domain.model.AuthResult
import me.arun.vastu.domain.model.LoginRequest
import me.arun.vastu.domain.model.RegisterRequest
import me.arun.vastu.domain.model.UserDetails

fun AuthResponse.toDomain(): AuthResult {
    return AuthResult(
        token = this.token,
        userDetails = this.user.toDomain()
    )
}

fun User.toDomain(): UserDetails {
    return UserDetails(
        id = this.id,
        name = this.name,
        email = this.email
    )
}

fun LoginRequest.toData(): LoginRequest {
    return LoginRequest(
        email = this.email,
        password = this.password
    )
}

fun RegisterRequest.toData(): RegisterRequest {
    return RegisterRequest(
        email = this.email,
        password = this.password,
        name = this.name
    )
}
