package me.arun.vastu.core.di

import io.kotzilla.sdk.analytics.koin.analytics
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration


fun initKoin(config: KoinAppDeclaration? = null){
    startKoin {
        config?.invoke(this)
        modules(modules=appModule)
        analytics()
    }
}