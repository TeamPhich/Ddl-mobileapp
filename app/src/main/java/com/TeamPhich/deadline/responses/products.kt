package com.TeamPhich.deadline.responses


import com.google.gson.annotations.SerializedName

data class products(
    @SerializedName("product")
    var product: Product
)