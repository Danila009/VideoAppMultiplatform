package me.danbe.common.data.network.common

sealed class Response<T>(val data:T? = null, val message:String? = null){
    class Error<T>(message: String): Response<T>(message = message)
    class Loading<T>: Response<T>()
    class Succes<T>(data: T): Response<T>(data = data)
}
