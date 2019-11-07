package com.TeamPhich.deadline.responses.Space


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("rows")
    val rows: List<Row>
)