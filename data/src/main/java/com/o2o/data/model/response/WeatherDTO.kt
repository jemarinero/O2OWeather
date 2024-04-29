package com.o2o.data.model.response

import com.squareup.moshi.Json


data class WeatherDTO(
    @Json(name = "temperature") val temperature: String,
    @Json(name = "wind") val wind: String,
    @Json(name = "description") val description: String,
    @Json(name = "forecast") val forecast: List<ForecastDTO>
)
