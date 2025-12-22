package me.arun.vastu.data.mapper

import me.arun.vastu.data.model.Course as DataCourse
import me.arun.vastu.domain.model.Course as DomainCourse
import me.arun.vastu.data.model.Lesson as DataLesson
import me.arun.vastu.domain.model.Lesson as DomainLesson
import me.arun.vastu.data.model.Chapter as DataChapter
import me.arun.vastu.domain.model.Chapter as DomainChapter
import me.arun.vastu.data.model.CurriculumResponse as DataCurriculumResponse
import me.arun.vastu.domain.model.Curriculum as DomainCurriculum
import me.arun.vastu.data.model.ProgressUpdateRequest as DataProgressUpdateRequest
import me.arun.vastu.domain.model.Progress as DomainProgress
import me.arun.vastu.data.model.ProgressUpdateResponse as DataProgressUpdateResponse
import me.arun.vastu.domain.model.ProgressResult as DomainProgressResult


fun DataCourse.toDomain(): DomainCourse {
    return DomainCourse(
        id = this.id,
        title = this.title,
        description = this.description
    )
}

fun DataLesson.toDomain(): DomainLesson {
    return DomainLesson(
        id = this.id,
        title = this.title
    )
}

fun DataChapter.toDomain(): DomainChapter {
    return DomainChapter(
        id = this.id,
        title = this.title,
        lessons = this.lessons.map { it.toDomain() }
    )
}

fun DataCurriculumResponse.toDomain(): DomainCurriculum {
    return DomainCurriculum(
        courseId = this.courseId,
        chapters = this.chapters.map { it.toDomain() }
    )
}

fun DomainProgress.toData(): DataProgressUpdateRequest {
    return DataProgressUpdateRequest(
        courseId = this.courseId,
        lessonId = this.lessonId,
        completed = this.completed
    )
}

fun DataProgressUpdateResponse.toDomain(): DomainProgressResult {
    return DomainProgressResult(
        success = this.success
    )
}
