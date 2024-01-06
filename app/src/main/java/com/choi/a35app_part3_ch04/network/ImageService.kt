package com.choi.a35app_part3_ch04.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ImageService {
    @Headers("Authorization: Client-ID xr5EcriIiBUVPkS3JWUMlnpr4UM5uhyYivvJD9qGPVA")
    @GET("photos/random")
    fun getRandomImage() : Call<ImageResponse>
}