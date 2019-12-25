package com.TeamPhich.deadline.responses.Space.group.chat.grchatmem


import com.google.gson.annotations.SerializedName

data class ppnotingr(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("reason")
    val reason: String
)