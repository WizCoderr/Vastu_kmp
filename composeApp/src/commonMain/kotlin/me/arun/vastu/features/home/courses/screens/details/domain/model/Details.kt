package me.arun.vastu.features.home.courses.screens.details.domain.model

import me.arun.vastu.domain.model.Chapter

data class CourseDetails(
    val id: String,
    val title: String,
    val description: String,
    val instructor: String,
    val price: Double
)

data class Details(
    val courseDetails: CourseDetails,
    val curriculum: List<Chapter>
)
