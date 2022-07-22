package me.danbe.common.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class Video(
    val id:Int,
    val title:String,
    val datePublication:String,
    val description:String,
    val previewsUrl:String,
    val videoUrl:String,
    val channel: Channel
)

@Serializable
data class Channel(
    val id:Int,
    val title:String,
    val icon:String,
    val description:String
)