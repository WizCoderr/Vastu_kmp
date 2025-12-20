package me.arun.vastu.features.home.courses.screens.details.di

import me.arun.vastu.features.home.courses.screens.details.data.di.detailsDataModule
import me.arun.vastu.features.home.courses.screens.details.domain.di.detailsDomainModule
import me.arun.vastu.features.home.courses.screens.details.presentation.di.detailsPresentationModule
import org.koin.dsl.module

val courseDetailsModule = module{
    includes(_root_ide_package_.me.arun.vastu.features.home.courses.screens.details.data.di.detailsDataModule)
    includes(_root_ide_package_.me.arun.vastu.features.home.courses.screens.details.domain.di.detailsDomainModule)
    includes(_root_ide_package_.me.arun.vastu.features.home.courses.screens.details.presentation.di.detailsPresentationModule)
}