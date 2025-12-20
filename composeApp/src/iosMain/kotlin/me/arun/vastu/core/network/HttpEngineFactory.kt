package me.arun.vastu.core.network

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin

actual class HttpEngineFactory actual constructor() {
    actual fun createEngine(): HttpClientEngine {
        return Darwin.create {
            configureRequest {
                setAllowsCellularAccess(true)
            }
        }
    }
}
