package me.arun.vastu.persistence.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

internal const val APP_PREFERENCES = "app_preferences"
expect fun createDataStore(): DataStore<Preferences>