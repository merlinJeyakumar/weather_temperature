package com.example.myapplication.home.domain.weather


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class WeatherResponse(
    @SerializedName("main")
    val main: Main
) {
    @Keep
    data class Main(
        @SerializedName("temp")
        val temp: Double
    )
}