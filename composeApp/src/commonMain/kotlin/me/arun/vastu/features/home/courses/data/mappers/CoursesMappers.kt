package me.arun.vastu.features.home.courses.data.mappers

import me.arun.vastu.data.model.Course
import me.arun.vastu.features.home.courses.domain.model.CourseAccessType
import me.arun.vastu.features.home.courses.domain.model.Course as DomainCourse

fun Course.toDomain(): DomainCourse {
    return DomainCourse(
        id = id,
        title = title,
        tag = tag,
        imageUrl = imageUrl,
        accessType = accessType.toAccessType()
    )
}
private fun String.toAccessType(): CourseAccessType =
    when (this.uppercase()) {
        "BATCH" -> CourseAccessType.BATCH
        "LIFETIME" -> CourseAccessType.LIFETIME
        "RECORDED" -> CourseAccessType.RECORDED
        "COMBINED" -> CourseAccessType.COMBINED
        else -> CourseAccessType.RECORDED
    }