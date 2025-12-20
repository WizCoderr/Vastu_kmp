package me.arun.vastu.core.di

import me.arun.vastu.data.local.localStorageModule
import me.arun.vastu.di.dataModule
import me.arun.vastu.di.useCaseModule
import me.arun.vastu.features.auth.di.authModule
import me.arun.vastu.features.auth.login.data.di.loginDataModule
import me.arun.vastu.features.auth.login.domain.di.loginDomainModule
import me.arun.vastu.features.auth.login.presentation.di.loginPresentationModule
import me.arun.vastu.features.auth.register.data.di.registerDataModule
import me.arun.vastu.features.auth.register.domain.di.registerDomainModule
import me.arun.vastu.features.auth.register.presentation.di.registerPresentationModule
import me.arun.vastu.features.home.courses.di.courseScreensModule
import me.arun.vastu.features.home.courses.di.courseModule
import me.arun.vastu.features.home.dashboard.data.di.dashboardDataModule
import me.arun.vastu.features.home.dashboard.domain.di.dashboardDomainModule
import me.arun.vastu.features.home.dashboard.presentation.di.dashboardPresentationModule
import me.arun.vastu.features.home.di.homeModule
import me.arun.vastu.features.home.profile.di.profileModule
import me.arun.vastu.features.splash.di.splashModule
import org.koin.dsl.module

/**
 * A Koin module that aggregates all the dependency injection modules for the entire application.
 * It uses the `includes` function to combine modules from different layers (data, domain, presentation)
 * and features (auth, home, splash, etc.), providing a single point of configuration for Koin.
 */
val appModule = module {
    includes(authModule)
    includes(homeModule)
    includes(splashModule)
    includes(dataModule)
    includes(useCaseModule)
    includes(me.arun.vastu.features.home.courses.di.courseScreensModule)
    includes(localStorageModule())
    includes(networkModule)
}