package com.choi.a35app_part3_ch04.mvvm.repository

import com.choi.a35app_part3_ch04.mvvm.model.Image
import com.choi.a35app_part3_ch04.network.RetrofitManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ImageRepositoryImpl : ImageRepository {

    override fun getRandomImage() = RetrofitManager.imageService.getRandomImageRx()
        // 어느 Thread 를 통해 통신을 할 것인지 subScribeOn 에 작성
        .subscribeOn(Schedulers.io())
        // subscribe 될 때 어떤 스레드로? observeOn 에 작성
        .observeOn(AndroidSchedulers.mainThread())
        .flatMap { item ->
            // 값 반환
            Single.just(Image(item.urls.regular,item.color))
        }


}