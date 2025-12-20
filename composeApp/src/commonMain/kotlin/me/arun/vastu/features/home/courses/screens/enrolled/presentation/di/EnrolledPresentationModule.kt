package me.arun.vastu.features.home.courses.screens.enrolled.presentation.di

import me.arun.vastu.features.home.courses.screens.enrolled.presentation.EnrolledViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin module that provides presentation layer dependencies (ViewModel) for the Enrolled feature.
 */
val enrolledPresentationModule = module {
    viewModel {
        _root_ide_package_.me.arun.vastu.features.home.courses.screens.enrolled.presentation.EnrolledViewModel(
            get()
        )
    }
}