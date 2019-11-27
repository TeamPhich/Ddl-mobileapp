package com.TeamPhich.deadline.responses.Space.group


import com.google.gson.annotations.SerializedName

data class RowsGroup(
    @SerializedName("group_id")
    val groupId: Int,
    @SerializedName("name")
    val name: String
)