package com.choi.a35app_part3_ch04.mvvm.bindingadapter

import android.graphics.Color
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.choi.a35app_part3_ch04.mvvm.model.Image

// dataBinding 을 위한 binding Adapter 설정
@BindingAdapter("image") // app:image 와 같이 xml에 속성 설정이 가능해진다
fun ImageView.setImage(image: Image?) {
    if (image==null) {
         return
    }

    setBackgroundColor(Color.parseColor(image.color))

    load(image.url) {
        crossfade(300)
    }

}