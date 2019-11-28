package com.TeamPhich.deadline.responses.Space.group.chat


import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("imagesUrl")
    val imagesUrl: String,
    @SerializedName("isUserMessages")
    val isUserMessages: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("time")
    val time: Long,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("user_name")
    val userName: String
)