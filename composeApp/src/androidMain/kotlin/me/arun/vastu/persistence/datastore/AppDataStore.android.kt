package me.arun.vastu.persistence.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

fun provideDataStore(context: Context): DataStore<Preferences> {
    return context.dataStore
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = APP_PREFERENCES)