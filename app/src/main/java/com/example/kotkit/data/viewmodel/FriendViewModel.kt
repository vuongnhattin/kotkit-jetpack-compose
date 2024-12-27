package com.example.kotkit.data.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.kotkit.data.api.fetchApi
import com.example.kotkit.data.api.service.FriendApiService
import com.example.kotkit.data.api.service.UserApiService
import com.example.kotkit.data.model.ApiState
import com.example.kotkit.data.model.Comment
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FriendViewModel @Inject constructor(
    private val friendApiService: FriendApiService
) : ViewModel() {
    var friendshipState by mutableStateOf<ApiState<Void>>(ApiState.Empty())
        private set

    fun unfriend(userId: Int) {
        fetchApi({ friendshipState = it }) {
            friendApiService.unfriend(userId)
        }
    }

    fun sendFriendRequest(userId: Int) {
        fetchApi({ friendshipState = it }) {
            friendApiService.sendFriendRequest(userId)
        }
    }

    fun takeBackFriendRequest(userId: Int) {
        fetchApi({ friendshipState = it }) {
            friendApiService.takeBackFriendRequest(userId)
        }
    }

    fun rejectFriendRequest(userId: Int) {
        fetchApi({ friendshipState = it }) {
            friendApiService.rejectFriendRequest(userId)
        }
    }

    fun acceptFriendRequest(userId: Int) {
        fetchApi({ friendshipState = it }) {
            friendApiService.acceptFriendRequest(userId)
        }
    }
}