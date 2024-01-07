package com.choi.a35app_part3_ch04.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.choi.a35app_part3_ch04.mvvm.model.Image
import com.choi.a35app_part3_ch04.mvvm.repository.ImageRepository
import io.reactivex.disposables.CompositeDisposable

class MvvmViewModel(private val imageRepository: ImageRepository) : ViewModel() {

    // 이미지, Count 횟 수 관련 Live Data 설정
    private val _countLiveData = MutableLiveData<String>()
    val countLiveData : LiveData<String> by lazy { _countLiveData }

    private val _imageLiveData= MutableLiveData<Image>()
    val imageLiveData : LiveData<Image> by lazy { _imageLiveData }

    // Rx 사용을 위한 변수 선언
    private var disposable : CompositeDisposable? = CompositeDisposable()
    private var count=0

    fun loadRandomImage() {
        // disposable 에 의해 자동으로 관찰
        disposable?.add(imageRepository.getRandomImage()
            .doOnSuccess {
                count++
            }
            .subscribe { item->
                _imageLiveData.value=item
                _countLiveData.value="불러온 이미지 : $count"
            })
    }

    override fun onCleared() {
        super.onCleared()
        // 생명주기 파악으로 메모리 누수 방지
        disposable=null
    }


    // Activity 에서 ViewModel 을 호출할 때 파라미터가 필요하기 때문에 선언
    class MvvmViewModelFactory(private val imageRepository: ImageRepository) :
            ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MvvmViewModel(imageRepository) as T
        }
            }

}