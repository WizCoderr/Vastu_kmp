package me.arun.vastu.domain.usecase.auth

import me.arun.vastu.domain.repository.AuthRepository

class GetAuthTokenUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(): String? {
        return authRepository.getAuthToken()
    }
}
