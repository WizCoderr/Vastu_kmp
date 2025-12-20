package me.arun.vastu.core.network

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import java.util.concurrent.TimeUnit

actual class HttpEngineFactory actual constructor() {
    actual fun createEngine(): HttpClientEngine {
        return OkHttp.create {
            config {
                retryOnConnectionFailure(true)
                connectTimeout(5, TimeUnit.SECONDS)
            }
        }
    }
}