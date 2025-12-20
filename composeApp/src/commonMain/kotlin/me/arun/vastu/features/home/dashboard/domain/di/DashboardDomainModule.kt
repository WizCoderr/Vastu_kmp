package me.arun.vastu.features.home.dashboard.domain.di

import me.arun.vastu.features.home.dashboard.domain.usecase.GetDashboardDataUseCase
import org.koin.dsl.module

val dashboardDomainModule = module {
    factory { GetDashboardDataUseCase(get()) }
}
