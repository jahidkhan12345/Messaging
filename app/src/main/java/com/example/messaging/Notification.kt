package com.example.messaging

data class Notification(
    val title: String,
    val body: String,
    val android_channel_id: String,
    val sound: Boolean,

)