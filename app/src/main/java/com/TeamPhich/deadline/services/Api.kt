package com.TeamPhich.deadline.services

import com.TeamPhich.deadline.responses.Space.getListSpaceRespone
import com.TeamPhich.deadline.responses.Space.regSpaceRespone
import com.TeamPhich.deadline.responses.Space.spaceTokenRespone
import com.TeamPhich.deadline.responses.login.loginRespone
import com.TeamPhich.deadline.responses.signUp.signUpRespone
import kotlinx.coroutines.Deferred
import retrofit2.http.*
import retrofit2.http.GET


interface Api {

    @FormUrlEncoded
    @POST("api/v1/accounts/register")
    fun createUser(
        @Field("user_name") user_name: String,
        @Field("password") password: String,
        @Field("email") email: String,
        @Field("full_name") full_name: String
    ): Deferred<signUpRespone>

    @FormUrlEncoded
    @POST("api/v1/accounts/login")
    fun login(
        @Field("user_name") user_name: String,
        @Field("password") password: String
    ): Deferred<loginRespone>

    @FormUrlEncoded
    @POST("api/v1/spaces/")
    fun createSpace(
        @Header("token") token: String,
        @Field("name") name: String
    ): Deferred<regSpaceRespone>


    @GET("api/v1/spaces/")
    fun getListSpace(
        @Header("token") token: String
    ): Deferred<getListSpaceRespone>

    @GET("/api/v1/accounts/spaces/{space_id}")
    fun getSpaceToken(
        @Header("token") token: String,
        @Path("space_id") space_id: Int

    ): Deferred<spaceTokenRespone>

}