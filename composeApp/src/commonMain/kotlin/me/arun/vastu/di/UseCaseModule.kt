package me.arun.vastu.di

import me.arun.vastu.domain.usecase.auth.GetAuthTokenUseCase
import me.arun.vastu.domain.usecase.auth.LoginUseCase
import me.arun.vastu.domain.usecase.auth.RegisterUseCase
import me.arun.vastu.domain.usecase.auth.SaveAuthTokenUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { SaveAuthTokenUseCase(get()) }
    factory { GetAuthTokenUseCase(get()) }
    factory { LoginUseCase(get()) }
    factory { RegisterUseCase(get()) }
}