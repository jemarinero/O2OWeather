package com.o2o.ui.features.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.o2o.domain.common.utilities.error.Failure
import com.o2o.domain.common.utilities.result.onFailure
import com.o2o.domain.common.utilities.result.onSuccess
import com.o2o.domain.model.Weather
import com.o2o.domain.usecase.GetWeatherByCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel
@Inject
constructor(
    private val getWeatherByCityUseCase: GetWeatherByCityUseCase
): ViewModel() {

    private val _state = MutableStateFlow<State>(State.OnLoading(false))
    val state = _state.asStateFlow()

    fun getWeatherByCity(city: String?) {
        city?.let { ct ->
            getWeatherByCityUseCase.execute(ct)
                .catch { _state.value = State.OnError(Failure.GenericError(it.message)) }
                .onStart { _state.value = State.OnLoading(true) }
                .onEach { res ->
                    res.onSuccess { weather ->
                        _state.value = State.OnSuccess(weather)
                    }.onFailure { failure ->
                        _state.value = State.OnError(failure)
                    }
                }
                .launchIn(viewModelScope)
        } ?: run {
            _state.value = State.OnError(Failure.NoDataError)
        }
    }

    sealed class State {
        data class OnLoading(val show: Boolean) : State()
        data class OnSuccess(val data: Weather) : State()
        data class OnError(val error: Failure) : State()
    }
}