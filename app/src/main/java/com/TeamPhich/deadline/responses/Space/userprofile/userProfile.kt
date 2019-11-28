package com.TeamPhich.deadline.responses.Space.userprofile


import com.google.gson.annotations.SerializedName

data class userProfile(
    @SerializedName("profile")
    val profile: List<Profile>
)