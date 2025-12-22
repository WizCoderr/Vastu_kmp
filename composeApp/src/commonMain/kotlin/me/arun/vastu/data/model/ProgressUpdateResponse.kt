package me.arun.vastu.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ProgressUpdateResponse(
    val success: Boolean,
    val message: String
)
