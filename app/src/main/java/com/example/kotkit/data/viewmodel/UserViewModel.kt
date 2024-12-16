package com.example.kotkit.data.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.kotkit.data.model.ApiState
import com.example.kotkit.data.mock.UserMock
import com.example.kotkit.data.model.UserDetails

class UserViewModel : ViewModel() {
    var listUserDetails by mutableStateOf<ApiState<List<UserDetails>>>(ApiState.Loading())
        private set

    var userDetails by mutableStateOf<ApiState<UserDetails>>(ApiState.Loading())
        private set

    var filteredListUser by mutableStateOf<List<UserDetails>>(emptyList())

    fun searchUsers(query: String) {
        fetchApi(stateSetter = { listUserDetails = it }) {
//            val response = RetrofitInstance.userApi.searchUsers(query).data ?: emptyList()
//            delay(1000)
            val response = UserMock.users.data ?: emptyList()
            response
        }
    }

    fun getUserDetails(userId: Int) {
        fetchApi(stateSetter = { userDetails = it }) {
            val response = UserMock.users.data?.firstOrNull { it.id == userId } ?: UserDetails()
            response
        }
    }

    fun getFriendsOfUser(userId: Int) {
        fetchApi(stateSetter = { listUserDetails = it }) {
//            val response = RetrofitInstance.userApi.searchUsers(query).data ?: emptyList()
//            delay(1000)
            val response = UserMock.users.data ?: emptyList()
            filteredListUser = response

            response
        }
    }

    fun filterFriendsOfUser(query: String) {
        val users = (listUserDetails as ApiState.Success<List<UserDetails>>).data!!

        filteredListUser = if (query.isEmpty()) {
            users
        } else {
            users.filter {
                it.username.contains(query, ignoreCase = true) || it.fullName.contains(query, ignoreCase = true)
            }
        }
    }
}