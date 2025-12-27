package me.arun.vastu.core.di

import kotlinx.serialization.json.Json
import me.arun.vastu.core.network.HttpEngineFactory
import me.arun.vastu.core.network.createHttpClient
import org.koin.dsl.module

val networkModule = module {
    single { HttpEngineFactory() }
    single { createHttpClient(get()) }
    single {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
            encodeDefaults = true
        }
    }
}
