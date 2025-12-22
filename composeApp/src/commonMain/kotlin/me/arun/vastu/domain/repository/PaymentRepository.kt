package me.arun.vastu.domain.repository

import me.arun.vastu.domain.model.PaymentIntent
import me.arun.vastu.domain.model.PaymentRequest

interface PaymentRepository {
    suspend fun createPaymentIntent(request: PaymentRequest): Result<PaymentIntent>
}
