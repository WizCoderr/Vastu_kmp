package me.arun.vastu.data.mapper

import me.arun.vastu.data.model.PaymentIntentRequest as DataPaymentIntentRequest
import me.arun.vastu.domain.model.PaymentRequest as DomainPaymentRequest
import me.arun.vastu.data.model.PaymentIntentResponse as DataPaymentIntentResponse
import me.arun.vastu.domain.model.PaymentIntent as DomainPaymentIntent


fun DomainPaymentRequest.toData(): DataPaymentIntentRequest {
    return DataPaymentIntentRequest(
        courseId = this.courseId
    )
}

fun DataPaymentIntentResponse.toDomain(): DomainPaymentIntent {
    return DomainPaymentIntent(
        clientSecret = this.clientSecret
    )
}
