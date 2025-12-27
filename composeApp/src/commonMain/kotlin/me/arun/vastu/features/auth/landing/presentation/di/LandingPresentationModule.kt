package me.arun.vastu.features.auth.landing.presentation.di

import me.arun.vastu.features.auth.landing.presentation.LandingViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin module that provides presentation layer dependencies (ViewModel) for the Landing feature.
 */
val landingPresentationModule = module {
    viewModel { LandingViewModel() }
}