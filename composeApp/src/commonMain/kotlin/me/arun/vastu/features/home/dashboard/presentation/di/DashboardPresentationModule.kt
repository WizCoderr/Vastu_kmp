package me.arun.vastu.features.home.dashboard.presentation.di

import me.arun.vastu.features.home.dashboard.presentation.DashboardViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin module that provides presentation layer dependencies (ViewModel) for the Dashboard feature.
 */
val dashboardPresentationModule = module {
    viewModel { DashboardViewModel(get()) }
}