package com.example.kotkit.data.model

sealed class ApiState<T> {
    data class Success<T>(val data: T?) : ApiState<T>()
    data class Error<T>(val status: Int?, val code: String?) : ApiState<T>()
    class Loading<T> : ApiState<T>()
}