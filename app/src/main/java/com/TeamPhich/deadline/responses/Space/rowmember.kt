package com.TeamPhich.deadline.responses.Space


import com.google.gson.annotations.SerializedName

data class rowmember(
    @SerializedName("user_id")
    val user_id: Int,
    @SerializedName("user_name")
    val user_name: String,
    @SerializedName("full_name")
    val full_name: String
)


