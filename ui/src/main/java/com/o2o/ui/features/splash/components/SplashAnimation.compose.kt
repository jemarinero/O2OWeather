package com.o2o.ui.features.splash.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.o2o.ui.R

@Composable
fun SplashAnimation(onAnimationFinished: () -> Unit) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_lottie))
    val progress by animateLottieCompositionAsState(composition)

    LottieAnimation(
        composition = composition,
        progress = { progress }
    )
    if(progress == 1.0f) {
        LaunchedEffect(Unit) {
            onAnimationFinished.invoke()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashFragmentPreview() {
    SplashAnimation{}
}