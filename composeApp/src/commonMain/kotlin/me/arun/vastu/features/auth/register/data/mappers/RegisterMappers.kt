package me.arun.vastu.features.auth.register.data.mappers

import me.arun.vastu.features.auth.register.data.model.RegisterDto
import me.arun.vastu.features.auth.register.domain.model.Register

/**
 * Maps a RegisterDto (Data Layer) object to a Register (Domain Layer) object.
 *
 * @return The mapped Register object.
 */
fun RegisterDto.toDomain(): Register {
    return Register(
        name = this.name,
        email = this.email,
        password = this.password,
    )
}