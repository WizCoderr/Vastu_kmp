package me.arun.vastu.features.home.courses.screens.enrolled.domain.di

import me.arun.vastu.features.home.courses.screens.enrolled.domain.usecase.GetEnrolledDataUseCase
import org.koin.dsl.module

/**
 * Koin module that provides domain layer dependencies (use cases) for the Enrolled feature.
 */
val enrolledDomainModule = module {
    factory {
        _root_ide_package_.me.arun.vastu.features.home.courses.screens.enrolled.domain.usecase.GetEnrolledDataUseCase(
            get()
        )
    }
}