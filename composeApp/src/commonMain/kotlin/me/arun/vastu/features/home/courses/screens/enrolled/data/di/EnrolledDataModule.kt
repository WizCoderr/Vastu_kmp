package me.arun.vastu.features.home.courses.screens.enrolled.data.di

import me.arun.vastu.features.home.courses.screens.enrolled.data.repository.DefaultEnrolledRepository
import me.arun.vastu.features.home.courses.screens.enrolled.domain.repository.EnrolledRepository
import org.koin.dsl.module

/**
 * Koin module that provides data layer dependencies for the Enrolled feature.
 */
val enrolledDataModule = module {
    factory<me.arun.vastu.features.home.courses.screens.enrolled.domain.repository.EnrolledRepository> { _root_ide_package_.me.arun.vastu.features.home.courses.screens.enrolled.data.repository.DefaultEnrolledRepository() }
}