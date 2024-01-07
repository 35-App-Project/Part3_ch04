package com.choi.a35app_part3_ch04.mvvm.repository

import com.choi.a35app_part3_ch04.mvvm.model.Image
import io.reactivex.Single

interface ImageRepository {
    // rxJava 를 활용한 REST 처리
    fun getRandomImage() : Single<Image>

}