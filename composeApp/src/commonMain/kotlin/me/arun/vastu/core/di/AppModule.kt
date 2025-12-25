package me.arun.vastu.core.di

import me.arun.vastu.di.dataModule
import me.arun.vastu.di.useCaseModule
import me.arun.vastu.features.auth.login.presentation.di.loginPresentationModule
import me.arun.vastu.features.auth.register.presentation.di.registerPresentationModule
import me.arun.vastu.features.home.courses.di.courseScreensModule
import me.arun.vastu.features.home.di.homeModule
import me.arun.vastu.features.splash.di.splashModule
import me.arun.vastu.persistence.di.persistenceModule
import org.koin.dsl.module

/**
 * A Koin module that aggregates all the dependency injection modules for the entire application.
 * It uses the `includes` function to combine modules from different layers (data, domain, presentation)
 * and features (auth, home, splash, etc.), providing a single point of configuration for Koin.
 */
val appModule = module {
    includes(homeModule)
    includes(splashModule)
    includes(dataModule)
    includes(useCaseModule)
    includes(courseScreensModule)
    includes(persistenceModule)
    includes(networkModule)
    includes(loginPresentationModule)
    includes(registerPresentationModule)
}