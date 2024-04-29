package com.o2o.ui.features.weather.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.o2o.domain.model.Forecast
import com.o2o.domain.model.Weather
import com.o2o.ui.common.TextStyles
import java.util.Date

@Composable
fun WeatherComponent(weather: Weather, city: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(city.uppercase(), style = TextStyles.TitleH2())
        Spacer(modifier = Modifier.height(16.dp))
        Text(weather.temperature, style = TextStyles.TitleH1())
        Spacer(modifier = Modifier.height(8.dp))
        Text(weather.description)
        Spacer(modifier = Modifier.height(16.dp))
        ForecastList(weather.forecast)
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherComponentPreview() {
    WeatherComponent(
        Weather("16ºC", "16km/hh", "Soleado", listOf(
            Forecast(Date(), "16ºC", "15km/h"),
            Forecast(Date(), "16ºC", "15km/h"),
            Forecast(Date(), "16ºC", "15km/h"),
        )
    ), "Madrid")
}