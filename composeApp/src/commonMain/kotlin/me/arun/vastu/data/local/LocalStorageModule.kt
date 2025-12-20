package me.arun.vastu.data.local

import com.russhwolf.settings.Settings
import org.koin.core.module.Module
import org.koin.dsl.module

fun localStorageModule(): Module = module {
    single { Settings() }
}
