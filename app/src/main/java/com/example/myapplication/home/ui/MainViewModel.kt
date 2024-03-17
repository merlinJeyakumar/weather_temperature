package com.example.myapplication.home.ui

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.myapplication.home.data.rest.weather.RetrieveWeatherUseCase
import com.example.myapplication.home.support.ErrorApiResult
import com.example.myapplication.home.support.SuccessApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val application: Application,
    private val retrieveWeatherUseCase: RetrieveWeatherUseCase
) : ViewModel() {

    fun retrieveWeather() = liveData {
        retrieveWeatherUseCase.invoke(Unit).collectLatest {
            when (it) {
                is SuccessApiResult -> emit(
                    if (it.data.main.temp > 25) {
                        "temperature is too hot"
                    } else {
                        "temperature is not too hot"
                    } + " ${it.data.main.temp}°"
                )

                is ErrorApiResult -> {
                    application.toast("Failed to retrieve: ${it.message}")
                }

                else -> {}
            }
        }
    }
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}