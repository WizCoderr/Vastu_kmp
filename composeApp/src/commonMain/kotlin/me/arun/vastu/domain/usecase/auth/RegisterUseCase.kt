package me.arun.vastu.domain.usecase.auth

import me.arun.vastu.data.model.RegisterRequest
import me.arun.vastu.domain.repository.AuthRepository

class RegisterUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(request: RegisterRequest) = authRepository.register(request)
}