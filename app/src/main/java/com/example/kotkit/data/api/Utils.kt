package com.example.kotkit.data.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotkit.LocalAuthViewModel
import com.example.kotkit.data.model.ApiResponse
import com.example.kotkit.data.model.ApiState
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

fun parseErrorResponse(exception: HttpException): ApiResponse<*>? {
    return try {
        val errorBody = exception.response()?.errorBody()?.string()

        Gson().fromJson(errorBody, ApiResponse::class.java)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun <T> ViewModel.fetchApi(
    stateSetter: (ApiState<T>) -> Unit,
    apiCall: suspend () -> T
) {
    stateSetter(ApiState.Loading())
    viewModelScope.launch {
        try {
            val result = apiCall()

            println("result: $result")

            stateSetter(ApiState.Success(result))
        } catch (e: HttpException) {
            val errorResponse = parseErrorResponse(e)
            stateSetter(ApiState.Error(data = errorResponse?.data ,status = errorResponse?.status, code = errorResponse?.code))

            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()

            stateSetter(ApiState.Error())
        }
    }
}