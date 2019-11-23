package com.TeamPhich.deadline.services

import com.TeamPhich.deadline.responses.Space.*
import com.TeamPhich.deadline.responses.defaultRespone
import com.TeamPhich.deadline.responses.findmember.findPeople
import com.TeamPhich.deadline.responses.login.loginRespone
import com.TeamPhich.deadline.responses.signUp.signUpRespone
import kotlinx.coroutines.Deferred
import retrofit2.http.*
import retrofit2.http.GET


interface Api {

    @FormUrlEncoded
    @POST("/api/v1/accounts/register")
    fun createUser(
        @Field("user_name") user_name: String,
        @Field("password") password: String,
        @Field("email") email: String,
        @Field("full_name") full_name: String
    ): Deferred<signUpRespone>

    @FormUrlEncoded
    @POST("/api/v1/accounts/login")
    fun login(
        @Field("user_name") user_name: String,
        @Field("password") password: String
    ): Deferred<loginRespone>

    @FormUrlEncoded
    @POST("/api/v1/spaces/")
    fun createSpace(
        @Header("token") token: String,
        @Field("name") name: String
    ): Deferred<regSpaceRespone>


    @GET("/api/v1/spaces/")
    fun getListSpace(
        @Header("token") token: String
    ): Deferred<getListSpaceRespone>

    @GET("/api/v1/accounts/spaces/{space_id}")
    fun getSpaceToken(
        @Header("token") token: String,
        @Path("space_id") space_id: Int

    ): Deferred<spaceTokenRespone>

    @FormUrlEncoded
    @POST("/api/v1/spaces/members")
    fun importMemberToSpace(
        @Header("space-token") space_token: String,
        @Field("member_username") member_username: String
    ): Deferred<defaultRespone>

    @FormUrlEncoded
    @POST("/api/v1/tasks/")
    fun createTask(
        @Header("space-token") space_token: String,
        @Field("member_id") member_id: Int,
        @Field("title") title: String,
        @Field("deadline") deadline: String,
        @Field("description") description: String

    ): Deferred<defaultRespone>

    @GET("/api/v1/spaces/members")
    fun getSpacemember(
        @Header("space-token") space_token: String
    ): Deferred<listspacemem>




    @DELETE("/api/v1/spaces/members")
    fun deleteMember(
        @Header("space-token") space_token: String,
        @Field("user_id") user_id: String
    ): Deferred<defaultRespone>

    @DELETE("/api/v1/spaces/leavings")
    fun memberOutSpace(
        @Header("space-token") space_token: String
    ): Deferred<defaultRespone>

    @PUT("/api/v1/spaces/admins")
    fun adminToMem(
        @Header("space-token") space_token: String,
        @Field("user_id") user_id: String
    ): Deferred<defaultRespone>

    @GET("/api/v1/tasks/status")
    fun showSpacetask(
        @Header("space-token") space_token: String
    )


    //task
    @DELETE("/api/v1/spaces/members")
    fun deleteTask(
        @Header("space-token") space_token: String,
        @Field("task_id") task_id: String
    ): Deferred<defaultRespone>

    @GET("/api/v1/accounts")
    fun findmember(
        @Header("token") token: String,
        @Query("keywords") keywords:String
    ): Deferred<findPeople>

    @GET("/api/v1/tasks/statusd")
    fun getlisttask(
        @Header("token") token: String,
        @Query("keywords") keywords:String
    ): Deferred<findPeople>





}