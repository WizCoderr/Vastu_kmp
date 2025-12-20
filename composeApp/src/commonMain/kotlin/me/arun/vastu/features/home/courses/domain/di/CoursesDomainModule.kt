package me.arun.vastu.features.home.courses.domain.di

import me.arun.vastu.features.home.courses.domain.usecase.GetCoursesDataUseCase
import org.koin.dsl.module

/**
 * Koin module that provides domain layer dependencies (use cases) for the Courses feature.
 */
val coursesDomainModule = module {
    factory { GetCoursesDataUseCase(get()) }
}