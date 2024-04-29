package com.o2o.data.datasource

import com.o2o.data.common.mapToResult
import com.o2o.data.exception.BadRequestException
import com.o2o.data.exception.ConnectivityException
import com.o2o.data.exception.GenericException
import com.o2o.data.exception.ServerErrorException
import com.o2o.data.exception.ServiceNotFoundException
import com.o2o.data.exception.TimeoutException
import com.o2o.data.exception.UnauthorizedException
import com.o2o.data.model.response.WeatherDTO
import com.o2o.data.service.WeatherService
import javax.inject.Inject

class WeatherRemoteDataSource
@Inject
constructor(private val service: WeatherService){

    @Throws(
        BadRequestException::class,
        UnauthorizedException::class,
        ServiceNotFoundException::class,
        ServerErrorException::class,
        TimeoutException::class,
        ConnectivityException::class,
        GenericException::class
    )
    suspend fun getWeatherByCity(city: String): WeatherDTO? {
        return service.getWeatherByCity(city).mapToResult()
    }
}