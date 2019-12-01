package com.TeamPhich.deadline.responses.Space


import com.google.gson.annotations.SerializedName

data class datapepleinsp(
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("imagesUrl")
    val imagesUrl: String,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("user_name")
    val userName: String,
    @SerializedName("id")
    val id: Int

)