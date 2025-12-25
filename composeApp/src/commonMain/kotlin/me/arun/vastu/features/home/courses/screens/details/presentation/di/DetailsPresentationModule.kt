package me.arun.vastu.features.home.courses.screens.details.presentation.di

import me.arun.vastu.features.home.courses.screens.details.presentation.DetailsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin module that provides presentation layer dependencies (ViewModel) for the Details feature.
 */
val detailsPresentationModule = module {
    viewModel { params ->
        DetailsViewModel(
            courseId = params.get(),
            getDetailsDataUseCase = get(),
            createPaymentIntentUseCase = get()
        )
    }
}