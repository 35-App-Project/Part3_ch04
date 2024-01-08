package com.choi.a35app_part3_ch04.mvi

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import coil.load
import com.choi.a35app_part3_ch04.databinding.ActivityMviBinding
import com.choi.a35app_part3_ch04.mvi.repository.ImageRepositoryImpl
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


// MainActivity가 Mvi 아키텍쳐에서 View의 역할을 한다
class MviActivity: AppCompatActivity() {

    private lateinit var binding : ActivityMviBinding

    private val viewModel : MviViewModel by viewModels {
        MviViewModel.MviViewModelFactory(ImageRepositoryImpl())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMviBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.view=this
        }
        observeViewModel()
    }

   fun loadImage() {
        lifecycleScope.launch {
            // intent 에 의해 viewModel 에 전달
            viewModel.channel.send(MviIntent.LoadImage)
        }
    }

    // 상태에 대한 관찰을 해야 하기 때문에
    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
                when(state) {
                    is MviState.Idle -> {
                        binding.progressView.isVisible=false
                    }
                    is MviState.Loading -> {
                        binding.progressView.isVisible=true
                    }
                    is MviState.LoadedImage -> {
                       binding.progressView.isVisible=false
                       binding.imageView.run {
                           setBackgroundColor(Color.parseColor(state.image.color))
                           load(state.image.url) {
                               crossfade(300)
                           }
                       }
                        binding.imageCountTextView.text="불러온 이미지 : ${state.count}"
                    }
                }
            }
        }
    }

}