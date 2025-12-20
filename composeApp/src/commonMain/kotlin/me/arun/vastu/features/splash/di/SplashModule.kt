package me.arun.vastu.features.splash.di

import me.arun.vastu.features.splash.SplashViewModel
import org.koin.dsl.module

val splashModule = module {
    factory { SplashViewModel(get()) }
}
