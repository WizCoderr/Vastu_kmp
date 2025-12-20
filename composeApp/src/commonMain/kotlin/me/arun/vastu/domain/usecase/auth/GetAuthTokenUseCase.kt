package me.arun.vastu.domain.usecase.auth

import me.arun.vastu.domain.repository.AuthRepository

class GetAuthTokenUseCase(private val authRepository: AuthRepository) {
    operator fun invoke(): String? {
        return authRepository.getAuthToken()
    }
}
