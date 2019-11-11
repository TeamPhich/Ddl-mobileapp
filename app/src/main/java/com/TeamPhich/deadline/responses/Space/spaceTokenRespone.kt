package com.TeamPhich.deadline.responses.Space


import com.google.gson.annotations.SerializedName

data class spaceTokenRespone(
    @SerializedName("data")
    val `data`: token,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("reason")
    val reason: String
)