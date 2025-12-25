package me.arun.vastu.features.auth.di

import me.arun.vastu.features.auth.login.data.di.loginDataModule
import me.arun.vastu.features.auth.login.domain.di.loginDomainModule
import me.arun.vastu.features.auth.login.presentation.di.loginPresentationModule
import me.arun.vastu.features.auth.register.data.di.registerDataModule
import me.arun.vastu.features.auth.register.domain.di.registerDomainModule
import me.arun.vastu.features.auth.register.presentation.di.registerPresentationModule
import org.koin.dsl.module

val authModule = module {
    includes(loginDataModule)
    includes(loginDomainModule)
    includes(loginPresentationModule)
    includes(registerDomainModule)
    includes(registerDataModule)
    includes(registerPresentationModule)
}