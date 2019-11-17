package com.TeamPhich.deadline.responses.Space


import com.google.gson.annotations.SerializedName

data class listspacemem(
    @SerializedName("data")
    val `data`: DataX,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("reason")
    val reason: String
)