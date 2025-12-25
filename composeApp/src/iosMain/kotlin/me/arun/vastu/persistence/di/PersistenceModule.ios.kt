package me.arun.vastu.persistence.di

import me.arun.vastu.persistence.datastore.createDataStore
import me.arun.vastu.persistence.repository.DefaultPreferencesRepository
import me.arun.vastu.persistence.repository.PreferencesRepository
import org.koin.dsl.module

actual val persistenceModule = module {
    single { createDataStore() }
    single<PreferencesRepository> { DefaultPreferencesRepository(get()) }
}
