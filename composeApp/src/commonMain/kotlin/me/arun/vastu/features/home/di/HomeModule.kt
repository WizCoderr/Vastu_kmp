package me.arun.vastu.features.home.di

import me.arun.vastu.features.home.courses.di.courseModule
import me.arun.vastu.features.home.dashboard.dashboardModule
import me.arun.vastu.features.home.profile.di.profileModule
import org.koin.dsl.module

val  homeModule = module {
    includes(dashboardModule)
    includes(courseModule)
    includes(profileModule)
}