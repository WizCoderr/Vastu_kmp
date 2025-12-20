package me.arun.vastu.features.home.courses.screens.details.domain.di

import me.arun.vastu.features.home.courses.screens.details.domain.usecase.GetDetailsDataUseCase
import org.koin.dsl.module

/**
 * Koin module that provides domain layer dependencies (use cases) for the Details feature.
 */
val detailsDomainModule = module {
    factory {
        _root_ide_package_.me.arun.vastu.features.home.courses.screens.details.domain.usecase.GetDetailsDataUseCase(
            get()
        )
    }
}