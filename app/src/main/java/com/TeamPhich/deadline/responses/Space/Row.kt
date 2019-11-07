package com.TeamPhich.deadline.responses.Space


import com.google.gson.annotations.SerializedName

data class Row(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)