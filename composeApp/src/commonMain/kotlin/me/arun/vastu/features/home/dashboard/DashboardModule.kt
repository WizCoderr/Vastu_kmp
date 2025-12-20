package me.arun.vastu.features.home.dashboard

import me.arun.vastu.features.home.dashboard.data.di.dashboardDataModule
import me.arun.vastu.features.home.dashboard.domain.di.dashboardDomainModule
import me.arun.vastu.features.home.dashboard.presentation.di.dashboardPresentationModule
import org.koin.dsl.module

val dashboardModule = module {
    includes(dashboardDataModule)
    includes(dashboardDomainModule)
    includes(dashboardPresentationModule)
}