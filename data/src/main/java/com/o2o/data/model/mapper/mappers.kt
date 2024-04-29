package com.o2o.data.model.mapper

import com.o2o.data.exception.BadRequestException
import com.o2o.data.exception.ConnectivityException
import com.o2o.data.exception.ServerErrorException
import com.o2o.data.exception.ServiceNotFoundException
import com.o2o.data.exception.TimeoutException
import com.o2o.data.exception.UnauthorizedException
import com.o2o.data.model.response.ForecastDTO
import com.o2o.data.model.response.WeatherDTO
import com.o2o.domain.common.utilities.error.Failure
import com.o2o.domain.common.utilities.result.resultError
import com.o2o.domain.model.Forecast
import com.o2o.domain.model.Weather
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date

fun WeatherDTO.toDomain() =  Weather(temperature, wind, description, forecast.map { it.toDomain() })

fun ForecastDTO.toDomain() = Forecast(Date().addDays(day.toInt()), temperature, wind)

fun Exception.toFailure() = when(this) {
    is ConnectivityException -> resultError(Failure.ConnectivityError)
    is BadRequestException -> resultError(Failure.ServiceError(message))
    is ServerErrorException,
    is ServiceNotFoundException -> resultError(Failure.ServerError)
    is TimeoutException -> resultError(Failure.TimeoutError)
    is UnauthorizedException -> resultError(Failure.UnauthorizedError)
    else -> resultError(Failure.GenericError(message))
}

fun Date.addDays(days: Int): Date {
    val c: Calendar = Calendar.getInstance()
    c.setTime(this)
    c.add(Calendar.DATE, days)
    return c.time
}