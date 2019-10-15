package com.TeamPhich.deadline.services

import com.TeamPhich.deadline.responses.defaultRespone
import retrofit2.Call
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

    ):Call<defaultRespone>

    @FormUrlEncoded
    @POST("login")
    fun login(
            @Field("user_name") user_name:String,
            @Field("password") password: String
    ):Call<defaultRespone>
}