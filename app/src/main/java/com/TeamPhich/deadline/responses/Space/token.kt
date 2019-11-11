package com.TeamPhich.deadline.responses.Space


import com.google.gson.annotations.SerializedName

data class token(
    @SerializedName("tokenSpace")
    val tokenSpace: String
)