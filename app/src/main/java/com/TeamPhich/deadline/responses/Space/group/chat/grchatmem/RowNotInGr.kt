package com.TeamPhich.deadline.responses.Space.group.chat.grchatmem


import com.google.gson.annotations.SerializedName

data class RowNotInGr(
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imagesUrl")
    val imagesUrl: String,
    @SerializedName("role_id")
    val roleId: Int,
    @SerializedName("user_name")
    val userName: String
)