package me.arun.vastu.features.home.profile.di

import me.arun.vastu.features.home.profile.data.di.profileDataModule
import me.arun.vastu.features.home.profile.domain.di.profileDomainModule
import me.arun.vastu.features.home.profile.presentation.di.profilePresentationModule
import org.koin.dsl.module

val profileModule = module {
    includes(profileDataModule)
    includes(profileDomainModule)
    includes(profilePresentationModule)
}