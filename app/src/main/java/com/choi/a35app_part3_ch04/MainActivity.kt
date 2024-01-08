package com.choi.a35app_part3_ch04

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.choi.a35app_part3_ch04.databinding.ActivityMainBinding
import com.choi.a35app_part3_ch04.mvc.MvcActivity
import com.choi.a35app_part3_ch04.mvi.MviActivity
import com.choi.a35app_part3_ch04.mvp.MvpActivity
import com.choi.a35app_part3_ch04.mvvm.MvvmActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.view=this
        }
    }

    fun openMvc() {
        startActivity(Intent(this,MvcActivity::class.java))
    }

    fun openMvp() {
        startActivity(Intent(this, MvpActivity::class.java))
    }

    fun openMvvm() {
        startActivity(Intent(this, MvvmActivity::class.java))
    }

    fun openMvi() {
        startActivity(Intent(this, MviActivity::class.java))
    }

}