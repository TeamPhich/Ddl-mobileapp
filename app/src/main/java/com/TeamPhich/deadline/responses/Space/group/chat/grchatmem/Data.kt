package com.TeamPhich.deadline.responses.Space.group.chat.grchatmem


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("rows")
    val rows: List<RowNotInGr>
)