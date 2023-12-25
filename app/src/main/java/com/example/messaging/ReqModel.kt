package com.example.messaging

import com.example.messaging.Notification

data class ReqModel(
    val notification: Notification,
    val registration_ids: List<String>
)