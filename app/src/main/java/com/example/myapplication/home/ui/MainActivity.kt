package com.example.myapplication.home.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            ActivityMainBinding.inflate(layoutInflater).also {
                initView(it)
                initData(it)
                initListener(it)
                initPreview()
            }.root
        )
    }

    private fun initData(binding: ActivityMainBinding) = with(viewModel) {
        retrieveWeather().observe(this@MainActivity) {
            binding.weatherTextView.setText(it)
        }
    }

    private fun initView(it: ActivityMainBinding) {
        //todo
    }

    private fun initListener(it: ActivityMainBinding) {
        //todo
    }

    private fun initPreview() {
        //todo
    }
}