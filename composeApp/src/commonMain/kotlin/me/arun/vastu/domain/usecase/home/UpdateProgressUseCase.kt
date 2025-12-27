package me.arun.vastu.domain.usecase.home

import me.arun.vastu.data.model.ProgressUpdateRequest
import me.arun.vastu.data.remote.StudentRemoteDataSource
import me.arun.vastu.domain.model.Progress

class UpdateProgressUseCase(
    private val studentRemoteDataSource: StudentRemoteDataSource
) {
    suspend operator fun invoke(progress: Progress) {
        studentRemoteDataSource.updateProgress(ProgressUpdateRequest(progress.courseId, progress.lessonId, progress.completed))
    }
}