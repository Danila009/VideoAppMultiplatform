package me.danbe.common.data.network

import me.danbe.common.data.network.common.ApiResponse
import me.danbe.common.data.network.common.Response
import me.danbe.common.data.network.model.Video
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Api(
    private val httpClient:HttpClient
): ApiResponse() {

    fun getVideos():Flow<Response<List<Video>>> = flow{
        emit( response(httpClient.get(BASE_PATH + VIDEO_URL)) )
    }

    companion object {
        const val BASE_PATH = "/youTube/api/"

        const val VIDEO_URL = "/video"
    }
}