package com.TeamPhich.deadline.services


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor



object RetrofitClient {


    private const val BASE_URL = "http://18.162.125.153"
    var interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    var interceptor2 = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS);
    private val okHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(
        interceptor2)
        .build();
//        .addInterceptor{ chain ->
//            val original = chain.request()
//
//            val requestBuilder = original.newBuilder()
//                .method(original.method(), original.body())
//            val request = requestBuilder.build()
//            chain.proceed(request)
//        }.build()

    val instance: Api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        retrofit.create(Api::class.java)
    }

}