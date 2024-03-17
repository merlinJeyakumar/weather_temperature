package com.example.myapplication.home.di

import android.content.Context
import com.example.myapplication.BuildConfig
import com.example.myapplication.home.data.rest.weather.WeatherRepositoryImpl
import com.example.myapplication.home.domain.api.ApiService
import com.example.myapplication.home.domain.repository.IWeatherRepository
import com.example.myapplication.home.support.ResultCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideHttpClient(
        @ApplicationContext appContext: Context
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .readTimeout(180, TimeUnit.SECONDS)
            .connectTimeout(180, TimeUnit.SECONDS).build()

    //Retrofit
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(ResultCallAdapterFactory())
        .baseUrl(BuildConfig.BaseUlr)
        .build()

    @Provides
    @Singleton
    fun provideWeatherApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(apiService: ApiService): IWeatherRepository {
        return WeatherRepositoryImpl(apiService)
    }
}