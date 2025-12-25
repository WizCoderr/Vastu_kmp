package me.arun.vastu.features.home.courses.data.di

import me.arun.vastu.features.home.courses.data.repository.DefaultCoursesRepository
import me.arun.vastu.features.home.courses.domain.repository.CoursesRepository
import org.koin.dsl.module

/**
 * Koin module that provides data layer dependencies for the Courses feature.
 */
val coursesDataModule = module {
    factory<CoursesRepository> { DefaultCoursesRepository(get()) }
}