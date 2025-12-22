package me.arun.vastu.features.auth.login.presentation.di

import me.arun.vastu.features.auth.login.presentation.LoginViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin module that provides presentation layer dependencies (ViewModel) for the Login feature.
 */
val loginPresentationModule = module {
    viewModel { LoginViewModel(get(), get()) }
}