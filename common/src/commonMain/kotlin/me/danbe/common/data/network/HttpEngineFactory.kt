package me.danbe.common.data.network

import io.ktor.client.engine.*

expect class HttpEngineFactory constructor() {

    fun createEngine(): HttpClientEngineFactory<HttpClientEngineConfig>

}