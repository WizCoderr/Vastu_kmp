package me.arun.vastu.features.auth.register.domain.di

import me.arun.vastu.features.auth.register.domain.usecase.RegisterUseCase
import org.koin.dsl.module

/**
 * Koin module that provides domain layer dependencies (use cases) for the Register feature.
 */
val registerDomainModule = module {
    factory { RegisterUseCase(get()) }
}