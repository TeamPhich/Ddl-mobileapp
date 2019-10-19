package com.TeamPhich.deadline.responses.loginRespone
//loginand reg respone


data class loginRespone(
    val success: Boolean,
    val reason:String,
    val data: token
)
data class token (
    val token:String
)
