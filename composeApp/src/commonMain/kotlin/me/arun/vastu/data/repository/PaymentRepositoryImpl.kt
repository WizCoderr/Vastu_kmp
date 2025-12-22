package me.arun.vastu.data.repository

import me.arun.vastu.data.mapper.toData
import me.arun.vastu.data.mapper.toDomain
import me.arun.vastu.data.remote.PaymentRemoteDataSource
import me.arun.vastu.domain.model.PaymentIntent
import me.arun.vastu.domain.model.PaymentRequest
import me.arun.vastu.domain.repository.PaymentRepository

class PaymentRepositoryImpl(
    private val paymentRemoteDataSource: PaymentRemoteDataSource
) : PaymentRepository {
    override suspend fun createPaymentIntent(request: PaymentRequest): Result<PaymentIntent> {
        return runCatching {
            paymentRemoteDataSource.createPaymentIntent(request.toData()).toDomain()
        }
    }
}
