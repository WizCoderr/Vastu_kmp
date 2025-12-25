package me.arun.vastu.di

import me.arun.vastu.data.local.AuthLocalDataSource
import me.arun.vastu.data.repository.AuthRepositoryImpl
import me.arun.vastu.domain.repository.AuthRepository
import org.koin.dsl.module

val dataModule = module {
    single { AuthLocalDataSource(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
}
