package com.example.myapplication.home.data.rest.weather

import com.example.myapplication.home.domain.api.ApiService
import com.example.myapplication.home.domain.repository.IWeatherRepository
import com.example.myapplication.home.domain.weather.WeatherResponse
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    IWeatherRepository {
    override suspend fun getWeather(
        lat: Long,
        lon: Long,
        appid: String,
        units: String
    ): Result<WeatherResponse> {
        return apiService.getWeather(lat, lon, appid, units)
    }
}