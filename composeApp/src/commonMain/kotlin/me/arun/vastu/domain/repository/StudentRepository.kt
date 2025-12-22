package me.arun.vastu.domain.repository

import me.arun.vastu.domain.model.Course
import me.arun.vastu.domain.model.Curriculum
import me.arun.vastu.domain.model.Progress
import me.arun.vastu.domain.model.ProgressResult

interface StudentRepository {
    suspend fun getCourses(page: Int, limit: Int): Result<List<Course>>
    suspend fun getCourseDetails(id: String): Result<Course>
    suspend fun getCourseCurriculum(id: String): Result<Curriculum>
    suspend fun updateProgress(progress: Progress): Result<ProgressResult>
}
