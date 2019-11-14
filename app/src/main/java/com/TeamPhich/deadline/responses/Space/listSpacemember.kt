package com.TeamPhich.deadline.responses.Space

import com.google.gson.annotations.SerializedName

data class listSpacemember(
    @SerializedName("data")
    val `data`: rowmember,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("reason")
    val reason: String
)