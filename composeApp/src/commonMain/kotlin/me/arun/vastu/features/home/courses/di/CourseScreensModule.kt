package me.arun.vastu.features.home.courses.di

import me.arun.vastu.features.home.courses.screens.details.di.courseDetailsModule
import org.koin.dsl.module

val courseScreensModule = module {
    includes(courseDetailsModule)
}