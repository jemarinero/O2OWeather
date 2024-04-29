package com.o2o.domain.usecase

import com.o2o.domain.common.BaseUseCase
import com.o2o.domain.common.utilities.error.Failure
import com.o2o.domain.common.utilities.result.Result
import com.o2o.domain.dispatcher.DispatcherProvider
import com.o2o.domain.model.Weather
import com.o2o.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWeatherByCityUseCase
@Inject
constructor(
    private val weatherRepository: WeatherRepository,
    dispatcherProvider: DispatcherProvider
): BaseUseCase<String, Result<Weather, Failure>>(dispatcherProvider){

    override fun configure(param: String): Flow<Result<Weather, Failure>> = flow{
        emit(weatherRepository.getWeatherByCity(param))
    }
}