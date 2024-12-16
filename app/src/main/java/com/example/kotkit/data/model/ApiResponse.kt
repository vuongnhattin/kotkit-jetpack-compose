package com.example.kotkit.data.model

data class ApiResponse<T> (
    var data: T? = null,
    var status: Int = 200,
    var code: String = "SUCCESS",
)