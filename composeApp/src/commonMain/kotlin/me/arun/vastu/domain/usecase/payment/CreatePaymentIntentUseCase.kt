package me.arun.vastu.domain.usecase.payment

import me.arun.vastu.domain.model.PaymentRequest
import me.arun.vastu.domain.repository.PaymentRepository

class CreatePaymentIntentUseCase(
    private val paymentRepository: PaymentRepository
) {
    suspend operator fun invoke(request: PaymentRequest) = paymentRepository.createPaymentIntent(request)
}
