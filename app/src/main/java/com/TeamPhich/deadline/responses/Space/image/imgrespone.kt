package com.TeamPhich.deadline.responses.Space.image


import com.google.gson.annotations.SerializedName

data class imgrespone(
    @SerializedName("data")
    val `data`: urlimage,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("reason")
    val reason: String
)