package com.TeamPhich.deadline.responses.Space


import com.google.gson.annotations.SerializedName

data class getListSpaceRespone(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("reason")
    val reason: String
)