package me.arun.vastu.features.home.profile.domain.di

import me.arun.vastu.features.home.profile.domain.usecase.GetProfileDataUseCase
import org.koin.dsl.module

/**
 * Koin module that provides domain layer dependencies (use cases) for the Profile feature.
 */
val profileDomainModule = module {
    factory { GetProfileDataUseCase(get()) }
}