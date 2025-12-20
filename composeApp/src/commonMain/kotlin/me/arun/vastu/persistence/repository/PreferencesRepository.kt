package me.arun.vastu.persistence.repository

import kotlinx.coroutines.flow.Flow
import me.arun.vastu.persistence.model.VideoProgress

interface PreferencesRepository {
    suspend fun saveAuthToken(token: String)
    fun getAuthToken(): Flow<String?>

    suspend fun saveUserName(name: String)
    fun getUserName(): Flow<String?>

    suspend fun saveVideoProgress(courseId: String, positionMillis: Long, progress: Int)
    fun getVideoProgress(courseId: String): Flow<VideoProgress?>
}
