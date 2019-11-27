package com.TeamPhich.deadline.responses.Space.group


import com.google.gson.annotations.SerializedName

data class listGroupRespone(
    val reason: String,
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("success")
    val success: Boolean
)