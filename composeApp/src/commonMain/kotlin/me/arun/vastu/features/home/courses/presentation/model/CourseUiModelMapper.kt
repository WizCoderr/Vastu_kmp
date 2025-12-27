package me.arun.vastu.features.home.courses.presentation.model

import me.arun.vastu.features.home.courses.domain.model.Course

fun Course.toUiModel(): CourseUiModel {
    return CourseUiModel(
        id = id,
        title = title,
        thumbnail = thumbnail,
        isEnrolled = isEnrolled
    )
}