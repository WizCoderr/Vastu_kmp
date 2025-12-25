package me.arun.vastu.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Course(
    val id: String,
    val title: String,
    val description: String,
    val instructor: String,
    val price: Double,
    val tag: String,
    val imageUrl: String?,
    val accessType: String
)
