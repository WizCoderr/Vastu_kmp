package me.arun.vastu.features.home.courses.presentation.di

import me.arun.vastu.features.home.courses.presentation.CoursesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin module that provides presentation layer dependencies (ViewModel) for the Courses feature.
 */
val coursesPresentationModule = module {
    viewModel { CoursesViewModel(get()) }
}