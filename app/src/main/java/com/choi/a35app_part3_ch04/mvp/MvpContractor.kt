package com.choi.a35app_part3_ch04.mvp

// 연결을 위한 interFace
interface MvpContractor {

    // 사용자 에게 보여줄  화면에 대한 Interface
    interface View {

        fun showImage(url:String, color:String)

        fun showImageCountText(count: Int)
    }

    // View 와 연결, Model 과 연결을 위한 메서드 들 등록
    interface Presenter {

        // View 에 연결
        fun attachView(view: View)

        // View 와 연동 해제
        fun detachView()

        // Model 로 부터 데이터 불러오기
        fun loadRandomImage()
    }
}