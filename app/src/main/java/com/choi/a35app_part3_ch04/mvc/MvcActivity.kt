package com.choi.a35app_part3_ch04.mvc

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.choi.a35app_part3_ch04.databinding.ActivityMvcBinding
import com.choi.a35app_part3_ch04.mvc.provider.ImageProvider

class MvcActivity: AppCompatActivity(), ImageProvider.Callback {

    private lateinit var binding : ActivityMvcBinding

    // Model과 provider를 Activity 쪽에서는 알지만 반대로는 모르기 때문에 재사용이 가능하다
    private val model=ImageCountModel()
    private val imageProvider= ImageProvider(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMvcBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.view=this
        }
    }

    fun loadImage() {
        imageProvider.getRandomImage()
    }

    override fun loadImage(url: String, color: String) {
        model.increase()
        with(binding) {
            imageView.run {
                setBackgroundColor(Color.parseColor(color))
                // Coil의 함수
                load(url)
            }
            imageCountTextView.text="불러온 수 : ${model.count}"
        }
    }
}