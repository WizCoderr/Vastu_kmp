package me.arun.vastu.features.home.profile.domain.model

/**
 * Represents the main domain model for the Profile feature.
 * This is the "clean" class used within the app (domain, presentation).
 *
 * @property id The unique identifier of the model.
 * @property data An example data field for the model.
 */
data class Profile(
    val name: String,
    val email: String,
    val institute: String,
    val studentId: String,
    val joinedDate: String,
    val appVersion: String
)