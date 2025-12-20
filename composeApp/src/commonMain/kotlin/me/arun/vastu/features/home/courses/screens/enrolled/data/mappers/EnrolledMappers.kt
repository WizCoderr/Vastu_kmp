package me.arun.vastu.features.home.courses.screens.enrolled.data.mappers

import me.arun.vastu.features.home.courses.screens.enrolled.data.model.EnrolledDto
import me.arun.vastu.features.home.courses.screens.enrolled.domain.model.Enrolled

/**
 * Maps a EnrolledDto (Data Layer) object to a Enrolled (Domain Layer) object.
 *
 * @return The mapped Enrolled object.
 */
fun me.arun.vastu.features.home.courses.screens.enrolled.data.model.EnrolledDto.toDomain(): me.arun.vastu.features.home.courses.screens.enrolled.domain.model.Enrolled {
    return _root_ide_package_.me.arun.vastu.features.home.courses.screens.enrolled.domain.model.Enrolled(
        id = this.uniqueId,
        data = this.payload ?: "Data not available"
    )
}