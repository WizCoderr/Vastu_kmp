package me.arun.vastu.features.home.courses.data.mappers

import me.arun.vastu.data.model.Course
import me.arun.vastu.features.home.courses.domain.model.Course as DomainCourse

fun Course.toDomain(): DomainCourse {
    return DomainCourse(
        id = id,
        title = title,
        thumbnail = imageUrl ?: "",
        isEnrolled = accessType == "enrolled"
    )
}
