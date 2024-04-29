package com.o2o.domain.repository

import com.o2o.domain.common.utilities.error.Failure
import com.o2o.domain.common.utilities.result.Result
import com.o2o.domain.model.Weather

interface WeatherRepository {

    suspend fun getWeatherByCity(city: String): Result<Weather, Failure>
}