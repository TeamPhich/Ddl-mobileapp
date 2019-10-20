package com.TeamPhich.deadline.responses.login

//loginand reg respone


data class loginRespone(
    val success: Boolean,
    val reason: String,
    val data: token
)

