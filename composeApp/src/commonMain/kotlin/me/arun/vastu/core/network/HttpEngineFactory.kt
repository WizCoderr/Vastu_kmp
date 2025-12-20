package me.arun.vastu.core.network

import io.ktor.client.engine.HttpClientEngine

expect class HttpEngineFactory constructor() {
    fun createEngine(): HttpClientEngine
}
