package com.choi.a35app_part3_ch04.mvp

import com.choi.a35app_part3_ch04.mvp.model.ImageCountModel
import com.choi.a35app_part3_ch04.mvp.repository.ImageRepository

class MvpPresenter(
    private val model: ImageCountModel,
    private val imageRepository: ImageRepository
) : MvpContractor.Presenter, ImageRepository.CallBack {

    private var view: MvpContractor.View? = null

    override fun attachView(view: MvpContractor.View) {
        this.view=view
    }

    override fun detachView() {
        view=null
    }

    override fun loadRandomImage() {
        imageRepository.getRandomImage(this)
    }

    // CallBack -> View 에 전달 + model 에 count 를 증가시키는 작업
    override fun loadImage(url: String, color: String) {
        model.increase()
        view?.showImage(url,color)
        view?.showImageCountText(model.count)
    }
}