package com.TeamPhich.deadline.responses.findmember


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("accounts")
    val accounts: List<Account>
)