package com.example.myapplication.home.domain.api

import com.example.myapplication.home.domain.weather.WeatherResponse
import com.example.myapplication.home.support.ResultCall
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    /*weather?lat=0&lon=0&appid=c6e381d8c7ff98f0fee43775817cf6ad&units=metric*/
    @GET("weather")
    suspend fun getWeather(
        @Query("lat") lat: Long,
        @Query("lon") lon: Long,
        @Query("appid") appid: String,
        @Query("units") units: String
    ): Result<WeatherResponse>
}