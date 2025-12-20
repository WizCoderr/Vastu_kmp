package me.arun.vastu.domain.usecase.auth

import me.arun.vastu.domain.repository.AuthRepository

class SaveAuthTokenUseCase(private val authRepository: AuthRepository) {
    operator fun invoke(token: String) {
        authRepository.saveAuthToken(token)
    }
}
