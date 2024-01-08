package com.choi.a35app_part3_ch04.mvi.repository

import com.choi.a35app_part3_ch04.mvi.model.Image
import com.choi.a35app_part3_ch04.network.RetrofitManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


// 초기화 부분에 Dispatcher 를 넣어야 테스트 코드 작성이 쉬워진다
class ImageRepositoryImpl(private val dispatcher: CoroutineDispatcher = Dispatchers.IO)
    : ImageRepository {

    override suspend fun getRandomImage() = withContext(dispatcher) {
        RetrofitManager.imageService.getRandomImageSuspend().let {
            Image(it.urls.regular,it.color)
        }
    }
}