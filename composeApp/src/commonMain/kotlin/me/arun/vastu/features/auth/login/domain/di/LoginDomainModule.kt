package me.arun.vastu.features.auth.login.domain.di

import me.arun.vastu.features.auth.login.domain.usecase.LoginUseCase
import org.koin.dsl.module

/**
 * Koin module that provides domain layer dependencies (use cases) for the Login feature.
 */
val loginDomainModule = module {
    factory { LoginUseCase(get()) }
}