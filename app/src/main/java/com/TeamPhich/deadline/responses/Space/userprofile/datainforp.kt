package com.TeamPhich.deadline.responses.Space.userprofile


import com.google.gson.annotations.SerializedName

data class datainforp(
    @SerializedName("data")
    val `data`: userProfile,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("reason")
    val reason: String
)