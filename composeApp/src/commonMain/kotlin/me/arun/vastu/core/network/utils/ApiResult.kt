package me.arun.vastu.core.network.utils

import kotlinx.serialization.Serializable

@Serializable
data class ApiResult<T>(
    val success: Boolean,
    val data: T? = null,
    val error: String? = null
)
