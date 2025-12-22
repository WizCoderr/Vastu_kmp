package me.arun.vastu.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PaymentIntentRequest(
    val courseId: String
)
