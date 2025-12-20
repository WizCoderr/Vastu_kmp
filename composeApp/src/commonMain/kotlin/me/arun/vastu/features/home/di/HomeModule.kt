package me.arun.vastu.features.home.di

import me.arun.vastu.features.auth.login.data.repository.DefaultLoginRepository
import me.arun.vastu.features.auth.login.domain.repository.LoginRepository
import me.arun.vastu.features.auth.login.domain.usecase.GetLoginDataUseCase
import me.arun.vastu.features.auth.login.presentation.LoginViewModel
import me.arun.vastu.features.auth.register.data.repository.DefaultRegisterRepository
import me.arun.vastu.features.auth.register.domain.repository.RegisterRepository
import me.arun.vastu.features.auth.register.domain.usecase.GetRegisterDataUseCase
import me.arun.vastu.features.auth.register.presentation.RegisterViewModel
import me.arun.vastu.features.home.courses.di.courseModule
import me.arun.vastu.features.home.dashboard.dashboardModule
import me.arun.vastu.features.home.dashboard.data.repository.DefaultDashboardRepository
import me.arun.vastu.features.home.dashboard.domain.repository.DashboardRepository
import me.arun.vastu.features.home.dashboard.domain.usecase.GetDashboardDataUseCase
import me.arun.vastu.features.home.dashboard.presentation.DashboardViewModel
import me.arun.vastu.features.home.profile.di.profileModule
import org.koin.dsl.module

val  homeModule = module {
    includes(dashboardModule)
    includes(courseModule)
    includes(profileModule)
}