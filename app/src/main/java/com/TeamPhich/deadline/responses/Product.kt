package com.TeamPhich.deadline.responses


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("_id")
    var id: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("price")
    var price: Int,
    @SerializedName("__v")
    var v: Int
)