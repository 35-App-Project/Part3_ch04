package com.choi.a35app_part3_ch04.mvp

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.choi.a35app_part3_ch04.databinding.ActivityMvpBinding
import com.choi.a35app_part3_ch04.mvp.model.ImageCountModel
import com.choi.a35app_part3_ch04.mvp.repository.ImageRepositoryImpl

// View 에 속한다 (Presenter 를 직접 참조)
class MvpActivity: AppCompatActivity(), MvpContractor.View {

    private lateinit var binding : ActivityMvpBinding

    private lateinit var presenter: MvpContractor.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMvpBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.view=this
        }

        // 의존성 ( view 와 연결 )
        presenter=MvpPresenter(ImageCountModel(),ImageRepositoryImpl())
        presenter.attachView(this)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    fun loadImage() {
        presenter.loadRandomImage()
    }

    override fun showImage(url: String, color: String) {
        binding.imageView.run {
            setBackgroundColor(Color.parseColor(color))
            load(url) {
                // 이미지 부드럽게 불러오기
                crossfade(300)
            }
        }
    }

    override fun showImageCountText(count: Int) {
        binding.imageCountTextView.text="불러온 이미지 : $count"
    }

}