package com.TeamPhich.deadline.responses.Space


import com.google.gson.annotations.SerializedName

data class DataX(
    @SerializedName("rows")
    val rows: List<RowX>
)