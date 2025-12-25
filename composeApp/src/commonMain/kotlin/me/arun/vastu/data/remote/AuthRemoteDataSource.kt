package me.arun.vastu.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import me.arun.vastu.core.network.ApiEndpoints
import me.arun.vastu.data.model.AuthResponse
import me.arun.vastu.data.model.LoginRequest
import me.arun.vastu.data.model.RegisterRequest

class AuthRemoteDataSource(private val httpClient: HttpClient) {

    suspend fun login(loginRequest: LoginRequest): AuthResponse {
        return httpClient.post(ApiEndpoints.LOGIN) {
            contentType(ContentType.Application.Json)
            setBody(loginRequest)
        }.body()
    }

    suspend fun register(registerRequest: RegisterRequest): AuthResponse {
        return httpClient.post(ApiEndpoints.REGISTER) {
            contentType(ContentType.Application.Json)
            setBody(registerRequest)
        }.body()
    }

    suspend fun logout() {
        httpClient.post(ApiEndpoints.LOGOUT)
    }
}
