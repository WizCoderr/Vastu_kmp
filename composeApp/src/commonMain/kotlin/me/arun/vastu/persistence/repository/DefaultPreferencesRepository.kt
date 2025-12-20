package me.arun.vastu.persistence.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import me.arun.vastu.persistence.keys.PreferenceKeys
import me.arun.vastu.persistence.model.VideoProgress

class DefaultPreferencesRepository(private val dataStore: DataStore<Preferences>) : PreferencesRepository {

    override suspend fun saveAuthToken(token: String) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.AUTH_TOKEN] = token
        }
    }

    override fun getAuthToken(): Flow<String?> {
        return dataStore.data
            .catch { exception ->
                if (exception is Exception) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[PreferenceKeys.AUTH_TOKEN]
            }
    }

    override suspend fun saveUserName(name: String) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.USER_NAME] = name
        }
    }

    override fun getUserName(): Flow<String?> {
        return dataStore.data
            .catch { exception ->
                if (exception is Exception) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[PreferenceKeys.USER_NAME]
            }
    }

    override suspend fun saveVideoProgress(courseId: String, positionMillis: Long, progress: Int) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.videoPositionKey(courseId)] = positionMillis
            preferences[PreferenceKeys.videoProgressKey(courseId)] = progress
        }
    }

    override fun getVideoProgress(courseId: String): Flow<VideoProgress?> {
        return dataStore.data
            .catch { exception ->
                if (exception is Exception) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val position = preferences[PreferenceKeys.videoPositionKey(courseId)]
                val progress = preferences[PreferenceKeys.videoProgressKey(courseId)]
                if (position != null && progress != null) {
                    VideoProgress(position, progress)
                } else {
                    null
                }
            }
    }
}
