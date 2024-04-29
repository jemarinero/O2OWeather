package com.o2o.ui.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object TextStyles {

    @Composable
    @ReadOnlyComposable
    fun TitleH1() = TextStyle(
        fontSize = 64.sp,
        lineHeight = 68.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White
    )

    @Composable
    @ReadOnlyComposable
    fun TitleH2() = TextStyle(
            fontSize = 28.sp,
            lineHeight = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

    val AppTextInputColors: TextFieldColors
        @Composable
        get() = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            cursorColor = MaterialTheme.colorScheme.onBackground,
            focusedLabelColor = MaterialTheme.colorScheme.onBackground,
            unfocusedLabelColor = MaterialTheme.colorScheme.onBackground,
            focusedLeadingIconColor = MaterialTheme.colorScheme.onBackground,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.onBackground,
            focusedTrailingIconColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTrailingIconColor = MaterialTheme.colorScheme.onBackground,
            errorTextColor = MaterialTheme.colorScheme.onBackground,
            errorLeadingIconColor = MaterialTheme.colorScheme.onBackground,
            errorTrailingIconColor = MaterialTheme.colorScheme.onBackground,
            errorLabelColor = MaterialTheme.colorScheme.onBackground,
            errorSupportingTextColor = MaterialTheme.colorScheme.error
        )
}
