package me.arun.vastu.data.repository

import me.arun.vastu.data.mapper.toData
import me.arun.vastu.data.mapper.toDomain
import me.arun.vastu.data.remote.StudentRemoteDataSource
import me.arun.vastu.domain.model.Course
import me.arun.vastu.domain.model.Curriculum
import me.arun.vastu.domain.model.Progress
import me.arun.vastu.domain.model.ProgressResult
import me.arun.vastu.domain.repository.StudentRepository

class StudentRepositoryImpl(
    private val studentRemoteDataSource: StudentRemoteDataSource
) : StudentRepository {
    override suspend fun getCourses(page: Int, limit: Int): Result<List<Course>> {
        return runCatching {
            studentRemoteDataSource.getCourses().items.map { it.toDomain() }
        }
    }

    override suspend fun getCourseDetails(id: String): Result<Course> {
        return runCatching {
            studentRemoteDataSource.getCourseDetails(id).toDomain()
        }
    }

    override suspend fun getCourseCurriculum(id: String): Result<Curriculum> {
        return runCatching {
            studentRemoteDataSource.getCourseCurriculum(id).toDomain()
        }
    }

    override suspend fun updateProgress(progress: Progress): Result<ProgressResult> {
        return runCatching {
            studentRemoteDataSource.updateProgress(progress.toData()).toDomain()
        }
    }
}
