package com.example.kotkit.data.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.kotkit.data.api.fetchApi
import com.example.kotkit.data.api.service.UserApiService
import com.example.kotkit.data.dto.input.UpdateInfoInput
import com.example.kotkit.data.model.ApiState
import com.example.kotkit.data.mock.UserMock
import com.example.kotkit.data.dto.response.ApiResponse
import com.example.kotkit.data.model.FriendshipStatus
import com.example.kotkit.data.model.UserDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userApiService: UserApiService
) : ViewModel() {
    var listUserDetails by mutableStateOf<ApiState<List<UserDetails>>>(ApiState.Empty())
        private set

    var userDetails by mutableStateOf<ApiState<UserDetails>>(ApiState.Empty())
        private set

    var filteredListUser by mutableStateOf<List<UserDetails>>(emptyList())

    var updateInfoResponse by mutableStateOf<ApiState<UserDetails>>(ApiState.Empty())

    fun searchUsers(query: String) {
        fetchApi(
            stateSetter = { listUserDetails = it },
            apiCall = {
                val response = userApiService.searchUsers(query)
                response
            }
        )
    }

    fun updateFriendStatus(userId: Int, status: FriendshipStatus?) {
        val users = (listUserDetails as? ApiState.Success<List<UserDetails>>)?.data ?: return
        val updatedUsers = users.map { user ->
            if (user.userId == userId) {
                user.copy(friendStatus = status) // Create a new UserDetails object
            } else {
                user
            }
        }
        listUserDetails = ApiState.Success(updatedUsers)

        val user = (userDetails as? ApiState.Success<UserDetails>)?.data
        println("aaa")
        if (user?.userId == userId) {
            println("bbb")
            userDetails = ApiState.Success(user.copy(friendStatus = status))
        }

        val filteredUsers = filteredListUser.map { user ->
            if (user.userId == userId) {
                user.copy(friendStatus = status)
            } else {
                user
            }
        }
        filteredListUser = filteredUsers
    }

    fun getUserDetails(userId: Int) {
        fetchApi(stateSetter = { userDetails = it }) {
//            val user = UserMock.users.data?.firstOrNull { it.userId == userId }
            val response = userApiService.getUserDetails(userId)
            response
        }
    }

    fun getMe() {
        fetchApi(stateSetter = { userDetails = it }) {
            userApiService.getMe()
        }
    }

    fun updateMe(updateInfoInput: UpdateInfoInput) {
        fetchApi(stateSetter = { updateInfoResponse = it }) {
            userApiService.updateMe(updateInfoInput)
        }
    }

    fun resetUpdateInfoState() {
        updateInfoResponse = ApiState.Empty()
    }

    fun getFriendsOfUser(userId: Int) {
        fetchApi(stateSetter = { listUserDetails = it }) {
//            val response = UserMock.users
            val response = userApiService.getFriendsOfUser(userId)
            filteredListUser = response.data!!

            response
        }
    }

    fun filterFriendsOfUser(query: String) {
        val users = (listUserDetails as ApiState.Success<List<UserDetails>>).data!!

        filteredListUser = if (query.isEmpty()) {
            users
        } else {
            users.filter {
                it.email.contains(query, ignoreCase = true) || it.fullName.contains(query, ignoreCase = true)
            }
        }
    }
}