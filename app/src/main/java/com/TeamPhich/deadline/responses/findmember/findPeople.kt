package com.TeamPhich.deadline.responses.findmember


import com.google.gson.annotations.SerializedName

data class findPeople(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("success")
    val success: Boolean ,
    @SerializedName("reason")
    val reason: String
)