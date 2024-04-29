package com.o2o.data.model.response

import com.squareup.moshi.Json

data class ForecastDTO(
    @Json(name = "day") val day: String,
    @Json(name = "temperature") val temperature: String,
    @Json(name = "wind") val wind: String
)
