package com.choi.a35app_part3_ch04.network

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ImageService {
    @Headers("Authorization: Client-ID xr5EcriIiBUVPkS3JWUMlnpr4UM5uhyYivvJD9qGPVA")
    @GET("photos/random")
    fun getRandomImage() : Call<ImageResponse>

    // Rx를 통한 REST 처리
    @Headers("Authorization: Client-ID xr5EcriIiBUVPkS3JWUMlnpr4UM5uhyYivvJD9qGPVA")
    @GET("photos/random")
    fun getRandomImageRx() : Single<ImageResponse>

    // Mvi 패턴은 Coroutine 으로 처리
    @Headers("Authorization: Client-ID xr5EcriIiBUVPkS3JWUMlnpr4UM5uhyYivvJD9qGPVA")
    @GET("photos/random")
    suspend fun getRandomImageSuspend() : ImageResponse

}