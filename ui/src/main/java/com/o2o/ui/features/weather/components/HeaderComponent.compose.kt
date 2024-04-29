package com.o2o.ui.features.weather.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.o2o.ui.R
import com.o2o.ui.common.TextStyles

@Composable
fun HeaderComponent(enable: Boolean, onSearchClicked: (String, Boolean) -> Unit) {
    var text by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painterResource(id = R.drawable.o2o_logo), contentDescription = stringResource(R.string.text_o2o_logo), modifier = Modifier.width(128.dp), contentScale = ContentScale.FillWidth )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.text_weather_app),
            style = TextStyles.TitleH2()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                modifier = Modifier.weight(1f),
                value = text,
                enabled = enable,
                onValueChange = {
                text = it
            }, label = {
                Text(stringResource(R.string.label_search_city))
            },
                colors = TextStyles.AppTextInputColors,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                enabled = enable,
                onClick = {
                    onSearchClicked.invoke(text, false)
                    text = ""
                    focusManager.clearFocus()
                }) {
                Text(text = stringResource(R.string.btn_search))
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HeaderComponentPreview() {
    HeaderComponent(true) { _,_ -> }
}