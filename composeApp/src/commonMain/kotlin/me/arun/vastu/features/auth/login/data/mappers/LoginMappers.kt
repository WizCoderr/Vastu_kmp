package me.arun.vastu.features.auth.login.data.mappers

import me.arun.vastu.features.auth.login.data.model.LoginDto
import me.arun.vastu.features.auth.login.domain.model.Login

/**
 * Maps a LoginDto (Data Layer) object to a Login (Domain Layer) object.
 *
 * @return The mapped Login object.
 */
fun LoginDto.toDomain(): Login {
    return Login(
        id = this.uniqueId,
        data = this.payload ?: "Data not available"
    )
}