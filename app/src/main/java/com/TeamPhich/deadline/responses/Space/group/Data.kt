package com.TeamPhich.deadline.responses.Space.group


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("rows1")
    val rows1: List<RowsGroup>,
    @SerializedName("rows2")
    val rows2: List<RowsGroup>
)