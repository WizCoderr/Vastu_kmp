package me.arun.vastu.domain.usecase.auth

import me.arun.vastu.core.network.dto.LoginRequest
import me.arun.vastu.domain.repository.AuthRepository

class LoginUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(loginRequest: LoginRequest) = authRepository.login(loginRequest)
}
