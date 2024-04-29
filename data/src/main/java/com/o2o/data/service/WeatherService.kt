package com.o2o.data.service

import com.o2o.data.model.response.WeatherDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {

    @GET("weather/{city}")
    suspend fun getWeatherByCity(@Path("city") city: String): Response<WeatherDTO>
}