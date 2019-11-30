package com.TeamPhich.deadline.responses.Space.task


import com.google.gson.annotations.SerializedName

data class task(
    @SerializedName("creator_id")
    val creatorId: Int,
    @SerializedName("deadline")
    val deadline: Long,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("member_id")
    val memberId: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("role")
    val role: Int
)