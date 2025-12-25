package me.arun.vastu.features.home.courses.screens.details.data.di

import me.arun.vastu.features.home.courses.screens.details.data.repository.DefaultDetailsRepository
import me.arun.vastu.features.home.courses.screens.details.domain.repository.DetailsRepository
import org.koin.dsl.module

/**
 * Koin module that provides data layer dependencies for the Details feature.
 */
val detailsDataModule = module {
    factory<DetailsRepository> { DefaultDetailsRepository(get()) }
}