package com.choi.a35app_part3_ch04.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object RetrofitManager {

    // okHttpClient 설정
    private val okHttpClient=OkHttpClient.Builder()
        .connectTimeout(5,TimeUnit.SECONDS)
        .readTimeout(5,TimeUnit.SECONDS)
        .writeTimeout(5,TimeUnit.SECONDS)
        .build()

    // Json 으로 변환을 위한 Gson Converter 세팅
    private val gson= GsonBuilder().setLenient().create()

    // retrofit2 설정
    private val retrofit= Retrofit.Builder()
        .baseUrl("https://api.unsplash.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        // rxjava 활용
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()

    val imageService : ImageService by lazy { retrofit.create(ImageService::class.java) }
}