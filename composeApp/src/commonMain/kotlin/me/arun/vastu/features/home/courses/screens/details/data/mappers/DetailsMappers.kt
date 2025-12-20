package me.arun.vastu.features.home.courses.screens.details.data.mappers

import me.arun.vastu.features.home.courses.screens.details.data.model.DetailsDto
import me.arun.vastu.features.home.courses.screens.details.domain.model.Details

/**
 * Maps a DetailsDto (Data Layer) object to a Details (Domain Layer) object.
 *
 * @return The mapped Details object.
 */
fun me.arun.vastu.features.home.courses.screens.details.data.model.DetailsDto.toDomain(): me.arun.vastu.features.home.courses.screens.details.domain.model.Details {
    return _root_ide_package_.me.arun.vastu.features.home.courses.screens.details.domain.model.Details(
        id = this.uniqueId,
        data = this.payload ?: "Data not available"
    )
}