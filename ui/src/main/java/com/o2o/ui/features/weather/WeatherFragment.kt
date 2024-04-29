package com.o2o.ui.features.weather

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.o2o.ui.features.weather.WeatherViewModel.State
import com.o2o.ui.features.weather.components.ErrorComponent
import com.o2o.ui.features.weather.components.HeaderComponent
import com.o2o.ui.features.weather.components.LoadingComponent
import com.o2o.ui.features.weather.components.WeatherComponent

@Composable
fun WeatherFragment(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel = hiltViewModel()
) {

    val states = viewModel.state.collectAsStateWithLifecycle()
    var city by remember { mutableStateOf("") }
    var enabled by remember { mutableStateOf(true) }
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            HeaderComponent(enabled) { text, enable ->
                viewModel.getWeatherByCity(text)
                city = text
                enabled = !enable
            }
            with(states.value) {
                when(this) {
                    is State.OnLoading -> if(this.show) LoadingComponent()
                    is State.OnError -> ErrorComponent(errorType = this.error)
                    is State.OnSuccess -> WeatherComponent(this.data, city)
                }
            }
        }
    }
}