package com.choi.a35app_part3_ch04.mvc.provider

import com.choi.a35app_part3_ch04.network.ImageResponse
import com.choi.a35app_part3_ch04.network.RetrofitManager
import retrofit2.Call
import retrofit2.Response

// 초기 생성 시 Callback을 받기 위해 설정  (Hilt로 주입 가능할 듯?)
class ImageProvider(private val callback:Callback) {

    interface Callback {
        fun loadImage(url:String, color: String)
    }

    // 이미지 불러오기 버튼이 눌리면 해당 함수가 호출 -> 성공 시 CallBack의 Load이미지가 호출되어
    // 상속받은 부분에서 오버라이딩 된 부분의 함수가 호출 => 이미지가 Load 됨
    fun getRandomImage() {
        RetrofitManager.imageService.getRandomImage()
            .enqueue(object : retrofit2.Callback<ImageResponse>{
                override fun onResponse(
                    call: Call<ImageResponse>,
                    response: Response<ImageResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            callback.loadImage(it.urls.regular,it.color)
                        }
                    }
                }

                override fun onFailure(call: Call<ImageResponse>, t: Throwable) { }
            })
    }
}