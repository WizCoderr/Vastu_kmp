package me.arun.vastu.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import me.arun.vastu.core.network.ApiEndpoints
import me.arun.vastu.data.model.PaymentIntentRequest
import me.arun.vastu.data.model.PaymentIntentResponse

class PaymentRemoteDataSource(private val httpClientProvider: () -> HttpClient) {

    suspend fun createPaymentIntent(request: PaymentIntentRequest): PaymentIntentResponse {
        return httpClientProvider().post(ApiEndpoints.CREATE_PAYMENT_INTENT) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }
}
