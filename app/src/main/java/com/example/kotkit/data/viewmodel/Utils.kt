package com.example.kotkit.data.viewmodel

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotkit.data.model.ApiResponse
import com.example.kotkit.data.model.ApiState
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

fun parseErrorResponse(exception: HttpException): ApiResponse<*>? {
    return try {
        val errorBody = exception.response()?.errorBody()?.string()
        if (errorBody != null) {
            val gson = Gson() // Use Gson or any JSON parser
            gson.fromJson(errorBody, ApiResponse::class.java)
        } else null
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
            stateSetter(ApiState.Success(result))
        } catch (e: HttpException) {
            val errorResponse = parseErrorResponse(e)
            stateSetter(ApiState.Error(errorResponse?.status, errorResponse?.code))
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
            stateSetter(ApiState.Error(null, null))
        }
    }
}