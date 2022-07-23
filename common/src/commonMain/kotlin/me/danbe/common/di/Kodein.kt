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
import me.danbe.common.data.network.HttpEngineFactory
import me.danbe.common.ui.screen.mainScreen.MainViewModel

val apiModule = DI.Module("apiModule"){

    bindSingleton { HttpEngineFactory() }

    bindSingleton {
        Json {
            ignoreUnknownKeys = true
            explicitNulls = false
        }
    }

    bindSingleton {

        val httpEngineFactory = instance<HttpEngineFactory>()

        HttpClient(httpEngineFactory.createEngine()) {

            install(ContentNegotiation){
                json(instance())
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

val viewModelsModule = DI.Module("viewModelsModule"){
    bindProvider { MainViewModel(
        api = instance()
    ) }
}