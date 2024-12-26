package com.example.kotkit.data.model

sealed class ApiState<T> {
    data class Success<T>(val data: T? = null, val status: Int? = 200, val code: String? = "SUCCESS") : ApiState<T>()
    data class Error<T>(val data: Any? = null, val status: Int? = null, val code: String?) : ApiState<T>()
    class Loading<T> : ApiState<T>()
    class Empty<T> : ApiState<T>()
}