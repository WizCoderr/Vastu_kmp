package me.arun.vastu.features.auth.login.data.di

import me.arun.vastu.features.auth.login.data.repository.DefaultLoginRepository
import me.arun.vastu.features.auth.login.domain.repository.LoginRepository
import org.koin.dsl.module

/**
 * Koin module that provides data layer dependencies for the Login feature.
 */
val loginDataModule = module {
    factory<LoginRepository> { DefaultLoginRepository() }
}