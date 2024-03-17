package com.example.myapplication.home.data.rest.weather

import com.example.myapplication.home.domain.api.ApiService
import com.example.myapplication.home.domain.repository.IWeatherRepository
import com.example.myapplication.home.domain.weather.WeatherResponse
import com.example.myapplication.home.support.ErrorApiResult
import com.example.myapplication.home.support.FlowUseCase
import com.example.myapplication.home.support.NetworkResult
import com.example.myapplication.home.support.SuccessApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class RetrieveWeatherUseCase @Inject constructor(private val weatherRepository: IWeatherRepository) :
    FlowUseCase<Unit, WeatherResponse>() {
    override fun performAction(parameters: Unit): Flow<NetworkResult<WeatherResponse>> {
        return channelFlow {
            val result = try {
                SuccessApiResult(
                    weatherRepository.getWeather(
                        0,
                        0,
                        "c6e381d8c7ff98f0fee43775817cf6ad",
                        "metric"
                    ).getOrThrow()
                )
            } catch (e: Exception) {
                e.printStackTrace()
                ErrorApiResult(e.message ?: "undefine error", e)
            }
            this.trySend(result)
        }
    }
}