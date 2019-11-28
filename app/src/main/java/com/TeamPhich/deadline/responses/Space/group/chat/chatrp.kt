package com.TeamPhich.deadline.responses.Space.group.chat


import com.google.gson.annotations.SerializedName

data class chatrp(
    @SerializedName("messages")
    val messages: List<Message>
)