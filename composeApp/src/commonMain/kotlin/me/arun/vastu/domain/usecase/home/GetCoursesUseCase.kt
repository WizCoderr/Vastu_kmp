package me.arun.vastu.domain.usecase.home

import me.arun.vastu.domain.repository.StudentRepository

class GetCoursesUseCase(
    private val studentRepository: StudentRepository
) {
    suspend operator fun invoke(page: Int, limit: Int) = studentRepository.getCourses(page, limit)
}