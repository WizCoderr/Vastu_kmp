package me.arun.vastu.di

import me.arun.vastu.core.network.createHttpClient
import org.koin.dsl.module

val networkModule = module {
    single { createHttpClient(get()) }
}
