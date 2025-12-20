package me.arun.vastu.domain.usecase.auth

import me.arun.vastu.domain.repository.AuthRepository

class ClearAuthTokenUseCase(private val authRepository: AuthRepository) {
    operator fun invoke() {
        authRepository.clearAuthToken()
    }
}
