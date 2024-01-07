package com.choi.a35app_part3_ch04.mvp.repository

// 불러온 image 를 저장 할 원천지
interface ImageRepository {

    fun getRandomImage(callback:CallBack)

    interface CallBack {
        fun loadImage(url:String, color: String)
    }

}