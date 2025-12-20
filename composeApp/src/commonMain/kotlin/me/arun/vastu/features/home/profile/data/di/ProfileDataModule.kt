package me.arun.vastu.features.home.profile.data.di

import me.arun.vastu.features.home.profile.data.repository.DefaultProfileRepository
import me.arun.vastu.features.home.profile.domain.repository.ProfileRepository
import org.koin.dsl.module

/**
 * Koin module that provides data layer dependencies for the Profile feature.
 */
val profileDataModule = module {
    factory<ProfileRepository> { DefaultProfileRepository() }
}