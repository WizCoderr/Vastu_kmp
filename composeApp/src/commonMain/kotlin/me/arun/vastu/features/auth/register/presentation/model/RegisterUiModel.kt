package me.arun.vastu.features.auth.register.presentation.model

/**
 * Represents the UI model for a single item in the Register feature.
 * This class is optimized for display in the Presentation Layer.
 */
data class RegisterUiModel(
    val name: String,
    val email: String,
    val password: String
)