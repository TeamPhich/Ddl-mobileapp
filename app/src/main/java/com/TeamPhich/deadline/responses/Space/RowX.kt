package com.TeamPhich.deadline.responses.Space


import com.google.gson.annotations.SerializedName

data class RowX(
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("user_name")
    val userName: String
)