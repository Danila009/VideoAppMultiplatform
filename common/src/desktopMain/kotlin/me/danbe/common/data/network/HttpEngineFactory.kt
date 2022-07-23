package me.danbe.common.data.network

import io.ktor.client.engine.*
import io.ktor.client.engine.apache.*

actual class HttpEngineFactory actual constructor() {

    actual fun createEngine(): HttpClientEngineFactory<HttpClientEngineConfig> {
        return Apache
    }
}