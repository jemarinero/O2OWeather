package com.o2o.ui.features.weather.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.o2o.domain.model.Forecast
import com.o2o.ui.R
import java.text.DateFormat
import java.util.Date


@Composable
fun ForecastList(list: List<Forecast>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(list) {
            ForecastCard(forecast = it)
        }
    }
}

@Composable
fun ForecastCard(forecast: Forecast) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(DateFormat.getDateInstance(DateFormat.LONG).format(forecast.day) )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center
                )  {
                    Image(painterResource(id = R.drawable.ic_thermometer), contentDescription = forecast.temperature)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(forecast.temperature)
                }
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(painterResource(id = R.drawable.ic_wind), contentDescription = forecast.wind)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(forecast.wind)
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Preview(showBackground = true)
@Composable
fun ForecastCardPreview() {
    ForecastCard(Forecast(Date(), "16ºC", "15km/h"))
}

@Preview(showBackground = true)
@Composable
fun ForecastListPreview() {
    ForecastList(
        listOf(
            Forecast(Date(), "16ºC", "15km/h"),
            Forecast(Date(), "16ºC", "15km/h"),
            Forecast(Date(), "16ºC", "15km/h"),
        )
    )
}