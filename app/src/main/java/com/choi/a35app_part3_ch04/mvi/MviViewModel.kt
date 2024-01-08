package com.choi.a35app_part3_ch04.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.choi.a35app_part3_ch04.mvi.repository.ImageRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

// 데이터를 모델과 연결시킬 뷰모델
class MviViewModel(private val imageRepository: ImageRepository) : ViewModel() {

    // Mvi 에서 Intent 는 Channel 을 통해서 받으면 된다
    val channel = Channel<MviIntent>()

    // MutableStateFlow 는 초깃 값이 반드시 필요
    private val _state= MutableStateFlow<MviState>(MviState.Idle)

    val state : StateFlow<MviState>
        get()=_state

    private var count=0

    init {
        handleIntent()
    }

    // intent 를 다룰 수 있는 함수
    private fun handleIntent() {
        viewModelScope.launch {
            channel.consumeAsFlow().collectLatest {
                when(it) {
                    MviIntent.LoadImage -> {
                        loadImage()
                    }
                }
            }
        }
    }

    fun loadImage() {
        viewModelScope.launch {
            _state.value=MviState.Loading
            val image=imageRepository.getRandomImage()
            count++
            _state.value=MviState.LoadedImage(image,count)
        }
    }


    class MviViewModelFactory(private val imageRepository: ImageRepository) :
            ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MviViewModel(imageRepository) as T
        }
            }
}