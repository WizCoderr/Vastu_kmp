package me.arun.vastu.features.auth.di

import me.arun.vastu.features.auth.login.data.di.loginDataModule
import me.arun.vastu.features.auth.login.data.repository.DefaultLoginRepository
import me.arun.vastu.features.auth.login.domain.di.loginDomainModule
import me.arun.vastu.features.auth.login.domain.repository.LoginRepository
import me.arun.vastu.features.auth.login.domain.usecase.GetLoginDataUseCase
import me.arun.vastu.features.auth.login.presentation.LoginViewModel
import me.arun.vastu.features.auth.login.presentation.di.loginPresentationModule
import me.arun.vastu.features.auth.register.data.di.registerDataModule
import me.arun.vastu.features.auth.register.data.repository.DefaultRegisterRepository
import me.arun.vastu.features.auth.register.domain.di.registerDomainModule
import me.arun.vastu.features.auth.register.domain.repository.RegisterRepository
import me.arun.vastu.features.auth.register.domain.usecase.GetRegisterDataUseCase
import me.arun.vastu.features.auth.register.presentation.RegisterViewModel
import me.arun.vastu.features.auth.register.presentation.di.registerPresentationModule
import org.koin.dsl.module

val authModule = module {
    includes(loginDataModule)
    includes(loginDomainModule)
    includes(loginPresentationModule)
    includes(registerDomainModule)
    includes(registerDataModule)
    includes(registerPresentationModule)
}