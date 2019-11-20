package com.TeamPhich.deadline.responses.findmember


import com.google.gson.annotations.SerializedName

data class Account(
    @SerializedName("id")
    val id: Int,
    @SerializedName("user_name")
    val userName: String
)