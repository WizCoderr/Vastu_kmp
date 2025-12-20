package me.arun.vastu.persistence.di

import me.arun.vastu.persistence.datastore.provideDataStore
import me.arun.vastu.persistence.repository.DefaultPreferencesRepository
import me.arun.vastu.persistence.repository.PreferencesRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val persistenceModule = module {
    single { provideDataStore(androidContext()) }
    single<PreferencesRepository> { DefaultPreferencesRepository(get()) }
}
