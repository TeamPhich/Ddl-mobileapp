package com.TeamPhich.deadline.responses.Space.task


import com.google.gson.annotations.SerializedName

data class alltask(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("reason")
    val reason:String
)