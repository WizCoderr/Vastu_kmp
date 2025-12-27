package me.arun.vastu.features.home.profile.presentation.di

import me.arun.vastu.features.home.profile.presentation.ProfileViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin module that provides presentation layer dependencies (ViewModel) for the Profile feature.
 */
val profilePresentationModule = module {
    viewModel { ProfileViewModel(get(), get(), get()) }
}