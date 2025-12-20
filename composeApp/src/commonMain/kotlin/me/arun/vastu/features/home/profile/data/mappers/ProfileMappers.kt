package me.arun.vastu.features.home.profile.data.mappers

import me.arun.vastu.features.home.profile.data.model.ProfileDto
import me.arun.vastu.features.home.profile.domain.model.Profile

/**
 * Maps a ProfileDto (Data Layer) object to a Profile (Domain Layer) object.
 *
 * @return The mapped Profile object.
 */
fun ProfileDto.toDomain(): Profile {
    return Profile(
       name = this.name,
        email = this.email,
        institute = this.institute,
        studentId = this.studentId,
        joinedDate = this.joinedDate,
        appVersion = this.appVersion,

    )
}