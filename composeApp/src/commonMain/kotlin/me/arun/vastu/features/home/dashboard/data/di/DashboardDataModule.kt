package me.arun.vastu.features.home.dashboard.data.di

import me.arun.vastu.features.home.dashboard.data.repository.DefaultDashboardRepository
import me.arun.vastu.features.home.dashboard.domain.repository.DashboardRepository
import org.koin.dsl.module

val dashboardDataModule = module {
    single<DashboardRepository> { DefaultDashboardRepository() }
}
