package me.arun.vastu.features.vedio.di

import me.arun.vastu.features.vedio.presentation.VideoPlayerViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val videoFeatureModule = module {
    viewModel { VideoPlayerViewModel(get()) }
}