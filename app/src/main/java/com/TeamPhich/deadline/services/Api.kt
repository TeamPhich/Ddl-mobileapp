package com.TeamPhich.deadline.services

import com.TeamPhich.deadline.responses.loginRespone.loginRespone
import com.TeamPhich.deadline.responses.signUpRespone.signUpRespone
import kotlinx.coroutines.Deferred
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST("register")
    fun createUser(
            @Field("user_name") user_name:String,
            @Field("password") password:String,
            @Field("email") email:String

    ):Deferred<signUpRespone>

    @FormUrlEncoded
    @POST("login")
    fun login(
            @Field("user_name") user_name:String,
            @Field("password") password: String
    ):Deferred<loginRespone>

}