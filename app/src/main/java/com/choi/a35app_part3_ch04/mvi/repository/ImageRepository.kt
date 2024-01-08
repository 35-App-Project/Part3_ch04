package com.choi.a35app_part3_ch04.mvi.repository

import com.choi.a35app_part3_ch04.mvi.model.Image

interface ImageRepository {

    suspend fun getRandomImage() : Image

}