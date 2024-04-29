package com.o2o.domain.model

import java.util.Date

data class Forecast (
    val day: Date,
    val temperature: String,
    val wind: String
)