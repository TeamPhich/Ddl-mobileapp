package com.TeamPhich.deadline.services

import com.TeamPhich.deadline.responses.githubResponse
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface apiGithubService {
    @GET("octocat")
    fun getDemo(): Deferred<githubResponse>

    companion object {
        operator fun invoke(): apiGithubService{

            return Retrofit.Builder()
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.github.com/users/")
                .build()
                .create(apiGithubService::class.java)
        }
    }
}