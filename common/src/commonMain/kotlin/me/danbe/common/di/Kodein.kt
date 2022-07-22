package me.danbe.common.di

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import org.kodein.di.DI
import org.kodein.di.instance
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.kodein.di.*
import me.danbe.common.data.network.Api

val apiModule = DI.Module("apiModule"){
    bindSingleton {
        HttpClient {
            install(ContentNegotiation){
                json(
                    Json {
                        ignoreUnknownKeys = true
                        explicitNulls = false
                    }
                )
            }

            defaultRequest {
                url {
                    val basePath = "/youTube/api/"
                    protocol = URLProtocol.HTTPS
                    host = "api.cfif31.ru"
                    encodedPath = "$basePath$encodedPath"
                }
            }
        }
    }

    bindSingleton {
        Api(
            httpClient = instance()
        )
    }
}