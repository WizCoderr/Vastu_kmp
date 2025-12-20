package me.arun.vastu.di

import me.arun.vastu.domain.usecase.auth.ClearAuthTokenUseCase
import me.arun.vastu.domain.usecase.auth.GetAuthTokenUseCase
import me.arun.vastu.domain.usecase.auth.SaveAuthTokenUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { SaveAuthTokenUseCase(get()) }
    factory { GetAuthTokenUseCase(get()) }
    factory { ClearAuthTokenUseCase(get()) }
}
