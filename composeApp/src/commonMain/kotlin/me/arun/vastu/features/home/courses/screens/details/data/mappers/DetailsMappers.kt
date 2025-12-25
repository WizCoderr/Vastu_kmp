package me.arun.vastu.features.home.courses.screens.details.data.mappers

import me.arun.vastu.data.model.Chapter as DataChapter
import me.arun.vastu.data.model.Course as DataCourse
import me.arun.vastu.data.model.CurriculumResponse
import me.arun.vastu.data.model.Lesson as DataLesson
import me.arun.vastu.domain.model.Chapter as DomainChapter
import me.arun.vastu.domain.model.Lesson as DomainLesson
import me.arun.vastu.features.home.courses.screens.details.domain.model.CourseDetails

fun DataCourse.toDomain(): CourseDetails {
    return CourseDetails(
        id = id,
        title = title,
        description = description,
        instructor = instructor,
        price = price
    )
}

fun CurriculumResponse.toDomain(): List<DomainChapter> {
    return chapters.map { it.toDomain() }
}

fun DataChapter.toDomain(): DomainChapter {
    return DomainChapter(
        id = id,
        title = title,
        lessons = lessons.map { it.toDomain() }
    )
}

fun DataLesson.toDomain(): DomainLesson {
    return DomainLesson(
        id = id,
        title = title
    )
}