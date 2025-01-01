package com.example.kotkit.data.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotkit.data.dto.response.ApiResponse
import com.example.kotkit.data.model.ApiState
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

fun <T> ViewModel.fetchApi(
    stateSetter: (ApiState<T>) -> Unit,
    apiCall: suspend () -> ApiResponse<T>
) {
    stateSetter(ApiState.Loading())

    viewModelScope.launch {
        try {
            delay(200)
            val result = apiCall()
            stateSetter(ApiState.Success(result.data, result.status, result.code))

            println("result: $result")
        } catch (e: HttpException) {
            // Get the api response (data, status, code)
            val errorBody = e.response()?.errorBody()?.string()
            // Convert to ApiResponse
            val errorResponse = Gson().fromJson(errorBody, ApiResponse::class.java)

            stateSetter(
                ApiState.Error(
                    data = errorResponse?.data,
                    status = errorResponse?.status,
                    code = errorResponse?.code
                )
            )

            println("error: $errorResponse")
            e.printStackTrace()
        } catch (e: Exception) {
            when (e) {
                is SocketTimeoutException -> stateSetter(ApiState.Error(code = "TIMEOUT"))
                is ConnectException -> stateSetter(ApiState.Error(code = "CONNECT_ERROR"))
                else -> stateSetter(ApiState.Error(code = "UNKNOWN_ERROR"))
            }
            e.printStackTrace()
        }
    }
}