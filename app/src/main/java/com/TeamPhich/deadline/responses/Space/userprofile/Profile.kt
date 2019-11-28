package com.TeamPhich.deadline.responses.Space.userprofile


import com.google.gson.annotations.SerializedName

data class Profile(
    @SerializedName("email")
    val email: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("imagesUrl")
    val imagesUrl: String,
    @SerializedName("role_name")
    val roleName: String,
    @SerializedName("user_name")
    val userName: String
)