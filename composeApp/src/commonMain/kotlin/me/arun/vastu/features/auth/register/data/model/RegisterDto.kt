package me.arun.vastu.features.auth.register.data.model

import kotlinx.serialization.Serializable

/**
 * Data Transfer Object (DTO) for the Register feature received from an external source (e.g., API).
 * Its structure mirrors the data source's response.
 *
 * @property uniqueId The identifier from the API (could have a different name).
 * @property payload The raw data from the API, which could be null.
 */
@Serializable
data class RegisterDto(
    val name: String,
    val email: String,
    val password: String
)