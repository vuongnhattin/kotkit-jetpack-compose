package com.example.kotkit.data.model

import org.json.JSONObject

sealed class ApiState<T> {
    data class Success<T>(val data: T?, val status: Int?, val code: String?) : ApiState<T>()
    data class Error<T>(val data: Any? = null, val status: Int? = null, val code: String?) : ApiState<T>()
    class Loading<T> : ApiState<T>()
}