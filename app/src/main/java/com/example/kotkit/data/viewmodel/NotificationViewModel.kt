package com.example.kotkit.data.viewmodel
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotkit.data.api.fetchApi
import com.example.kotkit.data.dto.input.CommentInput
import com.example.kotkit.data.api.service.CommentApiService
import com.example.kotkit.data.mock.NotificationMock
import com.example.kotkit.data.model.ApiState
import com.example.kotkit.data.model.Comment
import com.example.kotkit.data.model.Notification
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class NotificationViewModel @Inject constructor() : ViewModel() {

    var notifications by mutableStateOf<List<Notification>>(emptyList())
        private set

    var notificationState by mutableStateOf<ApiState<List<Notification>>>(ApiState.Empty())
        private set
    fun getNotificationById(notificationId: Int): Notification? {
        return notifications.find { it.notificationID == notificationId }
    }


    fun fetchNotifications() {
        notificationState = ApiState.Loading()
        try {
            val response = NotificationMock.notificationList
            if (response.data != null) {
                notifications = response.data!!
                notificationState = ApiState.Success(response.data)
            } else {
                notificationState = ApiState.Error(code="No data available")
            }
        } catch (e: Exception) {
            notificationState = ApiState.Error(code="Failed to fetch notifications")
        }
    }

    fun markNotificationAsChecked(notificationID: Int) {
        notifications = notifications.map { notification ->
            if (notification.notificationID == notificationID) {
                notification.copy(isChecked = true)
            } else {
                notification
            }
        }
    }
}

