package me.arun.vastu.features.auth.register.data.di

import me.arun.vastu.features.auth.register.data.repository.DefaultRegisterRepository
import me.arun.vastu.features.auth.register.domain.repository.RegisterRepository
import org.koin.dsl.module

/**
 * Koin module that provides data layer dependencies for the Register feature.
 */
val registerDataModule = module {
    factory<RegisterRepository> { DefaultRegisterRepository() }
}