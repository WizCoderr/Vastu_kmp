package me.arun.vastu.core.di

import io.ktor.client.HttpClient
import me.arun.vastu.core.network.HttpEngineFactory
import me.arun.vastu.core.network.createHttpClient
import org.koin.dsl.module

val networkModule = module {
    single { HttpEngineFactory() }
    single<() -> HttpClient> { { createHttpClient(get()) } }
}
