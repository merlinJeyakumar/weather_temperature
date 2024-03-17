package com.example.myapplication.home.domain.repository

import com.example.myapplication.home.domain.weather.WeatherResponse

interface IWeatherRepository {

    suspend fun getWeather(
        lat: Long,
        lon: Long,
        appid: String,
        units: String
    ): Result<WeatherResponse>
}