package me.arun.vastu.features.home.courses.di

import me.arun.vastu.features.home.courses.data.di.coursesDataModule
import me.arun.vastu.features.home.courses.domain.di.coursesDomainModule
import me.arun.vastu.features.home.courses.presentation.di.coursesPresentationModule
import org.koin.dsl.module

val courseModule = module {
    includes(coursesDataModule)
    includes(coursesDomainModule)
    includes(coursesPresentationModule)
}