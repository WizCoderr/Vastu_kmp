package me.arun.vastu.features.auth.register.domain.model

/**
 * Represents the main domain model for the Register feature.
 * This is the "clean" class used within the app (domain, presentation).
 *
 * @property id The unique identifier of the model.
 * @property data An example data field for the model.
 */
data class Register(
    val name: String,
    val email: String,
    val password: String
)