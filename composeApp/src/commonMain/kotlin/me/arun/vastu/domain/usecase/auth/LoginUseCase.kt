package me.arun.vastu.domain.usecase.auth

import me.arun.vastu.domain.model.LoginRequest
import me.arun.vastu.domain.repository.AuthRepository

class LoginUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(request: LoginRequest) = authRepository.login(request)
}