package me.arun.vastu.domain.usecase.home

import me.arun.vastu.domain.model.Progress
import me.arun.vastu.domain.repository.StudentRepository

class UpdateProgressUseCase(
    private val studentRepository: StudentRepository
) {
    suspend operator fun invoke(progress: Progress) = studentRepository.updateProgress(progress)
}