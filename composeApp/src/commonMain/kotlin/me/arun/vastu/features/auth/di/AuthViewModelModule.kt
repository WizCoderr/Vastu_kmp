package me.arun.vastu.features.auth.di

import me.arun.vastu.features.auth.viewmodel.AuthViewModel
import org.koin.dsl.module

val authViewModelModule = module {
    factory { AuthViewModel(get()) }
}
