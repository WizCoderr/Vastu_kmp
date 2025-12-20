package me.arun.vastu.features.auth.register.presentation.di

import me.arun.vastu.features.auth.register.presentation.RegisterViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin module that provides presentation layer dependencies (ViewModel) for the Register feature.
 */
val registerPresentationModule = module {
    viewModel { RegisterViewModel() }
}