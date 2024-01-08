package com.choi.a35app_part3_ch04.mvi

import com.choi.a35app_part3_ch04.mvi.model.Image

// 상태들을 위한 클래스
sealed class MviState {
    // 인자가 있는 경우 -> data Class, 인자가 없는 경우 -> object로 선언
    object Idle: MviState()
    object Loading : MviState()
    data class LoadedImage(val image: Image, val count: Int) : MviState()
}
