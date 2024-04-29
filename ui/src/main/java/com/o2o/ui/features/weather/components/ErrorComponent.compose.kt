package com.o2o.ui.features.weather.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.o2o.domain.common.utilities.error.Failure
import com.o2o.ui.R

@Composable
fun ErrorComponent(errorType: Failure?) {
    val description = when(errorType) {
        is Failure.ServiceError -> stringResource(R.string.msg_service_error)
        Failure.ConnectivityError -> stringResource(R.string.msg_connection_error)
        Failure.NoDataError -> stringResource(R.string.msg_no_data_error)
        Failure.ServerError -> stringResource(R.string.msg_server_error)
        Failure.TimeoutError -> stringResource(R.string.msg_timeout_error)
        Failure.UnauthorizedError -> stringResource(R.string.msg_unauthorized_error)
        else -> stringResource(R.string.msg_generic_error)
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image(painterResource(id = R.drawable.ic_error), contentDescription = description, modifier = Modifier.width(128.dp), contentScale = ContentScale.FillWidth)
        Spacer(modifier = Modifier.height(16.dp))
        Text(description)
    }
}


@Preview(showBackground = true)
@Composable
fun ErrorComponentNoDataErrorPreview() {
    ErrorComponent(Failure.NoDataError)
}

@Preview(showBackground = true)
@Composable
fun ErrorComponentServiceErrorPreview() {
    ErrorComponent(Failure.ServiceError(null))
}

@Preview(showBackground = true)
@Composable
fun ErrorComponentConnectivityErrorPreview() {
    ErrorComponent(Failure.ConnectivityError)
}

@Preview(showBackground = true)
@Composable
fun ErrorComponentServerErrorPreview() {
    ErrorComponent(Failure.ServerError)
}

@Preview(showBackground = true)
@Composable
fun ErrorComponentTimeoutErrorPreview() {
    ErrorComponent(Failure.TimeoutError)
}

@Preview(showBackground = true)
@Composable
fun ErrorComponentUnauthorizedErrorPreview() {
    ErrorComponent(Failure.UnauthorizedError)
}

@Preview(showBackground = true)
@Composable
fun ErrorComponentGenericErrorPreview() {
    ErrorComponent(Failure.GenericError(null))
}