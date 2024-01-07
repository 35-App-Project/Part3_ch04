package com.choi.a35app_part3_ch04.mvvm.repository

import com.choi.a35app_part3_ch04.mvvm.model.Image
import io.reactivex.Single

interface ImageRepository {
    fun getRandomImage() : Single<Image>

}