package com.o2o.data.repository

import com.o2o.data.datasource.WeatherRemoteDataSource
import com.o2o.data.model.mapper.toDomain
import com.o2o.data.model.mapper.toFailure
import com.o2o.domain.common.utilities.error.Failure
import com.o2o.domain.common.utilities.result.Result
import com.o2o.domain.common.utilities.result.resultError
import com.o2o.domain.common.utilities.result.resultSuccess
import com.o2o.domain.model.Weather
import com.o2o.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl
@Inject
constructor(
   private val dataSource: WeatherRemoteDataSource
): WeatherRepository {

    override suspend fun getWeatherByCity(city: String): Result<Weather, Failure> {
        return try {
            dataSource.getWeatherByCity(city)?.let {
                resultSuccess(it.toDomain())
            }?: resultError(Failure.NoDataError)
        } catch (ex: Exception) {
           ex.toFailure()
        }
    }
}