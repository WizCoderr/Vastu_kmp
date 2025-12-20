package me.arun.vastu.persistence.keys

import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys {
    val AUTH_TOKEN = stringPreferencesKey("auth_token")
    val USER_NAME = stringPreferencesKey("user_name")
    val USER_EMAIL = stringPreferencesKey("user_email")

    fun videoPositionKey(courseId: String) = longPreferencesKey("video_position_$courseId")
    fun videoProgressKey(courseId: String) = intPreferencesKey("video_progress_$courseId")
}
